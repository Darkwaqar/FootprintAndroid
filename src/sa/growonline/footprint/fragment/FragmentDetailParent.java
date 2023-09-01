package sa.growonline.footprint.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.ActivitySingleProduct;
import sa.growonline.footprint.ActivitySplash;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterAttributeDetail;
import sa.growonline.footprint.adapter.AdapterDetailPagerFromUrl;
import sa.growonline.footprint.asynctask.AsyncTaskDeleteProductFromCart;
import sa.growonline.footprint.asynctask.AsynctaskAddToCart;
import sa.growonline.footprint.asynctask.AsynctaskGetCartDetails;
import sa.growonline.footprint.asynctask.AsynctaskGetProductDetails;
import sa.growonline.footprint.bean.BeanPostAttributes;
import sa.growonline.footprint.bean.BeanPostAttributes.BeanProductAttributeValues;
import sa.growonline.footprint.bean.BeanProductDetail;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.bean.BeanServerImage;
import sa.growonline.footprint.network.NetworkUtils;
import sa.growonline.footprint.utils.SystemIntents;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.growonline.footprint.view.VerticalViewPager;
import sa.growonline.footprint.view.VerticalViewPager.SwipeDetectWhileSwipeDisable;

public class FragmentDetailParent extends Fragment implements OnClickListener, OnPageChangeListener {
    private BeanProductDetail mDetailProductBean;

    private VerticalViewPager mChildViewPager;

    private TextView mShareLinearLayout, mAddToCartTextView;

    private RelativeLayout mAddToCartLayout;
    private String mProductId;
    private LinearLayout mOverflowLinearLayout;

    private BeanProductAttributeValues mSelectedSizeAttribute, mSelectedColorAttribute;
    private boolean mHaveSizeAttribute, mHaveColorAttribute;
    private LinearLayout mBottomRelatedLinearLayout, mBottomCompleteRelatedProducts;

    private RelativeLayout mBottomBarLinearLayout;
    private RelativeLayout mBuyItNowPopup;
    private TextView mContinueShppingTextView, mProceedToCheckout, mBuyPriceTextView;
    private TextView mInfotextView;

    private String mCartNumber;

    private TextView mPriceTextView;
    private View mDisableView;
    private View mCurrentVisibleLayout;
    private LinearLayout mPageIndicatorLayout;
    private ImageView mBottomColorImageView;
    private TextView mBottomSizeTextView;
    private View mColorView;
    private RelativeLayout mBottomOverflowImageView;
    private View mWishListButton;
    private ArrayList<BeanProductAttributeValues> mColorAttributes;
    private ArrayList<BeanProductAttributeValues> mSizeAttributes;
    private GridView mInflaterSizeGridView, mInflaterColorGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_parent, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitUI(view);
        setTypeFace();
    }


    private void setTypeFace() {
        ZuniUtils.applyAppFont(mAddToCartTextView);
        ZuniUtils.applyAppFont(mShareLinearLayout);
    }

    private void InitUI(View view) {
        mDisableView = view.findViewById(R.id.detail_disable_view);
        mChildViewPager = (VerticalViewPager) view.findViewById(R.id.detail_child_viewpager);
        mBottomColorImageView = (ImageView) view.findViewById(R.id.detail_parent_bottom_info_imgview);
        mBottomSizeTextView = (TextView) view.findViewById(R.id.detail_parent_bottom_fav);
        mAddToCartTextView = (TextView) view.findViewById(R.id.detail_parent_bottom_addto_cart_imgview);
        mAddToCartLayout = (RelativeLayout) view.findViewById(R.id.detail_parent_bottom_addto_cart_layout);
        mBuyItNowPopup = (RelativeLayout) view.findViewById(R.id.detail_parent_buynow_popup);
        mContinueShppingTextView = (TextView) view.findViewById(R.id.detail_parent_buynow_popup_continue_shopping);
        mProceedToCheckout = (TextView) view.findViewById(R.id.detail_parent_buynow_popup_proceed_tocheckout);
        //!--- Dialog for overflow
        mBottomBarLinearLayout = (RelativeLayout) view.findViewById(R.id.detail_parent_bottom_layout);
        mBottomRelatedLinearLayout = (LinearLayout) view.findViewById(R.id.detail_parent_bottom_related_layout);
        mBottomCompleteRelatedProducts = (LinearLayout) view.findViewById(R.id.detail_parent_bottom_related_complete_layout);
        mOverflowLinearLayout = (LinearLayout) view.findViewById(R.id.detail_parent_dialog_overflow);
        mShareLinearLayout = (TextView) view.findViewById(R.id.detail_parent_dialog_overflow_share);
        mBuyPriceTextView = (TextView) view.findViewById(R.id.detail_parent_buynow_popup_total_items_lbl1);
        mColorView = view.findViewById(R.id.detail_parent_bottom_info_layout);

        mInfotextView = (TextView) view.findViewById(R.id.detail_parent_buynow_desc);
        mPriceTextView = (TextView) view.findViewById(R.id.detail_parent_price_txtview);
        mPageIndicatorLayout = (LinearLayout) view.findViewById(R.id.detail_page_indicator_layout);

        mInflaterColorGridView = (GridView) view.findViewById(R.id.detail_parent_dialog_color);
        mInflaterSizeGridView = (GridView) view.findViewById(R.id.detail_parent_dialog_size);
        mWishListButton = view.findViewById(R.id.detail_parent_dialog_overflow_wishlist);
        DisableClickListener a = new DisableClickListener();
        mPriceTextView.setOnTouchListener(a);
        view.findViewById(R.id.bottom_layout).setOnTouchListener(a);
        mBottomOverflowImageView = (RelativeLayout) view.findViewById(R.id.detail_parent_bottom_overflow_new_layout);

        mChildViewPager.mDetect = new SwipeDetectWhileSwipeDisable() {

            @Override
            public void onSwipeDetect() {
                createIndicators(mDetailProductBean.getmProductImageModel().size() - 1);
                showOrCloseRelatedProductsDropdown(mBottomBarLinearLayout, mBottomCompleteRelatedProducts);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChildViewPager.setSwipeEnabled(true);
                        if (getActivity() instanceof ActivityProductDetail)
                            ((ActivityProductDetail) getActivity()).setViewPagerSwipe(true);
                    }
                }, 500);
            }
        };

        mWishListButton.setOnClickListener(this);
        mDisableView.setOnClickListener(this);
        mShareLinearLayout.setOnClickListener(this);
        mAddToCartLayout.setOnClickListener(this);
        mColorView.setOnClickListener(this);
        mContinueShppingTextView.setOnClickListener(this);
        mBottomSizeTextView.setOnClickListener(this);
        mBottomOverflowImageView.setOnClickListener(this);
        mProceedToCheckout.setOnClickListener(this);
    }

    private class DisableClickListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    private void manageBottomDialogVisibilities(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            mCurrentVisibleLayout = null;
            mDisableView.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        } else {
            mOverflowLinearLayout.setVisibility(View.GONE);
            mDisableView.setVisibility(View.VISIBLE);
            mBuyItNowPopup.setVisibility(View.GONE);

            mCurrentVisibleLayout = view;
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (mProceedToCheckout == v) {
            startActivity(new Intent(getActivity(), ActivityCartDetails.class));
            return;
        }
        if (mWishListButton == v) {
            if (!mDetailProductBean.getIsWishListItem()) {
                new AsynctaskAddToCart(getActivity(), "{\"productId\":\"" + mDetailProductBean.getmProductId() + "\"}", "2", "", FragmentDetailParent.this).execute();
            } else {
                //!-- Cart Item Id to delete the product from wishlist
                new AsyncTaskDeleteProductFromCart(getActivity(), mDetailProductBean.getmProductId(), mDetailProductBean.getWishListItemId(), FragmentDetailParent.this).execute();
            }
            manageBottomDialogVisibilities(mOverflowLinearLayout);
            return;
        }
        if (v == mBottomSizeTextView) {
            if (mInflaterColorGridView.getVisibility() == View.VISIBLE)
                mInflaterColorGridView.setVisibility(View.GONE);
            if (mHaveSizeAttribute)
                manageBottomDialogVisibilities(mInflaterSizeGridView);
            else
                ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), getString(R.string.NoSizeAttribute), null, null);
        }
        if (mDisableView == v) {
            if (mCurrentVisibleLayout != null)
                manageBottomDialogVisibilities(mCurrentVisibleLayout);
            return;
        }

        if (mContinueShppingTextView == v) {
            getActivity().finish();
            return;
        }

        if (v == mBottomOverflowImageView) {
            manageBottomDialogVisibilities(mOverflowLinearLayout);
            return;
        }

        if (v == mAddToCartLayout) {
            if (validateInfo()) {
                int availableQuantity = 0;
                try {
                    availableQuantity = Integer.parseInt(mDetailProductBean.getAddToCart().getEnteredQuantity());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (availableQuantity > 0) {
                    JSONObject mJsonRequest = new JSONObject();

                    try {

                        String defaultPrefix = "product_attribute_";
                        JSONObject attributeArray = new JSONObject();

                        if (mHaveColorAttribute)
                            attributeArray.put(defaultPrefix + mSelectedColorAttributeLbl.getId(), mSelectedColorAttribute.getId());
                        if (mHaveSizeAttribute)
                            attributeArray.put(defaultPrefix + mSelectedSizeAttributeLbl.getId(), mSelectedSizeAttribute.getId());
                        mJsonRequest.put("sQuantity", "1");
                        mJsonRequest.put("productId", mProductId);
                        mJsonRequest.put("sPrice", mDetailProductBean.getProductPrice().getPrice());
                        mJsonRequest.put("sUpdatedItemId", 1);
                        attributeArray.put("addtocart_" + mProductId + ".EnteredQuantity", "1");
                        attributeArray.put("addtocart_" + mProductId + ".CustomerEnteredPrice", mDetailProductBean.getProductPrice().getPrice());
                        attributeArray.put("addtocart_" + mProductId + ".UpdatedShoppingCartItemId", 1);
                        mJsonRequest.put("attributesJson", attributeArray.toString());


                        try {
                            mJsonRequest.put("sQuantity", "1");
                            mJsonRequest.put("productId", mDetailProductBean.getmProductId());
                            mJsonRequest.put("sPrice", mDetailProductBean.getProductPrice().getPrice());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new AsynctaskAddToCart(getActivity(), mJsonRequest.toString(), "1", "1", FragmentDetailParent.this).execute();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else
                    ZuniUtils.showMsgDialog(getActivity(), "", getString(R.string.OutofStock), null, null);
            }
            return;
        }
        if (v == mColorView) {
            if (mInflaterSizeGridView.getVisibility() == View.VISIBLE)
                mInflaterSizeGridView.setVisibility(View.GONE);
            if (mHaveColorAttribute)
                manageBottomDialogVisibilities(mInflaterColorGridView);
            else
                ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), getString(R.string.NoSizeAttribute), null, null);
            return;
        }

        if (v == mShareLinearLayout) {
            String txt = ZuniConstants.SHARING_TEXT + ZuniConstants.SHARE_URL + mDetailProductBean.getSharingName();
            SystemIntents.share(getActivity(), txt, null, NetworkUtils.PLAIN_TEXT);
        }
    }

    private boolean validateInfo() {


        if (mHaveColorAttribute && mSelectedColorAttributeLbl == null) {
            Toast.makeText(getActivity(), getString(R.string.SelectColor), Toast.LENGTH_SHORT).show();
            if (mInflaterSizeGridView.getVisibility() == View.VISIBLE)
                mInflaterSizeGridView.setVisibility(View.GONE);
            manageBottomDialogVisibilities(mInflaterColorGridView);
            return false;
        }

        if (mHaveSizeAttribute && mSelectedSizeAttributeLbl == null) {
            Toast.makeText(getActivity(), getString(R.string.SelectSize), Toast.LENGTH_SHORT).show();
            if (mInflaterColorGridView.getVisibility() == View.VISIBLE)
                mInflaterColorGridView.setVisibility(View.GONE);
            manageBottomDialogVisibilities(mInflaterSizeGridView);

            return false;
        }

        if (mDetailProductBean != null && mDetailProductBean.getAddToCart().getDisableBuyButton().equalsIgnoreCase("true")) {
            Toast.makeText(getActivity(), getString(R.string.CartDisabled), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        callAsync(isVisibleToUser);
    }

    private void updateImages() {
        if (mDetailProductBean.getmProductImageModel() != null && mDetailProductBean.getmProductImageModel().size() != 0)
            if (mChildViewPager.getAdapter() == null) {
                createIndicators(0);
                AdapterDetailPagerFromUrl adapter = new AdapterDetailPagerFromUrl(getActivity(), mDetailProductBean.getmProductImageModel(), FragmentDetailParent.this);
                mChildViewPager.setAdapter(adapter);
            }
    }

    private void createIndicators(int selected) {
        mPageIndicatorLayout.removeAllViews();
        ArrayList<BeanServerImage> list = mDetailProductBean.getmProductImageModel();
        for (int i = 0; i < list.size() + 1; i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setPadding(0, 0, 0, 10);
            if (i == selected) {
                if (selected != list.size())
                    imageView.setImageResource(R.drawable.selected_white_circle);
                else
                    imageView.setImageResource(R.drawable.selected_white_square);
            } else if (i == list.size())
                imageView.setImageResource(R.drawable.white_square);
            else
                imageView.setImageResource(R.drawable.white_circle);
            mPageIndicatorLayout.addView(imageView);
        }
    }


    private void updateAttrs(ArrayList<BeanPostAttributes> attributes) {
        BeanPostAttributes colorAttr = null;
        BeanPostAttributes sizeAttr = null;

        mColorAttributes = null;
        mSizeAttributes = null;

        mHaveColorAttribute = false;
        mHaveSizeAttribute = false;

        if (attributes != null) {
            for (int i = 0; i < attributes.size(); i++) {
                if (attributes.get(i).getmName() != null && attributes.get(i).getmName().toLowerCase().equalsIgnoreCase(ZuniConstants.ATTRIBUTE_COLOR_TYPE)) {
                    colorAttr = attributes.get(i);
                    mColorAttributes = attributes.get(i).getAttributeValues();
                    mHaveColorAttribute = true;
                } else if (attributes.get(i).getmName() != null && attributes.get(i).getmName().toLowerCase().equalsIgnoreCase(ZuniConstants.ATTRIBUTE_SIZE_TYPE)) {
                    sizeAttr = attributes.get(i);
                    mSizeAttributes = attributes.get(i).getAttributeValues();
                    mHaveSizeAttribute = true;
                }
            }

            checkToEnableAddToCart();
            if (mColorAttributes != null) {
                AdapterAttributeDetail adapter = new AdapterAttributeDetail(getActivity(), mColorAttributes, colorAttr, FragmentDetailParent.this, AdapterAttributeDetail.AttributeType.COLOR);
                mInflaterColorGridView.setAdapter(adapter);
            }

            if (mSizeAttributes != null) {
                AdapterAttributeDetail adapter = new AdapterAttributeDetail(getActivity(), mSizeAttributes, sizeAttr, FragmentDetailParent.this, AdapterAttributeDetail.AttributeType.SIZE);
                mInflaterSizeGridView.setAdapter(adapter);
            }
        }
    }

    public void updateBean(String mCartNumber, final BeanProductDetail mDetailProductBean2) {
        if (mDetailProductBean2 != null) {
            this.mDetailProductBean = mDetailProductBean2;
            this.mCartNumber = mCartNumber;
            //!-- Update activity title
            if (getActivity() instanceof ActivityProductDetail)
                ((ActivityProductDetail) getActivity()).getmTitleTextView(mDetailProductBean2.getmProductName());

            //!-- Updating imgs
            updateImages();
            mInfotextView.setText(Html.fromHtml(mDetailProductBean2.getFullDescription().replace("\n", "<br>")));
            mBuyPriceTextView.setText(String.format("Total: %s", mDetailProductBean2.getProductPrice().getPrice() + ".00"));
            updateRelatedProducts();
            mChildViewPager.setOnPageChangeListener(this);
            updatePrice(mDetailProductBean2.getProductPrice().getPriceValue());

            if (mDetailProductBean.getIsSoldOut().equalsIgnoreCase("true")) {
                enableCart(false);
                mAddToCartTextView.setText(R.string.SoldOut);//asd
                mAddToCartLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            } else {
                enableCart(true);
                mAddToCartTextView.setText(R.string.BuyNow);
                mAddToCartLayout.setBackgroundColor(getResources().getColor(R.color.app_theme_color));
            }
            updateAttrs(mDetailProductBean2.getProductAttributes());
        }
    }

    private void updateRelatedProducts() {
        final List<BeanProductForCategory> bean = mDetailProductBean.getmRelatedProducts();
        mBottomRelatedLinearLayout.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < bean.size(); i++) {
            View view = inflater.inflate(R.layout.fragment_detail_related_product, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.detail_related_product_imgview);
            view.setTag(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (Integer) view.getTag();
                    SharedPreferences.Editor editor = ZuniApplication.getmAppPrefEditor();
                    editor.putString("singleproductId", bean.get(tag).getmProductId());
                    editor.commit();
                    startActivity(new Intent(getActivity(), ActivitySingleProduct.class));
                }
            });
            ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse(bean.get(i).getImageModel().getThumbImageUrl()), imageView, null);
            mBottomRelatedLinearLayout.addView(view);
        }
    }

    private BeanPostAttributes mSelectedColorAttributeLbl;
    private BeanPostAttributes mSelectedSizeAttributeLbl;

    public void checkToEnableAddToCart() {
        if (mHaveColorAttribute) {
            if (mSelectedColorAttribute != null)
                enableCart(true);
            else
                enableCart(false);
        } else {
            enableCart(true);
        }

        if (mHaveSizeAttribute) {
            if (mSelectedSizeAttribute != null)
                enableCart(true);
            else
                enableCart(false);
        } else {
            enableCart(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void enableCart(boolean b) {
        if (b) {
            mAddToCartLayout.setBackgroundColor(getResources().getColor(R.color.app_theme_color));
            mAddToCartTextView.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            mAddToCartTextView.setTextColor(getResources().getColor(R.color.white));
            mAddToCartLayout.setBackgroundColor(getResources().getColor(R.color.app_theme_color));
        }
        mAddToCartLayout.setClickable(true);
        mAddToCartLayout.setEnabled(true);
    }

    private void updatePrice(String price) {
        ZuniUtils.LogEvent(price);
        price = addCommaToPrice(price);
        mPriceTextView.setText(Html.fromHtml("<small>PKR  </small><big>" + price + ".00</big>"));
    }

    private String addCommaToPrice(String price) {
        StringBuilder builder = new StringBuilder(price);
        try {
            if (price.contains("."))
                price = price.split("\\.")[0];

            if (price.length() == 4) {
                builder.insert(1, ",");
            } else if (price.length() == 5) {
                builder.insert(2, ",");
            } else if (price.length() == 6) {
                builder.insert(3, ",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();

    }

    public void setProductId(String getmProductId) {
        this.mProductId = getmProductId;
    }

    public void onAddToCartComplete() {
        new AsynctaskGetCartDetails(getActivity(), true).execute();
        mBuyItNowPopup.setVisibility(View.VISIBLE);
        hideAfterTime();


        if (mCurrentVisibleLayout != null)
            manageBottomDialogVisibilities(mCurrentVisibleLayout);

        mCartNumber = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CART_QUANTITY, "0");
    }

    public void hideAfterTime() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBuyItNowPopup.setVisibility(View.GONE);
            }
        }, 5000);

    }

    public void callAsync(final boolean isVisibleToUser) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isVisibleToUser && isResumed()) //!--- Check, If fragment is visible
                {
                    if (mDetailProductBean == null) {
                        if (mProductId == null)
                            mProductId = ZuniApplication.getmAppPreferences().getString(ZuniConstants.DETAILED_PRODUCT_ID, "0");
                        new AsynctaskGetProductDetails(FragmentDetailParent.this, mProductId, FragmentDetailParent.this).execute();
                    } else {
                        updateBean(mCartNumber, mDetailProductBean);
                    }
                }
            }
        }, 500);
    }

    public void onAddToWishListComplete(String mCartid) {
        mDetailProductBean.setIsWishListItem(true);
        mDetailProductBean.setWishListItemId(mCartid);
    }

    public void onDeleteFromWishListComplete() {
    }

    boolean lastPageChange = false;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int lastIdx = mChildViewPager.getAdapter().getCount() - 1;

        if (lastPageChange && position == lastIdx) {
            lastPageChange = false;
            createIndicators(mDetailProductBean.getmProductImageModel().size());
            showOrCloseRelatedProductsDropdown(mBottomCompleteRelatedProducts, mBottomBarLinearLayout);
        }
    }

    /**
     * @param view1 the view which have to open
     * @param view2 the view which have to be close
     */
    private void showOrCloseRelatedProductsDropdown(final View view1, final View view2) {
        Animation topToBottomAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.top_to_bottom);
        topToBottomAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view2.setVisibility(View.GONE);
                Animation bottomTopAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top);
                bottomTopAnimation.setAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                        view1.setVisibility(View.VISIBLE);
                    }
                });
                view1.startAnimation(bottomTopAnimation);
            }
        });
        mChildViewPager.setSwipeEnabled(false);

        if (getActivity() instanceof ActivityProductDetail)
            ((ActivityProductDetail) getActivity()).setViewPagerSwipe(false);

        view2.startAnimation(topToBottomAnimation);
    }

    public void onAttrbuteselected(BeanProductAttributeValues beanProductAttributeValues, AdapterAttributeDetail.AttributeType type, BeanPostAttributes attributes) {
        if (type == AdapterAttributeDetail.AttributeType.COLOR) {
            ArrayList<BeanServerImage> imageBeans = mDetailProductBean.getmProductImageModel();
            for (int i = 0; i < imageBeans.size(); i++) {
                try {
                    if (beanProductAttributeValues.getPictureModel() != null) {
                        if (beanProductAttributeValues.getPictureModel().getmFullSizeImageUrl().equalsIgnoreCase(imageBeans.get(i).getmFullSizeImageUrl())) {
                            mChildViewPager.setCurrentItem(i, true);
                            break;
                        }
                    } else {
                        mChildViewPager.setCurrentItem(0, true);
                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            //!-- Making effect on price
            int priceInteger = Integer.valueOf(mDetailProductBean.getProductPrice().getPriceValue());
            int increment = Integer.valueOf(beanProductAttributeValues.getPriceAdjustmentValue());

            int finalPrice = priceInteger + increment;
            updatePrice(String.valueOf(finalPrice));

            //!-- Updating color on Thumbnail
            if (beanProductAttributeValues.getColorSquaresRgb() != null)
                mBottomColorImageView.setBackgroundColor(Color.parseColor(beanProductAttributeValues.getColorSquaresRgb()));
            else if (beanProductAttributeValues.getName() != null) {
                try {
                    mBottomColorImageView.setBackgroundColor(Color.parseColor(beanProductAttributeValues.getName().toLowerCase()));
                } catch (Exception e) {
                    Log.w("FragmentDetailParent ", ":onAttributeSelected: Unknown color");
                }
            }
            manageBottomDialogVisibilities(mInflaterColorGridView);
            mSelectedColorAttribute = beanProductAttributeValues;
            mSelectedColorAttributeLbl = attributes;
        } else if (type == AdapterAttributeDetail.AttributeType.SIZE) {
            manageBottomDialogVisibilities(mInflaterSizeGridView);
            mSelectedSizeAttribute = beanProductAttributeValues;
            mSelectedSizeAttributeLbl = attributes;

            int increment = Integer.valueOf(mSelectedSizeAttribute.getPriceAdjustmentValue());
            try {
                mBottomSizeTextView.setText(String.format("%s", mSelectedSizeAttribute.getName()));
                if (increment == 0) {
                    //!-- Set default price
                    updatePrice(mDetailProductBean.getProductPrice().getPriceValue());
                } else {
                    //!-- Add/Remove in default price
                    updatePrice(String.valueOf(Integer.parseInt(mDetailProductBean.getProductPrice().getPriceValue()) + increment));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        checkToEnableAddToCart();
    }

    @Override
    public void onPageSelected(int i) {
        createIndicators(i);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        int lastIdx = mChildViewPager.getAdapter().getCount() - 1;

        int curItem = mChildViewPager.getCurrentItem();
        lastPageChange = curItem == lastIdx && state == 1;
    }

    public VerticalViewPager mChildView() {
        return mChildViewPager;
    }
}
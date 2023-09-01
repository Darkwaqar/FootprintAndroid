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
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ActivityGroupProductDetail;
import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.ActivitySingleProduct;
import sa.growonline.footprint.ActivityWishListDetails;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterDetailPagerFromUrl;
import sa.growonline.footprint.asynctask.AsyncTaskDeleteProductFromCart;
import sa.growonline.footprint.asynctask.AsynctaskAddToCart;
import sa.growonline.footprint.asynctask.AsynctaskGetProductDetails;
import sa.growonline.footprint.bean.BeanPostAttributes;
import sa.growonline.footprint.bean.BeanProductDetail;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.bean.BeanServerImage;
import sa.growonline.footprint.network.NetworkUtils;
import sa.growonline.footprint.utils.SystemIntents;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.growonline.footprint.view.VerticalViewPager;

public class FragmentDetailGroup extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private BeanProductDetail mDetailProductBean;

    private VerticalViewPager mChildViewPager;
    private ImageView mBottomOverflowImageView;

    private TextView mMyLikedTextView, mAddToCartTextView;

    private RelativeLayout mAddToCartLayout;
    private String mProductId;
    private LinearLayout mInflaterSizeLayout, mInflaterColorLayout, mOverflowLinearLayout;

    private BeanPostAttributes.BeanProductAttributeValues mSelectedSizeAttribute, mSelectedColorAttribute;
    private boolean mHaveSizeAttribute, mHaveColorAttribute;
    private LinearLayout mBottomBarLinearLayout, mBottomRelatedLinearLayout, mBottomCompleteRelatedProducts;
    private ImageView mBottomFavLayout, mSizeTextView, mShareLinearLayout;
    private RelativeLayout mBuyItNowPopup;
    private TextView mContinueShppingTextView, mProceedToCheckout, mBuyPriceTextView;
    private View mInfoLayout, mInfoView;
    private TextView mInfotextView;

    private String mCartNumber;

    private TextView mPriceTextView;
    private View mDisableView;
    private View mCurrentVisibleLayout;
    private LinearLayout mPageIndicatorLayout;
    private View mShopTheLookTitle;

    public FragmentDetailGroup() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_group, container, false);

        InitUI(view);
        setTypeFace();
        return view;
    }

    private void setTypeFace() {
        ZuniUtils.applyAppFont(mAddToCartTextView);
        ZuniUtils.applyAppFont(mMyLikedTextView);
    }

    private void InitUI(View view) {
        mDisableView = view.findViewById(R.id.detail_disable_view);
        mChildViewPager = (VerticalViewPager) view.findViewById(R.id.detail_child_viewpager);
        mBottomOverflowImageView = (ImageView) view.findViewById(R.id.detail_parent_bottom_overflow_new);
        mBottomFavLayout = (ImageView) view.findViewById(R.id.shoe_rack_imgview);
        mSizeTextView = (ImageView) view.findViewById(R.id.detail_parent_bottom_info_imgview);
        mAddToCartTextView = (TextView) view.findViewById(R.id.detail_parent_bottom_addto_cart_imgview);
        mAddToCartLayout = (RelativeLayout) view.findViewById(R.id.detail_parent_bottom_addto_cart_layout);
        mInfoLayout = view.findViewById(R.id.scrollview_detail_info_layout);
        mBuyItNowPopup = (RelativeLayout) view.findViewById(R.id.detail_parent_buynow_popup);
        mContinueShppingTextView = (TextView) view.findViewById(R.id.detail_parent_buynow_popup_continue_shopping);
        mProceedToCheckout = (TextView) view.findViewById(R.id.detail_parent_buynow_popup_proceed_tocheckout);
        //!--- Dialog for overflow
        mBottomBarLinearLayout = (LinearLayout) view.findViewById(R.id.detail_parent_bottom_layout);
        mBottomRelatedLinearLayout = (LinearLayout) view.findViewById(R.id.detail_parent_bottom_related_layout);
        mBottomCompleteRelatedProducts = (LinearLayout) view.findViewById(R.id.detail_parent_bottom_related_complete_layout);
        mOverflowLinearLayout = (LinearLayout) view.findViewById(R.id.detail_parent_dialog_overflow);
        mMyLikedTextView = (TextView) view.findViewById(R.id.detail_parent_dialog_overflow_favi);
        mShareLinearLayout = (ImageView) view.findViewById(R.id.share_imgview);
        mBuyPriceTextView = (TextView) view.findViewById(R.id.detail_parent_buynow_popup_total_items_lbl1);
        mInfoView = view.findViewById(R.id.detail_parent_bottom_info_layout);

        mInfotextView = (TextView) view.findViewById(R.id.detail_parent_buynow_desc);
        mPriceTextView = (TextView) view.findViewById(R.id.detail_parent_price_txtview);
        mPageIndicatorLayout = (LinearLayout) view.findViewById(R.id.detail_page_indicator_layout);
        mShopTheLookTitle = view.findViewById(R.id.shop_the_look_title);
//        mShoeRackImageView = (ImageView) view.findViewById(R.id.shoe_rack_imgview);


        DisableClickListener a = new DisableClickListener();
        mPriceTextView.setOnTouchListener(a);
        view.findViewById(R.id.bottom_layout).setOnTouchListener(a);

        //!-- Dialog for Color
        mInflaterColorLayout = (LinearLayout) view.findViewById(R.id.detail_parent_dialog_color);
        //!-- End Of Color

        //!-- Dialog for Size
        mInflaterSizeLayout = (LinearLayout) view.findViewById(R.id.detail_parent_dialog_size);
        //!-- End Of Size

        mChildViewPager.mDetect = new VerticalViewPager.SwipeDetectWhileSwipeDisable() {

            @Override
            public void onSwipeDetect() {
                createIndicators(mDetailProductBean.getmProductImageModel().size() - 1);
                showOrCloseRelatedProductsDropdown(null/*mBottomBarLinearLayout*/, mBottomCompleteRelatedProducts);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mShopTheLookTitle.setVisibility(View.VISIBLE);
                        mChildViewPager.setSwipeEnabled(true);
                        if (getActivity() instanceof ActivityProductDetail)
                            ((ActivityProductDetail) getActivity()).setViewPagerSwipe(true);
                        else if (getActivity() instanceof ActivityGroupProductDetail)
                            ((ActivityGroupProductDetail) getActivity()).setViewPagerSwipe(true);
                    }
                }, 100);
            }
        };

        mDisableView.setOnClickListener(this);
        mInfoView.setOnClickListener(this);
        mMyLikedTextView.setOnClickListener(this);
        mShareLinearLayout.setOnClickListener(this);
        mAddToCartLayout.setOnClickListener(this);
        mBottomFavLayout.setOnClickListener(this);
        mBottomOverflowImageView.setOnClickListener(this);
        mContinueShppingTextView.setOnClickListener(this);
        mProceedToCheckout.setOnClickListener(this);
        mShopTheLookTitle.setOnClickListener(this);
    }

    private class DisableClickListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    private void manageBottomDialogVisibilities(View view)
    {
        if (view.getVisibility() == View.VISIBLE)
        {
            mCurrentVisibleLayout = null;
            mDisableView.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
        else
        {
            mOverflowLinearLayout.setVisibility(View.GONE);
            mInfoLayout.setVisibility(View.GONE);
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
        if (mDisableView == v) {
            if (mCurrentVisibleLayout != null)
                manageBottomDialogVisibilities(mCurrentVisibleLayout);
            return;
        }
        if (mInfoView == v) {
            manageBottomDialogVisibilities(mInfoLayout);
            return;
        }

        if (mContinueShppingTextView == v) {
            getActivity().finish();
            return;
        }

        if (mShopTheLookTitle == v)
        {
            mShopTheLookTitle.setVisibility(View.GONE);
            showOrCloseRelatedProductsDropdown(/*null*/mBottomCompleteRelatedProducts, /*mBottomCompleteRelatedProducts*/null/*mBottomBarLinearLayout*/);
        }

        if (v == mBottomOverflowImageView) {
            manageBottomDialogVisibilities(mOverflowLinearLayout);
            return;
        }

        if (v == mMyLikedTextView) {
            getActivity().startActivity(new Intent(getActivity(), ActivityWishListDetails.class));
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
                        mJsonRequest.put("sQuantity", "1");
                        mJsonRequest.put("productId", mDetailProductBean.getmProductId());
                        mJsonRequest.put("sPrice", mDetailProductBean.getProductPrice().getPrice());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new AsynctaskAddToCart(getActivity(), mJsonRequest.toString(), "1", "1", FragmentDetailGroup.this).execute();
                } else
                    ZuniUtils.showMsgDialog(getActivity(), "", "This item is out of stock..!", null, null);
            }
            return;
        }
        if (v == mBottomFavLayout) {
            try {
                if (!mDetailProductBean.getIsWishListItem()) {
                    new AsynctaskAddToCart(getActivity(), "{\"productId\":\"" + mDetailProductBean.getmProductId() + "\"}", "2", "", FragmentDetailGroup.this).execute();
                } else {
                    //!-- Cart Item Id to delete the product from wishlist
                    new AsyncTaskDeleteProductFromCart(getActivity(), mDetailProductBean.getmProductId(), mDetailProductBean.getWishListItemId(), FragmentDetailGroup.this).execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        if (v == mSizeTextView) {
            manageBottomDialogVisibilities(mInflaterSizeLayout);
            return;
        }

        if (v == mShareLinearLayout) {
            String txt = ZuniConstants.SHARING_TEXT + ZuniConstants.SHARE_URL + mDetailProductBean.getSharingName();
            SystemIntents.share(getActivity(), txt, null, NetworkUtils.PLAIN_TEXT);
        }

    }

    private boolean validateInfo() {
        return !(mDetailProductBean != null && mDetailProductBean.getAddToCart().getDisableBuyButton().equalsIgnoreCase("true"));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        callAsync(isVisibleToUser);
    }

    private void updateImages() {
        if (mDetailProductBean.getmProductImageModel() != null && mDetailProductBean.getmProductImageModel().size() != 0) {
            if (mChildViewPager.getAdapter() != null)
            {
            }
            else
            {
                //!-- Setup the Adapter
                createIndicators(0);
                AdapterDetailPagerFromUrl adapter = new AdapterDetailPagerFromUrl(getActivity(), mDetailProductBean.getmProductImageModel(), FragmentDetailGroup.this);
                mChildViewPager.setAdapter(adapter);
            }
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

    public void updateBean(String mCartNumber, final BeanProductDetail mDetailProductBean2) {
        if (mDetailProductBean2 != null) {

            this.mDetailProductBean = mDetailProductBean2;
            this.mCartNumber = mCartNumber;
            ZuniUtils.LogEvent(mDetailProductBean2.getAssociatedProducts() + "");
            //!-- Update activity title
//            if (getActivity() instanceof ActivityProductDetail)
                /*((ActivityProductDetail) getActivity()).getmTitleTextView().setText((mDetailProductBean2.getmProductName()));*/

                //!-- Updating imgs
                updateImages();

            //!-- Cart Details
//            if (getActivity() instanceof ActivityProductDetail)
                /*((ActivityProductDetail) getActivity()).getmCartTextView().setText(mCartNumber);*/

                //!-- Updating wishlist icon
                if (!mDetailProductBean2.getIsWishListItem()) {
                    mBottomFavLayout.setImageResource(R.drawable.fav_icon);
                } else {
                    mBottomFavLayout.setImageResource(R.drawable.fav_icon_red);
                }

            mInfotextView.setText(Html.fromHtml(mDetailProductBean2.getFullDescription().replace("\n", "<br>")));
            mBuyPriceTextView.setText(String.format("Total: %s", mDetailProductBean2.getProductPrice().getPrice() + ".00"));

            //!-- Update Related Products
            updateRelatedProducts();

            mChildViewPager.setOnPageChangeListener(this);
            //!-- Updating price
            updatePrice(mDetailProductBean2.getProductPrice().getPriceValue());

            if (mDetailProductBean.getIsSoldOut().equalsIgnoreCase("true")) {
                enableCart(false);
                mAddToCartTextView.setText(getString(R.string.SoldOut));//asd
                mAddToCartLayout.setBackgroundColor(Color.parseColor("#695291"));
            } else {
                enableCart(true);
                mAddToCartTextView.setText(getString(R.string.BuyNow));
                mAddToCartLayout.setBackgroundColor(Color.parseColor("#695291"));
            }

            //!-- Setting attributes
            mInflaterColorLayout.removeAllViews();
            mInflaterSizeLayout.removeAllViews();
        }
    }

    private void updateRelatedProducts()
    {
        final List<BeanProductForCategory> bean = mDetailProductBean.getAssociatedProducts();
        mBottomRelatedLinearLayout.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < bean.size(); i++) {
            View view = inflater.inflate(R.layout.fragment_detail_related_product, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.detail_related_product_imgview);
            view.setTag(i);
            view.setOnClickListener(new View.OnClickListener() {
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
//		if (b)
//			mAddToCartLayout.setBackground(null);
//		else
//			mAddToCartLayout.setBackgroundResource(R.color.disable_addtocart);

        mAddToCartLayout.setClickable(b);
        mAddToCartLayout.setEnabled(b);
    }

    private void updatePrice(String price) {
        price = addCommaToPrice(price);
        mPriceTextView.setText(Html.fromHtml("<small>PKR  </small><big>" + price + ".00</big>"));
    }

    private String addCommaToPrice(String price) {
        StringBuilder builder = new StringBuilder(price);
        try {
            if (price.length() == 4) {
                builder.insert(1, ",");
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
        mBuyItNowPopup.setVisibility(View.VISIBLE);

        if (mCurrentVisibleLayout != null)
            manageBottomDialogVisibilities(mCurrentVisibleLayout);
        mCartNumber = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CART_QUANTITY, "0");

        /*if (getActivity() instanceof ActivityProductDetail)
            *//*((ActivityProductDetail) getActivity()).getmCartTextView().setText(ZuniApplication.getmAppPreferences().getString(ZuniConstants.CART_QUANTITY, "0"));*/
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
                        new AsynctaskGetProductDetails(FragmentDetailGroup.this, mProductId, FragmentDetailGroup.this).execute();
                    } else {
                        updateBean(mCartNumber, mDetailProductBean);
                    }
                }
            }
        }, 500);
    }

    public void onAddToWishListComplete(String mCartid) {
        //!-- Updating wishlist icon
        mBottomFavLayout.setImageResource(R.drawable.fav_icon_red);
        ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), mDetailProductBean.getmProductName() + " has been successfully added to your wishlist", null, null);
        mDetailProductBean.setIsWishListItem(true);
        mDetailProductBean.setWishListItemId(mCartid);
    }

    public void onDeleteFromWishListComplete() {
        //!-- Updating wishlist icon
        ZuniUtils.showMsgDialog(getActivity(), "", mDetailProductBean.getmProductName() + " has been successfully removed from your wishlist"/*getActivity().getString(R.string.delete_wishlist_msg)*/, null, null);
        mBottomFavLayout.setImageResource(R.drawable.fav_icon);
        mDetailProductBean.setIsWishListItem(false);
    }

    boolean lastPageChange = false;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int lastIdx = mChildViewPager.getAdapter().getCount() - 1;

        if (lastPageChange && position == lastIdx) {
            lastPageChange = false;
            createIndicators(mDetailProductBean.getmProductImageModel().size());
            showOrCloseRelatedProductsDropdown(mBottomCompleteRelatedProducts, null/*mBottomBarLinearLayout*/);
        }
    }

    /**
     * @param view1 the view which have to open
     * @param view2 the view which have to be close
     */
    private void showOrCloseRelatedProductsDropdown(final LinearLayout view1, final LinearLayout view2)
    {
        Animation topToBottomAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.top_to_bottom);
        topToBottomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (view2 != null)
                    view2.setVisibility(View.GONE);
                Animation bottomTopAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top);
                bottomTopAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {

                        if (view1 != null)
                            view1.setVisibility(View.VISIBLE);
                    }
                });

                if (view1 != null)
                    view1.startAnimation(bottomTopAnimation);
            }
        });

//        if (view1 != null)
            mChildViewPager.setSwipeEnabled(false);

        if (getActivity() instanceof ActivityProductDetail)
            ((ActivityProductDetail) getActivity()).setViewPagerSwipe(false);
        else if (getActivity() instanceof ActivityGroupProductDetail)
            ((ActivityGroupProductDetail) getActivity()).setViewPagerSwipe(false);

        if (view2 != null)
            view2.startAnimation(topToBottomAnimation);
        else
        {
            if (view2 != null)
                view2.setVisibility(View.GONE);
            Animation bottomTopAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_to_top);
            bottomTopAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationStart(Animation animation) {

                    if (view1 != null)
                        view1.setVisibility(View.VISIBLE);
                }
            });

            if (view1 != null) {
                view1.setVisibility(View.VISIBLE);
                view1.startAnimation(bottomTopAnimation);
            }
        }
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
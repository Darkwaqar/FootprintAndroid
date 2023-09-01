package sa.growonline.footprint.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sa.growonline.footprint.ActivityAddresses;
import sa.growonline.footprint.ActivityBrand;
import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ActivityChangePassword;
import sa.growonline.footprint.ActivityContact;
import sa.growonline.footprint.ActivityCustomerInfo;
import sa.growonline.footprint.ActivityGiftCard;
import sa.growonline.footprint.ActivityHomeNew;
import sa.growonline.footprint.ActivityLogin;
import sa.growonline.footprint.ActivityOrderListing;
import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.ActivityRewardPoints;
import sa.growonline.footprint.ActivityShopTheLook;
import sa.growonline.footprint.ActivityWebView;
import sa.growonline.footprint.ActivityWishListDetails;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.DialogFilter;
import sa.growonline.footprint.utils.DialoguePlus;
import sa.growonline.footprint.utils.SystemIntents;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class FragmentCategoryMenu extends Fragment {
    private List<BeanGetAllCategory> mCategoryList;
    private LinearLayout mCategoryLinearLayout;
    private LayoutInflater mLayoutInflator;

    public FragmentCategoryMenu() {
    }

    private DrawerLayout mDrawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_menu, null);
        mLayoutInflator = inflater;

        InitUI(view);
        return view;
    }

    public void setDrawer(DrawerLayout layout) {
        this.mDrawerLayout = layout;
    }

    private void InitUI(View view) {
        mCategoryLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_left_menu_category_layout);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void updateCategories(List<BeanGetAllCategory> categories) {
        if (categories != null) {
            for (int i = 0; i < categories.size(); i++)
                if (categories.get(i).getmCategoryId().equalsIgnoreCase("110")) {
                    categories.remove(i);
                    break;
                }

            mCategoryList = ((List) ((ArrayList) categories).clone());



            mCategoryList.add(new BeanGetAllCategory("-6", "CONTACT", 0));

            //// TODO: 2/13/2018 right Drawer things here

            mCategoryList.add(new BeanGetAllCategory("-111", "MY ACCOUNT", 0));
            mCategoryList.add(new BeanGetAllCategory("-12", "ORDERS", 0));
            mCategoryList.add(new BeanGetAllCategory("-13", "ADDRESSES", 0));
            mCategoryList.add(new BeanGetAllCategory("-15", "SHOPPING CART", 0));
            mCategoryList.add(new BeanGetAllCategory("-112", "GIFT CARD", 0));
//        mProfileCategoryList.add(new BeanGetAllCategory("-11", "NEWSLETTER", 0));
//		mProfileCategoryList.add(new BeanGetAllCategory("-6", "I LIKED", 0));
            mCategoryList.add(new BeanGetAllCategory("-19", "REWARD POINTS", 0));
            mCategoryList.add(new BeanGetAllCategory("-110", "CHANGE PASSWORD", 0));
            mCategoryList.add(new BeanGetAllCategory("-18", "LOGOUT", 0));

            mCategoryLinearLayout.removeAllViews();
            for (int i = 0; i < mCategoryList.size(); i++) {
                View view = mLayoutInflator.inflate(R.layout.adapter_left_category_menu, null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.setBackground(getResources().getDrawable(android.R.drawable.list_selector_background, getActivity().getTheme()));
                } else {
                    view.setBackground(getResources().getDrawable(android.R.drawable.list_selector_background));
                }

                AppCompatTextView mCategoryNameTextView = (AppCompatTextView) view.findViewById(R.id.adapter_left_textview);
                final ImageView mCategoryImageView = (ImageView) view.findViewById(R.id.adapter_left_imgview);
                ZuniUtils.applyAppFont(mCategoryNameTextView);

                if (!mCategoryList.get(i).getmCategoryId().contains("-")) {
                    final LinearLayout mCategoryLinearLayout = (LinearLayout) view.findViewById(R.id.adapter_left_sub_category);

                    if (mCategoryList.get(i).getSubCategories().size() != 0) {
                        //!-- Fetch Category
                        final ArrayList<BeanGetAllCategory> mSubCategories = mCategoryList.get(i).getSubCategories();

                        for (int j = 0; j < mSubCategories.size(); j++) {
                            View subview = mLayoutInflator.inflate(R.layout.adapter_left_category_menu, null);

                            AppCompatTextView mSubCategoryNameTextView = (AppCompatTextView) subview.findViewById(R.id.adapter_left_textview);
                            ImageView mSubCategoryImageView = (ImageView) subview.findViewById(R.id.adapter_left_imgview);

                            ZuniUtils.applyAppFont(mSubCategoryNameTextView);

                            mSubCategoryNameTextView.setText(mSubCategories.get(j).getmCategoryName());
                            mSubCategoryImageView.setImageBitmap(null);

                            mCategoryLinearLayout.addView(subview);

                            subview.setTag(R.string.fragment_category_tag_i, i);
                            subview.setTag(R.string.fragment_category_tag_j, j);

                            subview.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int j = (Integer) v.getTag(R.string.fragment_category_tag_j);
                                    if (!mSubCategories.get(j).getmCategoryId().contains("-"))
                                        openProduct(mSubCategories.get(j).getmCategoryId(), mSubCategories.get(j).getmCategoryName());
                                    else {
                                        Intent i = new Intent(getActivity(), ActivityWebView.class);
                                        switch (mSubCategories.get(j).getmCategoryId()) {

                                            case "-2.1":
                                                i.putExtra("url", ZuniConstants.PDF_URL + getString(R.string.AmnaIsmailUrl));
                                                i.putExtra("title", "AmnaIsmail");
                                                break;

                                            case "-2.2":
                                                i.putExtra("url", ZuniConstants.PDF_URL + getString(R.string.FarahUrl));
                                                i.putExtra("title", "Farah Leghari");
                                                break;

                                            case "-2.3":
                                                i.putExtra("url", ZuniConstants.PDF_URL + getString(R.string.ZuniPdf));
                                                i.putExtra("title", "Zuni");
                                                break;
                                        }
                                        startActivity(i);
                                    }
                                }
                            });
                        }
                    } else
                        mCategoryImageView.setImageBitmap(null);

                    view.setTag(i);



                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            int i = (Integer) v.getTag();

                            if (mCategoryList.get(i).getSubCategories().size() != 0) {
                                if (mCategoryLinearLayout.getVisibility() == View.GONE) {
                                    mCategoryLinearLayout.setVisibility(View.VISIBLE);
                                    mCategoryImageView.setImageResource(R.drawable.nav_category_ico_top);
                                } else {
                                    mCategoryImageView.setImageResource(R.drawable.nav_category_ico);
                                    mCategoryLinearLayout.setVisibility(View.GONE);
                                }
                            } else {
                                mCategoryLinearLayout.setVisibility(View.GONE);
                                openProduct(mCategoryList.get(i).getmCategoryId(), mCategoryList.get(i).getmCategoryName());
                            }
                        }
                    });
                    mCategoryNameTextView.setText(mCategoryList.get(i).getmCategoryName());
                } else {

                    mCategoryNameTextView.setText(mCategoryList.get(i).getmCategoryName());
                    mCategoryImageView.setImageResource(mCategoryList.get(i).getmResourceId());

                    view.setTag(i);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int i = (Integer) v.getTag();
                            Class<?> nextClass = null;
                            Intent intent = new Intent();
                            switch (mCategoryList.get(i).getmCategoryId()) {
                                case "-1":
                                    nextClass = ActivityHomeNew.class;
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    break;

                                case "-2":
                                    SystemIntents.requestUrlIntent(getActivity(), ZuniConstants.BASE_DOMAIN + ZuniConstants.FAQS);
                                    break;

                                case "-3":
                                    intent.putExtra("url", "http://Digitrends.ae/Amna_Ismail");
                                    intent.putExtra("title", "ABOUT");
                                    nextClass = ActivityWebView.class;
                                    break;


                                case "-4":
                                    intent.putExtra("url", "http://Digitrends.ae/National");
                                    intent.putExtra("title", "STORE LOCATOR");
                                    nextClass = ActivityWebView.class;
                                    break;

                                case "-7":
                                    intent.putExtra("url", "http://digitrends.ae/Return_and_exchange/");
                                    intent.putExtra("title", "RETURN AND EXCHANGE");
                                    nextClass = ActivityWebView.class;
                                    break;

                                case "-8":
                                    break;

                                case "-6":
                                    intent.setClass(getActivity(),ActivityContact.class);
                                    break;

                                case "-11":
                                    ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.START);
                                    new DialogFilter(getActivity()).showDialog();
                                    return;

                                case "-111":
                                    if (ZuniApplication.getmAppPreferences().getString(ZuniConstants.SIGNED_IN_FROM, "").equalsIgnoreCase(ZuniConstants.GUEST_USER))
                                        intent.setClass(getActivity(), ActivityLogin.class);
                                    else
                                        intent.setClass(getActivity(), ActivityCustomerInfo.class);
                                    break;

                                case "-12":
                                    intent.setClass(getActivity(), ActivityOrderListing.class);
                                    break;


                                case "-13":
                                    intent.setClass(getActivity(), ActivityAddresses.class);
                                    break;

                                case "-15":
                                    intent.setClass(getActivity(), ActivityCartDetails.class);
                                    break;

                                case "-16":
                                    intent.setClass(getActivity(), ActivityWishListDetails.class);
                                    break;

                                case "-17":
                                    intent.putExtra("url", "http://digitrends.ae/Return_and_exchange/");
                                    intent.putExtra("title", "FAQS");
                                    intent.setClass(getActivity(), ActivityWebView.class);
                                    break;

                                case "-19":
                                    intent.setClass(getActivity(), ActivityRewardPoints.class);
                                    break;


                                case "-110":
                                    if (ZuniApplication.getmAppPreferences().getString(ZuniConstants.SIGNED_IN_FROM, "").equalsIgnoreCase(ZuniConstants.GUEST_USER))
                                        intent.setClass(getActivity(), ActivityLogin.class);
                                    else
                                        intent.setClass(getActivity(), ActivityChangePassword.class);
                                    break;

                                case "-18":
                                    intent.setClass(getActivity(), ActivityLogin.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    break;


                                case "-112":
                                    intent.setClass(getActivity(), ActivityGiftCard.class);
                                    break;

                                case "-113":
                                    new DialoguePlus(getActivity()).showDialog();
                                    return;
                            }

                            startActivity(intent);


//                            try {
//                                if (!getActivity().getClass().getName().equalsIgnoreCase(nextClass.getName())) {
//                                    intent.setClass(getActivity(), nextClass);
//                                    getActivity().startActivity(intent);
//                                } else {
//                                    ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.START);
//                                }
//                            } catch (ActivityNotFoundException e) {
//                                e.printStackTrace();
//                            }
                            ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.START);
                        }
                    });
                }
                this.mCategoryLinearLayout.addView(view);
            }
        }
    }

    public void openProduct(String categoryId, String CategoryName) {
        ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.START);

        Intent intent;
        if (CategoryName.toLowerCase().startsWith(ZuniConstants.SHOP_THE_LOOK_ID)) {
            intent = new Intent(getActivity(), ActivityShopTheLook.class);
        } else if (categoryId.equalsIgnoreCase(ZuniConstants.BRAND_ID)) {
            ActivityBrand.mFor = "br";
            intent = new Intent(getActivity(), ActivityBrand.class);
        } else if (categoryId.equalsIgnoreCase(ZuniConstants.BESPOKE_ID)) {
            ActivityBrand.mFor = "be";
            intent = new Intent(getActivity(), ActivityBrand.class);
        } else if (categoryId.equalsIgnoreCase(ZuniConstants.GIFT_ID)) {
            intent = new Intent(getActivity(), ActivityGiftCard.class);
        } else {
            intent = new Intent(getActivity(), ActivityProductList.class);
        }
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.POST_LIST_JSON, "").commit();
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_ID_PREF_KEY, categoryId).commit();
        startActivity(intent);


    }

    public void loadLastFetchCatrgories() {
        try {
            String category = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CATEGORY_JSON, "[]");
            Type listType = new TypeToken<ArrayList<BeanGetAllCategory>>() {
            }.getType();
            ArrayList<BeanGetAllCategory> a = new ArrayList<>();
            a.addAll((Collection<BeanGetAllCategory>) new Gson().fromJson(category, listType));
            updateCategories(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
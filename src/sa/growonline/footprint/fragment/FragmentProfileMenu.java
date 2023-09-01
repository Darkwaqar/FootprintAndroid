package sa.growonline.footprint.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityAddresses;
import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ActivityChangePassword;
import sa.growonline.footprint.ActivityCustomerInfo;
import sa.growonline.footprint.ActivityGiftCard;
import sa.growonline.footprint.ActivityLogin;
import sa.growonline.footprint.ActivityOrderListing;
import sa.growonline.footprint.ActivityRewardPoints;
import sa.growonline.footprint.ActivityWebView;
import sa.growonline.footprint.ActivityWishListDetails;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.asynctask.AsynctaskLogOut;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.DialoguePlus;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class FragmentProfileMenu extends Fragment {
    private LinearLayout mProfileCategoryLayout;

    private ArrayList<BeanGetAllCategory> mProfileCategoryList;
    private LayoutInflater mLayoutInflator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_menu, null);
        mLayoutInflator = inflater;
        InitUI(view);
        inflateCategories();

        return view;
    }

    private void InitUI(View view) {
        mProfileCategoryLayout = (LinearLayout) view.findViewById(R.id.fragment_profile_menu_category_layout);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void inflateCategories() {
        mProfileCategoryList = new ArrayList<>();

        mProfileCategoryList.add(new BeanGetAllCategory("-1", "MY ACCOUNT", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-2", "ORDERS", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-3", "ADDRESSES", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-5", "SHOPPING CART", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-12", "GIFT CARD", 0));
//        mProfileCategoryList.add(new BeanGetAllCategory("-11", "NEWSLETTER", 0));
//		mProfileCategoryList.add(new BeanGetAllCategory("-6", "I LIKED", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-9", "REWARD POINTS", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-10", "CHANGE PASSWORD", 0));
        mProfileCategoryList.add(new BeanGetAllCategory("-8", "LOGOUT", 0));

        for (int i = 0; i < mProfileCategoryList.size(); i++) {
            View view = mLayoutInflator.inflate(R.layout.adapter_right_profile_menu, null);

            AppCompatTextView mCategoryNameTextView = (AppCompatTextView) view.findViewById(R.id.adapter_right_textview);
            ImageView mCategoryImageView = (ImageView) view.findViewById(R.id.adapter_right_imgview);

            ZuniUtils.applyAppFont(mCategoryNameTextView);
            if (mProfileCategoryList.get(i).getmResourceId() != 0)
                mCategoryImageView.setImageResource(mProfileCategoryList.get(i).getmResourceId());

            mCategoryNameTextView.setText(mProfileCategoryList.get(i).getmCategoryName());

            view.setTag(i);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setBackground(getResources().getDrawable(android.R.drawable.list_selector_background, getActivity().getTheme()));
            } else {
                view.setBackground(getResources().getDrawable(android.R.drawable.list_selector_background));
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int pos = (Integer) v.getTag();
                        String categoryId = mProfileCategoryList.get(pos).getmCategoryId();

                        if (categoryId.equalsIgnoreCase("-8") && mProfileCategoryList.get(pos).getmCategoryName().equalsIgnoreCase("logout")) //!-- Logout
                        {
                            new AsynctaskLogOut(getActivity(), true, FragmentProfileMenu.this).execute();
                            return;
                        }

                        Intent intent = new Intent();
                        switch (categoryId) {

                            case "-1":
                                if (ZuniApplication.getmAppPreferences().getString(ZuniConstants.SIGNED_IN_FROM, "").equalsIgnoreCase(ZuniConstants.GUEST_USER))
                                    intent.setClass(getActivity(), ActivityLogin.class);
                                else
                                    intent.setClass(getActivity(), ActivityCustomerInfo.class);
                                break;

                            case "-2":
                                intent.setClass(getActivity(), ActivityOrderListing.class);
                                break;


                            case "-3":
                                intent.setClass(getActivity(), ActivityAddresses.class);
                                break;

                            case "-5":
                                intent.setClass(getActivity(), ActivityCartDetails.class);
                                break;

                            case "-6":
                                intent.setClass(getActivity(), ActivityWishListDetails.class);
                                break;

                            case "-7":
                                intent.putExtra("url", "http://digitrends.ae/Return_and_exchange/");
                                intent.putExtra("title", "FAQS");
                                intent.setClass(getActivity(), ActivityWebView.class);
                                break;

                            case "-9":
                                intent.setClass(getActivity(), ActivityRewardPoints.class);
                                break;


                            case "-10":
                                if (ZuniApplication.getmAppPreferences().getString(ZuniConstants.SIGNED_IN_FROM, "").equalsIgnoreCase(ZuniConstants.GUEST_USER))
                                    intent.setClass(getActivity(), ActivityLogin.class);
                                else
                                    intent.setClass(getActivity(), ActivityChangePassword.class);
                                break;

                            case "-8":
                                intent.setClass(getActivity(), ActivityLogin.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                break;

                            case "-11":

                                break;

                            case "-12":
                                intent.setClass(getActivity(), ActivityGiftCard.class);
                                break;

                            case "-13":
                                new DialoguePlus(getActivity()).showDialog();
                                return;
                        }


                        startActivity(intent);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            });
            mProfileCategoryLayout.addView(view);
        }
    }
}
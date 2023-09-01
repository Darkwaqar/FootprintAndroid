package sa.growonline.footprint.utils;

import java.util.List;

import sa.growonline.footprint.R;
import sa.growonline.footprint.adapter.FilterArrayAdapter;
import sa.growonline.footprint.asynctask.AsynctaskFilter;
import sa.growonline.footprint.asynctask.AsynctaskGetFilterData;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.bean.ManufacturerBean;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DialogFilter implements OnClickListener {
    private Activity mActivity;
    private Dialog mDialog;
    private TextView mCategoryTextView;
    private CheckBox mDescriptionCheckBox;
    private CheckBox mSubCategoriesButton;
    private EditText mSearchEditText;
    private EditText mMinimumTextView;
    private EditText mMaximumTextView;
    private List<BeanGetAllCategory> mBeanCategories;
    private List<ManufacturerBean> mManufacturersList;
    private String mCategoryId = "";

    public DialogFilter(Activity activityHomeMaterialViewPager) {
        this.mActivity = activityHomeMaterialViewPager;
    }

    public void showDialog() {
        mDialog = new Dialog(mActivity, android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setContentView(R.layout.dialog_filter);
        InitUI(mDialog);
        mDialog.setCancelable(true);
        mDialog.show();

        if (mManufacturersList == null)
            new AsynctaskGetFilterData(mActivity, true, DialogFilter.this).execute();
    }

    private void InitUI(Dialog mDialog) {
        ImageView mBackButton = (ImageView) mDialog.findViewById(R.id.search_back_imgview);
        Button mSearchButton = (Button) mDialog.findViewById(R.id.search_start_button);

        mSearchEditText = (EditText) mDialog.findViewById(R.id.search_edit_text);
        mDescriptionCheckBox = (CheckBox) mDialog.findViewById(R.id.search_in_product_descriptions);
        mSubCategoriesButton = (CheckBox) mDialog.findViewById(R.id.search_in_sub_categories_descriptions);

        mMinimumTextView = (EditText) mDialog.findViewById(R.id.search_price_minimum_text_view);
        mMaximumTextView = (EditText) mDialog.findViewById(R.id.search_price_maximum_text_view);

        mCategoryTextView = (TextView) mDialog.findViewById(R.id.search_category_text_view);

        mCategoryTextView.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.search_back_imgview:
                mDialog.dismiss();
                break;

            case R.id.search_start_button:
                if (!ZuniUtils.getText(mSearchEditText).equalsIgnoreCase("")) {
                    new AsynctaskFilter(mActivity, true, ZuniUtils.getText(mSearchEditText), ZuniUtils.getText(mMinimumTextView), ZuniUtils.getText(mMaximumTextView), "", mCategoryId, mSubCategoriesButton.isChecked(), mDescriptionCheckBox.isChecked(), mDialog).execute();
                } else {
                    ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), mActivity.getString(R.string.MinimumCharacterToSearch), null, null);
                }
                break;


            case R.id.search_category_text_view:
                AlertDialog.Builder alertDialogcat = new AlertDialog.Builder(mActivity);
                ListView listViewcat = new ListView(mActivity);

                alertDialogcat.setView(listViewcat);

                AlertDialog alertDialog2cate = alertDialogcat.create();
                listViewcat.setAdapter(new FilterArrayAdapter(mActivity, android.R.id.text1, mBeanCategories, alertDialog2cate, DialogFilter.this));
                alertDialog2cate.show();
                break;
        }

    }

    public void onFilterDataReceived(List<ManufacturerBean> mBeanManufacturers, List<BeanGetAllCategory> mBeanCategories) {
        this.mManufacturersList = mBeanManufacturers;
        this.mBeanCategories = mBeanCategories;

        for (int i = 0; i < mBeanCategories.size(); i++)
            if (mBeanCategories.get(i).getmCategoryId().equalsIgnoreCase("110")) {
                mBeanCategories.remove(i);
                break;
            }
    }

    public void callBack(Object object) {
        if (object instanceof ManufacturerBean) {
        } else if (object instanceof BeanGetAllCategory) {
            mCategoryTextView.setText(((BeanGetAllCategory) object).getmCategoryName());
            mCategoryId = ((BeanGetAllCategory) object).getmCategoryId();
        }
    }
}

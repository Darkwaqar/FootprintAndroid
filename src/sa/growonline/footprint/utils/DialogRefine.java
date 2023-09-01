package sa.growonline.footprint.utils;

import android.app.Activity;
import android.app.Dialog;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterRefineItem;
import sa.growonline.footprint.base.BaseToolbarx;
import sa.growonline.footprint.bean.NotFilteredItem;
import sa.growonline.footprint.view.RangeBar;

public class DialogRefine implements View.OnClickListener {

    private Activity mActivity;
    private Dialog mDialog;

    private TextView styleBtn;
    private TextView sizeBtn;
    private TextView colorBtn;
    private TextView materialBtn;
    private TextView soleBtn;
    private TextView txtPriceRanges;

    private LinearLayout styleBtnLayout;
    private LinearLayout sizeBtnLayout;
    private LinearLayout colorBtnLayout;
    private LinearLayout materialBtnLayout;
    private LinearLayout soleBtnLayout;

    private LinearLayout styleBtnLayoutCircle;
    private LinearLayout sizeBtnLayoutCircle;
    private LinearLayout colorBtnLayoutCircle;
    private LinearLayout materialBtnLayoutCircle;
    private LinearLayout soleBtnLayoutCircle;

    private RangeBar priceBar;

    private AdapterRefineItem attrAdapter;
    private RecyclerView attrRecyclerView;

    private float mMinPrice;
    private float mMaxPrice;

    public DialogRefine(Activity activity, float minPrice, float maxPrice) {
        this.mActivity = activity;
        this.mMinPrice = minPrice;
        this.mMaxPrice = maxPrice;

        mDialog = new Dialog(this.mActivity, android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setContentView(R.layout.dialog_refine);
        mDialog.setCancelable(true);
        InitUI(mDialog);
    }

    public void showDialog() {
        styleBtnLayout.setVisibility(View.GONE);
        sizeBtnLayout.setVisibility(View.GONE);
        colorBtnLayout.setVisibility(View.GONE);
        materialBtnLayout.setVisibility(View.GONE);
        soleBtnLayout.setVisibility(View.GONE);

        setMainButtonVisibility();
        mDialog.show();
    }
    public void dismissDialog() {

        mDialog.dismiss();
    }

    private void InitUI(final Dialog mDialog) {

        AppBarLayout dialogAppBar = (AppBarLayout) mDialog.findViewById(R.id.appbar_refine);
        BaseToolbarx appBar = new BaseToolbarx((AppCompatActivity) this.mActivity, BaseToolbarx.ToolBarType.CUSTOMIZABLE);
        appBar.install(dialogAppBar);
        appBar.setToolBarTitle("REFINE");
        appBar.setNavigationProps(R.drawable.backbutton_blue, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.hide();
            }
        });

        styleBtnLayout = (LinearLayout) mDialog.findViewById(R.id.layout_refine_style);
        sizeBtnLayout = (LinearLayout) mDialog.findViewById(R.id.layout_refine_size);
        colorBtnLayout = (LinearLayout) mDialog.findViewById(R.id.layout_refine_color);
        materialBtnLayout = (LinearLayout) mDialog.findViewById(R.id.layout_refine_material);
        soleBtnLayout = (LinearLayout) mDialog.findViewById(R.id.layout_refine_sole);

        styleBtnLayoutCircle = (LinearLayout) mDialog.findViewById(R.id.btn_style_attr_pt);
        sizeBtnLayoutCircle = (LinearLayout) mDialog.findViewById(R.id.btn_size_attr_pt);
        colorBtnLayoutCircle = (LinearLayout) mDialog.findViewById(R.id.btn_color_attr_pt);
        materialBtnLayoutCircle = (LinearLayout) mDialog.findViewById(R.id.btn_material_attr_pt);
        soleBtnLayoutCircle = (LinearLayout) mDialog.findViewById(R.id.btn_sole_attr_pt);

        styleBtn = (TextView) mDialog.findViewById(R.id.btn_style_attr);
        sizeBtn = (TextView) mDialog.findViewById(R.id.btn_size_attr);
        colorBtn = (TextView) mDialog.findViewById(R.id.btn_color_attr);
        materialBtn = (TextView) mDialog.findViewById(R.id.btn_material_attr);
        soleBtn = (TextView) mDialog.findViewById(R.id.btn_sole_attr);
        TextView resetBtn = (TextView) mDialog.findViewById(R.id.btn_refine_reset);
        txtPriceRanges = (TextView) mDialog.findViewById(R.id.refine_price_range_txt);

        attrRecyclerView = (RecyclerView) mDialog.findViewById(R.id.recyclerView_attr_values);
        attrRecyclerView.setItemAnimator(new RefineAttributeAnimator());
        attrRecyclerView.setLayoutManager(new LinearLayoutManager(null, LinearLayoutManager.HORIZONTAL, false));

        priceBar = (RangeBar) mDialog.findViewById(R.id.range_bar_refine_price);
        if (this.mMinPrice > 0 && this.mMaxPrice > 2) {
            priceBar.setUpRangeBar(this.mMinPrice, this.mMaxPrice, countTickInterval());
            priceBar.setDrawTicks(false);
        } else {
            priceBar.setEnabled(false);
            txtPriceRanges.setText(R.string.NoPriceDefined);
        }


        Button applyBtn = (Button) mDialog.findViewById(R.id.btn_refine_apply);

        styleBtnLayoutCircle.setOnClickListener(this);
        sizeBtnLayoutCircle.setOnClickListener(this);
        colorBtnLayoutCircle.setOnClickListener(this);
        materialBtnLayoutCircle.setOnClickListener(this);
        soleBtnLayoutCircle.setOnClickListener(this);
        applyBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);

        priceBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                txtPriceRanges.setText("PKR." + leftPinValue + " - PKR." + rightPinValue);
            }
        });

    }

    private void setMainButtonVisibility() {

        if (((ZuniApplication) mActivity.getApplication()).getRefineItems() != null) {
            List<NotFilteredItem> allRefineAttr = ((ZuniApplication) mActivity.getApplication()).getRefineItems();

            for (int i = 0; i < allRefineAttr.size(); i++) {
                NotFilteredItem tempItem = allRefineAttr.get(i);
                String attrName = tempItem.getSpecificationAttributeName().toLowerCase();

                switch (attrName) {
                    case ZuniConstants.REFINE_ATTRIBUTE_STYLE:
                        styleBtnLayout.setVisibility(View.VISIBLE);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_SIZE:
                        sizeBtnLayout.setVisibility(View.VISIBLE);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_COLOR:
                        colorBtnLayout.setVisibility(View.VISIBLE);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_MATERIAL:
                        materialBtnLayout.setVisibility(View.VISIBLE);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_SOLE:
                        soleBtnLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_style_attr_pt:

                styleBtn.setSelected(!styleBtn.isSelected());
                styleBtnLayoutCircle.setSelected(!styleBtnLayoutCircle.isSelected());
                handleButtonState(styleBtn, ZuniConstants.REFINE_ATTRIBUTE_STYLE);

                break;
            case R.id.btn_size_attr_pt:
                sizeBtn.setSelected(!sizeBtn.isSelected());
                sizeBtnLayoutCircle.setSelected(!sizeBtnLayoutCircle.isSelected());
                handleButtonState(sizeBtn, ZuniConstants.REFINE_ATTRIBUTE_SIZE);

                break;
            case R.id.btn_color_attr_pt:
                colorBtn.setSelected(!colorBtn.isSelected());
                colorBtnLayoutCircle.setSelected(!colorBtnLayoutCircle.isSelected());
                handleButtonState(colorBtn, ZuniConstants.REFINE_ATTRIBUTE_COLOR);

                break;
            case R.id.btn_material_attr_pt:
                materialBtn.setSelected(!materialBtn.isSelected());
                materialBtnLayoutCircle.setSelected(!materialBtnLayoutCircle.isSelected());
                handleButtonState(materialBtn, ZuniConstants.REFINE_ATTRIBUTE_MATERIAL);

                break;
            case R.id.btn_sole_attr_pt:
                soleBtn.setSelected(!soleBtn.isSelected());
                soleBtnLayoutCircle.setSelected(!soleBtnLayoutCircle.isSelected());
                handleButtonState(soleBtn, ZuniConstants.REFINE_ATTRIBUTE_SOLE);

                break;
            case R.id.btn_refine_apply:
                applyRefine();
                break;
            case R.id.btn_refine_reset:
                applyReset();
                break;
            default:
                break;
        }

    }

    private void handleButtonState(TextView btn, String type) {
        if (btn.isSelected()) {
            reBindAdapter(type);
        } else {
            releaseRecyclerView();

            if (btn.getTag() != null) {
                switch (type) {
                    case ZuniConstants.REFINE_ATTRIBUTE_STYLE:
                        btn.setBackgroundResource(R.drawable.refine_style_attr);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_SIZE:
                        btn.setBackgroundResource(R.drawable.refine_size_attr);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_COLOR:
                        btn.setBackgroundResource(R.drawable.refine_color_attr);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_MATERIAL:
                        btn.setBackgroundResource(R.drawable.refine_material_attr);
                        break;
                    case ZuniConstants.REFINE_ATTRIBUTE_SOLE:
                        btn.setBackgroundResource(R.drawable.refine_sole_attr);
                        break;
                    default:
                        break;
                }

                btn.setText(null);
                btn.setTag(null);
                btn.setSelected(true);
                btn.setSelected(false);
            }
        }
    }

    private void reBindAdapter(String type) {
        if (attrAdapter == null) {
            attrAdapter = new AdapterRefineItem(this.mActivity, ((ZuniApplication) mActivity.getApplication()).getRefineItems(), type, this);
            attrRecyclerView.setAdapter(attrAdapter);
        } else {
            attrAdapter.refresh(((ZuniApplication) mActivity.getApplication()).getRefineItems(), type);
            attrAdapter.notifyDataSetChanged();
        }
    }

    private void releaseRecyclerView() {

        if (attrAdapter != null && attrAdapter.getItemCount() <= 0) {
            return;
        }

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(200);
        attrRecyclerView.startAnimation(fadeOut);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (attrAdapter != null) {
                    attrAdapter.clear();
                    attrAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void adapterSingleItemClick(String type, String selectedAttributeText, String selectedAttributeId) {
        switch (type) {
            case ZuniConstants.REFINE_ATTRIBUTE_STYLE:
                styleBtn.setText(formatAttributeText(selectedAttributeText));
                styleBtn.setTag(selectedAttributeId);
                styleBtn.setBackgroundResource(0);
                break;
            case ZuniConstants.REFINE_ATTRIBUTE_SIZE:
                sizeBtn.setText(selectedAttributeText);
                sizeBtn.setTag(selectedAttributeId);
                sizeBtn.setBackgroundResource(0);
                break;
            case ZuniConstants.REFINE_ATTRIBUTE_COLOR:
                colorBtn.setText(formatAttributeText(selectedAttributeText));
                colorBtn.setTag(selectedAttributeId);
                colorBtn.setBackgroundResource(0);
                break;
            case ZuniConstants.REFINE_ATTRIBUTE_MATERIAL:
                materialBtn.setText(formatAttributeText(selectedAttributeText));
                materialBtn.setTag(selectedAttributeId);
                materialBtn.setBackgroundResource(0);
                break;
            case ZuniConstants.REFINE_ATTRIBUTE_SOLE:
                soleBtn.setText(formatAttributeText(selectedAttributeText));
                soleBtn.setTag(selectedAttributeId);
                soleBtn.setBackgroundResource(0);
                break;
            default:
                break;
        }

        releaseRecyclerView();
    }

    private String formatAttributeText(String text) {
        text = text.trim();

        String tempText;
        if (text.contains(" ")) {
            int i = text.indexOf(" ");
            String firstPart = text.substring(0, i).trim();
            String secondPart = text.substring(i, text.length()).trim();

            tempText = firstPart.substring(0, 1) + secondPart.substring(0, 1);
        } else {
            tempText = text.substring(0, 1);
        }

        return tempText.toUpperCase();
    }

    private void applyRefine() {
        String specs = "";

        if (styleBtn.getTag() != null) {
            specs += styleBtn.getTag().toString() + ",";
        }
        if (sizeBtn.getTag() != null) {
            specs += sizeBtn.getTag().toString() + ",";
        }
        if (colorBtn.getTag() != null) {
            specs += colorBtn.getTag().toString() + ",";
        }
        if (materialBtn.getTag() != null) {
            specs += materialBtn.getTag().toString() + ",";
        }
        if (soleBtn.getTag() != null) {
            specs += soleBtn.getTag().toString() + ",";
        }

        String minP = priceBar.getLeftPinValue();
        String maxP = priceBar.getRightPinValue();

        ((ActivityProductList) mActivity).Refine(specs, minP, maxP);

        mDialog.hide();
    }

    private void applyReset() {
        refreshControl();
        ((ActivityProductList) mActivity).ResetRefine();
        mDialog.hide();
    }

    private float countTickInterval() {

        float priceDiff = mMaxPrice - mMinPrice;

        float tickInterval;
        if (priceDiff <= 10) {
            tickInterval = 1;
        } else if (priceDiff <= 50) {
            tickInterval = 5;
        } else if (priceDiff <= 100) {
            tickInterval = 10;
        } else if (priceDiff <= 500) {
            tickInterval = 50;
        } else if (priceDiff <= 2000) {
            tickInterval = 100;
        } else if (priceDiff <= 3000) {
            tickInterval = 150;
        } else if (priceDiff <= 4000) {
            tickInterval = 200;
        } else {
            tickInterval = 500;
        }

        return tickInterval;
    }

    private void refreshControl() {

        if (styleBtnLayoutCircle.isSelected()) {
            styleBtnLayoutCircle.performClick();
        }
        if (sizeBtnLayoutCircle.isSelected()) {
            sizeBtnLayoutCircle.performClick();
        }
        if (colorBtnLayoutCircle.isSelected()) {
            colorBtnLayoutCircle.performClick();
        }
        if (materialBtnLayoutCircle.isSelected()) {
            materialBtnLayoutCircle.performClick();
        }
        if (soleBtnLayoutCircle.isSelected()) {
            soleBtnLayoutCircle.performClick();
        }

        txtPriceRanges.setText(R.string.AllPrices);
    }


}

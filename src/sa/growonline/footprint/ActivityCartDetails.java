/*
 * Copyright (c) 2016 Arsal Raza Imam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import sa.growonline.footprint.adapter.AdapterCartDetail;
import sa.growonline.footprint.asynctask.AsynctaskGetCartDetails;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanGetAllCarts;
import sa.growonline.footprint.utils.SystemIntents;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityCartDetails extends BaseActivityx implements OnClickListener {
    private ListView mCartListView;
    private BeanGetAllCarts mCartDetailsBean;
    private AppCompatTextView mTotalProductsTextView;
    private TextView mTotalPriceTextView;
    private View mProcessBtn;
    private View mPhonebtn;
    private final String[] PERMISSION = {Manifest.permission.CALL_PHONE};
    private TextView mShippingFees;
    private TextView mCartDetailEmptyProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
//        setupToolBar();
        removeToolbar();
        updateTitle("Cart");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        InitUI();

        if (mCartDetailsBean == null) {
            new AsynctaskGetCartDetails(ActivityCartDetails.this, true).execute();
        }
    }

    private void InitUI() {
        mCartListView = (ListView) findViewById(R.id.activity_addtocart_listview);
        mTotalProductsTextView = (AppCompatTextView) findViewById(R.id.adapter_cartdetails_bottom_layout_total_product);
        mTotalPriceTextView = (TextView) findViewById(R.id.activity_cartdetails_bottom_layout_product_total_price);
        mProcessBtn = findViewById(R.id.adapter_cartdetails_bottom_layout_process_btn);
        mShippingFees = (TextView) findViewById(R.id.activity_cartdetails_bottom_layout_product_total_price_shipping);
        mCartDetailEmptyProduct =(TextView) findViewById(R.id.activity_cartdetail_emptyproduct);

        mPhonebtn = findViewById(R.id.activity_addtocart_phone_btn);

        mPhonebtn.setOnClickListener(this);
        mProcessBtn.setOnClickListener(this);

    }

    public void onCartDetailsReceived(BeanGetAllCarts mCartDetailBean, boolean isFromAdapter) {
        if (mCartDetailBean != null) {

            if (mCartDetailBean.getItems().size() == 0)
                mCartDetailEmptyProduct.setText(R.string.CartEmpty);
                /*ZuniUtils.showMsgDialog(ActivityCartDetails.this, "", "Your cart is empty", null, null);*/
            mCartDetailEmptyProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP,28);
            this.mCartDetailsBean = mCartDetailBean;

            int totalQuantity = 0;
            for (int i = 0; i < mCartDetailBean.getItems().size(); i++) {
                int itemQuantity = mCartDetailBean.getItems().get(i).getQuantity();
                totalQuantity = totalQuantity + itemQuantity;
            }
            String products = getString(R.string.activity_cartdetails_bottom_layout_total_product);
            if (totalQuantity == 0 || totalQuantity == 1) {
                products = products.replace("products", "product");
            }
            mTotalProductsTextView.setText(products.replace("0", totalQuantity + ""));

            if (mCartDetailBean.getmTotalPaymentPaidByUser() != null && !mCartDetailBean.getmTotalPaymentPaidByUser().toLowerCase().equalsIgnoreCase("null")) {
                mTotalPriceTextView.setText(Html.fromHtml("<small>PKR </small><big>" + mCartDetailBean.getmTotalPaymentPaidByUser().replace("PKR", "") + ".00</big>"));
            } else
                mTotalPriceTextView.setText(R.string.Zero);

            if (mCartDetailBean.getmShippingFees() != null && !mCartDetailBean.getmShippingFees().toLowerCase().equalsIgnoreCase("null"))
                mShippingFees.setText(Html.fromHtml("<small>PKR </small><big>" + mCartDetailBean.getmShippingFees().replace("PKR", "") + ".00</big>"));
            else
                mShippingFees.setText(R.string.Zero);

            if (mCartListView.getAdapter() == null) {
                AdapterCartDetail adapterCartDetail = new AdapterCartDetail(this, mCartDetailsBean.getItems());
                mCartListView.setAdapter(adapterCartDetail);
            } else
                ((AdapterCartDetail) mCartListView.getAdapter()).notifyBeanChanged(mCartDetailBean.getItems());
        }
    }

    public void updateList(/*int mAdapterId*/) {
        new AsynctaskGetCartDetails(ActivityCartDetails.this, true).execute();
        /*mCartDetailsBean.getItems().remove(mAdapterId);
        onCartDetailsReceived(mCartDetailsBean, false);*/
    }

    @Override
    public void onClick(View v) {
        if (v == mProcessBtn) {
            if (mCartDetailsBean != null && mCartDetailsBean.getItems() != null && mCartDetailsBean.getItems().size() != 0)
                startActivity(new Intent(ActivityCartDetails.this, ActivityCheckOut.class));
            else
                ZuniUtils.showMsgDialog(ActivityCartDetails.this, "", "Your cart is empty..!", null, null);
            return;
        }

        if (v == mPhonebtn) {
            if (ZuniUtils.hasPermission(ActivityCartDetails.this, PERMISSION[0]) == PermissionChecker.PERMISSION_GRANTED)
                SystemIntents.makeCall(this, ZuniConstants.HELPLINE_NUMBER);
//            else
//                ActivityCartDetails.this.requestPermissions(new String[]{PERMISSION[0]}, 1);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPhonebtn.performClick();
            }
        }
    }

    public void updateList(boolean b) {
        new AsynctaskGetCartDetails(ActivityCartDetails.this, false).execute();
        //mWaveRefreshView.setRefreshing(b);
    }
}
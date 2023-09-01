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

package sa.growonline.footprint.adapter;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterHomeProduct extends BaseAdapter implements OnClickListener {
    private FragmentActivity mActivity;
    private ArrayList<BeanProductForCategory> mProducts;
    private LayoutInflater mLayoutInflator;

    public AdapterHomeProduct(FragmentActivity activity, ArrayList<BeanProductForCategory> products) {
        this.mActivity = activity;
        mProducts = products;
        mLayoutInflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View convertView = v;
        if (convertView == null)
            convertView = mLayoutInflator.inflate(R.layout.adapter_material_product, parent, false);

        ImageView mProductImageView = (ImageView) convertView.findViewById(R.id.materail_product_img);
        ImageView mSoldImageView = (ImageView) convertView.findViewById(R.id.sold_out_imageview);
        TextView mProductTitleView = (TextView) convertView.findViewById(R.id.materail_product_name_txtview);
        TextView mProductPriceView = (TextView) convertView.findViewById(R.id.materail_product_price_txtview);

        ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse(mProducts.get(position).getImageModel().getThumbImageUrl()), mProductImageView, null);
        mProductPriceView.setText(Html.fromHtml("<small>PKR  </small><big>" + mProducts.get(position).getProductPrice().getPrice().replace("PKR", "") + ".00</big>"));
        mProductTitleView.setText(mProducts.get(position).getProductName());

        if (v == null) {
            ZuniUtils.applyAppFont(mProductPriceView);
            ZuniUtils.applyAppFont(mProductTitleView);
        }
        if (mProducts.get(position).getIsSoldOut().equalsIgnoreCase("true")) {
            mSoldImageView.setVisibility(View.VISIBLE);
        } else {
            mSoldImageView.setVisibility(View.GONE);
        }

        convertView.setTag(position);
        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int pos = (Integer) v.getTag();
        Intent intent = new Intent(mActivity, ActivityProductDetail.class);

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(mProducts, new TypeToken<ArrayList<BeanProductForCategory>>() {
        }.getType());

        JsonArray jsonArray = element.getAsJsonArray();

        SharedPreferences.Editor editor = ZuniApplication.getmAppPrefEditor();

        editor.putString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, jsonArray.toString());
        editor.putString(ActivityProductDetail.TOTAL_PRODUCTS_KEY, mProducts.size() + "");
        editor.putString(ActivityProductDetail.POSITION_KEY, pos + "");

        editor.commit();
        mActivity.startActivity(intent);
    }
}
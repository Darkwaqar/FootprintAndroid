package sa.growonline.footprint.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class AdapterProductList extends BaseAdapter implements OnClickListener {

    private List<BeanProductForCategory> mList;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;

    public AdapterProductList(Activity activityProductList,
                              List<BeanProductForCategory> mAllProductsList2) {
        this.mActivity = activityProductList;
        this.mList = mAllProductsList2;
        this.mLayoutInflater = (LayoutInflater) activityProductList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View convertView = v;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_product_list, null);
        }

        ImageView mBackgroundImageView = (ImageView) convertView.findViewById(R.id.adapter_product_imageview);
        TextView mTitleTextView = (TextView) convertView.findViewById(R.id.adapter_product_name);
        TextView mPriceTextView = (TextView) convertView.findViewById(R.id.adapter_product_price);
        ProgressBar mProgressBar = (ProgressBar) convertView.findViewById(R.id.adapter_product_progress_bar);
        ImageView mSoldImageView = (ImageView) convertView.findViewById(R.id.sold_out_imageview);


        convertView.setTag(position);
        convertView.setOnClickListener(this);

        if (v == null) {
            ZuniUtils.applyAppFont(mTitleTextView);
            ZuniUtils.applyAppFont(mPriceTextView);
        }

        setContentView(mList.get(position).getImageModel().getThumbImageUrl(), mBackgroundImageView, mProgressBar);

        mTitleTextView.setText(mList.get(position).getProductName());
        mPriceTextView.setText(Html.fromHtml("<small>PKR  </small><big>" + mList.get(position).getProductPrice().getPrice().replace("PKR", "") + ".00</big>"));
        try
        {
            if (mList.get(position).getIsSoldOut().equalsIgnoreCase("true"))
            {
                mSoldImageView.setVisibility(View.VISIBLE);
            }
            else
            {
                mSoldImageView.setVisibility(View.GONE);
            }
        } catch (Exception e) //!-- In some cases mList.get(position).getStockAvailability() is == null
        {
            mSoldImageView.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
        return convertView;
    }

    public static String ADAPTER_PRODUCT_LIST_TAG = "ADAPTER_PRODUCT_LIST_TAG";

    private void setContentView(String url, ImageView mFirstImageView, ProgressBar progressBar) {
        ZuniApplication.getmCacheManager().loadImageWithTag(Uri.parse(url), mFirstImageView, progressBar, ADAPTER_PRODUCT_LIST_TAG);
    }

    public void notifyListChanged(List<BeanProductForCategory> mAllProductsList2) {
        this.mList = mAllProductsList2;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int tag = (Integer) v.getTag();
        Intent intent = new Intent(mActivity, ActivityProductDetail.class);

        SharedPreferences.Editor editor = ZuniApplication.getmAppPrefEditor();

        editor.putString(ActivityProductDetail.TOTAL_PRODUCTS_KEY, mList.size() + "");
        editor.putString(ActivityProductDetail.POSITION_KEY, tag + "");
        editor.putString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, new Gson().toJson(mList, ArrayList.class));
        editor.commit();
        mActivity.startActivity(intent);
    }
}
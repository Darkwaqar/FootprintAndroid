package sa.growonline.footprint.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.growonline.footprint.view.GridRecyclerView;

public class AdapterProductListRecycler extends RecyclerView.Adapter<AdapterProductListRecycler.ViewHolder> implements View.OnClickListener {

    private final Activity mActivity;
    private  List<BeanProductForCategory> mList;
    private final LayoutInflater mLayoutInflater;

    public static final int totalSpan = 75;
    private int currentSpan;
    private GridRecyclerView  mGridRecyclerView;
    private boolean mShouldShowPrice = true;

    public AdapterProductListRecycler(GridRecyclerView view, Activity activityProductList,
                                      List<BeanProductForCategory> mAllProductsList2) {
        this.mActivity = activityProductList;
        this.mList = mAllProductsList2;
        this.mLayoutInflater = (LayoutInflater) activityProductList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        this.mGridRecyclerView = view;
        currentSpan = 37;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.adapter_product_list, parent, false);
        return new ViewHolder(v);
    }

    private void setContentView(String url, ImageView mFirstImageView, ProgressBar progressBar) {
        String ADAPTER_PRODUCT_LIST_TAG = "ADAPTER_PRODUCT_LIST_TAG";
        ZuniApplication.getmCacheManager().loadImageWithTag(Uri.parse(url), mFirstImageView, progressBar, ADAPTER_PRODUCT_LIST_TAG);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setContentView(mList.get(position).getImageModel().getThumbImageUrl(), holder.mBackgroundImageView, holder.mProgressBar);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        holder.mTitleTextView.setText(mList.get(position).getProductName());


        if (mShouldShowPrice)
            holder.mPriceTextView.setVisibility(View.VISIBLE);
        else
            holder.mPriceTextView.setVisibility(View.GONE);

        holder.mPriceTextView.setText(Html.fromHtml("<small>PKR  </small><big>" + mList.get(position).getProductPrice().getPrice().replace("PKR", "") + ".00</big>"));
        try {
            if (mList.get(position).getIsSoldOut().equalsIgnoreCase("true")) {
                holder.mSoldImageView.setVisibility(View.VISIBLE);
            } else {
                holder.mSoldImageView.setVisibility(View.GONE);
            }
        } catch (Exception e)
        {
            holder.mSoldImageView.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }
    public void notifyListChanged(List<BeanProductForCategory> mAllProductsList2) {
        this.mList = mAllProductsList2;
        notifyDataSetChanged();
    }

    public GridLayoutManager.SpanSizeLookup getScalableSpanSizeLookUp() {
        return scalableSpanSizeLookUp;
    }

    private int calculateRange() {
        int start = ((GridLayoutManager) mGridRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        int end = ((GridLayoutManager) mGridRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
        if (start < 0)
            start = 0;
        if (end < 0)
            end = 0;
        return end - start;
    }

    private GridLayoutManager.SpanSizeLookup scalableSpanSizeLookUp = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            return getCurrentSpan();
        }
    };


    public int getCurrentSpan() {
        return currentSpan;
    }

    private void setCurrentSpan(int span) {
        this.currentSpan = span;

    }

    private void delayedNotify(final int pos, final int range) {
        mGridRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeChanged(pos - range > 0 ? pos - range : 0, range * 2 < getItemCount() ? range * 2 : range);
            }
        }, 5);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View view) {

        int tag = (Integer) view.getTag();
        Intent intent = new Intent(mActivity, ActivityProductDetail.class);
        SharedPreferences.Editor editor = ZuniApplication.getmAppPrefEditor();
        editor.putString(ActivityProductDetail.TOTAL_PRODUCTS_KEY, mList.size() + "");
        editor.putString(ActivityProductDetail.POSITION_KEY, tag + "");
        editor.commit();
        mActivity.startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleTextView;
        private final ImageView mBackgroundImageView;
        private final TextView mPriceTextView;
        private final ImageView mSoldImageView;
        private final ProgressBar mProgressBar;

        ViewHolder(View convertView) {
            super(convertView);

            mBackgroundImageView = (ImageView) convertView.findViewById(R.id.adapter_product_imageview);
            mTitleTextView = (TextView) convertView.findViewById(R.id.adapter_product_name);
            mPriceTextView = (TextView) convertView.findViewById(R.id.adapter_product_price);
            mProgressBar = (ProgressBar) convertView.findViewById(R.id.adapter_product_progress_bar);
            mSoldImageView = (ImageView) convertView.findViewById(R.id.sold_out_imageview);

            ZuniUtils.applyAppFont(mTitleTextView);
            ZuniUtils.applyAppFont(mPriceTextView);
        }
    }

    public void changeSpan(int span)
    {
        mShouldShowPrice = span != 25;
        setCurrentSpan(span);
        delayedNotify(((GridLayoutManager) mGridRecyclerView.getLayoutManager()).findFirstVisibleItemPosition(), calculateRange());
    }
}

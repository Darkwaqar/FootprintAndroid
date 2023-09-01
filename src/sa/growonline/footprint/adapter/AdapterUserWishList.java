
package sa.growonline.footprint.adapter;

import java.util.ArrayList;

import sa.growonline.footprint.ActivitySingleGroupProduct;
import sa.growonline.footprint.ActivitySingleProduct;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.asynctask.AsyncTaskDeleteProductFromCart;
import sa.growonline.footprint.bean.BeanWishListModel;
import sa.growonline.footprint.utils.ZuniConstants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterUserWishList extends BaseAdapter implements OnClickListener {
    private Activity mActivity;
    private ArrayList<BeanWishListModel> mWishListModels;
    private LayoutInflater mLayoutInflator;

    public AdapterUserWishList(Activity activity, ArrayList<BeanWishListModel> wishListModels) {
        this.mActivity = activity;
        this.mWishListModels = wishListModels;
        this.mLayoutInflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mWishListModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mWishListModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflator.inflate(R.layout.adapter_wish_list, parent, false);

        ImageView mProductImageView = (ImageView) convertView.findViewById(R.id.adapter_wishlist_bottom_layout_product_imgview);
        TextView mProductTitleView = (TextView) convertView.findViewById(R.id.adapter_wishlist_bottom_layout_product_title);
        TextView mProductPriceView = (TextView) convertView.findViewById(R.id.adapter_wishlist_bottom_layout_product_price_val);
        AppCompatButton mViewCompatButton = (AppCompatButton) convertView.findViewById(R.id.adapter_wishlist_bottom_layout_view_textview);
        AppCompatButton mDeleteFromWishListAppCompatButton = (AppCompatButton) convertView.findViewById(R.id.adapter_wishlist_bottom_layout_delete_textview);

        mProductTitleView.setText(mWishListModels.get(position).getProductName());
//		mProductPriceView.setText(mWishListModels.get(position).getUnitPrice().replace("PKR.","PKR") + ".00");
        mProductPriceView.setText(String.format("%s.00", mWishListModels.get(position).getUnitPrice().replace("PKR.", "PKR")));

        ZuniApplication.getmCacheManager().loadImage(Uri.parse(mWishListModels.get(position).getPicture().getThumbImageUrl()), mProductImageView, null);
        mViewCompatButton.setTag(position);
        mViewCompatButton.setOnClickListener(this);

        mDeleteFromWishListAppCompatButton.setTag(position);
        mDeleteFromWishListAppCompatButton.setOnClickListener(this);

        return convertView;
    }

    public void notifyListChanged(ArrayList<BeanWishListModel> mWishListModels2) {
        mWishListModels = mWishListModels2;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int pos = (Integer) v.getTag();
        switch (v.getId()) {

            case R.id.adapter_wishlist_bottom_layout_delete_textview:
                new AsyncTaskDeleteProductFromCart(mActivity, mWishListModels.get(pos).getProductId(), mWishListModels.get(pos).getId()).execute();
                break;

            case R.id.adapter_wishlist_bottom_layout_view_textview:

                if (mWishListModels.get(pos).getProductType().equalsIgnoreCase(ZuniConstants.PRODUCT_TYPE_SIMPLE)) {
                    Intent intent = new Intent(mActivity, ActivitySingleProduct.class);
                    ZuniApplication.getmAppPrefEditor().putString("singleProductId", mWishListModels.get(pos).getProductId()).commit();
                    mActivity.startActivity(intent);
                } else {
                    Intent intent = new Intent(mActivity, ActivitySingleGroupProduct.class);
                    ZuniApplication.getmAppPrefEditor().putString("singleProductId", mWishListModels.get(pos).getProductId()).commit();
                    mActivity.startActivity(intent);
                }
                break;
        }
    }
}
package sa.growonline.footprint.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityBrand;
import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.ActivityShopTheLook;
import sa.growonline.footprint.ActivitySubCategory;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.DialoguePlus;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.parallax.lib.ParallaxBaseAdapter;
import sa.parallax.lib.ParallaxListView;

public class AdapterDashboard extends ParallaxBaseAdapter implements ParallaxListView.TapListener {
    private final Activity mActivity;
    private final ArrayList<BeanGetAllCategory> mCategoryList;
    private final LayoutInflater mLayoutInflator;

    public AdapterDashboard(Activity activityDashboard, ArrayList<BeanGetAllCategory> categoryList) {
        this.mActivity = activityDashboard;
        this.mCategoryList = categoryList;
        mLayoutInflator = LayoutInflater.from(mActivity);
    }

    @Override
    public int getParallaxViewId(int pos) {
        return R.id.parallax_imageview;
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCategoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        if (v == null) v = mLayoutInflator.inflate(R.layout.adapter_dashboard, viewGroup, false);

        ImageView imageView = (ImageView) v.findViewById(R.id.parallax_imageview);
        TextView txtView = (TextView) v.findViewById(R.id.adapter_category_name);

        txtView.setText(mCategoryList.get(i).getmCategoryName());
        if (mCategoryList.get(i).getCategoryPictureUrl() != null)
            ZuniApplication.getmCacheManager().loadImage(Uri.parse(mCategoryList.get(i).getCategoryPictureUrl()), imageView, null);
        else
            imageView.setImageResource(mCategoryList.get(i).getmResourceId());
        v.setTag(i);
        return v;
    }

    @Override
    public void onTap(ParallaxListView parallaxListView, int pos, View view) {

        Intent intent;

        if (mCategoryList.get(pos).getmCategoryName().toLowerCase().startsWith(ZuniConstants.SHOP_THE_LOOK_ID)) {
            intent = new Intent(mActivity, ActivityShopTheLook.class);
        } else if (mCategoryList.get(pos).getmCategoryId().equalsIgnoreCase(ZuniConstants.BRAND_ID)) {
            ActivityBrand.mFor = "br";
            intent = new Intent(mActivity, ActivityBrand.class);
        } else if (mCategoryList.get(pos).getmCategoryId().equalsIgnoreCase(ZuniConstants.BESPOKE_ID)) {
            ActivityBrand.mFor = "bs";
            intent = new Intent(mActivity, ActivityBrand.class);
        } else if (mCategoryList.get(pos).getmCategoryId().equalsIgnoreCase(ZuniConstants.BESPOKE_ID)) {
            intent = new Intent(mActivity, ActivityBrand.class);
        } else if (mCategoryList.get(pos).getmCategoryId().equalsIgnoreCase("-1")) {
            new DialoguePlus(mActivity).showDialog();
            return;
        } else {
            intent = new Intent(mActivity, ActivityProductList.class);
        }

        if (mCategoryList.get(pos).getSubCategories() != null && mCategoryList.get(pos).getSubCategories().size() > 0) {
            intent = new Intent(mActivity, ActivitySubCategory.class);
            ActivitySubCategory.mSubCategoryList = mCategoryList.get(pos).getSubCategories();
        }
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.POST_LIST_JSON, "").commit();
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_ID_PREF_KEY, mCategoryList.get(pos).getmCategoryId()).commit();
        mActivity.startActivity(intent);
    }
}
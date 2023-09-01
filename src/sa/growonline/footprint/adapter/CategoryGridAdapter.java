package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.ActivitySubCategory;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.ZuniConstants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CategoryGridAdapter extends BaseAdapter implements OnClickListener {
    private List<BeanGetAllCategory> mList;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;

    public CategoryGridAdapter(Activity context, List<BeanGetAllCategory> mBeanGetAllCategories) {
        this.mList = mBeanGetAllCategories;
        this.mActivity = context;
        mLayoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.adapter_category, parent);

        ImageView mImageView = (ImageView) convertView.findViewById(R.id.adapter_product_imageview);
        TextView mTitleView = (TextView) convertView.findViewById(R.id.adapter_product_name);
        ProgressBar mProgressView = (ProgressBar) convertView.findViewById(R.id.adapter_product_progress_bar);

        mTitleView.setText(mList.get(position).getmCategoryName());

        convertView.setTag(position);
        convertView.setOnClickListener(this);

        ZuniApplication.getmCacheManager().loadImage(Uri.parse(mList.get(position).getImageModel().getThumbImageUrl()), mImageView, mProgressView);

        return convertView;
    }

    public void notifyListSetChanged(List<BeanGetAllCategory> mBeanGetAllCategories) {
        this.mList = mBeanGetAllCategories;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        BeanGetAllCategory beanGetAllCategory = mList.get((Integer) v.getTag());

        Editor editor = ZuniApplication.getmAppPrefEditor();
        editor.putString(ZuniConstants.SELECTED_CATEGORY_NAME, beanGetAllCategory.getmCategoryName());
        editor.commit();

        if (beanGetAllCategory.getmHasSubCategory().equalsIgnoreCase("true")) {
            Intent intent = new Intent(mActivity, ActivitySubCategory.class);
            intent.putExtra("categoryid", beanGetAllCategory.getmCategoryId());
            mActivity.startActivity(intent);
        } else {

            Intent intent = new Intent(mActivity, ActivityProductList.class);
            ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_ID_PREF_KEY, beanGetAllCategory.getmCategoryId()).commit();
            mActivity.startActivity(intent);
        }
    }
}
package sa.growonline.footprint.adapter;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.ActivitySubCategory;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterHomeCategory extends BaseAdapter implements OnClickListener 
{
	private FragmentActivity mActivity;
	private ArrayList<BeanGetAllCategory> mSubCategories;
	private LayoutInflater mLayoutInflator;

	public AdapterHomeCategory(FragmentActivity activity, ArrayList<BeanGetAllCategory> subCategories)
	{
		this.mActivity = activity;
		this.mSubCategories = subCategories;
		mLayoutInflator = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{
		return mSubCategories.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mSubCategories.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent)
	{
        View convertView = v;
		if (convertView == null) convertView = mLayoutInflator.inflate(R.layout.list_item_card_big, parent, false);

		TextView mNameTextView = (TextView) convertView.findViewById(R.id.adapter_material_category_txtview);
		ImageView mImageView = (ImageView) convertView.findViewById(R.id.adapter_material_category_imgview);

		ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse(mSubCategories.get(position).getCategoryPictureUrl()), mImageView, null);

        if (v == null)
            ZuniUtils.applyAppFont(mNameTextView);
		
		mNameTextView.setText(mSubCategories.get(position).getmCategoryName());
		convertView.setTag(position);
		convertView.setOnClickListener(this);
		return convertView;
	}

	@Override
	public void onClick(View v)
	{
		int pos = (Integer) v.getTag();
		Intent intent;
		BeanGetAllCategory beanGetAllCategory = mSubCategories.get(pos);

		if (beanGetAllCategory.getmHasSubCategory().equalsIgnoreCase("true"))
		{
			intent = new Intent(mActivity, ActivitySubCategory.class);
			intent.putExtra("categoryid", beanGetAllCategory.getmCategoryId());
		}
		else
		{
			intent = new Intent(mActivity, ActivityProductList.class);

            ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.POST_LIST_JSON, "").commit();
            ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_ID_PREF_KEY, beanGetAllCategory.getmCategoryId()).commit();
		}
		mActivity.startActivity(intent);
	}
}
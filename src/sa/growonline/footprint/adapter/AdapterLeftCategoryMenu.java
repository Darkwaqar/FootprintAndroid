package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.ZuniConstants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterLeftCategoryMenu extends BaseAdapter implements OnClickListener
{
	private Activity mActivity;
	private List<BeanGetAllCategory> mList;
	private LayoutInflater mInflator;
	private Typeface mTrebuchetMsTypeFace;

	public AdapterLeftCategoryMenu(Activity activity, List<BeanGetAllCategory> mList)
	{
		this.mActivity = activity;
		this.mList = mList;
		this.mInflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mTrebuchetMsTypeFace = Typeface.createFromAsset(activity.getAssets(), "fonts/montserrat_regular.ttf");
	}
	
	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		if (view == null) view = mInflator.inflate(R.layout.adapter_left_category_menu, parent);
		
		TextView textView = (TextView) view.findViewById(R.id.adapter_left_textview);
		ImageView imageView = (ImageView) view.findViewById(R.id.adapter_left_imgview);
		
		textView.setTypeface(mTrebuchetMsTypeFace);
		textView.setText(mList.get(position).getmCategoryName().toUpperCase());
		
		setResourceImg(imageView, position);
		view.setTag(position);
		view.setOnClickListener(this);
		
		return view;
	}

	private void setResourceImg(ImageView imageView, int position)
	{
		if (mList.get(position).getmCategoryId().equalsIgnoreCase("-1"))
		{
			//!-- Home Button
			imageView.setImageResource(R.drawable.nav_category_home);
		}
		else if (mList.get(position).getmCategoryId().equalsIgnoreCase("-2"))
		{
			//!-- News Letter Button
			imageView.setImageResource(R.drawable.nav_category_msg);
		}
		else if (mList.get(position).getmCategoryId().equalsIgnoreCase("-3"))
		{
			//!-- Search Button
			imageView.setImageResource(R.drawable.nav_category_search);
		}
		else if (mList.get(position).getmCategoryId().equalsIgnoreCase("-4"))
		{
			//!-- Scan code Button
			imageView.setImageResource(R.drawable.nav_category_home_qr_code);
		}
	}

	public void notifyCategoryChanged(List<BeanGetAllCategory> mCategoryList)
	{
		mList = mCategoryList;
		notifyDataSetChanged();
	}

	@Override
	public void onClick(View v)
	{
		int pos = (Integer) v.getTag();
		
		if (mList.get(pos).getSubCategories().size() == 0)
		{
			//!---- Goto products
			Intent intent = new Intent(mActivity, ActivityProductList.class);
            ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_ID_PREF_KEY, mList.get(pos).getmCategoryId()).commit();
			mActivity.startActivity(intent);
		}
	}
}
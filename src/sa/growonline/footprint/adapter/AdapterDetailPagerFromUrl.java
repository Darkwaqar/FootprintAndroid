package sa.growonline.footprint.adapter;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityImageDetail;
import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanServerImage;
import sa.growonline.footprint.fragment.FragmentDetailGroup;
import sa.growonline.footprint.fragment.FragmentDetailParent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AdapterDetailPagerFromUrl extends PagerAdapter implements OnClickListener
{
	private FragmentActivity mActivity;
	private ArrayList<BeanServerImage> mList;
	private LayoutInflater mInflator;
	private Object mFromWhere;
    public AdapterDetailPagerFromUrl(FragmentActivity activity, ArrayList<BeanServerImage> getProductImageModel, Object mFromWhere)
	{
		this.mActivity = activity;
		this.mList = getProductImageModel;
		mInflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mFromWhere = mFromWhere;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object group)
	{
		return view == group;
	}
	@Override
	public int getItemPosition(Object object) {
	
		return POSITION_NONE;
	}
	
	@Override
	public void destroyItem(ViewGroup collection, int position, Object view)
	{
		collection.removeView((RelativeLayout) view);
	}

	@Override
	public void finishUpdate(View arg0)
	{
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1)
	{
	}

	@Override
	public Parcelable saveState()
	{
		return null;
	}

	@Override
	public void startUpdate(View arg0)
	{
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		View view = mInflator.inflate(R.layout.adapter_cviewpager_detail, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.adapter_cviewpager_detail_imgview);
		ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.adapter_cviewpager_detail_progress_bar);

        if (mActivity instanceof ActivityProductDetail)
		    ZuniApplication.getmCacheManager().loadImage(Uri.parse(mList.get(position).getmFullSizeImageUrl()), imageView, progressBar);
		else
            ZuniApplication.getmCacheManager().loadImage(Uri.parse(mList.get(position).getmFullSizeImageUrl()), imageView, progressBar);

		view.setTag(position);
		view.setOnClickListener(this);
		
		container.addView(view);
		return view;
	}
	
	@Override
	public void onClick(View v)
	{
		if (mFromWhere instanceof FragmentDetailParent && !((FragmentDetailParent) mFromWhere).mChildView().mIsSwipeEnabled)
		{ return; } else if (mFromWhere instanceof FragmentDetailGroup && !((FragmentDetailGroup) mFromWhere).mChildView().mIsSwipeEnabled)
        { return; }
		int pos = (Integer) v.getTag();
		Intent intent = new Intent(mActivity, ActivityImageDetail.class);
		intent.putExtra("img", mList.get(pos).getmFullSizeImageUrl());
		mActivity.startActivity(intent);
	}

}
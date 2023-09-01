package sa.growonline.footprint.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.fragment.FragmentDetailParent;
import sa.growonline.footprint.utils.ZuniConstants;

public class AdapterDetailParentViewPager extends FragmentPagerAdapter {
	private int mSize;
	private ArrayList<FragmentDetailParent> mFragmentList;
	private List<BeanProductForCategory> mProductList;

	public AdapterDetailParentViewPager(FragmentManager fm, int size, List<BeanProductForCategory> productList)
	{
		super(fm);
		this.mSize = size;
		mProductList = productList;
		setFragmentList(new ArrayList<FragmentDetailParent>());
	}

	@Override
	public Fragment getItem(int pos)
	{
		if (!hasIndex(pos))
		{
			FragmentDetailParent fragmentDetailParent = new FragmentDetailParent();
            ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.DETAILED_PRODUCT_ID, mProductList.get(pos).getmProductId()).commit();
			fragmentDetailParent.setProductId(mProductList.get(pos).getmProductId());
			getFragmentList().add(fragmentDetailParent);
		}
		return getFragmentList().get(pos);
	}
	
	private boolean hasIndex(int index)
	{
		return index < getFragmentList().size();

	}
	
	@Override
	public int getCount()
	{
		return mSize;
	}

	private ArrayList<FragmentDetailParent> getFragmentList() {
		return mFragmentList;
	}

	private void setFragmentList(ArrayList<FragmentDetailParent> mFragmentList) {
		this.mFragmentList = mFragmentList;
	}
}
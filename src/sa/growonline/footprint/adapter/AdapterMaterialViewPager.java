package sa.growonline.footprint.adapter;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.fragment.FragmentMaterialRecyclerView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AdapterMaterialViewPager extends FragmentStatePagerAdapter
{
	private List<BeanGetAllCategory> mCategoryList;
	private ArrayList<FragmentMaterialRecyclerView> mFragmentList;

	public AdapterMaterialViewPager(FragmentActivity activityHomeMaterialViewPager,List<BeanGetAllCategory> mCategoryList)
	{
		super(activityHomeMaterialViewPager.getSupportFragmentManager());
		this.mCategoryList = mCategoryList;
		mFragmentList = new ArrayList<>();
	}
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

	@Override
	public Fragment getItem(int pos)
	{
		if (!hasIndex(pos))
		{
			FragmentMaterialRecyclerView fragmentDetailParent = new FragmentMaterialRecyclerView();
			fragmentDetailParent.setProductId(pos);
			mFragmentList.add(fragmentDetailParent);
		}
		return mFragmentList.get(pos);
	}
	
	private boolean hasIndex(int index)
	{
		return index < mFragmentList.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position)
	{
		return mCategoryList.get(position).getmCategoryName();
	}
	
	@Override
	public int getCount()
	{
		return mCategoryList.size();
	}

}
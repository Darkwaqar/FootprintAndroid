package sa.growonline.footprint;

import java.util.List;

import sa.growonline.footprint.adapter.CategoryGridAdapter;
import sa.growonline.footprint.asynctask.AsyncTaskGetAllCategories;
import sa.growonline.footprint.base.BaseSlidingActivity;
import sa.growonline.footprint.base.BaseTopBar;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.fragment.FragmentCategoryMenu;
import sa.growonline.footprint.fragment.FragmentProfileMenu;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.growonline.footprint.view.DynamicGridView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseSlidingActivity
{
	private DynamicGridView mRecyclerView;
	private List<BeanGetAllCategory> mAllCategoryBean;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setEnableSwipeGesture(false);
		setFragmentToMenu(new FragmentCategoryMenu(), new FragmentProfileMenu());
		
		new BaseTopBar(this);
		
		InitUI();
		
		//!---- Updating beans
		if (mAllCategoryBean == null)
			new AsyncTaskGetAllCategories(this, MainActivity.this).execute();
		else
			updateCategories(mAllCategoryBean);
	}

	private void InitUI()
	{
		mRecyclerView = (DynamicGridView) findViewById(R.id.activity_category_recycler);
		mRecyclerView.setExpanded(true);
		ZuniUtils.LogEvent(mRecyclerView + "");
	}

	public void updateCategories(List<BeanGetAllCategory> mBeanGetAllCategories)
	{
		if (mBeanGetAllCategories == null)
			return;
		
		this.mAllCategoryBean = mBeanGetAllCategories;
		if (mBeanGetAllCategories.size() != 0)
		{
			if (mRecyclerView.getAdapter() == null)
			{
				final CategoryGridAdapter adapter = new CategoryGridAdapter(this, mBeanGetAllCategories);
				
				mRecyclerView.setAdapter(adapter);
			}
			else
			{
				((CategoryGridAdapter) mRecyclerView.getAdapter()).notifyListSetChanged(mBeanGetAllCategories);
			}
		}
		else
			Toast.makeText(this, R.string.CategroiesNotFound, Toast.LENGTH_LONG).show();
		
	}
}
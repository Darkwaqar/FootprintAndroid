package sa.growonline.footprint;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.fragment.FragmentDetailGroup;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.view.DetailActivityViewPager;

public class ActivityGroupProductDetail extends BaseActivityx
{
    public static final String POSITION_KEY = "POSITION_KEY";
    public static final String TOTAL_PRODUCTS_KEY = "TOTAL_PRODUCTS_KEY";

    private DetailActivityViewPager mHorizontalViewPager;
    private List<BeanProductForCategory> mBeanGetAllCategories;
    private int mIntialPosition;
    private int mTotalSize;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_product_details);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        removeToolbar();

        String jsonString = ZuniApplication.getmAppPreferences().getString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, "[]");

        Gson gson = new Gson();

        Type type = new TypeToken<List<BeanProductForCategory>>() {
        }.getType();
        this.mBeanGetAllCategories = (List<BeanProductForCategory>) gson.fromJson(jsonString, type);
        mHorizontalViewPager = (DetailActivityViewPager) findViewById(R.id.detail_parent_viewpager);

        AdapterDetailGroupViewPager adapter = new AdapterDetailGroupViewPager(getSupportFragmentManager(), mBeanGetAllCategories.size(), mBeanGetAllCategories);
        mHorizontalViewPager.setAdapter(adapter);

        //!-- set Page according to the previous screen
        mIntialPosition = 0;
        mTotalSize = 0;
        try
        {
            mIntialPosition = Integer.valueOf(ZuniApplication.getmAppPreferences().getString(ActivityGroupProductDetail.POSITION_KEY, "0"));
            mTotalSize = Integer.valueOf(ZuniApplication.getmAppPreferences().getString(ActivityGroupProductDetail.TOTAL_PRODUCTS_KEY, "0"));
            mHorizontalViewPager.setOffscreenPageLimit(mTotalSize);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHorizontalViewPager.setCurrentItem(mIntialPosition, false);

            }
        }, 500);
    }


    public void setViewPagerSwipe(boolean b) {
        mHorizontalViewPager.setViewPagerSwipe(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
	}

    private class AdapterDetailGroupViewPager extends FragmentStatePagerAdapter
    {
        private int mSize;
        private ArrayList<FragmentDetailGroup> mFragmentList;
        private List<BeanProductForCategory> mProductList;

        public AdapterDetailGroupViewPager(FragmentManager fm, int size, List<BeanProductForCategory> productList)
        {
            super(fm);

            this.mSize = size;
            mProductList = productList;
            setmFragmentList(new ArrayList<FragmentDetailGroup>());
        }

        @Override
        public Fragment getItem(int pos)
        {
            if (!hasIndex(pos))
            {
                FragmentDetailGroup fragmentDetailParent = new FragmentDetailGroup();
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.DETAILED_PRODUCT_ID, mProductList.get(pos).getmProductId()).commit();
                fragmentDetailParent.setProductId(mProductList.get(pos).getmProductId());
                getmFragmentList().add(fragmentDetailParent);
            }
            return getmFragmentList().get(pos);
        }

        private boolean hasIndex(int index)
        {
            if(index < getmFragmentList().size())
                return true;

            return false;
        }

        @Override
        public int getCount()
        {
            return mSize;
        }

        public ArrayList<FragmentDetailGroup> getmFragmentList() {
            return mFragmentList;
        }

        public void setmFragmentList(ArrayList<FragmentDetailGroup> mFragmentList) {
            this.mFragmentList = mFragmentList;
        }
    }
}
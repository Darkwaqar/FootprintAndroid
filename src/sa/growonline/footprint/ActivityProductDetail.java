package sa.growonline.footprint;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import sa.growonline.footprint.adapter.AdapterDetailParentViewPager;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.view.DetailActivityViewPager;

public class ActivityProductDetail extends BaseActivityx {
    public static final String POSITION_KEY = "POSITION_KEY";
    public static final String TOTAL_PRODUCTS_KEY = "TOTAL_PRODUCTS_KEY";

    private DetailActivityViewPager mHorizontalViewPager;
    private List<BeanProductForCategory> mBeanGetAllCategories;
    private int mIntialPosition;
    private int mTotalSize;
    private TextView mTitleTextView;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        removeToolbar();


        String jsonString = ZuniApplication.getmAppPreferences().getString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, "[]");

        Gson gson = new Gson();

        Type type = new TypeToken<List<BeanProductForCategory>>() {
        }.getType();

        this.mBeanGetAllCategories = (List<BeanProductForCategory>) gson.fromJson(jsonString, type);
        mHorizontalViewPager = (DetailActivityViewPager) findViewById(R.id.detail_parent_viewpager);

        AdapterDetailParentViewPager adapter = new AdapterDetailParentViewPager(getSupportFragmentManager(), mBeanGetAllCategories.size(), mBeanGetAllCategories);
        mHorizontalViewPager.setAdapter(adapter);

        //!-- set Page according to the previous screen
        mIntialPosition = 0;
        mTotalSize = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    mIntialPosition = Integer.valueOf(ZuniApplication.getmAppPreferences().getString(ActivityProductDetail.POSITION_KEY, "0"));
                    mTotalSize = Integer.valueOf(ZuniApplication.getmAppPreferences().getString(ActivityProductDetail.TOTAL_PRODUCTS_KEY, "0"));
                    mHorizontalViewPager.setOffscreenPageLimit(mTotalSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    public void getmTitleTextView(String t) {
        updateTitle(mBeanGetAllCategories.get(mHorizontalViewPager.getCurrentItem()).getProductName());
    }


}
package sa.growonline.footprint;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import sa.growonline.footprint.adapter.AdapterShopTheLook;
import sa.growonline.footprint.asynctask.AsynctaskGetCategoryDetail;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.bean.CategoryDetailBean;
import sa.growonline.footprint.utils.ZuniConstants;

public class ActivityShopTheLook extends BaseActivityx {

    private String mCategoryId;

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mCategoryId = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CATEGORY_ID_PREF_KEY, "");
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_recyclerview_1st);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.activity_recyclerview_2nd);
//        setupToolBar();

        removeToolbar();
        new AsynctaskGetCategoryDetail(ActivityShopTheLook.this, mCategoryId, null).execute();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        StaggeredGridLayoutManager sec = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        sec.setReverseLayout(true);
        mRecyclerView2.setLayoutManager(sec);
    }

    public void onBeanObtained(CategoryDetailBean mCategoryDetailBean) {

        updateLeftNavigation();

        if (mCategoryDetailBean == null) return;

        ArrayList<BeanProductForCategory> mainArray = mCategoryDetailBean.getProducts();
        ArrayList<BeanProductForCategory> firstArray = new ArrayList<BeanProductForCategory>();
        ArrayList<BeanProductForCategory> secondArray = new ArrayList<BeanProductForCategory>();

        for (int i = 0; i < mainArray.size(); i++)
            if (i % 2 == 0)
                firstArray.add(mainArray.get(i));
            else
                secondArray.add(mainArray.get(i));

        AdapterShopTheLook adapter = new AdapterShopTheLook(ActivityShopTheLook.this, firstArray);
        mRecyclerView.setAdapter(adapter);

        AdapterShopTheLook adapter1 = new AdapterShopTheLook(ActivityShopTheLook.this, secondArray);
        mRecyclerView2.setAdapter(adapter1);
    }
}

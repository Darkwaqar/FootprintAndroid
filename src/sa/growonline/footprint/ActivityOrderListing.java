package sa.growonline.footprint;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import sa.growonline.footprint.adapter.AdapterOrderList;
import sa.growonline.footprint.asynctask.AsynctaskGetOrderList;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanOrderListing;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityOrderListing extends BaseActivityx implements View.OnClickListener {


    private ListView mGridView;
    /*private TextView mTitleTextView;
    private FragmentManager mFragmentManagerObject;
    private FragmentCategoryMenu mFragmentCategoryMenu;
    private FragmentProfileMenu mFragmentUserProfileMenu;*/
    private View mBannerView;
   /* private DrawerLayout mDrawerLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_listing);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
        removeToolbar();
       /* mFragmentManagerObject = getSupportFragmentManager();

        //!-- Left Category Menu
        if (mFragmentCategoryMenu == null)
            mFragmentCategoryMenu = new FragmentCategoryMenu();

        //!-- Right Category Menu
        if (mFragmentUserProfileMenu == null)
            mFragmentUserProfileMenu = new FragmentProfileMenu();

        mFragmentManagerObject.beginTransaction().replace(R.id.left_drawer, mFragmentCategoryMenu).commitAllowingStateLoss();
        mFragmentManagerObject.beginTransaction().replace(R.id.right_drawer, mFragmentUserProfileMenu).commitAllowingStateLoss();
*/
        mBannerView = findViewById(R.id.header);
        /*ImageView mBackButton = (ImageView) findViewById(R.id.activity_home_header_backbutton);
        ImageView mRightMenuButton = (ImageView) findViewById(R.id.activity_home_header_right_nav);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFragmentCategoryMenu.setDrawer(mDrawerLayout);

        mBackButton.setOnClickListener(this);
        mRightMenuButton.setOnClickListener(this);
*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*mFragmentCategoryMenu.loadLastFetchCatrgories();*/
            }
        }, 500);

        mGridView = (ListView) findViewById(R.id.activity_order_list_gridview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       /* mTitleTextView =(TextView) findViewById(R.id.activity_home_header_logo_nav);
        ZuniUtils.applyAppFont(mTitleTextView);*/


        new AsynctaskGetOrderList(ActivityOrderListing.this, true).execute();
    }

    public void onListObtained(BeanOrderListing mOrderListModel) {
        if (mOrderListModel == null) return;

        if (mOrderListModel.getOrders().size() == 0) {
            ZuniUtils.showMsgDialog(ActivityOrderListing.this, "", "No orders found...!", null, null);
            return;
        }

        if (mGridView.getAdapter() != null) {
            ((AdapterOrderList) mGridView.getAdapter()).updateData(mOrderListModel.getOrders());
        } else {
            AdapterOrderList adapterOrderList = new AdapterOrderList(ActivityOrderListing.this, mOrderListModel.getOrders());
            mGridView.setAdapter(adapterOrderList);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.activity_home_header_backbutton:
             *//*   ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.LEFT);*//*
               finish();
                break;

            case R.id.activity_home_header_right_nav:
                ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.RIGHT);
                break;*/

        }
    }
}

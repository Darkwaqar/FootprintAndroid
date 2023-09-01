package sa.growonline.footprint;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import sa.growonline.footprint.asynctask.AsynctaskGetOrderDetails;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.OrderDetail;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityOrderDetail extends BaseActivityx implements  View.OnClickListener
{
    public static String mId;

    private TextView mOrderIdTextView;
    private TextView mOrderDateTimeTextView;
    private TextView mOrderTotalTextView;
    private TextView mOrderStatusTextView;
    private TextView mOrderNameTextView;
    private TextView mOrderAddrTextView;
    private TextView mOrderPaymentTextView;
    private TextView mOrderShippingTextView;
    private LinearLayout mSkuLayout;
    private LayoutInflater mInflator;
    /*private TextView mTitleTextView;
    private FragmentManager mFragmentManagerObject;
    private FragmentCategoryMenu mFragmentCategoryMenu;
    private FragmentProfileMenu mFragmentUserProfileMenu;*/
    private View mBannerView;
   /* private DrawerLayout mDrawerLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();

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
        InitUI();

        /*mFragmentCategoryMenu.setDrawer(mDrawerLayout);*/
        new AsynctaskGetOrderDetails(ActivityOrderDetail.this, mId, true).execute();
    }

    private void InitUI()
    {
        mOrderIdTextView = (TextView) findViewById(R.id.txtview_order_id);
        mOrderDateTimeTextView = (TextView) findViewById(R.id.activity_order_date_time);
        mOrderTotalTextView = (TextView) findViewById(R.id.activity_order_total);
        mOrderStatusTextView = (TextView) findViewById(R.id.activity_order_status);
        mOrderNameTextView = (TextView) findViewById(R.id.activity_order_name);
        mOrderAddrTextView = (TextView) findViewById(R.id.activity_order_addr);
        mOrderPaymentTextView = (TextView) findViewById(R.id.activity_order_payment);
        mOrderShippingTextView = (TextView) findViewById(R.id.activity_order_shipping);
        mSkuLayout = (LinearLayout) findViewById(R.id.sku_layout);
       /* mTitleTextView =(TextView) findViewById(R.id.activity_home_header_logo_nav);*/

        mBannerView = findViewById(R.id.header);
      /*  ImageView mBackButton = (ImageView) findViewById(R.id.activity_home_header_backbutton);
        ImageView mRightMenuButton = (ImageView) findViewById(R.id.activity_home_header_right_nav);*/
      /*  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
              /*  mFragmentCategoryMenu.loadLastFetchCatrgories();*/
            }
        }, 500);

     /*   mBackButton.setOnClickListener(this);
        mRightMenuButton.setOnClickListener(this);*/

        mInflator = LayoutInflater.from(ActivityOrderDetail.this);

       /* ZuniUtils.applyAppFont(mTitleTextView);*/
        ZuniUtils.applyAppFont(mOrderIdTextView);
        ZuniUtils.applyAppFont(mOrderDateTimeTextView);
        ZuniUtils.applyAppFont(mOrderTotalTextView);
        ZuniUtils.applyAppFont(mOrderStatusTextView);
        ZuniUtils.applyAppFont(mOrderNameTextView);
        ZuniUtils.applyAppFont(mOrderAddrTextView);
        ZuniUtils.applyAppFont(mOrderPaymentTextView);
        ZuniUtils.applyAppFont(mOrderShippingTextView);
    }

    public void onDetailReceived(OrderDetail mOrderDetail)
    {

        if (mOrderDetail == null) return;


        mOrderIdTextView.setText(String.format(getString(R.string.activity_order_sku), mOrderDetail.getId()));
        mOrderDateTimeTextView.setText(String.format(getString(R.string.activity_order_date_time), mOrderDetail.getCreatedOn()));
        mOrderTotalTextView.setText(String.format(getString(R.string.activity_order_total), mOrderDetail.getOrderTotal()));
        mOrderStatusTextView.setText(String.format(getString(R.string.activity_order_status), mOrderDetail.getOrderStatus()));
        mOrderNameTextView.setText(PreferenceManager.getDefaultSharedPreferences(ActivityOrderDetail.this).getString(ZuniConstants.USER_NAME_PREFS_KEY, ""));
        mOrderAddrTextView.setText(mOrderDetail.getShippingAddress().getAddress1());
        mOrderPaymentTextView.setText(String.format(getString(R.string.activity_order_payment), mOrderDetail.getPaymentMethod()));
        mOrderShippingTextView.setText(String.format(getString(R.string.activity_order_shipping), mOrderDetail.getShippingMethod()));

        mSkuLayout.removeAllViews();
        for (int i = 0; i < mOrderDetail.getItems().size(); i++)
        {
            View view = mInflator.inflate(R.layout.activity_order_sku, mSkuLayout, false);

            TextView mOrderSkuTextView = (TextView) view.findViewById(R.id.activity_order_sku);
            TextView mOrderProductNameTextView = (TextView) view.findViewById(R.id.activity_order_product_name);
            TextView mOrderQuantityTextView = (TextView) view.findViewById(R.id.activity_order_quantity);
            TextView mOrderPriceTextView = (TextView) view.findViewById(R.id.activity_order_price);

            mOrderSkuTextView.setText(String.format(getString(R.string.activity_order_sku), mOrderDetail.getItems().get(i).getSku()));
            mOrderQuantityTextView.setText(String.format(getString(R.string.activity_order_quantity), mOrderDetail.getItems().get(i).getQuantity()));
            mOrderProductNameTextView.setText(mOrderDetail.getItems().get(i).getProductName());
            mOrderPriceTextView.setText(mOrderDetail.getItems().get(i).getUnitPrice());

            mSkuLayout.addView(view);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
          /*  case R.id.activity_home_header_backbutton:
                finish();
                break;

            case R.id.activity_home_header_right_nav:
                ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.RIGHT);
                break;
*/
        }


    }
}
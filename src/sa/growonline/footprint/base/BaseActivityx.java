package sa.growonline.footprint.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseToolbarx.ToolBarType;
import sa.growonline.footprint.fragment.FragmentCategoryMenu;
import sa.growonline.footprint.fragment.FragmentProfileMenu;
import sa.growonline.footprint.utils.NotificationBadge;
import sa.growonline.footprint.utils.ZuniUtils;

public class BaseActivityx extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private View mActivityLayout;
    private FragmentManager mFragmentManagerObject;
    protected FragmentCategoryMenu mFragmentCategoryMenu;
    private AppBarLayout mAppBarLayout;
    private BaseToolbarx mBaseToolbarx;
    private FragmentProfileMenu mFragmentProfileMenu;
    public static int TotalQuantity;


    private ImageView mProfileImageView;
    private ImageView mMenuImageView;
    private NotificationBadge badge;
    private TextView titleTextView;

    private RelativeLayout header;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    public void setContentView(int layoutResID) {
        if (mActivityLayout == null) {
            mActivityLayout = getLayoutInflater().inflate(R.layout.base_activityx, null);
            header=(RelativeLayout)mActivityLayout.findViewById(R.id.header);
            mDrawerLayout = (DrawerLayout) mActivityLayout.findViewById(R.id.base_drawer_layout);
            mFrameLayout = (FrameLayout) mActivityLayout.findViewById(R.id.base_frame_layout);
            mAppBarLayout = (AppBarLayout) mActivityLayout.findViewById(R.id.base_appbar_layout);

            mMenuImageView = (ImageView) findViewById(R.id.activity_home_menu);
            badge = (NotificationBadge) findViewById(R.id.badge);

            mProfileImageView = (ImageView) findViewById(R.id.activity_home_profile);
            titleTextView = (TextView) findViewById(R.id.title);


            mMenuImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ZuniUtils.toggleNavigation(getmDrawerLayout(), Gravity.START);
                }
            });

            mProfileImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getBaseContext(), ActivityCartDetails.class));
                }
            });
        }



        setContentView(mActivityLayout);
        getLayoutInflater().inflate(layoutResID, mFrameLayout, true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mActivityLayout == null) {
            mActivityLayout = getLayoutInflater().inflate(R.layout.base_activityx, null);
            header=(RelativeLayout)mActivityLayout.findViewById(R.id.header);
            mDrawerLayout = (DrawerLayout) mActivityLayout.findViewById(R.id.base_drawer_layout);
            mFrameLayout = (FrameLayout) mActivityLayout.findViewById(R.id.base_frame_layout);
            mAppBarLayout = (AppBarLayout) mActivityLayout.findViewById(R.id.base_appbar_layout);
            mMenuImageView = (ImageView) mActivityLayout.findViewById(R.id.activity_home_menu);
            badge = (NotificationBadge) mActivityLayout.findViewById(R.id.badge);
            mProfileImageView = (ImageView) mActivityLayout.findViewById(R.id.activity_home_profile);
            titleTextView = (TextView) mActivityLayout.findViewById(R.id.title);

            mMenuImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ZuniUtils.toggleNavigation(getmDrawerLayout(), Gravity.START);
                }
            });

            mProfileImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getBaseContext(), ActivityCartDetails.class));
                }
            });

//            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//            TotalQuantity = prefs.getInt("total", 0);
//            badge.setNumber(TotalQuantity);
        }

        TotalQuantity=((ZuniApplication) this.getApplication()).getNumberOfItemsInCart();
        badge.setNumber(TotalQuantity);


        //!-- Navigation Menus
        mFragmentManagerObject = getSupportFragmentManager();

        if (mFragmentProfileMenu == null)
            mFragmentProfileMenu = new FragmentProfileMenu();

        //!-- Left Category Menu
        if (mFragmentCategoryMenu == null)
            mFragmentCategoryMenu = new FragmentCategoryMenu();

        mFragmentManagerObject.beginTransaction().replace(R.id.left_drawer, mFragmentCategoryMenu).commitAllowingStateLoss();
//        mFragmentManagerObject.beginTransaction().replace(R.id.right_drawer, mFragmentProfileMenu).commitAllowingStateLoss();

        mFragmentCategoryMenu.setDrawer(mDrawerLayout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentCategoryMenu.loadLastFetchCatrgories();
            }
        }, 1000);
    }

    public void updateLeftNavigation() {
        mFragmentCategoryMenu.loadLastFetchCatrgories();
    }

    public void setupToolBar() {
        mBaseToolbarx = new BaseToolbarx(BaseActivityx.this, ToolBarType.CUSTOMIZABLE);

        mBaseToolbarx.setToolBarTitle(R.drawable.topbar_logo);
        mBaseToolbarx.setNavigationProps(R.drawable.menu, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.LEFT);
            }
        });

//        ImageView mCartDetails = new ImageView(BaseActivityx.this);
//        mCartDetails.setPadding(10, 10, 15, 10);
//        mCartDetails.setLayoutParams(new Toolbar.LayoutParams(25,25));
//        mCartDetails.setImageResource(R.drawable.bag);
//
//        mCartDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(BaseActivityx.this, ActivityCartDetails.class));
//            }
//        });

        ImageView mAccountImageView = new ImageView(BaseActivityx.this);
        mAccountImageView.setPadding(15, 10, 10, 10);
        // TODO: 2/14/2018 change Menu icon here
//        mAccountImageView.setImageResource(R.drawable.menu_user);
        mAccountImageView.setImageResource(R.drawable.shopping_cart_black);
        mAccountImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBaseToolbarx.setMenuViews(new View[]{/*mCartDetails, */mAccountImageView});
        mBaseToolbarx.install(mAppBarLayout);
    }

    public DrawerLayout getmDrawerLayout() {
        return mDrawerLayout;
    }

    public void removeToolbar() {
        mAppBarLayout.setVisibility(View.GONE);
    }
    public void removeToolbarbase() {
        header.setVisibility(View.GONE);
    }

    public void updateTitle(String s) {
        if (mBaseToolbarx == null)
            setupToolBar();
        mBaseToolbarx.setToolBarTitle(s);
        titleTextView.setText(s);
    }


    public void updateCartQuantity(int size) {
//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putInt("total", size);
//        editor.apply();
        ((ZuniApplication) this.getApplication()).setNumberOfItemsInCart(size);
        TotalQuantity=((ZuniApplication) this.getApplication()).getNumberOfItemsInCart();
        badge.setNumber(TotalQuantity);
    }


}
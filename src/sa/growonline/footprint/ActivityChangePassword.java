package sa.growonline.footprint;

import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sa.growonline.footprint.asynctask.AsynctaskChangePassword;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityChangePassword extends BaseActivityx implements View.OnClickListener {

    TextView mOldPasswordTextView,mNewPasswordTextView,mConfirmPasswordTextView;
    EditText mold_psdEditText,mnew_psdEditText,mconfirm_psdEditText;
    Button mchangepassword_btn;
    String mOld_psdString,mNew_psdString,mConfirm_psdString;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private TabLayout mTablayout;
    private View mBannerView;
   /* private DrawerLayout mDrawerLayout;
    private FragmentCategoryMenu mFragmentCategoryMenu;
    private FragmentProfileMenu mFragmentUserProfileMenu;
    private FragmentManager mFragmentManagerObject;
    private TextView mTitleTextView;*/


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
        removeToolbar();
        //!-- Navigation Menus
       /* mFragmentManagerObject = getSupportFragmentManager();

        //!-- Left Category Menu
        if (mFragmentCategoryMenu == null)
            mFragmentCategoryMenu = new FragmentCategoryMenu();

        //!-- Right Category Menu
        if (mFragmentUserProfileMenu == null)
            mFragmentUserProfileMenu = new FragmentProfileMenu();

        mFragmentManagerObject.beginTransaction().replace(R.id.left_drawer, mFragmentCategoryMenu).commitAllowingStateLoss();
        mFragmentManagerObject.beginTransaction().replace(R.id.right_drawer, mFragmentUserProfileMenu).commitAllowingStateLoss();*/

        mOldPasswordTextView     =(TextView) findViewById(R.id.change_password_old);
        mNewPasswordTextView     =(TextView) findViewById(R.id.change_password_new);
        mConfirmPasswordTextView =(TextView) findViewById(R.id.change_password_confirm);

        mold_psdEditText     =(EditText) findViewById(R.id.editText_old);
        mnew_psdEditText     =(EditText) findViewById(R.id.editText_new);
        mconfirm_psdEditText =(EditText) findViewById(R.id.editText__confirm);

        mchangepassword_btn =(Button) findViewById(R.id.changepsd_btn);

        InitUI();


      /*  mFragmentCategoryMenu.setDrawer(mDrawerLayout);*/

        mchangepassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOld_psdString = ZuniUtils.getText(mold_psdEditText);
                mNew_psdString = ZuniUtils.getText(mnew_psdEditText);
                mConfirm_psdString = ZuniUtils.getText(mconfirm_psdEditText);


                if(mOld_psdString.isEmpty())
                {
                    ZuniUtils.showMsgDialog(ActivityChangePassword.this,"","Oops..! please type old password",null,null);
                    return;
                }

                if(mNew_psdString.isEmpty())
                {
                    ZuniUtils.showMsgDialog(ActivityChangePassword.this,"","Oops..! please type new password",null,null);
                    return;
                }

                if(!mConfirm_psdString.equals(mNew_psdString))
                {
                    ZuniUtils.showMsgDialog(ActivityChangePassword.this,"","Your password didn't match with the confirm password field..!",null,null);
                    return;
                }



                    if (ZuniUtils.isNetworkAvailable(getApplicationContext()))
                    {
                        new AsynctaskChangePassword(ActivityChangePassword.this, mOld_psdString, mNew_psdString).execute();
                    }
                    else
                    {
                        ZuniUtils.showMsgDialog(ActivityChangePassword.this, getString(R.string.app_name), getString(R.string.no_internet), null, null);
                    }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
               /* mFragmentCategoryMenu.loadLastFetchCatrgories();*/
            }
        }, 500);
    }

    private void InitUI()
    {
        mBannerView = findViewById(R.id.header);
       /* ImageView mBackButton = (ImageView) findViewById(R.id.activity_home_header_backbutton);
        ImageView mRightMenuButton = (ImageView) findViewById(R.id.activity_home_header_right_nav);
        
       *//* mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mTitleTextView =(TextView) findViewById(R.id.activity_home_header_logo_nav);*//*
        *//*ZuniUtils.applyAppFont(mTitleTextView);*//*

        mBackButton.setOnClickListener(this);
        mRightMenuButton.setOnClickListener(this);*/
        
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
           /* case R.id.activity_home_header_backbutton:
                finish();
               //ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.LEFT);

                break;*/

           /* case R.id.activity_home_header_right_nav:
                ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.RIGHT);
                break;
*/

        }
    }
}

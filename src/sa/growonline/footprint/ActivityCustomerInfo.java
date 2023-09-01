package sa.growonline.footprint;

import android.os.Handler;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;

import sa.growonline.footprint.asynctask.AsynctaskGetUserDetails;
import sa.growonline.footprint.asynctask.AsynctaskPostCustomerInfo;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanUserDetails;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityCustomerInfo extends BaseActivityx implements AdapterView.OnClickListener, OnMenuItemClickListener
{
    private EditText mFirstNameEditText, mLastNameEditText, mEmailEditText, mCompanyEditText;
    private String mDobDay, mDobMonth, mDobYear;
    private TextView mTextviewDay, mTextviewMonth, mTextviewYear;
    private PopupMenu mYearPopupMenu, mMonthPopupMenu, mDaysPopupMenu;
    private RadioButton mCheckboxMale, mCheckboxFemale;
    private String mGender;
    private Button mUpadteButton;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mCompany;
   /* private TextView mTitleTextView;
    private FragmentCategoryMenu mFragmentCategoryMenu;
    private FragmentManager mFragmentManagerObject;
    private FragmentProfileMenu mFragmentUserProfileMenu;*/
    private View mBannerView;
   /* private DrawerLayout mDrawerLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
        removeToolbar();
      /*  mFragmentManagerObject = getSupportFragmentManager();*/

        //!-- Left Category Menu
      /*  if (mFragmentCategoryMenu == null)
            mFragmentCategoryMenu = new FragmentCategoryMenu();

        //!-- Right Category Menu
        if (mFragmentUserProfileMenu == null)
            mFragmentUserProfileMenu = new FragmentProfileMenu();

        mFragmentManagerObject.beginTransaction().replace(R.id.left_drawer, mFragmentCategoryMenu).commitAllowingStateLoss();
        mFragmentManagerObject.beginTransaction().replace(R.id.right_drawer, mFragmentUserProfileMenu).commitAllowingStateLoss();*/

        new AsynctaskGetUserDetails(ActivityCustomerInfo.this, true).execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* mFragmentCategoryMenu.setDrawer(mDrawerLayout);
                mFragmentCategoryMenu.loadLastFetchCatrgories();*/
            }
        }, 500);
        IniUI();
    }

    private void IniUI() {

        mFirstNameEditText = (EditText) findViewById(R.id.activity_customer_first_name);
        mLastNameEditText = (EditText) findViewById(R.id.activity_customer_last_name);
        mTextviewDay = (TextView) findViewById(R.id.activity_registration_days);
        mTextviewMonth = (TextView) findViewById(R.id.activity_registration_month);
        mTextviewYear = (TextView) findViewById(R.id.activity_registration_year);
        mDaysPopupMenu = new PopupMenu(ActivityCustomerInfo.this, mTextviewDay);
        mMonthPopupMenu = new PopupMenu(ActivityCustomerInfo.this, mTextviewMonth);
        mYearPopupMenu = new PopupMenu(ActivityCustomerInfo.this, mTextviewYear);
        mEmailEditText = (EditText) findViewById(R.id.activity_registration_email);
        mCompanyEditText = (EditText) findViewById(R.id.activity_customer_company_name);
        mUpadteButton = (Button) findViewById(R.id.activity_customer_submit_btn);
        /*mTitleTextView = (TextView) findViewById(R.id.activity_home_header_logo_nav);
        ZuniUtils.applyAppFont(mTitleTextView);*/



        mBannerView = findViewById(R.id.header);
       /* ImageView mBackButton = (ImageView) findViewById(R.id.activity_home_header_back_button);
        ImageView mRightMenuButton = (ImageView) findViewById(R.id.activity_home_header_right_nav);*/

       /* mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);*/
        mCheckboxMale = (RadioButton) findViewById(R.id.activity_customer_male);
        mCheckboxFemale = (RadioButton) findViewById(R.id.activity_customer_female);
        final RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.activity_customer_radio_group);

        /*mBackButton.setOnClickListener(this);
        mRightMenuButton.setOnClickListener(this);*/

        for (int i = 0; i < 31; i++)
            mDaysPopupMenu.getMenu().add(1, R.id.activity_signup_day, i + 1, String.valueOf(i + 1));
        for (int i = 0; i < 12; i++)
            mMonthPopupMenu.getMenu().add(1, R.id.activity_signup_month, i + 1, String.valueOf(i + 1));
        for (int i = 0; i < 67; i++)
            mYearPopupMenu.getMenu().add(1, R.id.activity_signup_year, i + 1, String.valueOf(1950 + i));




        mUpadteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirstName = ZuniUtils.getText(mFirstNameEditText);
                mLastName = ZuniUtils.getText(mLastNameEditText);
                mEmail = ZuniUtils.getText(mEmailEditText);
                mCompany = ZuniUtils.getText(mCompanyEditText);

                if (mFirstName.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityCustomerInfo.this, "", getString(R.string.error_first_name), null, null);
                    return;
                }


                if (mLastName.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityCustomerInfo.this, "", getString(R.string.error_last_name), null, null);
                    return;
                }

                if (mEmail.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityCustomerInfo.this, "", getString(R.string.error_email), null, null);
                    return;
                }




                if (ZuniUtils.isNetworkAvailable(getApplicationContext())) {
                    new AsynctaskPostCustomerInfo(ActivityCustomerInfo.this, (mCheckboxMale.isChecked())? "m" : "f", mFirstName, mLastName, ZuniUtils.getText(mTextviewDay) + "/" + ZuniUtils.getText(mTextviewMonth) + "/" + ZuniUtils.getText(mTextviewYear), mEmail, mCompany).execute();
                } else {
                    ZuniUtils.showMsgDialog(ActivityCustomerInfo.this, getString(R.string.app_name), getString(R.string.no_internet), null, null);
                }
            }
        });


        mTextviewDay.setOnClickListener(this);
        mTextviewMonth.setOnClickListener(this);
        mTextviewYear.setOnClickListener(this);

        mDaysPopupMenu.setOnMenuItemClickListener(this);
        mMonthPopupMenu.setOnMenuItemClickListener(this);
        mYearPopupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_signup_day:
                mDobDay = item.getTitle().toString();
                mTextviewDay.setText(mDobDay);
                break;

            case R.id.activity_signup_month:
                mDobMonth = item.getTitle().toString();
                mTextviewMonth.setText(mDobMonth);
                break;

            case R.id.activity_signup_year:
                mDobYear = item.getTitle().toString();
                mTextviewYear.setText(mDobYear);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_registration_days:
                mDaysPopupMenu.show();
                break;

            case R.id.activity_registration_year:
                mYearPopupMenu.show();
                break;

            case R.id.activity_registration_month:
                mMonthPopupMenu.show();
                break;
            /*case R.id.activity_home_header_back_button:
                finish();
                break;

            case R.id.activity_home_header_right_nav:
                ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.RIGHT);
                break;*/



        }
    }

    public void updateDetails(BeanUserDetails mBeanUserDetails) {
        if (mBeanUserDetails.getGender() != null)
        {
            mGender = (mBeanUserDetails.getGender());
            if (mGender.equalsIgnoreCase("f")) {
                mCheckboxFemale.setChecked(true);
            } else {
                mCheckboxMale.setChecked(true);
            }
        }

        if (mBeanUserDetails.getFirstName() != null)
            mFirstNameEditText.setText(mBeanUserDetails.getFirstName());

        if (mBeanUserDetails.getLastName() != null)
            mLastNameEditText.setText(mBeanUserDetails.getLastName());

        if (mBeanUserDetails.getEmail() != null)
            mEmailEditText.setText(mBeanUserDetails.getEmail());

        if (mBeanUserDetails.getCompany() != null)
            mCompanyEditText.setText(mBeanUserDetails.getCompany());

        if (mBeanUserDetails.getDateOfBirthDay() != null)
            mTextviewDay.setText(mBeanUserDetails.getDateOfBirthDay().toString() + "");

        if (mBeanUserDetails.getDateOfBirthMonth() != null)
            mTextviewMonth.setText(mBeanUserDetails.getDateOfBirthMonth().toString() + "");

        if (mBeanUserDetails.getDateOfBirthYear() != null)
            mTextviewYear.setText(mBeanUserDetails.getDateOfBirthYear().toString() + "");
    }
}
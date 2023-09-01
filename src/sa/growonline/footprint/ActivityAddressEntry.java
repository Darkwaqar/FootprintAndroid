package sa.growonline.footprint;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.asynctask.AsynctaskAddShppingLocation;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.checkout.AvailableCountry;
import sa.growonline.footprint.bean.checkout.AvailableState;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.NewAddress;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityAddressEntry extends BaseActivityx implements View.OnClickListener {
    EditText mNameEditText, mContactNoEditText, mEmailEditText, mCompanyNameEditText, mAddressEditText, mCityEditText;
    Button SaveButton, AddNewButton;
    String mNameString, mContactNoString, mEmailString, mCompanyNameString, mAddressString;
    int mId = 0;
    public static boolean mIsEdit = false;
    private View mBannerView;
    private List<AvailableCountry> mAvailableCountries;
    private Spinner mCountryAutoCompleteTextView, mStateAutoCompleteTextView;

    private String mCountryName = "", mCountryId = "", mStateId = "1", mStateName = "", mCityNameString;
    private List<AvailableState> mAllStates;
    private ArrayList<AvailableState> mFilterStates;

    public void updateComplete() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_entry);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
        removeToolbar();
        initUI();

        if (!ActivityAddressEntry.mIsEdit) {
            SaveButton.setVisibility(View.GONE);
            AddNewButton.setVisibility(View.VISIBLE);
        } else {
            SaveButton.setVisibility(View.VISIBLE);
            AddNewButton.setVisibility(View.GONE);
        }
        setEditValues();

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mNameString = ZuniUtils.getText(mNameEditText);
                mContactNoString = ZuniUtils.getText(mContactNoEditText);
                mEmailString = ZuniUtils.getText(mEmailEditText);
                mCompanyNameString = ZuniUtils.getText(mCompanyNameEditText);
                mAddressString = ZuniUtils.getText(mAddressEditText);
                mCityNameString = ZuniUtils.getText(mCityEditText);

                if (mNameString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Your Name", null, null);
                    return;
                }
                if (mContactNoString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Contact No", null, null);
                    return;
                }
                if (!ZuniUtils.validateEmail(mEmailString)) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Valid Email", null, null);
                    return;
                }
                if (mCompanyNameString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Company Name ", null, null);
                    return;
                }

                if (mAddressString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Your Address ", null, null);
                    return;
                }

                if (mCountryId == null || mCountryId.equalsIgnoreCase("")) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Your country is not supported by our system  ", null, null);
                    return;
                }

                if (mCityNameString == null || mCityNameString.equalsIgnoreCase("")) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Your Address ", null, null);
                    return;
                }


                NewAddress address = new NewAddress();
                address.setId(mId);
                address.setFirstName(mNameString);
                address.setPhoneNumber(mContactNoString);
                address.setEmail(mEmailString);
                address.setCompany(mCompanyNameString);
                address.setAddress1(mAddressString);

                address.setCountryId(mCountryId);
                address.setCountryName(mCountryName);
                address.setStateProvinceId(mStateId);
                address.setCity(mCityNameString);

                saveAddress(address);

            }
        });

        AddNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNameString = ZuniUtils.getText(mNameEditText);
                mContactNoString = ZuniUtils.getText(mContactNoEditText);
                mEmailString = ZuniUtils.getText(mEmailEditText);
                mCompanyNameString = ZuniUtils.getText(mCompanyNameEditText);
                mAddressString = ZuniUtils.getText(mAddressEditText);
                mCityNameString = ZuniUtils.getText(mCityEditText);
                mIsEdit = false;
                mId = 0;

                if (mNameString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Your Name", null, null);
                    return;
                }
                if (mContactNoString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Contact No", null, null);
                    return;
                }
                if (!ZuniUtils.validateEmail(mEmailString)) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Valid Email", null, null);
                    return;
                }
                if (mCompanyNameString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Company Name ", null, null);
                    return;
                }

                if (mAddressString.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type Your Address ", null, null);
                    return;
                }


                if (mCountryId == null || mCountryId.equalsIgnoreCase("")) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Your country is not supported by our system  ", null, null);
                    return;
                }

                if (mCityNameString == null || mCityNameString.equalsIgnoreCase("")) {
                    ZuniUtils.showMsgDialog(ActivityAddressEntry.this, "", "Oops..! please type City Name ", null, null);
                    return;
                }


                NewAddress address = new NewAddress();
                address.setId(mId);
                address.setFirstName(mNameString);
                address.setPhoneNumber(mContactNoString);
                address.setEmail(mEmailString);
                address.setCompany(mCompanyNameString);
                address.setAddress1(mAddressString);
                address.setCountryId(mCountryId);
                address.setCountryName(mCountryName);
                address.setStateProvinceId(mStateId);
                address.setCity(mCityNameString);
                saveAddress(address);
            }
        });
    }

    private void setEditValues() {
        String json = "{}";

        if (mIsEdit)
            json = ZuniApplication.getmAppPreferences().getString(ZuniConstants.SELECTED_LOCATION_LISTING_JSON, "{}");
        else
            json = ZuniApplication.getmAppPreferences().getString(ZuniConstants.SELECTED_NEW_LOCATION_JSON, "{}");

        ZuniUtils.LogEvent(json);

        ExistingAddress address = new Gson().fromJson(json, ExistingAddress.class);

        try {
            mId = address.getId();

            mCountryId = address.getCountryId();
            mCountryName = address.getCountryName();
            mStateId = address.getStateProvinceId();
            mStateName = address.getStateProvinceName();

            mNameEditText.setText(address.getFirstName());
            mContactNoEditText.setText(address.getPhoneNumber());
            mEmailEditText.setText(address.getEmail());
            mCompanyNameEditText.setText(address.getCompany());
            mAddressEditText.setText(address.getAddress1());

            mAvailableCountries = address.getAvailableCountries();
            mCityNameString = address.getCity();

            int mSelectedCountrySpinner = 0;

            if (address.getAllStates() != null)
                mAllStates = address.getAllStates();
            else if (address.getAvailableStates() != null)
                mAllStates = address.getAvailableStates();
            else
                mAllStates = new ArrayList<AvailableState>();

            if (mIsEdit)
                mAllStates = address.getAvailableStates();
            if (mAvailableCountries != null) {
                ArrayList<String> countries = new ArrayList<String>();
                for (int i = 0; i < mAvailableCountries.size(); i++) {
                    countries.add(mAvailableCountries.get(i).getText());
                    if (address.getCountryId() != null) {
                        if (mAvailableCountries.get(i).getValue().equalsIgnoreCase(address.getCountryId())) {
                            mCountryId = mAvailableCountries.get(i).getValue();
                            mCountryName = mAvailableCountries.get(i).getText();
                            mSelectedCountrySpinner = i;
                        }
                    }
                }
                mCountryAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countries));
            }

            try {
                mCountryAutoCompleteTextView.setSelection(mSelectedCountrySpinner);
                mStateAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{mAllStates.get(0).getText()}));
            } catch (Exception e) {
                e.printStackTrace();
            }

            mCityEditText.setText((address.getCity() != null) ? address.getCity() : "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsEdit = false;
    }

    private void initUI() {

        mBannerView = findViewById(R.id.header);
        mCountryAutoCompleteTextView = (Spinner) findViewById(R.id.activity_address_country_name);
        mStateAutoCompleteTextView = (Spinner) findViewById(R.id.activity_address_state_name1);


        mCountryAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                mCountryId = mAvailableCountries.get(i).getValue();
                mCountryName = mAvailableCountries.get(i).getText();

                if (mFilterStates == null)
                    mFilterStates = new ArrayList<AvailableState>();
                else
                    mFilterStates.clear();

                ArrayList<String> mFilterList = new ArrayList<String>();
                if (mCountryId != null) {
                    for (int a = 0; a < mAllStates.size(); a++)
                        if (mAllStates.get(a).getCountryId().equalsIgnoreCase(mCountryId)) {
                            mFilterStates.add(mAllStates.get(a));
                            mFilterList.add(mAllStates.get(a).getText());
                        }
                }
                mFilterList.add(0, mAllStates.get(0).getText());
                mFilterStates.add(0, mAllStates.get(0));
                mStateId = "1";
                mStateAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(ActivityAddressEntry.this, android.R.layout.simple_spinner_dropdown_item, mFilterList));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mStateAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mStateId = mFilterStates.get(i).getValue();
                mStateName = mFilterStates.get(i).getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       /* mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mBackButton.setOnClickListener(this);
        mRightMenuButton.setOnClickListener(this);*/


        mNameEditText = (EditText) findViewById(R.id.txt_name);
        mContactNoEditText = (EditText) findViewById(R.id.txt_contactNo);
        mEmailEditText = (EditText) findViewById(R.id.txt_email);
        mCompanyNameEditText = (EditText) findViewById(R.id.txt_company_name);
        mAddressEditText = (EditText) findViewById(R.id.txt_address);
        mCityEditText = (EditText) findViewById(R.id.txt_city_name);

        SaveButton = (Button) findViewById(R.id.btn_save);
        AddNewButton = (Button) findViewById(R.id.btn_addnew);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* mFragmentCategoryMenu.loadLastFetchCatrgories();*/
            }
        }, 500);
    }

    private void saveAddress(NewAddress address) {
        new AsynctaskAddShppingLocation(this, true, address, true).execute();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
           /* case R.id.activity_home_header_backbutton:
                finish();
                break;

            case R.id.activity_home_header_right_nav:
                ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.RIGHT);
                break;*/

        }
    }
}
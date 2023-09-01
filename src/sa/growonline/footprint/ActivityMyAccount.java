/*
 * Copyright (c) 2016 Arsal Raza Imam
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint;

import sa.growonline.footprint.asynctask.AsynctaskGetUserDetails;
import sa.growonline.footprint.bean.BeanUserDetails;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

public class ActivityMyAccount extends AppCompatActivity implements OnClickListener
{
	private TextView mLocationTextView;
	private TextView mDobTextView;
	private TextView mNameTextView;
	private TextView mEmailTextView;
	private TextView mStreetTextView;
	private TextView mCityTextView;
	private TextView mCountryTextView;
	private TextView mNumberTextView;
	private TextView mCompanyTextView;
	private TextView mFaxTextView;
	private CollapsingToolbarLayout mCollapsingToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		initActionBar();
		initViews();
		
		new AsynctaskGetUserDetails(ActivityMyAccount.this, true).execute();
	}

	private void initViews()
	{
		mLocationTextView = (TextView) findViewById(R.id.activity_my_account_location_textview);
		mStreetTextView = (TextView) findViewById(R.id.activity_my_account_street_textview);
		mCityTextView = (TextView) findViewById(R.id.activity_my_account_city_textview);
		mCountryTextView = (TextView) findViewById(R.id.activity_my_account_country_textview);
		mCompanyTextView = (TextView) findViewById(R.id.activity_my_account_company_textview);
		mFaxTextView = (TextView) findViewById(R.id.activity_my_account_fax_textview);
		mNumberTextView = (TextView) findViewById(R.id.activity_my_account_number_textview);
		mDobTextView = (TextView) findViewById(R.id.activity_my_account_dob_textview);
		mNameTextView = (TextView) findViewById(R.id.activity_my_account_name_textview);
		mEmailTextView = (TextView) findViewById(R.id.activity_my_account_email_textview);
	}
	
	public void updateDetails(BeanUserDetails mBeanUserDetails)
	{
		if (mBeanUserDetails == null) return;
		
		String loc = "", cityString = mBeanUserDetails.getCity() + "", street = mBeanUserDetails.getStreetAddress() + "", companyString = mBeanUserDetails.getCompany() + "", phone = mBeanUserDetails.getPhone() + "", fax = mBeanUserDetails.getFax() + "", 
				dob = mBeanUserDetails.getDateOfBirthDay() 	
					+ " - " + mBeanUserDetails.getDateOfBirthMonth()
					+ " - " + mBeanUserDetails.getDateOfBirthYear(),
				
				nameString = mBeanUserDetails.getFirstName(), email = mBeanUserDetails.getEmail();
		
		if (nameString.equalsIgnoreCase("") || nameString.equalsIgnoreCase("null"))
			nameString = String.format(getString(R.string.activity_sign_up_name_val), "N/A");
		else
			nameString = String.format(getString(R.string.activity_sign_up_name_val), nameString);
			
		if (companyString.equalsIgnoreCase("") || companyString.equalsIgnoreCase("null"))
			companyString = String.format(getString(R.string.activity_sign_up_company), "N/A");
		else
			companyString = String.format(getString(R.string.activity_sign_up_company), companyString);
		
		if (phone.equalsIgnoreCase("") || phone.equalsIgnoreCase("null"))
			phone = String.format(getString(R.string.activity_sign_up_number_val), "N/A");
		else
			phone = String.format(getString(R.string.activity_sign_up_number_val), phone);
		
		if (fax.equalsIgnoreCase("") || fax.equalsIgnoreCase("null"))
			fax = String.format(getString(R.string.activity_sign_up_fax), "N/A");
		else
			fax = String.format(getString(R.string.activity_sign_up_fax), fax);
		
		if (cityString.equalsIgnoreCase("") || cityString.equalsIgnoreCase("null"))
			cityString = String.format(getString(R.string.activity_sign_up_city), "N/A");
		else
			cityString = String.format(getString(R.string.activity_sign_up_city), cityString);
		
		if (street.equalsIgnoreCase("") || street.equalsIgnoreCase("null"))
			street = String.format(getString(R.string.activity_sign_up_street), "N/A");
		else
			street = String.format(getString(R.string.activity_sign_up_street), street);
		
		if (email.equalsIgnoreCase("") || street.equalsIgnoreCase("null"))
			email = String.format(getString(R.string.activity_sign_up_email_val), "N/A");
		else
			email = String.format(getString(R.string.activity_sign_up_email_val), email);
		
		mCountryTextView.setText("Country: N/A");
		mCityTextView.setText(cityString);
		mStreetTextView.setText(street);
		mCompanyTextView.setText(companyString);
		mNumberTextView.setText(phone);
		mFaxTextView.setText(fax);
		
		mDobTextView.setText(dob);
		
		mNameTextView.setText(String.format(getString(R.string.activity_sign_up_name_val), mBeanUserDetails.getFirstName() + " " + mBeanUserDetails.getLastName()));
		mCollapsingToolbar.setTitle(mBeanUserDetails.getFirstName());
		mEmailTextView.setText(email);
	}
	
	private void initActionBar()
	{
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_account_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){finish();}});
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.activity_my_account_collapsing_toolbar);
        mCollapsingToolbar.setTitle("Profile Name");
    }

	@Override
	public void onClick(View v)
	{ 
		
	}
}
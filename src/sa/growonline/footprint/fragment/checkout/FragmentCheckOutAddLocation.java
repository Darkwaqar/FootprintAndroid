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

package sa.growonline.footprint.fragment.checkout;

import java.util.List;

import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.RegionAdapter;
import sa.growonline.footprint.asynctask.AsynctaskAddShppingLocation;
import sa.growonline.footprint.bean.checkout.AvailableCountry;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.NewAddress;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

public class FragmentCheckOutAddLocation extends Fragment implements OnClickListener, OnItemClickListener
{
	private enum RegionType { STATE, COUNTRY }

	private EditText mAddress1EditText;
	private EditText mAddress2EditText;
	private EditText mPostalCodeEditText;
	private EditText mNameEditText;
	private EditText mCompanyEditText;
	private EditText mEmailEditText;
	private EditText mPhoneNumberEditText;
	private EditText mFaxNumberEditText;
	private TextView mStateEditText;
	private TextView mCountryTextView;
	private Dialog mRegionDialog;
	private ListView mRegionDialogSearchListView;
	private NewAddress mNewAddress;
	private RegionAdapter mRegionAdapter;
	private int mCountryPosition;
	private AppCompatButton mConfirmBtn;
	private NewAddress newAddress;
	private EditText mCityEditText;
	private Integer mId;
	private String mCountryId;
	private String mStateId;
	private List<AvailableCountry> mAvailableCountries;
	public static boolean isEdit = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_add_location, container, false);
		InitUI(view);
		
		if (isEdit)
			setDataForEditLocation();
		return view;
	}
	
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		isEdit = false;
	}
	
	private void setDataForEditLocation()
	{
		try
		{
			ExistingAddress existingAddress = new Gson().fromJson(ZuniApplication.getmAppPreferences().getString(ZuniConstants.SELECTED_LOCATION_FOR_EDIT, "{}"), ExistingAddress.class);
			
			if (existingAddress != null)
			{
				mId = existingAddress.getId();
				mCountryId = existingAddress.getCountryId();
				mStateId = existingAddress.getStateProvinceId();
						
				if (existingAddress.getAddress1() != null)
					mAddress1EditText.setText(existingAddress.getAddress1());

				if (existingAddress.getAddress2() != null)
					mAddress2EditText.setText(existingAddress.getAddress2());
				
				if (existingAddress.getZipPostalCode() != null)
					mPostalCodeEditText.setText(existingAddress.getZipPostalCode());
				
				if (existingAddress.getFirstName() != null)
					mNameEditText.setText(existingAddress.getFirstName());
				
				if (existingAddress.getEmail() != null)
					mEmailEditText.setText(existingAddress.getEmail());
				
				if (existingAddress.getCompany() != null)
					mCompanyEditText.setText(existingAddress.getCompany());
				
				if (existingAddress.getPhoneNumber() != null)
					mPhoneNumberEditText.setText(existingAddress.getPhoneNumber());
				
				if (existingAddress.getStateProvinceName() != null)
					mStateEditText.setText(existingAddress.getStateProvinceName());
				
				if (existingAddress.getCity() != null)
					mCityEditText.setText(existingAddress.getCity());
				
				if (existingAddress.getCountryName() != null)
					mCountryTextView.setText(existingAddress.getCountryName());
				
				mAvailableCountries = existingAddress.getAvailableCountries();
//				if (existingAddress.getAvailableCountries() != null && existingAddress.getAvailableCountries().size() != 0)
//					mNewAddress.setAvailableCountries(existingAddress.getAvailableCountries());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void InitUI(View view)
	{
		mAddress1EditText = (EditText) view.findViewById(R.id.shipping_location_address_1);
		mAddress2EditText = (EditText) view.findViewById(R.id.shipping_location_address_2);
		mPostalCodeEditText = (EditText) view.findViewById(R.id.shipping_location_postalcode);
		mNameEditText = (EditText) view.findViewById(R.id.shipping_location_name);
		mEmailEditText = (EditText) view.findViewById(R.id.shipping_location_email);
		mCompanyEditText = (EditText) view.findViewById(R.id.shipping_location_company);
		mPhoneNumberEditText = (EditText) view.findViewById(R.id.shipping_location_ph_no);
		mFaxNumberEditText = (EditText) view.findViewById(R.id.shipping_location_fax_no);
		
		mStateEditText = (EditText) view.findViewById(R.id.shipping_location_state_edittext);
		mCityEditText = (EditText) view.findViewById(R.id.shipping_location_city_edittext);
		mCountryTextView = (TextView) view.findViewById(R.id.shipping_location_country_txtview);
		
		mConfirmBtn = (AppCompatButton) view.findViewById(R.id.shipping_location_confirm_btn);
		
		mRegionDialog = new Dialog(getActivity());
		mRegionDialog.setContentView(R.layout.dialog_region);
	
		mRegionDialogSearchListView = (ListView) mRegionDialog.findViewById(R.id.dialog_region_search_listview);
		
		mConfirmBtn.setOnClickListener(this);
		mRegionDialogSearchListView.setOnItemClickListener(this);
		mCountryTextView.setOnClickListener(this);
	}


	public void updateAddLocation(NewAddress newAddress)
	{
		if (newAddress == null) return;
		mNewAddress = newAddress;
	}

	public void regionAdapter (List<?> list)
	{
		if (mRegionDialogSearchListView.getAdapter() != null)
			mRegionDialogSearchListView.setAdapter(new RegionAdapter(getActivity(), list));//((RegionAdapter) mRegionDialogSearchListView.getAdapter()).notifyRegionsChanged(list);
		else
		{
			mRegionDialogSearchListView.setAdapter(new RegionAdapter(getActivity(), list));
		}
	}

	private boolean validateAndPopulateData()
	{
		newAddress = new NewAddress();
		
		String mAddress = getText(mAddress1EditText), mAddress2 = getText(mAddress2EditText), mcountry = getText(mCountryTextView),mcity = getText(mCityEditText), mState = getText(mStateEditText)
				, mpostalCode = getText(mPostalCodeEditText), mName = getText(mNameEditText), memail = getText(mEmailEditText), mCompany = getText(mCompanyEditText)
						, mphoneNumber = getText(mPhoneNumberEditText);
		
		if (mAddress.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter your address...!", null, null);
			return false;
		}
		newAddress.setAddress1(mAddress);
		
		
		newAddress.setAddress2(mAddress2);
		
		if (mcountry.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter your Country...!", null, null);
			return false;
		}
	
		newAddress.setCountryName(mcountry);
		
		if (!isEdit)
			newAddress.setCountryId(mNewAddress.getAvailableCountries().get(mCountryPosition).getValue());
		else
			newAddress.setCountryId(mCountryId);
			
		if (mcity.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter your City...!", null, null);
			return false;
		}
		newAddress.setCity(mcity);

		newAddress.setStateProvinceName(mState);
		newAddress.setStateProvinceId("0");
		
		newAddress.setZipPostalCode(mpostalCode);
		
		if (mName.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter your Name...!", null, null);
			return false;
		}
		newAddress.setFirstName(mName);
		newAddress.setLastName("");
		
		if (memail.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter your Email Address...!", null, null);
			return false;
		}
		
		if (!android.util.Patterns.EMAIL_ADDRESS.matcher(memail).matches())
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter a valid Email Address...!", null, null);
			return false;
		}
		
		newAddress.setEmail(memail);
		
		newAddress.setCompany(mCompany);
		
		if (mphoneNumber.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter your Phone Number...!", null, null);
			return false;
		}
		
		if (!android.util.Patterns.PHONE.matcher(mphoneNumber).matches())
		{
			ZuniUtils.showMsgDialog(getActivity(), getString(R.string.app_name), "Please enter a valid Phone Number...!", null, null);
			return false;
		}
		newAddress.setPhoneNumber(mphoneNumber);
		return true;
	}


	private String getText(TextView editText)
	{
		return editText.getText().toString();
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		TextView textView = (TextView) view;
		mCountryTextView.setText(textView.getText().toString());
		mCountryPosition = (Integer) textView.getTag();
		mRegionDialog.dismiss();
	}
	
	@Override
	public void onClick(View v)
	{

		if (mConfirmBtn == v)
		{
			if (validateAndPopulateData())
			{
				if (isEdit)
					newAddress.setId(mId);
			
				new AsynctaskAddShppingLocation(getActivity(), true, newAddress, this).execute();	
			}
			return;
		}

		if (mCountryTextView == v)
		{
			mRegionDialogSearchListView.setTag(RegionType.COUNTRY);
			mRegionDialog.setTitle("Select Country");
			if (mNewAddress != null)
				regionAdapter(mNewAddress.getAvailableCountries());
			else if (mAvailableCountries != null)
				regionAdapter(mAvailableCountries);
			
			mRegionDialog.show();
		}
	}

}
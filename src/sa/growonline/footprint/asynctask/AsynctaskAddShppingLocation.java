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

package sa.growonline.footprint.asynctask;

import java.util.List;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityAddressEntry;
import sa.growonline.footprint.ActivityAddresses;
import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.NewAddress;
import sa.growonline.footprint.bean.checkout.ShippingLocation;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.app.Activity;

import com.google.gson.Gson;

public class AsynctaskAddShppingLocation extends BaseAsynctask {
    private boolean mIsEditLocation = false;
    private NewAddress mNewAddress;

    public AsynctaskAddShppingLocation(Activity activity, boolean isProgressEnabled,
                                       NewAddress newAddress, Object object) {
        super(activity, isProgressEnabled);
        this.mNewAddress = newAddress;
        this.mIsEditLocation = false;
    }

    public AsynctaskAddShppingLocation(Activity activity, boolean isProgressEnabled,
                                       NewAddress newAddress, boolean isedit) {
        super(activity, isProgressEnabled);
        this.mNewAddress = newAddress;
        this.mIsEditLocation = isedit;
    }

    @Override
    protected void onComplete(String output) {
        if (mActivity instanceof ActivityCheckOut) {
            new AsynctaskGetCheckOutDetails(mActivity, true).execute();
            //	((ActivityCheckOut) mActivity).updateComplete();

        } else if (mActivity instanceof ActivityAddresses) {
            ((ActivityAddresses) mActivity).editComplete();
        } else if (mActivity instanceof ActivityAddressEntry) {
            ((ActivityAddressEntry) mActivity).updateComplete();
        }
    }

    private String obtainResponseFromService() {
        String response;

        JSONObject jsonObject = new JSONObject();
        try {
            if (mActivity instanceof ActivityCheckOut) {
                //jsonObject.put("shipping_address_id", "");

                if (mIsEditLocation)
                    jsonObject.put("Address.Id", mNewAddress.getId());
                else
                    jsonObject.put("Address.Id", 0);

                jsonObject.put("Address.FirstName", mNewAddress.getFirstName());
                jsonObject.put("Address.LastName", mNewAddress.getLastName());
                jsonObject.put("Address.Email", mNewAddress.getEmail());
                jsonObject.put("Address.Company", mNewAddress.getCompany());
                jsonObject.put("Address.CountryId", mNewAddress.getCountryId());
                jsonObject.put("Address.StateProvinceId", mNewAddress.getStateProvinceId());
                jsonObject.put("Address.City", mNewAddress.getCity());
                jsonObject.put("Address.Address1", mNewAddress.getAddress1());
                jsonObject.put("Address.Address2", mNewAddress.getAddress2());
                jsonObject.put("Address.ZipPostalCode", mNewAddress.getZipPostalCode());
                jsonObject.put("Address.PhoneNumber", mNewAddress.getPhoneNumber());
                jsonObject.put("Address.FaxNumber", mNewAddress.getFaxNumber());

                response = new GetPostSender().sendPostJSON(ZuniConstants.SAVE_SHIPPING_LOCATION, jsonObject.toString(), false);
            } else {
                jsonObject.put("Address.Id", mNewAddress.getId());
                jsonObject.put("Address.FirstName", mNewAddress.getFirstName());
                jsonObject.put("Address.LastName", mNewAddress.getLastName());
                jsonObject.put("Address.Email", mNewAddress.getEmail());
                jsonObject.put("Address.Company", mNewAddress.getCompany());
                jsonObject.put("Address.CountryId", mNewAddress.getCountryId());
                jsonObject.put("Address.StateProvinceId", mNewAddress.getStateProvinceId());
                jsonObject.put("Address.City", mNewAddress.getCity());
                jsonObject.put("Address.Address1", mNewAddress.getAddress1());
                jsonObject.put("Address.Address2", mNewAddress.getAddress2());
                jsonObject.put("Address.ZipPostalCode", mNewAddress.getZipPostalCode());
                jsonObject.put("Address.PhoneNumber", mNewAddress.getPhoneNumber());
                jsonObject.put("Address.FaxNumber", mNewAddress.getFaxNumber());

                response = new GetPostSender().sendPostJSON(ZuniConstants.SAVE_SHIPPING_LOCATION, jsonObject.toString(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return response;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = obtainResponseFromService();
        ZuniUtils.LogEvent(response);

        String checkPoint = onResponseReceived(response);
        if (checkPoint.equalsIgnoreCase("")) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                ShippingLocation mLocationBean = new Gson().fromJson(jsonObject.getString("Location"), ShippingLocation.class);
                List<ExistingAddress> mExistingLocation = mLocationBean.getExistingAddresses();

                if (mExistingLocation != null && mExistingLocation.size() != 0) {
                    ExistingAddress selectedExistingLocation = mExistingLocation.get(mExistingLocation.size() - 1);
                    ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.SELECTED_LOCATION_LISTING_JSON, new Gson().toJson(selectedExistingLocation, ExistingAddress.class)).commit();
                }
            } catch (Exception exce) {
                exce.printStackTrace();
                return mActivity.getString(R.string.InvalidResponse);
            }
        } else {
            return checkPoint;
        }
        return "";
    }
}
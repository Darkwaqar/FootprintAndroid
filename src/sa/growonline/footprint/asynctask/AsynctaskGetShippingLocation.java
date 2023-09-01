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

import sa.growonline.footprint.ActivityAddresses;
import sa.growonline.footprint.R;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.ShippingLocation;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutLocationListing;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;

import com.google.gson.Gson;

public class AsynctaskGetShippingLocation extends BaseAsynctask
{

	private ShippingLocation mShippingLocation;
	private Object mFromWhere;

	public AsynctaskGetShippingLocation(Activity activity, boolean isProgressEnabled, Object mFromWhere)
	{
		super(activity, isProgressEnabled);
		this.mFromWhere = mFromWhere;
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mActivity instanceof ActivityAddresses && mFromWhere instanceof FragmentCheckOutLocationListing)
			{
				((FragmentCheckOutLocationListing) mFromWhere).updateLocationListing(mShippingLocation);
			}
			if (mFromWhere instanceof FragmentCheckOutLocationListing)
				((FragmentCheckOutLocationListing) mFromWhere).updateLocationListing(mShippingLocation.getExistingAddresses());

		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_SHIPPING_LOCATIONS, false);
		String checkPoint = onResponseReceived(response);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				mShippingLocation = new Gson().fromJson(response, ShippingLocation.class);
				List<ExistingAddress> existingAddresses = mShippingLocation.getExistingAddresses();

                mShippingLocation.getNewAddress().setAvailableStates(mShippingLocation.getNewAddress().getAllStates());
				for (int i = 0; i < existingAddresses.size(); i++)
				{
					existingAddresses.get(i).setAvailableCountries(mShippingLocation.getNewAddress().getAvailableCountries());
                    existingAddresses.get(i).setAvailableStates(mShippingLocation.getNewAddress().getAllStates());
                }
			}
			catch (Exception exce)
			{ 
				exce.printStackTrace();
				return "Invalid response is coming from the server";
			}
		}
		else
		{
			return checkPoint;
		}
		return "";
	}
}
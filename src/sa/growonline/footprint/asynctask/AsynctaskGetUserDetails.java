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

import android.app.Activity;

import com.google.gson.Gson;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityCustomerInfo;
import sa.growonline.footprint.ActivityGiftCard;
import sa.growonline.footprint.ActivityMyAccount;
import sa.growonline.footprint.R;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanUserDetails;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class AsynctaskGetUserDetails extends BaseAsynctask
{
	private BeanUserDetails mBeanUserDetails;

	public AsynctaskGetUserDetails(Activity activity, boolean isProgressEnabled)
	{
		super(activity, isProgressEnabled);
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mActivity instanceof ActivityMyAccount)
			{
				((ActivityMyAccount) mActivity).updateDetails(mBeanUserDetails);
			}
			else if (mActivity instanceof ActivityCustomerInfo)
			{
				((ActivityCustomerInfo) mActivity).updateDetails(mBeanUserDetails);
			}
			else if (mActivity instanceof ActivityGiftCard)
			{
				((ActivityGiftCard) mActivity).updateDetails(mBeanUserDetails);
			}
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.EDIT_INFO, false);
		String checkPoint = onResponseReceived(response);

		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				ZuniUtils.LogEvent(response);
				JSONObject jsonObject = new JSONObject(response);
				String success = jsonObject.getString("success");
				if (success.equalsIgnoreCase("true"))
				{
					mBeanUserDetails = new Gson().fromJson(jsonObject.getString("Model"), BeanUserDetails.class);
				}
				else
				{
					return "Error: we encountered some problem..!";
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
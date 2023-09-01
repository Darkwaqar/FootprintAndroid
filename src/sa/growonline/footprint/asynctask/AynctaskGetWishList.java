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

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityWishListDetails;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanWishListModel;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AynctaskGetWishList extends BaseAsynctask
{
	private ArrayList<BeanWishListModel> mWishListModels;
	private String mCustomerGuId;
	
	public AynctaskGetWishList(Activity activity, boolean isProgressEnabled)
	{
		super(activity, isProgressEnabled);
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mActivity instanceof ActivityWishListDetails)
				((ActivityWishListDetails) mActivity).onWishListObtained(mWishListModels, mCustomerGuId);
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_WISHLIST_DETAILS, false);
		String checkPoint = onResponseReceived(response);
		ZuniUtils.LogEvent(response);
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				Gson gson = new Gson();
				Type type = new TypeToken<ArrayList<BeanWishListModel>>(){}.getType();
				mWishListModels = gson.fromJson(new JSONObject(response).getString("Items"), type);
				mCustomerGuId = new JSONObject(response).optString("CustomerGuid");
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
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
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.bean.BeanProductDetail;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AsynctaskFilter extends BaseAsynctask
{
	private String mSearchText;
	private String mMinimumString;
	private String mMaximumString;
	private String mCategory;
	private String mManufacturers;
	private boolean mSubCategories;
	private boolean mDescription;
	private List<BeanProductDetail> mBeanGetAllProducts;
	private String mResponse;
	private Dialog mFilterDialog;

	public AsynctaskFilter(Activity mActivity, boolean isProgressEnabled, String search, String mMinimumTextView, String mMaximum, 
			String mManufacturers, String mCategory, boolean mSubCategories, boolean mDescription, Dialog mDialog)
	{
		super(mActivity, isProgressEnabled);
		this.mSearchText    = search;
		this.mMinimumString = mMinimumTextView;
		this.mMaximumString = mMaximum;
		this.mManufacturers = mManufacturers;
		this.mCategory      = mCategory;
		this.mSubCategories = mSubCategories;
		this.mDescription   = mDescription;
		this.mFilterDialog  = mDialog;
	}
//	07-31 09:06:40.063: E/Zuni:(11842): Response:{"Warning":"Search term minimum length is 3 characters","NoResults":false,"q":"w","cid":0,"isc":false,"mid":0,"pf":null,"pt":null,"sid":false,"adv":true,"AvailableCategories":[{"Disabled":false,"Group":null,"Selected":false,"Text":"All","Value":"0"},{"Disabled":false,"Group":null,"Selected":false,"Text":"AMNA ISMAIL","Value":"106"},{"Disabled":false,"Group":null,"Selected":false,"Text":"AMNA ISMAIL \u003e\u003e Festive Lawn Collection","Value":"114"},{"Disabled":false,"Group":null,"Selected":false,"Text":"AMNA ISMAIL \u003e\u003e Chiffon Couture Collection","Value":"116"},{"Disabled":false,"Group":null,"Selected":false,"Text":"AMNA ISMAIL \u003e\u003e Chiffon Collection","Value":"111"},{"Disabled":false,"Group":null,"Selected":false,"Text":"AMNA ISMAIL \u003e\u003e Summer/Spring Lawn \u002716","Value":"112"},{"Disabled":false,"Group":null,"Selected":false,"Text":"FARAH LEGHARI","Value":"108"},{"Disabled":false,"Group":null,"Selected":false,"Text":"ZUNI","Value":"107"},{"Disabled":false,"Group":null,"Selected":false,"Text":"Gift Cards","Value":"110"}],"AvailableManufacturers":[{"Disabled":false,"Group":null,"Selected":false,"Text":"All","Value":"0"},{"Disabled":false,"Group":null,"Selected":false,"Text":"AMNA ISMAIL","Value":"16"},{"Disabled":false,"Group":null,"Selected":false,"Text":"Zuni","Value":"17"},{"Disabled":false,"Group":null,"Selected":false,"Text":"FARAH LEGHARI","Value":"18"}],"PagingFilteringContext":[],"Products":[],"CustomProperties":{}}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
            Intent intent = new Intent(mActivity, ActivityProductList.class);
            try
            {
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.POST_LIST_JSON, new JSONObject(mResponse).getString("Products")).commit();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            mActivity.startActivity(intent);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mFilterDialog.dismiss();
                }
            }, 500);
        }
		else
		{
			ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), output, null, null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String doInBackground(String... params)
	{
		this.mResponse = getResponseFromService();
		String checkPoint = onResponseReceived(mResponse);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(mResponse);
				String warn = jsonObject.optString("Warning");
				if (warn != null && !warn.equalsIgnoreCase("null"))
				{
					return warn;
				}
				
				Gson gson = new Gson();
				Type type = new TypeToken<List<BeanGetAllCategory>>(){}.getType();
				this.mBeanGetAllProducts = (List<BeanProductDetail>) gson.fromJson(new JSONObject(mResponse).getString("Products"), type);
			    ZuniUtils.LogEvent(mResponse);
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

	private String getResponseFromService()
	{
		String response = null;
		try
		{
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("q", mSearchText);
			jsonObject.put("adv", true);
			jsonObject.put("cid", mCategory);
			jsonObject.put("isc", mSubCategories);
			jsonObject.put("mid", mManufacturers);
			jsonObject.put("pf", mMinimumString);
			jsonObject.put("pt", mMaximumString);
			jsonObject.put("sid", mDescription);
			jsonObject.put("orderby", "");
			jsonObject.put("pagenumber", "");
			jsonObject.put("pagesize", "");
			
			response = new GetPostSender().sendPostJSON(ZuniConstants.GET_ALL_FILTER_PRODUCTS, jsonObject.toString(), false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return response;
	}
}
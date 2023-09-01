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

import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.bean.ManufacturerBean;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.DialogFilter;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AsynctaskGetFilterData extends BaseAsynctask
{
	private Object mFromWhere;
	private List<ManufacturerBean> mBeanManufacturers;
	private List<BeanGetAllCategory> mBeanCategories;
	
	public AsynctaskGetFilterData(Activity activity,
			boolean isProgressEnabled, Object mPopUp)
	{
		super(activity, isProgressEnabled);
		this.mFromWhere = mPopUp;
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
			((DialogFilter) mFromWhere).onFilterDataReceived(mBeanManufacturers, mBeanCategories);
	}
	
	@Override
	protected String doInBackground(String... params)
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_ALL_MANUFACTURERS, false);
		String checkPoint = onResponseReceived(response);

		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				Gson gson = new Gson();
				Type type = new TypeToken<List<ManufacturerBean>>(){}.getType();
				mBeanManufacturers = (List<ManufacturerBean>) gson.fromJson(response, type);

				ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.MANUFACTURER_JSON, response).commit();
				
				String categoryJson = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CATEGORY_JSON, "");


				Gson categoryGson = new Gson();
				Type categoryType = new TypeToken<List<BeanGetAllCategory>>(){}.getType();
				mBeanCategories = (List<BeanGetAllCategory>) categoryGson.fromJson(categoryJson, categoryType);

                for (int i = 0; i < mBeanCategories.size(); i++)
                    if (mBeanCategories.get(i).getmCategoryName().contains("ift ca"))
                        mBeanCategories.remove(i);

                ZuniUtils.LogEvent(categoryJson);
				
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
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

import org.json.JSONObject;

import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.ActivitySubCategory;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.CategoryDetailBean;
import sa.growonline.footprint.network.GetPostSender;
import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;

public class AsynctaskGetDetailsBySpecId extends BaseAsynctask
{
	private CategoryDetailBean mCategoryDetailBean;
	private Object mFromWhere;
	private String mStringProductArray = "[]";
	private String mUrl;
    private int mPageNumber;

    public AsynctaskGetDetailsBySpecId(Activity activity, String url, int mPageNumber, boolean b)
	{
		super(activity, b);
		this.mUrl = url;
		this.mFromWhere = activity;
        this.mPageNumber = mPageNumber;
    }

	@Override
	protected String doInBackground(String... params)
	{
		
		String responseModel = obtainResponseFromApi();
		String checkPoint = onResponseReceived(responseModel);

		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				String response = new JSONObject(responseModel).getString("Model");
				mCategoryDetailBean = new Gson().fromJson(response, CategoryDetailBean.class);
				mPageNumber = mCategoryDetailBean.getPageNumber();
				/*List<BeanSubCategoryProductForAdapter> mEmptyAdapterList = new ArrayList<BeanSubCategoryProductForAdapter>();
				Gson gson = new Gson();
				Type type = new TypeToken<List<BeanGetAllCategory>>(){}.getType();*/
				
				/*List<BeanGetAllCategory> mFilledSubCategories = (List<BeanGetAllCategory>) gson.fromJson(new JSONObject(response).getString("SubCategories"), type);
				
				for (BeanGetAllCategory beanGetAllCategory : mFilledSubCategories)
				{
					BeanSubCategoryProductForAdapter beanSubCategoryProductForAdapter = new BeanSubCategoryProductForAdapter();
					beanSubCategoryProductForAdapter.setmBean(beanGetAllCategory);
					beanSubCategoryProductForAdapter.setmIsSubCategoryObject(true);
					
					mEmptyAdapterList.add(beanSubCategoryProductForAdapter);
				}
				
				mCategoryDetailBean.setmBeanListForAdapter(mEmptyAdapterList);*/
				
				mStringProductArray = new JSONObject(response).getJSONArray("Products").toString();
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

	private String obtainResponseFromApi() 
	{
		String response = "";
		try
		{
			response = new GetPostSender().sendGet(mUrl + "&PageNumber=" + mPageNumber, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "false";
		}
		return response;
	}

	@Override
	protected void onComplete(String output) 
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mFromWhere instanceof ActivitySubCategory)
			{
//				((ActivitySubCategory) mFromWhere).updateCategories(mCategoryDetailBean);
			}
			else if (mFromWhere instanceof ActivityProductList)
			{
				if (mCategoryDetailBean != null)
				{
					((ActivityProductList) mFromWhere).updateProducts("0", mCategoryDetailBean.getProducts(), mStringProductArray, mCategoryDetailBean.getCategoryName(), mCategoryDetailBean.getPageNumber());
					((ActivityProductList) mFromWhere).updateFilter(mCategoryDetailBean.getSpecsFilter());
				}
			}
		}
		else
		{
			if (mFromWhere instanceof Activity)
				Toast.makeText(((Activity) mFromWhere), output, Toast.LENGTH_LONG).show();
		}
	}

}

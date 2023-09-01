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

import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.checkout.CheckOutBean;
import sa.growonline.footprint.bean.checkout.PaymentMethod;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;

import com.google.gson.Gson;

import java.util.List;

public class AsynctaskGetCheckOutDetails extends BaseAsynctask
{
	private CheckOutBean mCheckOutObj;

	public AsynctaskGetCheckOutDetails(Activity activity, boolean isProgressEnabled)
	{
		super(activity, isProgressEnabled);
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mActivity instanceof ActivityCheckOut)
				((ActivityCheckOut) mActivity).onCheckOutBeanReceived(mCheckOutObj);
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = getResponseFromService();
		String check = onResponseReceived(response);
		
		if (check.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(response);
				
				String success = jsonObject.getString("success");
				String result = jsonObject.getString("result");
				String msg = jsonObject.optString("msg");
				
				if (success.equalsIgnoreCase("true"))
				{
					mCheckOutObj = new Gson().fromJson(result, CheckOutBean.class);

                    if (mCheckOutObj != null && mCheckOutObj.getPaymentMethods() != null  && mCheckOutObj.getPaymentMethods().getPaymentMethods() != null)
                    {
                        List<PaymentMethod> payments = mCheckOutObj.getPaymentMethods().getPaymentMethods();
                        for (int i = 0; i < payments.size(); i++)
                            if (payments.get(i).getName().equalsIgnoreCase("hbl"))
                                mCheckOutObj.getPaymentMethods().getPaymentMethods().remove(i);
                    }
					return "";
				}
				else
				{
					return msg;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "Invalid response coming from the server";
			}
			
		}
		else
		{
			return check;
		}
	}

	private String getResponseFromService()
	{
		String response = "";
		
		try 
		{
			response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_CHECKOUT_DETAILS, false);
			ZuniUtils.LogEvent(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return response;
	}
}
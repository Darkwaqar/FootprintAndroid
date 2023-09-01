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

import org.json.JSONException;
import org.json.JSONObject;

import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;

public class AsynctaskForgetPassword extends BaseAsynctask
{
	private String mEmail;
    
	public AsynctaskForgetPassword(Activity activity, boolean isProgressEnabled, String email)
	{
		super(activity, isProgressEnabled);
		mEmail = email;
	}

	@Override
	protected void onComplete(String output)
	{
		if (!output.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.RECOVER_PASSWORD + mEmail, false);
		String checkPoint = onResponseReceived(response);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(response);
				String success = jsonObject.getString("Success");
				
				if (success.equalsIgnoreCase("false"))
				{
					return jsonObject.getString("Error");
				}
				else
				{
					return jsonObject.getString("Message");
				}
			}
			catch (JSONException e)
			{
				return "Invalid response is coming from the server";
			}
		}
		else
		{
			return checkPoint;
		}
	}
}
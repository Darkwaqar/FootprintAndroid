package sa.growonline.footprint.asynctask;

import java.util.HashMap;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityParallaxHome;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.MultiPartSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;

public class AsynctaskLogin extends BaseAsynctask
{
	private String mUserEmail;
	private String mUserPassword;

	public AsynctaskLogin(Activity activity, String email, String password) 
	{
		super(activity, true);
		this.mUserEmail = email;
		this.mUserPassword = password;
	}

	@Override
	protected String doInBackground(String... params) 
	{
		Editor editor = ZuniApplication.getmAppPrefEditor()/*.putString("", "")*/;
		editor.putString(ZuniConstants.IS_USER_LOGGED_IN, ZuniConstants.SIGNING_IN).commit();
		String response = obtainResponseFromService();
		String checkPoint = onResponseReceived(response);
		ZuniUtils.LogEvent(response);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(response);
				if (jsonObject.getString("Code").equalsIgnoreCase("100"))
				{
					editor.putString(ZuniConstants.USER_EMAIL_PREFS_KEY, mUserEmail);
                    editor.putString(ZuniConstants.SIGNED_IN_FROM, ZuniConstants.LOGIN_MANUAL);
					editor.putString(ZuniConstants.IS_USER_LOGGED_IN, "true");
					editor.putString(ZuniConstants.USER_NAME_PREFS_KEY, mUserEmail.split("@")[0]);
					editor.commit();
					return "";
				}
				else
				{
					return jsonObject.getString("Message");
				}
			}
			catch (Exception exce)
			{
				exce.printStackTrace();
				return "Invalid response is coming from the server...!";
			}
		}
		else
		{
			return checkPoint;
		}
	}

	private String obtainResponseFromService() 
	{
		String response = "";
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try 
		{
			hashMap.put("Email", mUserEmail);
			hashMap.put("Password", mUserPassword);
			
			response = new MultiPartSender().sendPostStringUsingMultiPart(mActivity, ZuniConstants.LOGIN_URL, hashMap, true);//new GetPostSender().sendPostJSON(ZunniConstants.LOGIN_URL, jsonObject.toString());
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
            Intent intent = new Intent(mActivity, ActivityParallaxHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			mActivity.startActivity(intent);
			mActivity.finish();
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), output, null, null);
		}
	}
}
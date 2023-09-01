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

public class AsynctaskRegisterUser extends BaseAsynctask 
{
    private final String mSignInFrom;
    private String mUserPassword;
	private String mUserEmail;
	private String mUserName;
	private boolean mIsFacebook;

	public AsynctaskRegisterUser(Activity activity, String mEmail,
			String mPassword, String username, boolean isFacebook, String signInFrom)
	{
		super(activity, true);
		this.mUserEmail = mEmail;
		this.mUserPassword = mPassword;
		this.mUserName = username;
		this.mIsFacebook = isFacebook;
        this.mSignInFrom = signInFrom;
	}

	@Override
	protected String doInBackground(String... params)
	{
		Editor editor = ZuniApplication.getmAppPrefEditor();

        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.IS_USER_LOGGED_IN, ZuniConstants.SIGNING_IN).commit();

		String response = obtainResponseFromService();
		String checkPoint = onResponseReceived(response);
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(response);
				ZuniUtils.LogEvent(response);
				if (jsonObject.getString("Code").equalsIgnoreCase("102") || jsonObject.getString("Code").equalsIgnoreCase("100"))
				{
					editor.putString(ZuniConstants.USER_EMAIL_PREFS_KEY, mUserEmail);
					editor.putString(ZuniConstants.IS_USER_LOGGED_IN, "true");
					editor.putString(ZuniConstants.USER_NAME_PREFS_KEY, mUserEmail.split("@")[0]);
                    editor.putString(ZuniConstants.SIGNED_IN_FROM, mSignInFrom);
					editor.commit();
					return "";
				}
				else
				{
					return jsonObject.getString("Message");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return mActivity.getString(R.string.InvalidResponse);
			}
		}
		else
		{
			return checkPoint;
		}
	}

	private String obtainResponseFromService() 
	{
		String response;
		HashMap<String, String> jsonObject = new HashMap<>();
		try
		{
			jsonObject.put("Email", mUserEmail);
			jsonObject.put("Password", mUserPassword);
			jsonObject.put("ConfirmPassword", mUserPassword);
			jsonObject.put("FirstName", mUserName);
			jsonObject.put("LastName", "");

			if (!mIsFacebook)
				response = new MultiPartSender().sendPostStringUsingMultiPart(mActivity, ZuniConstants.REGISTER_USER, jsonObject, true);
			else
				response = new MultiPartSender().sendPostStringUsingMultiPart(mActivity, ZuniConstants.LOGIN_AS_FACEBOOK, jsonObject, true);
		}
		catch(Exception exception)
		{
			return "false";
		}
		return response;
	}

	@Override
	protected void onComplete(String output) 
	{
        if (output.equalsIgnoreCase("")) {
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
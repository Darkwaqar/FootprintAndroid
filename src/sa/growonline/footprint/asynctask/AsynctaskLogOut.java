package sa.growonline.footprint.asynctask;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityLogin;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;

public class AsynctaskLogOut extends BaseAsynctask
{
	private Object mFromWhere;

	public AsynctaskLogOut(Activity activity, boolean isProgressEnabled, Object mFromWhere)
	{
		super(activity, isProgressEnabled);
		this.mFromWhere = mFromWhere;
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			Intent intent = new Intent(mActivity, ActivityLogin.class);
			mActivity.startActivity(intent);
			mActivity.finish();
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.LOGOUT, false);
		String checkPoint = onResponseReceived(response);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObj = new JSONObject(response);
				
				String code = jsonObj.getString("Code");
				String msg = jsonObj.getString("Message");
				
				if (code.equalsIgnoreCase("101"))
				{
					Editor editor = ZuniApplication.getmAppPrefEditor();
					editor.putString(ZuniConstants.COOKIE_HANDLER, "");
					editor.putString(ZuniConstants.USER_EMAIL_PREFS_KEY, "");
					editor.putString(ZuniConstants.CART_QUANTITY, "");
					
					editor.putString(ZuniConstants.SELECTED_LOCATION_METHOD_JSON, "");
					editor.putString(ZuniConstants.SELECTED_LOCATION_LISTING_JSON, "");
					editor.putString(ZuniConstants.SELECTED_PAYMENT_JSON, "");
					
					editor.putString(ZuniConstants.IS_USER_LOGGED_IN, "false");
					
					editor.commit();
					return "";
				}
				else
					return msg;
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
	}
}
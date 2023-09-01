package sa.growonline.footprint.base;

import sa.growonline.footprint.ActivityCustomerInfo;
import sa.growonline.footprint.ActivityGiftCard;
import sa.growonline.footprint.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.CalendarContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseAsynctask extends
		AsyncTask<String, Void, String>
{
	protected Activity mActivity;
	private ProgressDialog mDialog;
	private boolean mIsProgressEnabled;
	
	protected abstract void onComplete(String output);
	
	public BaseAsynctask (Activity activity, boolean isProgressEnabled)
	{
		this.mActivity = activity;
		this.mIsProgressEnabled = isProgressEnabled;
	}
	
	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		
		if (mIsProgressEnabled)
			mDialog = ProgressDialog.show(mActivity, "", mActivity.getString(R.string.progress_msg));


	}
	
	@Override
	protected void onPostExecute(String result) 
	{
		super.onPostExecute(result);
        try
        {
            if ( mIsProgressEnabled && mDialog != null && mDialog.isShowing())
            {
                mDialog.dismiss();
            }
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
		onComplete(result);
	}
	
	public String onResponseReceived(String response)
	{
		if (response == null)
			return "No response received..!"; 
		
		if (response.equalsIgnoreCase("false"))
			return "Connection timed out..!"; 

        try
        {
            new JSONObject(response);
            return "";
        }
        catch (JSONException e)
        {
            try
            {
                new JSONArray(response);
                return "";
            }
            catch (JSONException ej)
            {
                return response;
            }
        }
	}
}
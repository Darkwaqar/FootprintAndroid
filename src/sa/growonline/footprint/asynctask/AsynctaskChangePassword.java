package sa.growonline.footprint.asynctask;


import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;


public class AsynctaskChangePassword extends BaseAsynctask {

    private String mOLDPassword;
    private String mNEWPassword;

    public AsynctaskChangePassword(Activity activity, String mOld_psd, String mNew_psd) {
        super(activity, true);
        this.mOLDPassword =mOld_psd;
        this.mNEWPassword =mNew_psd;
    }

    @Override
    protected void onComplete(String output)
    {
        ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
    }

    @Override
    protected String doInBackground(String... strings) {

        JSONObject jsonObject = new JSONObject();
        String response;
        try
        {
            jsonObject.put("OldPassword", mOLDPassword);
            jsonObject.put("NewPassword", mNEWPassword);
            response = new GetPostSender().sendPostJSON(ZuniConstants.Change_Password, jsonObject.toString(), false);


            String checkPoint = onResponseReceived(response);


            if(checkPoint.equalsIgnoreCase("")) //for network connection check
            {
                try
                {
                    JSONObject responseObject = new JSONObject(response);

                    String success = responseObject.getString("Success");

                    if (success.equalsIgnoreCase("true"))
                    {
                        return responseObject.getString("Message");
                    }
                    else
                    {
                        return responseObject.getString("Message");
                    }
                }
                catch (JSONException e)
                {
                    return "Invalid response is coming from server";
                }
            }
            else
            {
                return checkPoint;
            }

        }
        catch(Exception exception)
        {
            return "Irregular response s coming from server";
        }
    }
}
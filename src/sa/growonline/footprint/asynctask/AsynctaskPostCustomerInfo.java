package sa.growonline.footprint.asynctask;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

/**
 * Created by Jawed on 8/29/2016.
 */
public class AsynctaskPostCustomerInfo extends BaseAsynctask
{
    private String mGender;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mDob;
    private String mCompanyName;

    public AsynctaskPostCustomerInfo(Activity activityCustomerInfo, String gender, String FirstName, String LastName, String Dob, String Email,  String CompanyName)
    {
        super(activityCustomerInfo, true);
        mGender = gender;
        mFirstName = FirstName;
        mLastName = LastName;
        mDob = Dob;
        mEmail = Email;
        mCompanyName = CompanyName;
    }

    @Override
    protected void onComplete(String output) {
        ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
    }

    @Override
    protected String doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        String response = "";
        try
        {
            String day = null;
            String month = null;
            String year = null;

            try
            {
                String[] arr = mDob.split("/");
                day = arr[0];
                month = arr[1];
                year = arr[2];
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            jsonObject.put("Gender", mGender);
            jsonObject.put("FirstName", mFirstName);
            jsonObject.put("LastName", mLastName);
            jsonObject.put("dob", mDob);


            jsonObject.put("DateOfBirthDay", day);
            jsonObject.put("DateOfBirthMonth", month);
            jsonObject.put("DateOfBirthYear", year);

            jsonObject.put("Email", mEmail);
            jsonObject.put("Company", mCompanyName);
            response = new GetPostSender().sendPostJSON(ZuniConstants.EDIT_INFO, jsonObject.toString(), false);

            String checkPoint = onResponseReceived(response);

            if (checkPoint.equalsIgnoreCase(""))
            {
                try
                {
                    JSONObject responseObject = new JSONObject(response);

                    String success = responseObject.getString("success");

                    if (success.equalsIgnoreCase("true"))
                    {
                        return  "Your data has been updated successfully...!";
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
            return "Irregular response is coming from server";
        }
    }
}

package sa.growonline.footprint.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import sa.growonline.footprint.ActivityContact;
import sa.growonline.footprint.R;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class AsynctaskContactinfo extends BaseAsynctask
{
    private String mName;
    private String mEmail;
    private String mQuery;



    public AsynctaskContactinfo(ActivityContact activityContact, String Name, String Email, String Query) {
        super(activityContact, true);
        this.mName = Name;
        this.mEmail = Email;
        this.mQuery = Query;
    }

    @Override
    protected void onComplete(String output) {
        ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
    }

    @Override
    protected String doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        String response;
        try
        {
            jsonObject.put("FullName", mName);
            jsonObject.put("Email", mEmail);
            jsonObject.put("Enquiry",mQuery);
            response = new GetPostSender().sendPostJSON(ZuniConstants.CONTACTUS, jsonObject.toString(), false);

            String checkPoint = onResponseReceived(response);

            if (checkPoint.equalsIgnoreCase(""))
            {
                try
                {
                    JSONObject responseObject = new JSONObject(response);

                    String success = responseObject.getString("Success");

                    if (success.equalsIgnoreCase("true"))
                    {
                        return  mActivity.getString(R.string.RequestDelivered);
                    }
                    else
                    {
                        return mActivity.getString(R.string.SomeThingWentWrong);
                    }
                }
                catch (JSONException e)
                {
                    return mActivity.getString(R.string.InvalidResponse);
                }
            }
            else
            {
                return checkPoint;
            }


        }
        catch(Exception exception)
        {
            return mActivity.getString(R.string.IrregularResponse);
        }
    }
}

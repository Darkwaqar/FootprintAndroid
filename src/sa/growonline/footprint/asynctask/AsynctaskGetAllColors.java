package sa.growonline.footprint.asynctask;

import android.app.Activity;

import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;

/**
 * Created by imac on 17/11/16.
 */
public class AsynctaskGetAllColors extends BaseAsynctask
{

    public AsynctaskGetAllColors(Activity activity, boolean isProgressEnabled) {
        super(activity, isProgressEnabled);
    }

    @Override
    protected void onComplete(String output) {

    }

    @Override
    protected String doInBackground(String... strings) {

        String response = new GetPostSender().sendGet(ZuniConstants.BASE_DOMAIN + "/content/colors.json", false);
        String checkPoint = onResponseReceived(response);
        if (checkPoint.equalsIgnoreCase(""))
        {
            try
            {
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.COLOR_LIST_KEY, response).commit();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Invalid response is coming from the server";
            }
        }
        else
        {
            return checkPoint;
        }
        return "";
    }

    public static class ColorModel
    {
        private String color, value;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
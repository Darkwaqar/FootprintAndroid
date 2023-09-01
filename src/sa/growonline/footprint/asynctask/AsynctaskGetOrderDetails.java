package sa.growonline.footprint.asynctask;

import android.app.Activity;

import com.google.gson.Gson;

import sa.growonline.footprint.ActivityOrderDetail;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.OrderDetail;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

/**
 * Created by Jawed on 9/1/2016.
 */
public class AsynctaskGetOrderDetails extends BaseAsynctask {

    private final String mId;
    private OrderDetail mOrderDetail;

    public AsynctaskGetOrderDetails(Activity activity, String mId, boolean isProgressEnabled) {
        super(activity, isProgressEnabled);
        this.mId = mId;
    }

    @Override
    protected void onComplete(String output) {

        if (output.equalsIgnoreCase(""))
            ((ActivityOrderDetail) mActivity).onDetailReceived(mOrderDetail);
        else
            ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
    }

    @Override
    protected String doInBackground(String... strings)
    {
        ZuniUtils.LogEvent(ZuniConstants.BASE_URL + ZuniConstants.GET_ORDER_DETAILS);
        String responseModel = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_ORDER_DETAILS + mId, false);
        String checkPoint = onResponseReceived(responseModel);

        ZuniUtils.LogEvent(responseModel);
        if (checkPoint.equalsIgnoreCase(""))
        {
            try
            {
                mOrderDetail = new Gson().fromJson(responseModel, OrderDetail.class);
                return "";
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
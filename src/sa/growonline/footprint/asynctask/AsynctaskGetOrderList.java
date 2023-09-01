package sa.growonline.footprint.asynctask;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityOrderListing;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanOrder;
import sa.growonline.footprint.bean.BeanOrderListing;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;


/**
 * Created by Jawed on 9/1/2016.
 */
public class AsynctaskGetOrderList extends BaseAsynctask
{
    private BeanOrderListing mOrderListModel;


    public AsynctaskGetOrderList(Activity activity, Boolean isProgressEnable) {

        super(activity, isProgressEnable);
        mOrderListModel = new BeanOrderListing();
    }

    @Override
    protected void onComplete(String output) {
        if (output.equalsIgnoreCase(""))
            if (mActivity instanceof ActivityOrderListing)
                ((ActivityOrderListing) mActivity).onListObtained(mOrderListModel);
        else
            ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
    }

    @Override
    protected String doInBackground(String... strings)
    {
        String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_ORDER_lIST, false);
        ZuniUtils.LogEvent(response);
        String checkpoint = onResponseReceived(response);

        if (checkpoint.equalsIgnoreCase(""))
        {
            try
            {
                ArrayList<BeanOrder> em = new ArrayList<BeanOrder>();
                JSONArray json = new JSONObject(response).getJSONObject("Model").getJSONArray("Orders");
                for (int i = 0; i < json.length();i++)
                {
                    BeanOrder beanOrder = new BeanOrder();
                    JSONObject orderJson = json.getJSONObject(i);

                    beanOrder.setCreatedOn(orderJson.getString("CreatedOn"));
                    beanOrder.setId(orderJson.getString("Id"));
                    beanOrder.setOrderStatus(orderJson.getString("OrderStatus"));
                    beanOrder.setOrderTotal(orderJson.getString("OrderTotal"));

                    em.add(beanOrder);
                }
                mOrderListModel.setOrders(em);
                return "";
            } catch (Exception exe) {
                exe.printStackTrace();
                return "Invalid response is coming from the server";
            }
        }
        else
        {
            return checkpoint;
        }
    }
}

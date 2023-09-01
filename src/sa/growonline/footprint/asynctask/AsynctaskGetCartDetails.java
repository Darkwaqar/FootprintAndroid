/*
 * Copyright (c) 2016 Arsal Raza Imam
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint.asynctask;

import android.app.Activity;

import com.google.gson.Gson;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ActivityProductDetail;
import sa.growonline.footprint.ActivitySplash;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanGetAllCarts;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class AsynctaskGetCartDetails extends BaseAsynctask {
    private BeanGetAllCarts mCartDetailBean;

    public AsynctaskGetCartDetails(Activity activity, boolean isProgressEnabled) {
        super(activity, isProgressEnabled);
    }

    @Override
    protected void onComplete(String output) {
        if (!output.equalsIgnoreCase("")) {
            ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
            return;
        }

        if (mActivity instanceof ActivityCartDetails)
            ((ActivityCartDetails) mActivity).onCartDetailsReceived(mCartDetailBean, false);
        else if (mActivity instanceof ActivitySplash) {

            ((ActivitySplash) mActivity).startApp(mCartDetailBean.getItems().size());
        }
        else if (mActivity instanceof BaseActivityx)
        {
            ((BaseActivityx) mActivity).updateCartQuantity(mCartDetailBean.getItems().size());
        }


    }

    @Override
    protected String doInBackground(String... params) {
        String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_CART_DETAILS, false);
        String responseTotalPriceString = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.CALCULATE_CART_TOTAL, false);

        String checkPoint = onResponseReceived(response);
        String checkpointTotalString = onResponseReceived(responseTotalPriceString);

        if (checkPoint.equalsIgnoreCase("") && checkpointTotalString.equalsIgnoreCase("")) {
            try {
                mCartDetailBean = new Gson().fromJson(response, BeanGetAllCarts.class);
                String total = new JSONObject(responseTotalPriceString).getString("OrderTotal");
                String shippingFees = new JSONObject(responseTotalPriceString).getString("Shipping");
                ZuniUtils.LogEvent(responseTotalPriceString);

                if (total == null || total.equalsIgnoreCase("null"))
                    total = new JSONObject(responseTotalPriceString).getString("SubTotal");

                ZuniUtils.LogEvent("Order Total:" + total);
                mCartDetailBean.setmTotalPaymentPaidByUser(total);
                mCartDetailBean.setmShippingFees(shippingFees);
            } catch (Exception exce) {
                exce.printStackTrace();
                return "Invalid response is coming from the server";
            }
        } else {
            return checkPoint;
        }
        return "";
    }
}
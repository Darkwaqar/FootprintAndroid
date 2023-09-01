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

import org.json.JSONObject;

import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ActivityWishListDetails;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.fragment.FragmentDetailGroup;
import sa.growonline.footprint.fragment.FragmentDetailParent;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

public class AsyncTaskDeleteProductFromCart extends BaseAsynctask {
    private Object mFromWhere;
    private String mProductId;
    private String mCartItemId;


    public AsyncTaskDeleteProductFromCart(Activity mActivity, String id,String valueOf) {
        super(mActivity, true);
        mProductId = id;
        mCartItemId = valueOf;

    }

    public AsyncTaskDeleteProductFromCart(FragmentActivity activity, String s, String updatedShoppingCartItemId, Object fragmentDetailParent) {
        super(activity, true);
        mProductId = s;
        mCartItemId = updatedShoppingCartItemId;
        mFromWhere = fragmentDetailParent;
    }

    @Override
    protected void onComplete(String output) {
        if (output.equalsIgnoreCase(""))
        {
            if (mActivity instanceof ActivityCartDetails) {
                ZuniUtils.showMsgDialog(mActivity, "", "Item has been removed from your shopping cart", null, null);
                ((ActivityCartDetails) mActivity).updateList();
            } else if (mActivity instanceof ActivityWishListDetails) {
                ZuniUtils.showMsgDialog(mActivity, "", mActivity.getString(R.string.delete_wishlist_msg), null, null);
                ((ActivityWishListDetails) mActivity).updateList(false);
            } else if (mFromWhere instanceof FragmentDetailParent) {
                ((FragmentDetailParent) mFromWhere).onDeleteFromWishListComplete();
            } else if (mFromWhere instanceof FragmentDetailGroup) {
                ((FragmentDetailGroup) mFromWhere).onDeleteFromWishListComplete();
            }
        } else {
            ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String response = getResponseFromService();
        String checkPoint = onResponseReceived(response);

        if (checkPoint.equalsIgnoreCase("")) {
            try {
                String quantity = new JSONObject(response).optString("ShoppingCartItemsCount");

                if (quantity != null && !quantity.equalsIgnoreCase(""))
                    ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CART_QUANTITY, quantity).commit();

                return "";
            } catch (Exception exce) {
                exce.printStackTrace();
                return "Invalid response is coming from the server";
            }
        } else {
            return checkPoint;
        }
    }

    private String getResponseFromService() {
        JSONObject jsonObject = new JSONObject();
        String response = "";
        try {
            if (mActivity instanceof ActivityCartDetails) {
                jsonObject.put("productId", mProductId);
                jsonObject.put("removefromcart", mCartItemId);
                response = new GetPostSender().sendPostJSON(ZuniConstants.DELETE_FROM_CART, jsonObject.toString(), false);
            } else if (mActivity instanceof ActivityWishListDetails || mFromWhere instanceof FragmentDetailParent) {
                jsonObject.put("cartitemid", mCartItemId);
                response = new GetPostSender().sendPostJSON(ZuniConstants.DELETE_FROM_WISHLIST, jsonObject.toString(), false);
            }

            ZuniUtils.LogEvent(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response = "";
        }
        return response;
    }
}
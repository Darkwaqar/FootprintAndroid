package sa.growonline.footprint.asynctask;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityGiftCard;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.fragment.FragmentDetailGroup;
import sa.growonline.footprint.fragment.FragmentDetailParent;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.support.v4.app.FragmentActivity;

public class AsynctaskAddToCart extends BaseAsynctask {
    private String mCartType;
    private Object mFromWhere;
    private String mJsonString;
    private String mUpdateCartValue;
    private String mCartId;

    public AsynctaskAddToCart(FragmentActivity activity, String mAttributeHashMap, String type, String updateCart, Object mFromWhere) {
        super(activity, true);

        this.mCartType = type;
        this.mFromWhere = mFromWhere;
        this.mJsonString = mAttributeHashMap;
        this.mUpdateCartValue = updateCart;
    }

    @Override
    protected void onComplete(String output) {
        if (output.equalsIgnoreCase("")) {
            if (mFromWhere instanceof FragmentDetailParent) {
                if (mCartType.equalsIgnoreCase("1"))
                    ((FragmentDetailParent) mFromWhere).onAddToCartComplete();
                else if (mCartType.equalsIgnoreCase("2"))
                    ((FragmentDetailParent) mFromWhere).onAddToWishListComplete("");
            } else if (mFromWhere instanceof FragmentDetailGroup) {
                if (mCartType.equalsIgnoreCase("1"))
                    ((FragmentDetailGroup) mFromWhere).onAddToCartComplete();
                else if (mCartType.equalsIgnoreCase("2"))
                    ((FragmentDetailGroup) mFromWhere).onAddToWishListComplete(mCartId);

            }else if (mActivity instanceof ActivityGiftCard){
                ((ActivityGiftCard) mActivity).onBackPressed();
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
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");
                if (success.equalsIgnoreCase("true"))/*ShoppingCartItemsCount=3*/ {
                    String quantity = jsonObject.optString("ShoppingCartItemsCount");
                    mCartId = jsonObject.optString("cartitemid");
                    ZuniUtils.LogEvent(quantity);

                    if (quantity != null && !quantity.equalsIgnoreCase(""))
                        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CART_QUANTITY, quantity).commit();
                    return "";
                } else {
                    return jsonObject.getString("message");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Invalid response is coming from the server";
            }
        } else {
            return checkPoint;
        }
    }

    private String getResponseFromService() {
        String response;
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            if (mCartType.equalsIgnoreCase("1")) {
                //!-- Shopping
                jsonObject.put("shoppingCartTypeId", "1");
            } else {
                //!-- Wish List
                jsonObject.put("shoppingCartTypeId", "2");
            }
            jsonObject.put("sUpdatedItemId", mUpdateCartValue);

            response = new GetPostSender().sendPostJSON(ZuniConstants.ADD_TO_CART, jsonObject.toString(), false);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
        return response;
    }
}
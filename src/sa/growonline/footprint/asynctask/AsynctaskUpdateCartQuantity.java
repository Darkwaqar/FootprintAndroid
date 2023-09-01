package sa.growonline.footprint.asynctask;

import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanGetAllCarts;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class AsynctaskUpdateCartQuantity extends BaseAsynctask {

	private int mUpdateQuantity;
	private String mProductId;
	private String mCartItemId;
	private BeanGetAllCarts mCartDetailBean;

	public AsynctaskUpdateCartQuantity(Activity activity, boolean isProgressEnabled, String productId, String cartId, int updateQuantity)
	{
		super(activity, isProgressEnabled);
		mUpdateQuantity = updateQuantity;
		mProductId = productId;
		mCartItemId = cartId;
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mActivity instanceof ActivityCartDetails) {
                ZuniUtils.showMsgDialog(mActivity, "", "Your shopping cart has been successfully updated.", null, null);
                ((ActivityCartDetails) mActivity).updateList();
            }
        }
        else
        {
            ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
        }
	}

	@Override
	protected String doInBackground(String... params)
	{
		String response = getResponseFromService();
		String checkPoint = onResponseReceived(response);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(response);

                if (jsonObject.has("message"))
                {
                    return jsonObject.getString("message");
                }
                else
                {
                    mCartDetailBean = new Gson().fromJson(jsonObject.getString("model"), BeanGetAllCarts.class);
                }
				String quantity = jsonObject.optString("ShoppingCartItemsCount");
				
				if (quantity != null && !quantity.equalsIgnoreCase(""))
					ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CART_QUANTITY, quantity).commit();
				
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

	private String getResponseFromService()
	{
		JSONObject jsonObject = new JSONObject();
		String response = "";
		try
		{
			jsonObject.put("productId", mProductId);
			jsonObject.put("cartitemid", mCartItemId);
			jsonObject.put("itemquantity", String.valueOf(mUpdateQuantity));

			response = new GetPostSender().sendPostJSON(ZuniConstants.UPDATE_CART, jsonObject.toString(), false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			response = "";
		}
		return response;
	}
}
package sa.growonline.footprint.asynctask;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityParallaxHome;
import sa.growonline.footprint.R;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.PaymentMethod;
import sa.growonline.footprint.bean.checkout.ShippingMethodBean;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

public class AsynctaskConfirmOrder extends BaseAsynctask
{
	private ExistingAddress mExistingLocation;
	private ShippingMethodBean mLocationMethod;
	private PaymentMethod mPaymentObj;

	public AsynctaskConfirmOrder(Activity activity, boolean isProgressEnabled, ExistingAddress mLocationObj, ShippingMethodBean mLocationMethodObj, PaymentMethod mPaymentObj)
	{
		super(activity, isProgressEnabled);
		this.mExistingLocation = mLocationObj;
		this.mLocationMethod = mLocationMethodObj;
		this.mPaymentObj = mPaymentObj;
		
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), mActivity.getString(R.string.OrderPlacedSuccessfully), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent intent = new Intent(mActivity, ActivityParallaxHome.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					mActivity.startActivity(intent);
				}
			}, null);
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String responseModel = obtainResponseFromApi();
		String checkPoint = onResponseReceived(responseModel);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject mainObject = new JSONObject(responseModel);
				if (mainObject.has("success"))
				{
					return "";
				}
				else
					return mainObject.getString("Warnings");
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
	}

	private String obtainResponseFromApi()
	{
		String response;
		try
		{
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("shippingmethod", String.valueOf(mLocationMethod.getName() + "___" + mLocationMethod.getShippingRateComputationMethodSystemName()));
			jsonObject.put("shippinglocationid", String.valueOf(mExistingLocation.getId()));
			jsonObject.put("paymentmethod", String.valueOf(mPaymentObj.getPaymentMethodSystemName()));
			
			response = new GetPostSender().sendPostJSON(ZuniConstants.CONFIRM_ORDER, jsonObject.toString(), false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "false";
		}
		return response;
	}
}
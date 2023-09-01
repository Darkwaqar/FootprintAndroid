package sa.growonline.footprint.asynctask;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanProductDetail;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.fragment.FragmentDetailGroup;
import sa.growonline.footprint.fragment.FragmentDetailParent;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;

public class AsynctaskGetProductDetails extends BaseAsynctask
{
	private String mProductId;
	private Object mObject;
	private BeanProductDetail mProductDetailBean;
	private String mCartNumber;

	public AsynctaskGetProductDetails(
			Fragment fragmentDetailParent, String mProductId, Object mFromWhere)
	{
		super(fragmentDetailParent.getActivity(), true);
		this.mProductId = mProductId;
		this.mObject = mFromWhere;
	}
	public AsynctaskGetProductDetails(
			Activity activity, String mProductId, Object mFromWhere)
	{
		super(activity, true);
		this.mProductId = mProductId;
		this.mObject = mFromWhere;
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mObject instanceof FragmentDetailParent)
			{
				((FragmentDetailParent) mObject).updateBean(mCartNumber,mProductDetailBean);
			}
            else if (mObject instanceof FragmentDetailGroup)
            {
                ((FragmentDetailGroup) mObject).updateBean(mCartNumber, mProductDetailBean);
            }
        }
		else
		{
			Toast.makeText(mActivity, output, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected String doInBackground(String... params)
	{
		String responseModel = obtainResponseFromService();

		String checkPoint = onResponseReceived(responseModel);
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(responseModel);
				String responsePageModel = jsonObject.getString("PageDetailModel");
				String responseRelatedProductsString = jsonObject.getString("RelatedProducts");
				
				mCartNumber = jsonObject.getString("ShoppingCartItemsCount");
				
				if (mCartNumber != null && mCartNumber.equalsIgnoreCase(""))
					ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CART_QUANTITY, mCartNumber).commit();
				
				mProductDetailBean = new Gson().fromJson(responsePageModel, BeanProductDetail.class);
				
				JSONArray relatedarray = new JSONArray(responseRelatedProductsString);
				ArrayList<BeanProductForCategory> details = new ArrayList<BeanProductForCategory>();
				for (int i = 0; i < relatedarray.length(); i++)
				{
					BeanProductForCategory beanProductDetail = new Gson().fromJson(relatedarray.getString(i), BeanProductForCategory.class);;
					details.add(beanProductDetail);
				}
				mProductDetailBean.setmRelatedProducts(details);
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
		return "";
	}
	
	private String obtainResponseFromService()
	{
		JSONObject jsonObject = new JSONObject();
		String response = "";
		try
		{
			jsonObject.put("productId", mProductId);
			response = new GetPostSender().sendPostJSON(ZuniConstants.GET_PRODUCT_WITH_RELATED_PRODUCTS, jsonObject.toString(), false);
		}
		catch(Exception exception)
		{
			return "false";
		}
		return response;
	}
}
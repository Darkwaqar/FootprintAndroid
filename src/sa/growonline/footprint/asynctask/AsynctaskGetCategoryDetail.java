package sa.growonline.footprint.asynctask;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.ActivityShopTheLook;
import sa.growonline.footprint.ActivitySubCategory;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.bean.CategoryDetailBean;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AsynctaskGetCategoryDetail extends BaseAsynctask {

	private String mCategoryId;
    private int mPageNumber;
    private CategoryDetailBean mCategoryDetailBean;
	private Object mFromWhere;
	private String mStringProductArray = "[]";
	private String mJson;
	private List<BeanProductForCategory> mBeanGetAllProducts;
	private String mCartNumber;
	private String url;

	public AsynctaskGetCategoryDetail(Activity activity, String mCategoryId, String json)
	{
		super(activity, true);

		this.url = ZuniConstants.BASE_URL + ZuniConstants.GET_CATEGORY_DETAILS + mCategoryId;
		this.mCategoryId = mCategoryId;
		this.mFromWhere = activity;
		this.mJson = json;
	}

    public AsynctaskGetCategoryDetail(ActivityProductList activity, String mCategoryId, int mPageNumber, boolean isProgressEnable) {
        super(activity, isProgressEnable);
        mFromWhere = activity;
        this.mCategoryId = mCategoryId;
        this.mPageNumber = mPageNumber;
    }

    @SuppressWarnings("unchecked")
	@Override
	protected String doInBackground(String... params)
	{
		if (mJson != null)
		{
			Gson gson = new Gson();
			Type type = new TypeToken<List<BeanProductForCategory>>(){}.getType();
			this.mBeanGetAllProducts = (List<BeanProductForCategory>) gson.fromJson(mJson, type);
			return "";
		}
		
		String responseModel = obtainResponseFromApi();
		String checkPoint = onResponseReceived(responseModel);

		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject json = new JSONObject(responseModel);
				
				String response = json.getString("Model");
				mCartNumber = json.getString("ShoppingCartItemsCount");
				
				if (mCartNumber != null && mCartNumber.equalsIgnoreCase(""))
					ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CART_QUANTITY, mCartNumber).commit();
				
				mCategoryDetailBean = new Gson().fromJson(response, CategoryDetailBean.class);
				mStringProductArray = new JSONObject(response).getJSONArray("Products").toString();

                ZuniUtils.LogEvent(responseModel);
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

	private String obtainResponseFromApi()
	{
		String response;
		try
		{
            String url = "";
            if (mPageNumber != 0 )
			    url = ZuniConstants.BASE_URL + ZuniConstants.GET_CATEGORY_DETAILS + mCategoryId + "&PageNumber=" + mPageNumber;
            else
                url = ZuniConstants.BASE_URL + ZuniConstants.GET_CATEGORY_DETAILS + mCategoryId;
            ZuniUtils.LogEvent(url);
			response = new GetPostSender().sendGet(url, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "false";
		}
		return response;
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mFromWhere instanceof ActivitySubCategory)
			{
			}
			else if (mFromWhere instanceof ActivityProductList)
			{
				if (mCategoryDetailBean != null)
				{
                       ((ActivityProductList) mFromWhere).updateProducts(mCategoryDetailBean.getProducts(), mStringProductArray, mCategoryDetailBean.getCategoryName(),
                            mCategoryDetailBean.getPageNumber(),
                            mCategoryDetailBean.getMinPrice(), mCategoryDetailBean.getMaxPrice());

					((ActivityProductList) mFromWhere).updateFilter(mCategoryDetailBean.getSpecsFilter());
				}
				else if (mBeanGetAllProducts != null)
					((ActivityProductList) mFromWhere).updateProducts("0", mBeanGetAllProducts, mJson, "Search", 0);
			}
			else if (mFromWhere instanceof ActivityShopTheLook)
			{
				((ActivityShopTheLook) mFromWhere).onBeanObtained(mCategoryDetailBean);
			}
		}
		else
		{
			if (mFromWhere instanceof Activity)
				Toast.makeText(((Activity) mFromWhere), output, Toast.LENGTH_LONG).show();
		}
	}
}
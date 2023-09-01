package sa.growonline.footprint.asynctask;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sa.growonline.footprint.MainActivity;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AsyncTaskGetAllCategories extends BaseAsynctask
{
	private List<BeanGetAllCategory> mBeanGetAllCategories;
	private Object mFromWhere;

	public AsyncTaskGetAllCategories (Activity activity, Object fromWhere)
	{
		super(activity, true);
		mFromWhere = fromWhere;
		mBeanGetAllCategories = new ArrayList<BeanGetAllCategory>();
	}

	@Override
	protected String doInBackground(String... params) 
	{
		String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_ALL_CATEGORY_URL, false);
		String checkPoint = onResponseReceived(response);
		
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				Gson gson = new Gson();
				Type type = new TypeToken<List<BeanGetAllCategory>>(){}.getType();
				@SuppressWarnings("unchecked")
				List<BeanGetAllCategory> mBeanGetAllCategories1 = (List<BeanGetAllCategory>) gson.fromJson(response, type);
				
				Iterator<BeanGetAllCategory> iterable = mBeanGetAllCategories1.iterator();
		        while (iterable.hasNext())
		        {
		        	BeanGetAllCategory beanGetAllCategory = iterable.next();
					if (beanGetAllCategory.getmHasSubCategory().equalsIgnoreCase("false"))
					{
						iterable.remove();
					}
					else
					{
						mBeanGetAllCategories.add(beanGetAllCategory);
					}
		        }
		        mBeanGetAllCategories1.clear();
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

	@Override
	protected void onComplete(String output) 
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mFromWhere instanceof MainActivity)
			{
				ZuniUtils.LogEvent(mBeanGetAllCategories.size() + "");
				((MainActivity) mFromWhere).updateCategories(mBeanGetAllCategories);
			}
		}
		else
		{
			if (mFromWhere instanceof Fragment)
			Toast.makeText((Activity) ((Fragment) mFromWhere).getActivity(), output, Toast.LENGTH_LONG).show();
		}
	}
}
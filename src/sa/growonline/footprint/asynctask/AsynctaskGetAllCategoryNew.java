package sa.growonline.footprint.asynctask;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import sa.growonline.footprint.ActivityHomeNew;
import sa.growonline.footprint.ActivityParallaxHome;
import sa.growonline.footprint.ActivitySplash;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class AsynctaskGetAllCategoryNew extends BaseAsynctask {
    private Object mFromWhere;
    private ArrayList<BeanGetAllCategory> mCategoryList;
    private String mCartTotal;

    public AsynctaskGetAllCategoryNew(Activity activity, Object object, boolean isProgress) {
        super(activity, isProgress);
        this.mFromWhere = object;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String doInBackground(String... params) {
        String response;

        if (ZuniUtils.isNetworkAvailable(mActivity))
            response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_ALL_NAV_CATEGORY_URL, false);
        else
            response = ZuniApplication.getmAppPreferences().getString(ZuniConstants.COMPLETE_CATEGORY_JSON, "{}");

        String checkPoint = onResponseReceive(response);
        if (checkPoint.equalsIgnoreCase("")) {
            try {
                mCategoryList = new ArrayList<>();
                Type listType = new TypeToken<ArrayList<BeanGetAllCategory>>() {
                }.getType();
                JSONObject json = new JSONObject(response);
                mCategoryList.addAll((Collection<BeanGetAllCategory>) new Gson().fromJson(json.getString("Categories"), listType));
                mCartTotal = json.getString("ShoppingCartItemsCount");
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_JSON, new JSONObject(response).getString("Categories")/*.toString()*/).commit();
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.COMPLETE_CATEGORY_JSON, new JSONObject(response).toString()).commit();


                //!-- Remove Gift cards
                for (int i = 0; i < mCategoryList.size(); i++)
                    if (mCategoryList.get(i).getmCategoryId().equalsIgnoreCase("110")) {
                        mCategoryList.remove(i);
                        break;
                    }
            } catch (Exception e) {
                e.printStackTrace();
                return mActivity.getString(R.string.InvalidResponse);
            }
        } else {
            return checkPoint;
        }
        return "";
    }

    private String onResponseReceive(String response) {
        if (response == null) {
            return mActivity.getString(R.string.NoResponse);
        }

        if (response.equalsIgnoreCase("false")) {
            return mActivity.getString(R.string.ConnectionTimeout);
        }
        return "";
    }

    @Override
    protected void onComplete(String output) {
        if (!output.equalsIgnoreCase("")) {
            ZuniUtils.showMsgDialog(mActivity, mActivity.getString(R.string.app_name), output, null, null);
            return;
        }

        if (mFromWhere instanceof ActivityHomeNew) {
            ((ActivityHomeNew) mFromWhere).updateCategories(mCategoryList, mCartTotal);
        }

        if (mFromWhere instanceof ActivityParallaxHome) {
            ((ActivityParallaxHome) mFromWhere).updateCategories(mCategoryList);
        }
    }
}
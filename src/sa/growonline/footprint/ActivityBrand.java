package sa.growonline.footprint;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityBrand extends BaseActivityx {

    private ListView mParallaxView;
    private ArrayList<BrandBespokeModel> mCategoryList;
    public static String mFor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bespoke_brand);
        removeToolbar();

        mParallaxView = (ListView) findViewById(R.id.parallax_home_listview);


        new AsynctaskGetBrandBespokeDetails(ActivityBrand.this, true).execute();
    }

    public void updateCategories(ArrayList<BrandBespokeModel> categoryList) {
        this.mCategoryList = categoryList;

        mFragmentCategoryMenu.loadLastFetchCatrgories();
        mParallaxView.setAdapter(new AdapterBrandBespoke(ActivityBrand.this, categoryList));
    }


    public class AsynctaskGetBrandBespokeDetails extends BaseAsynctask {
        private ArrayList<BrandBespokeModel> mBrandBespokeList;

        public AsynctaskGetBrandBespokeDetails(Activity activityBespokeBrand, boolean b) {
            super(activityBespokeBrand, b);
        }

        @Override
        protected void onComplete(String output) {

            if (output.equalsIgnoreCase(""))
                updateCategories(mBrandBespokeList);
            else
                ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = getResponseFromApi();
            String checkPoint = onResponseReceived(response);
            try {
                if (checkPoint.equalsIgnoreCase("")) {
                    try {
                        mBrandBespokeList = new ArrayList<BrandBespokeModel>();
                        JSONArray arr = new JSONArray(response);
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject object = arr.getJSONObject(i);
                            BrandBespokeModel model = new BrandBespokeModel();
                            model.setName(object.getString("Name"));
                            model.setImage(object.getString("image"));
                            model.setDesc(object.getString("desc"));
                            model.setIsCloseEnable(object.getString("isCloseEnable"));
                            mBrandBespokeList.add(model);
                        }

                        return "";
                    } catch (JSONException e) {
                        return "Invalid response is coming from server";
                    }
                } else {
                    return checkPoint;
                }

            } catch (Exception exception) {
                return "Irregular response s coming from server";
            }
        }

        public String getResponseFromApi() {
            String responseFromApi = "";
            try {
                if (mFor.equalsIgnoreCase("br"))
                    responseFromApi = new GetPostSender().sendPostJSON(ZuniConstants.GET_BRAND + "?type=brand", "", false);
                else
                    responseFromApi = new GetPostSender().sendPostJSON(ZuniConstants.GET_BRAND + "?type=bespoke", "", false);
            } catch (Exception e) {
                e.printStackTrace();
                return e.getLocalizedMessage();
            }
            return responseFromApi;
        }
    }

    private class BrandBespokeModel {
        private String Name, image, desc, isCloseEnable;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIsCloseEnable() {
            return isCloseEnable;
        }

        public void setIsCloseEnable(String isCloseEnable) {
            this.isCloseEnable = isCloseEnable;
        }
    }

    private class AdapterBrandBespoke extends BaseAdapter implements View.OnClickListener {
        private final Activity mActivity;
        private final ArrayList<BrandBespokeModel> mCategoryList;
        private final LayoutInflater mLayoutInflator;

        public AdapterBrandBespoke(Activity activityDashboard, ArrayList<BrandBespokeModel> categoryList) {
            this.mActivity = activityDashboard;
            this.mCategoryList = categoryList;
            mLayoutInflator = LayoutInflater.from(mActivity);
        }

        @Override
        public int getCount() {
            return mCategoryList.size();
        }

        @Override
        public Object getItem(int i) {
            return mCategoryList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View v, ViewGroup viewGroup) {
            if (v == null) v = mLayoutInflator.inflate(R.layout.adapter_brand, viewGroup, false);

            ImageView imageView = (ImageView) v.findViewById(R.id.parallax_imageview);
            View clickLayout = v.findViewById(R.id.adapter_clickable_layout);
            if (mFor.equalsIgnoreCase("bs")) {
                if (i == 0) {
                    ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://footprint-pk.growonlinepk.com/themes/BootstrapDC/Content/bespoke/5.jpg"), imageView, null);
                } else if (i == 1) {
                    ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://footprint-pk.growonlinepk.com/themes/BootstrapDC/Content/bespoke/3.jpg"), imageView, null);
                } else if (i == 2) {
                    ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://footprint-pk.growonlinepk.com/themes/BootstrapDC/Content/bespoke/7.jpg"), imageView, null);
                }
            } else {
                String imageURL = mCategoryList.get(i).getImage().replace("http://test2.growonlinepk.com/content/brandimages", "/Themes/BootstrapDC/content/bespoke");
                ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://" + imageURL), imageView, null);
            }

//            String imageURL = mCategoryList.get(i).getImage().replace("http://test2.growonlinepk.com/content/brandimages","http://footprint-pk.growonlinepk.com/themes/BootstrapDC/Content/bespoke/9.jpg");


//            ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://"+mCategoryList.get(i).getImage()), imageView, null);
//            ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://"+imageURL), imageView, null);
            TextView headView = (TextView) v.findViewById(R.id.adapter_category_name);
            headView.setText(mCategoryList.get(i).getName());


            clickLayout.setTag(i);
            clickLayout.setOnClickListener(this);

            return v;
        }

        @Override
        public void onClick(View view) {
            int i = (Integer) view.getTag();

            final Dialog dialog = new Dialog(mActivity, android.R.style.Theme_Translucent_NoTitleBar);
            dialog.setContentView(R.layout.dialog_brand_bespoke);

            TextView clsView = (TextView) dialog.findViewById(R.id.dialogue_txt_faqs_close);
            ImageView imageView = (ImageView) dialog.findViewById(R.id.parallax_imageview);
            TextView txtView = (TextView) dialog.findViewById(R.id.parallax_heading_txtview);
            TextView headView = (TextView) dialog.findViewById(R.id.adapter_category_name);

            headView.setText(mCategoryList.get(i).getName());
            txtView.setText(Html.fromHtml(mCategoryList.get(i).getDesc()));
            String imageURL = mCategoryList.get(i).getImage().replace("http://test2.growonlinepk.com/content/brandimages", "/Themes/BootstrapDC/content/bespoke");
            ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse("http://" + imageURL), imageView, null);

            clsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }


}
package sa.growonline.footprint.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityGroupProductDetail;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;


/**
 * Created by Jawed on 9/27/2016.
 */
public class AdapterShopTheLook extends RecyclerView.Adapter<AdapterShopTheLook.MyRecyclerViewHolder> {
    private ArrayList<BeanProductForCategory> itemlist;
    public Activity mActivity;

    public AdapterShopTheLook(Activity activity, ArrayList<BeanProductForCategory> mitemlist) {
        this.mActivity = activity;
        this.itemlist = mitemlist;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_straggered_recyclerview, null);
        return new MyRecyclerViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {

        holder.pid = itemlist.get(position).getmProductId();

        holder.RecyclerView_Image.getLayoutParams().height = 745;
        holder.RecyclerView_Image.requestLayout();

        holder.itemView.setTag(position);
        ZuniApplication.getmCacheManager().loadImage(Uri.parse(itemlist.get(position).getImageModel().getThumbImageUrl()), holder.RecyclerView_Image, null);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView RecyclerView_Image;
        public String pid;


        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            RecyclerView_Image = (ImageView) itemView.findViewById(R.id.activity_list_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int tag = (Integer) view.getTag();
            Intent intent = new Intent(mActivity, ActivityGroupProductDetail.class);

            SharedPreferences.Editor editor = ZuniApplication.getmAppPrefEditor();

            editor.putString(ActivityGroupProductDetail.TOTAL_PRODUCTS_KEY, itemlist.size() + "");
            editor.putString(ActivityGroupProductDetail.POSITION_KEY, tag + "");
            editor.putString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, new Gson().toJson(itemlist, ArrayList.class));
            ZuniUtils.LogEvent("List.size(): " + itemlist.size() + ", Position: " + tag);
            editor.commit();
            mActivity.startActivity(intent);
        }
    }
}

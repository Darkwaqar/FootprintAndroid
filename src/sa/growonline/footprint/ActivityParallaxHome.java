package sa.growonline.footprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sa.growonline.footprint.adapter.AdapterDashboard;
import sa.growonline.footprint.asynctask.AsynctaskGetAllCategoryNew;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.NotificationBadge;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.parallax.lib.ParallaxListView;

public class ActivityParallaxHome extends BaseActivityx {
    private ParallaxListView mParallaxView;
    private ImageView mProfileImageView;
    private ImageView mMenuImageView;
    private NotificationBadge badge;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_home);

        removeToolbar();
        removeToolbarbase();

        mParallaxView = (ParallaxListView) findViewById(R.id.parallax_home_listview);
        mMenuImageView = (ImageView) findViewById(R.id.activity_home_menu);
        badge = (NotificationBadge)findViewById(R.id.home_badge);
        mProfileImageView = (ImageView) findViewById(R.id.activity_home_profile);


        new AsynctaskGetAllCategoryNew(ActivityParallaxHome.this, this, true).execute();
    }

    public void updateCategories(ArrayList<BeanGetAllCategory> categoryList) {
        if (categoryList == null) return;
        categoryList.get(1).setCategoryPictureUrl("http://footprint-pk.growonlinepk.com/Themes/BootstrapDC/Content/home-banners/4.jpg");
        categoryList.add(new BeanGetAllCategory(ZuniConstants.BRAND_ID, "Brand", R.drawable.br1));
        categoryList.add(new BeanGetAllCategory(ZuniConstants.BESPOKE_ID, "Bespoke", R.drawable.bes1));
        categoryList.add(new BeanGetAllCategory("-1", "Plus", R.drawable.abc));
        mFragmentCategoryMenu.loadLastFetchCatrgories();
        mParallaxView.setAdapter(new AdapterDashboard(ActivityParallaxHome.this, categoryList));
        mParallaxView.setOnTapListener((ParallaxListView.TapListener) mParallaxView.getAdapter());
        Log.e("NumberOfItemsInCart", String.valueOf(((ZuniApplication) this.getApplication()).getNumberOfItemsInCart()), null);
        badge.setNumber(TotalQuantity);
    }


    public void OpenLeftDrawer(View view) {
        ZuniUtils.toggleNavigation(getmDrawerLayout(), Gravity.START);
    }

    public void OpenCart(View view) {
        startActivity(new Intent(ActivityParallaxHome.this, ActivityCartDetails.class));
    }
}
package sa.growonline.footprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sa.growonline.footprint.adapter.AdapterDashboard;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.NotificationBadge;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.parallax.lib.ParallaxListView;

public class ActivitySubCategory extends BaseActivityx {
    public static ArrayList<BeanGetAllCategory> mSubCategoryList;
    private ParallaxListView mParallaxView;
    private ImageView mProfileImageView;
    private ImageView mMenuImageView;
    private NotificationBadge badge;
    private TextView titleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_home);
        removeToolbar();
        removeToolbarbase();


        String categoryId = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CATEGORY_ID_PREF_KEY, "");

        mParallaxView = (ParallaxListView) findViewById(R.id.parallax_home_listview);

        mMenuImageView = (ImageView) findViewById(R.id.activity_home_menu);
        badge = (NotificationBadge) findViewById(R.id.home_badge);
        mProfileImageView = (ImageView) findViewById(R.id.activity_home_profile);
        titleTextView = (TextView) findViewById(R.id.title);

        badge.setNumber(TotalQuantity);


        if (mSubCategoryList != null)
            updateCategories(mSubCategoryList);
    }

    public void updateCategories(ArrayList<BeanGetAllCategory> categoryList) {
        mFragmentCategoryMenu.loadLastFetchCatrgories();
        mParallaxView.setAdapter(new AdapterDashboard(ActivitySubCategory.this, categoryList));
        mParallaxView.setOnTapListener((ParallaxListView.TapListener) mParallaxView.getAdapter());
    }


    public void OpenLeftDrawer(View view) {
        ZuniUtils.toggleNavigation(getmDrawerLayout(), Gravity.START);
    }

    public void OpenCart(View view) {
        startActivity(new Intent(ActivitySubCategory.this, ActivityCartDetails.class));
    }
}
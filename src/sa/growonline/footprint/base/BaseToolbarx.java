package sa.growonline.footprint.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sa.growonline.footprint.R;

public class BaseToolbarx {
    private AppCompatActivity mActivity;
    private Toolbar mToolbar;
    private RelativeLayout mCustomLayout;
    private TextView mTitleTextView;
    private ImageView mTitleImageView;
    private LinearLayout mToolbarMenuLayout;
    private ToolBarType mToolbarType;
    private ImageView mLeftImageView;

    public enum ToolBarType {DEFAULT, TITLE_IMAGE, CUSTOMIZABLE}

    public BaseToolbarx(AppCompatActivity activity, ToolBarType type) {
        this.mActivity = activity;
        this.mToolbarType = type;
        initToolBar();
    }

    private void initToolBar() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view = layoutInflater.inflate(R.layout.base_toolbar, null);

        mToolbar = (Toolbar) view.findViewById(R.id.base_toolbar_layout);
        mCustomLayout = (RelativeLayout) view.findViewById(R.id.toolbar_custom_layout);
        mTitleTextView = (TextView) view.findViewById(R.id.toolbar_title_textview);
        mTitleImageView = (ImageView) view.findViewById(R.id.toolbar_title_imageview);
        mLeftImageView = (ImageView) view.findViewById(R.id.toolbar_left_nav_imageview);
        mToolbarMenuLayout = (LinearLayout) view.findViewById(R.id.toolbar_menu_layout);

        if (mToolbarType != ToolBarType.DEFAULT)
            mCustomLayout.setVisibility(View.VISIBLE);

//		ZuniUtils.
//					applyHeaderAppFont(mTitleTextView);
    }

    public void setToolBarTitle(String title) {
        if (mToolbarType == ToolBarType.DEFAULT) {
            mToolbar.setTitle(title);
            mTitleTextView.setVisibility(View.GONE);
        } else {
            mToolbar.setTitle("");
            mTitleTextView.setText(title);
            mTitleTextView.setVisibility(View.VISIBLE);
        }
        mTitleImageView.setVisibility(View.GONE);
    }


    public void setToolBarTitle(@StringRes @DrawableRes final int title) {
        String type = mActivity.getResources().getResourceTypeName(title);

        if (type.equalsIgnoreCase("string")) {
            if (mToolbarType == ToolBarType.DEFAULT) {
                mToolbar.setTitle(mActivity.getString(title));
                mTitleTextView.setVisibility(View.GONE);
                mTitleImageView.setVisibility(View.GONE);
            } else {
                mToolbar.setTitle("");
                mTitleTextView.setText(mActivity.getString(title));
                mTitleImageView.setVisibility(View.GONE);
                mTitleTextView.setVisibility(View.VISIBLE);
            }
        } else if (type.equalsIgnoreCase("drawable")) {
            mToolbar.setTitle("");
            mTitleImageView.setImageResource(title);
            mTitleImageView.setVisibility(View.VISIBLE);
            mTitleTextView.setVisibility(View.GONE);
        }
    }

    public void setMenuViews(View[] views) {
        mToolbarMenuLayout.removeAllViews();
        mToolbarMenuLayout.setVisibility(View.VISIBLE);

        for (int i = 0; i < views.length; i++)
            mToolbarMenuLayout.addView(views[i]);
    }

    public void setNavigationProps(@DrawableRes int drawable, View.OnClickListener listener) {
        mLeftImageView.setVisibility(View.VISIBLE);
        mLeftImageView.setImageResource(drawable);
        mLeftImageView.setOnClickListener(listener);
    }

    public void install(AppBarLayout mAppBarLayout) {

        if (mAppBarLayout != null) mAppBarLayout.addView(mToolbar);

        mActivity.setSupportActionBar(mToolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public Toolbar exportToolBar() {
        return mToolbar;
    }

    public void setBackgroundColor(int white) {
        mToolbar.setBackgroundColor(white);
    }
}
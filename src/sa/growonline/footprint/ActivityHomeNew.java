/*
 * Copyright (c) 2016 Arsal Raza Imam
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import sa.growonline.footprint.adapter.AdapterMaterialViewPager;
import sa.growonline.footprint.asynctask.AsynctaskGetAllCategoryNew;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanGetAllCategory;

public class ActivityHomeNew extends BaseActivityx implements OnClickListener, OnPageChangeListener {
    private ViewPager mPageViewPager;
    private ArrayList<BeanGetAllCategory> mCategoryList;
    //	private DrawerLayout mDrawerLayout;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private TabLayout mTablayout;
    private sa.growonline.footprint.view.TextureVideoView mBannerView;
//	private FragmentManager mFragmentManagerObject;
//	private FragmentCategoryMenu mFragmentCategoryMenu;
//	private FragmentProfileMenu mFragmentUserProfileMenu;

    /*private void initActionBar()
    {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        
	    mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.activity_my_account_collapsing_toolbar);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);
//		setupToolBar();
        removeToolbar();
        //!-- Navigation Menus
		/*mFragmentManagerObject = getSupportFragmentManager();

		//!-- Left Category Menu
		if (mFragmentCategoryMenu == null)
			mFragmentCategoryMenu = new FragmentCategoryMenu();
		
		//!-- Right Category Menu
		if (mFragmentUserProfileMenu == null)
			mFragmentUserProfileMenu = new FragmentProfileMenu();
		
		mFragmentManagerObject.beginTransaction().replace(R.id.left_drawer, mFragmentCategoryMenu).commitAllowingStateLoss();
		mFragmentManagerObject.beginTransaction().replace(R.id.right_drawer, mFragmentUserProfileMenu).commitAllowingStateLoss();*/

	/*	initActionBar();*/
        InitUI();

        mBannerView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

		/*mFragmentCategoryMenu.setDrawer(mDrawerLayout);*/
        new AsynctaskGetAllCategoryNew(this, this, true).execute();
        mPageViewPager.setAdapter(null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            if (mBannerView != null && !mBannerView.isPlaying()) {

                //  mBannerView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.sample_video));

                mBannerView.requestFocus();
                mBannerView.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBannerView != null && mBannerView.isPlaying()) {
            mBannerView.stopPlayback();
        }
    }

    private void InitUI() {
        mTablayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mBannerView = (sa.growonline.footprint.view.TextureVideoView) findViewById(R.id.video_view);

        //	ImageView mLeftMenuButton = (ImageView) findViewById(R.id.activity_home_header_left_nav);
        //ImageView mRightMenuButton = (ImageView) findViewById(R.id.activity_home_header_right_nav);

		/*mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);*/
        mPageViewPager = (ViewPager) findViewById(R.id.activity_home_viewpager);
        mPageViewPager.addOnPageChangeListener(this);

        //	mLeftMenuButton.setOnClickListener(this);
        //	mRightMenuButton.setOnClickListener(this);
    }

    public void updateCategories(ArrayList<BeanGetAllCategory> mCategoryList, String mCartTotal) {
        if (mCategoryList == null) return;

        setBannerImage(mCategoryList.get(0).getCategoryPictureUrl());
		/*mFragmentCategoryMenu.updateCategories(mCategoryList);*/

        this.mCategoryList = mCategoryList;

        mPageViewPager.setOffscreenPageLimit(mCategoryList.size());
        mPageViewPager.setAdapter(null);
        mPageViewPager.setAdapter(new AdapterMaterialViewPager(this, mCategoryList));
        mTablayout.setupWithViewPager(mPageViewPager);
    }

    public void setBannerImage(String image) {
//		ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse(image), (ImageView) mBannerView, null);
    }

    public BeanGetAllCategory getCategoryBeans(int mCategoryId) {
        if (mCategoryList != null) {
            return mCategoryList.get(mCategoryId);
        } else {
//			String category = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CATEGORY_JSON, "[]");
//			Type listType = new TypeToken<ArrayList<BeanGetAllCategory>>(){}.getType();//new Gson().fromJson(jsonString, listType).stream()
//			ArrayList<BeanGetAllCategory> a = new ArrayList<BeanGetAllCategory>();
//			a.addAll((Collection<BeanGetAllCategory>) new Gson().fromJson(category, listType));
//			mCategoryList = a;
            return /*mCategoryList.get(mCategoryId)*/ null;
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mCategoryList == null){ mPageViewPager.setAdapter(null); updateBean();}
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
	/*	case R.id.activity_home_header_left_nav:
			ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.LEFT);
			break;*/
			
		/*case R.id.activity_home_header_right_nav:
			ZuniUtils.toggleNavigation(mDrawerLayout, Gravity.RIGHT);
			break;*/
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//		setBannerImage(mCategoryList.get(position).getCategoryPictureUrl());
    }

//    public void updateBean() {
////        isRunning =
//
//        if (mCategoryList == null)
//            new AsynctaskGetAllCategoryNew(this, this, true).execute();
//    }
}
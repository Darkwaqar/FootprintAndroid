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

import sa.growonline.footprint.adapter.AdapterCheckOutViewPager;
import sa.growonline.footprint.asynctask.AsynctaskGetCheckOutDetails;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.checkout.CheckOutBean;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutAddLocation;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutAddPayment;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutConfirmOrder;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutLocationListing;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutLocationMethod;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutPaymentMethod;
import sa.growonline.footprint.view.NonSwipeViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.WindowManager;

public class ActivityCheckOut extends BaseActivityx
{
	private NonSwipeViewPager mCheckOutViewPager;
	private AdapterCheckOutViewPager mAdapterCheckOutViewPager;
	private CheckOutBean mCheckOutObject;
	/*private TextView mTitleTextView;*/


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK)
		{
			if (requestCode == 12)
			{
				new AsynctaskGetCheckOutDetails(ActivityCheckOut.this, true).execute();
			}
		}
	}

	@Override @SuppressWarnings("deprecation")
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_checkout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setupToolBar();
		removeToolbar();
		updateTitle("CheckOut");
		InitUI();
		
	/*	mTitleTextView	= (TextView) findViewById(R.id.top_bar_title_text_view);*/
		
		/*setActionBarTitle("CheckOut: " + "Select Location Method");*/
		/*ZuniUtils.applyAppFont(mTitleTextView);*/
		
		mCheckOutViewPager = (NonSwipeViewPager) findViewById(R.id.checkout_viewpager);
		mCheckOutViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int pos)
			{
				try
				{
					Fragment fragment = ((AdapterCheckOutViewPager) mCheckOutViewPager.getAdapter()).getItem(pos);
					if (fragment instanceof FragmentCheckOutAddLocation)
					{
						((FragmentCheckOutAddLocation) fragment).updateAddLocation(mCheckOutObject.getShippingLocation().getNewAddress());
						setActionBarTitle("CheckOut: " + "Add Location");
					}
					else if (fragment instanceof FragmentCheckOutLocationListing)
					{
						((FragmentCheckOutLocationListing) fragment).updateLocationListing(mCheckOutObject.getShippingLocation().getExistingAddresses());
						setActionBarTitle("CheckOut: " + "Select Location");
					}
					else if (fragment instanceof FragmentCheckOutLocationMethod)
					{
						((FragmentCheckOutLocationMethod) fragment).updateShippingMethods(mCheckOutObject.getShippingMethod());
						setActionBarTitle("CheckOut: " + "Select Location Method");
					}
					else if (fragment instanceof FragmentCheckOutPaymentMethod)
					{
						((FragmentCheckOutPaymentMethod) fragment).updatePaymentMethods(mCheckOutObject.getPaymentMethods());
						setActionBarTitle("CheckOut: " + "Select Payment Method");
					}
					else if (fragment instanceof FragmentCheckOutAddPayment)
					{
						((FragmentCheckOutAddPayment) fragment).updatePaymentMethods(mCheckOutObject.getPaymentMethods());
						setActionBarTitle("CheckOut: " + "Add Payment Details");
					}
					else if (fragment instanceof FragmentCheckOutConfirmOrder)
					{
						((FragmentCheckOutConfirmOrder) fragment).update();
						setActionBarTitle("CheckOut: " + "Confirm Order");
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			@Override public void onPageScrolled(int arg0, float arg1, int arg2){}
			@Override public void onPageScrollStateChanged(int arg0){}
		});

		mAdapterCheckOutViewPager = new AdapterCheckOutViewPager( getSupportFragmentManager());
		mCheckOutViewPager.storeAdapter(mAdapterCheckOutViewPager);
		
		if (mCheckOutObject != null)
		{
			onCheckOutBeanReceived(mCheckOutObject);
		}
		else
		{
			new AsynctaskGetCheckOutDetails(ActivityCheckOut.this, true).execute();
		}
	}
	private void InitUI() {
	/*ImageView mBackImageView = (ImageView) findViewById(R.id.top_bar_backbutton_imgview);
		
		mBackImageView.setOnClickListener(new View.OnClickListener() {
		@Override
			public void onClick(View arg0) {
			  finish();
			}
		});*/
		
	}
	private void setActionBarTitle(String string) {
	//setContentView(sett);	
	/*mTitleTextView.setText(string);*/
	}
	public void onCheckOutBeanReceived(CheckOutBean mCheckOutObj)
	{
		if (mCheckOutObj == null)
			return;
		
		mCheckOutObject = mCheckOutObj;
		if (mCheckOutViewPager.getAdapter() != null)
			((AdapterCheckOutViewPager) mCheckOutViewPager.getAdapter()).updateAllFragments(mCheckOutObject);
		else
		{
			mAdapterCheckOutViewPager = new AdapterCheckOutViewPager(getSupportFragmentManager());
			mCheckOutViewPager.storeAdapter(mAdapterCheckOutViewPager);
		}
		changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_LOCATION);
	}
	
	public void changePage(int pageId)
	{
		try
		{
			if (mCheckOutViewPager.getCurrentItem() == pageId || pageId >= mCheckOutViewPager.getAdapter().getCount())
				return;
			
			mCheckOutViewPager.setCurrentItem(pageId, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
//	public void changePage()
//	{
//		int pageId = 0;
//
//		SharedPreferences mPrefs = ZuniApplication.getmAppPreferences();
////		if (mPrefs.getString(ZuniConstants.SELECTED_LOCATION_LISTING_JSON, "").equalsIgnoreCase(""))
////		{
////			pageId = AdapterCheckOutViewPager.ADAPTER_CHECKOUT_LOCATION;
////		}
////		else if (mPrefs.getString(ZuniConstants.SELECTED_LOCATION_METHOD_JSON, "").equalsIgnoreCase(""))
////		{
////			pageId = AdapterCheckOutViewPager.ADAPTER_CHECKOUT_LOCATION_METHOD;
////		}
////		else if (mPrefs.getString(ZuniConstants.SELECTED_PAYMENT_JSON, "").equalsIgnoreCase(""))
////		{
////			pageId = AdapterCheckOutViewPager.ADAPTER_CHECKOUT_PAYMENT_METHOD;
////		}
////		else
////		{
////			pageId = AdapterCheckOutViewPager.ADAPTER_CHECKOUT_CONFIRM_ORDER;
////		}
////		if (mTitleTextView.get)
//		changePage(pageId);
//	}
}
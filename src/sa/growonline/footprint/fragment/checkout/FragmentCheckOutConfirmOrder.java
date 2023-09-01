package sa.growonline.footprint.fragment.checkout;

import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterCheckOutViewPager;
import sa.growonline.footprint.asynctask.AsynctaskConfirmOrder;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.PaymentMethod;
import sa.growonline.footprint.bean.checkout.ShippingMethodBean;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class FragmentCheckOutConfirmOrder extends Fragment implements OnClickListener
{
	private PaymentMethod mPaymentObj;
	private ExistingAddress mLocationObj;
	private ShippingMethodBean mLocationMethodObj;

	private TextView mPaymentNameTextView, mPaymentFeesTextView, mLocationListingDescTextView, 
					mLocationListingTextView, mLocationMethodFeesTextView, mLocationMethodTextView, 
					mLocationMethodDescTextView;

	private ImageView mPaymentIconTextView;
	private Button mCheckOutConfirmButton;
	private CardView mLocationCardView, mLocationMethodCardView, mPaymentCardView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_checkout_confirm_order, container, false);
		InitUI(view);
		return view;
	}


	public void update()
	{
		retrievingLastSelectedItems();
	}
	
	private void retrievingLastSelectedItems()
	{
		SharedPreferences prefs = ZuniApplication.getmAppPreferences();
		String locationListingString = prefs.getString(ZuniConstants.SELECTED_LOCATION_LISTING_JSON, ""),
				paymentString = prefs.getString(ZuniConstants.SELECTED_PAYMENT_JSON, ""),
						locationMethod = prefs.getString(ZuniConstants.SELECTED_LOCATION_METHOD_JSON, "");
	
		
		mPaymentObj = new Gson().fromJson(paymentString, PaymentMethod.class);
		mLocationObj = new Gson().fromJson(locationListingString, ExistingAddress.class);
		mLocationMethodObj = new Gson().fromJson(locationMethod, ShippingMethodBean.class);
		

		ZuniUtils.LogEvent(locationListingString);
		ZuniUtils.LogEvent(locationMethod);
		setCheckOutDetails();
	}

	private void InitUI(View view)
	{
		//!--- Location Methods Views
		mLocationMethodTextView = (TextView) view.findViewById(R.id.adapter_location_method_loc_heading);
		mLocationMethodDescTextView = (TextView) view.findViewById(R.id.adapter_location_method_loc_method_desc);
		mLocationMethodFeesTextView = (TextView) view.findViewById(R.id.adapter_location_method_loc_price_value);
		
		//!--- Location Listing Views
		mLocationListingTextView = (TextView) view.findViewById(R.id.adapter_location_listing_loc_addr);
		mLocationListingDescTextView = (TextView) view.findViewById(R.id.adapter_location_listing_loc_listing_desc);
		
		//!--- Payment Listing Views
		mPaymentIconTextView = (ImageView) view.findViewById(R.id.adapter_payment_method_icon);
		mPaymentFeesTextView = (TextView) view.findViewById(R.id.adapter_payment_method_loc_price_value);
		mPaymentNameTextView = (TextView) view.findViewById(R.id.adapter_payment_method_name);
		
		//!-- Initializing cardViews
		mLocationMethodCardView = (CardView) view.findViewById(R.id.checkout_confirm_cardview1);
		mLocationCardView = (CardView) view.findViewById(R.id.checkout_confirm_cardview2);
		mPaymentCardView = (CardView) view.findViewById(R.id.payment_method_card_view);
	
		mCheckOutConfirmButton = (Button) view.findViewById(R.id.checkout_confirm_button);
		mCheckOutConfirmButton.setOnClickListener(this);

		mLocationCardView.setOnClickListener(this);
		mLocationMethodCardView.setOnClickListener(this);
		mPaymentCardView.setOnClickListener(this);
	}

	private void setCheckOutDetails()
	{
		mLocationMethodTextView.setText(mLocationMethodObj.getName());
		mLocationMethodDescTextView.setText(mLocationMethodObj.getDescription());
		mLocationMethodFeesTextView.setText(mLocationMethodObj.getFee());
	
		mLocationListingTextView.setText(mLocationObj.getAddress1());
		mLocationListingDescTextView.setText("Postal #: " + mLocationObj.getZipPostalCode() + ", " + mLocationObj.getCity() + ", " + mLocationObj.getCountryName());
	
		mPaymentNameTextView.setText(mPaymentObj.getName());
		mPaymentFeesTextView.setText(String.valueOf(mPaymentObj.getFee()));
		
		ZuniApplication.getmCacheManager().loadImageWithFit(Uri.parse(mPaymentObj.getLogoUrl()), mPaymentIconTextView);
	}

	@Override
	public void onClick(View v)
	{
		if (v == mCheckOutConfirmButton)
		{
			new AsynctaskConfirmOrder(getActivity(), true, mLocationObj, mLocationMethodObj, mPaymentObj).execute();
			return;
		}
		
		if (v == mLocationCardView)
		{
			((ActivityCheckOut) getActivity()).changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_LOCATION);
			return;
		}
		
		if (v == mLocationMethodCardView)
		{
			((ActivityCheckOut) getActivity()).changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_LOCATION_METHOD);
			return;
		}
		
		if (v == mPaymentCardView)
		{
			((ActivityCheckOut) getActivity()).changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_PAYMENT_METHOD);
		}
	}
}
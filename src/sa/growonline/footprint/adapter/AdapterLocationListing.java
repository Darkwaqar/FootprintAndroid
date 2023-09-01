package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.ActivityAddressEntry;
import sa.growonline.footprint.ActivityAddresses;
import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.utils.ZuniConstants;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

public class AdapterLocationListing extends BaseAdapter implements OnClickListener
{
	private FragmentActivity mActivity;
	private List<ExistingAddress> mList;
	private LayoutInflater mLayoutInflater;

	public AdapterLocationListing(FragmentActivity activity, List<ExistingAddress> list)
	{
		this.mActivity = activity;
		this.mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mList = list;
	}

@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void notifyLocationMethodsChanged(List<ExistingAddress> list)
	{
		mList = list;
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		if (view == null)
			view = mLayoutInflater.inflate(R.layout.adapter_location_listing, parent, false);
		
		TextView mNameTextView = (TextView) view.findViewById(R.id.adapter_location_listing_loc_addr);
		TextView mDescTextView = (TextView) view.findViewById(R.id.adapter_location_listing_loc_listing_desc);
		TextView adapterLocationMethodLocPriceLbl = (TextView) view.findViewById(R.id.adapter_location_method_loc_price_lbl);
		TextView adapterLocationMethodLocPriceValue = (AppCompatTextView) view.findViewById(R.id.adapter_location_method_loc_price_value);
		adapterLocationMethodLocPriceLbl.setVisibility(View.GONE);
		adapterLocationMethodLocPriceValue.setVisibility(View.GONE);

		mNameTextView.setText(mList.get(position).getAddress1());
		mDescTextView.setText("Postal #: " + mList.get(position).getZipPostalCode() + ", " + mList.get(position).getCity() + ", "+ mList.get(position).getStateProvinceName() + ", "  + mList.get(position).getCountryName());
		
		view.setTag(position);
		view.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v)
	{
		int pos = (Integer) v.getTag();

		String jsonString = new Gson().toJson(mList.get(pos), ExistingAddress.class);
		ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.SELECTED_LOCATION_LISTING_JSON, jsonString).commit();

		if (mActivity instanceof ActivityCheckOut)
			((ActivityCheckOut) mActivity).changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_LOCATION_METHOD);
		else if (mActivity instanceof ActivityAddresses)
		{
            ActivityAddressEntry.mIsEdit = true;
			mActivity.startActivityForResult(new Intent(mActivity, ActivityAddressEntry.class), 12);
		}
	}
}
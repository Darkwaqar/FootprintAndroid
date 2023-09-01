package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.checkout.ShippingMethodBean;
import sa.growonline.footprint.utils.ZuniConstants;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

public class AdapterLocationMethod extends BaseAdapter implements OnClickListener
{
	private FragmentActivity mActivity;
	private List<ShippingMethodBean> mList;
	private LayoutInflater mLayoutInflater;

	public AdapterLocationMethod(FragmentActivity activity, List<ShippingMethodBean> list)
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

	public void notifyLocationMethodsChanged(List<ShippingMethodBean> list)
	{
		mList = list;
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		if (view == null)
			view = mLayoutInflater.inflate(R.layout.adapter_location_method, parent, false);
		
		TextView mNameTextView = (TextView) view.findViewById(R.id.adapter_location_method_loc_heading);
		TextView mPriceTextView = (TextView) view.findViewById(R.id.adapter_location_method_loc_price_value);
		TextView mDescTextView = (TextView) view.findViewById(R.id.adapter_location_method_loc_method_desc);
		
		mNameTextView.setText(mList.get(position).getName());
		mPriceTextView.setText(mList.get(position).getFee());
		mDescTextView.setText(mList.get(position).getDescription());
		
		view.setTag(position);
		view.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v)
	{
		int pos = (Integer) v.getTag();
	
		String jsonString = new Gson().toJson(mList.get(pos), ShippingMethodBean.class);
		ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.SELECTED_LOCATION_METHOD_JSON, jsonString).commit();
	
		((ActivityCheckOut) mActivity).changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_PAYMENT_METHOD);
	}
}
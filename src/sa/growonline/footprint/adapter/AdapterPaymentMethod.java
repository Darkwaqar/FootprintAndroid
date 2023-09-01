package sa.growonline.footprint.adapter;

import java.util.List;

import com.google.gson.Gson;

import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.bean.checkout.PaymentMethod;
import sa.growonline.footprint.utils.ZuniConstants;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterPaymentMethod extends BaseAdapter implements OnClickListener
{
	private Activity mActivity;
	private List<PaymentMethod> mList;
	private LayoutInflater mLayoutInflator;

	public AdapterPaymentMethod (Activity activity, List<PaymentMethod> list)
	{
		mActivity = activity;
		mList = list;
		mLayoutInflator = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent)
	{
		if (view == null)
			view = mLayoutInflator.inflate(R.layout.adapter_payment_method, parent, false);
		
		ImageView mPaymentIconImageView = (ImageView) view.findViewById(R.id.adapter_payment_method_icon);
		TextView mPaymentTitleTextView = (TextView) view.findViewById(R.id.adapter_payment_method_name);
		TextView mPaymentFeesTextView = (TextView) view.findViewById(R.id.adapter_payment_method_loc_price_value);
		
		mPaymentFeesTextView.setText(String.valueOf(mList.get(position).getFee()));
		mPaymentTitleTextView.setText(mList.get(position).getName());
        try
        {
            ZuniApplication.getmCacheManager().loadImageWithFit(Uri.parse(mList.get(position).getLogoUrl()), mPaymentIconImageView);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		view.setTag(position);
		view.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v)
	{
		int pos = (Integer) v.getTag();
		
		String jsonString = new Gson().toJson(mList.get(pos), PaymentMethod.class);
		ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.SELECTED_PAYMENT_JSON, jsonString).commit();
		
		((ActivityCheckOut) mActivity).changePage(AdapterCheckOutViewPager.ADAPTER_CHECKOUT_CONFIRM_ORDER);
	}

	public void notifyLocationMethodsChanged(List<PaymentMethod> paymentMethods)
	{
		mList = paymentMethods;
		notifyDataSetChanged();
	}
}
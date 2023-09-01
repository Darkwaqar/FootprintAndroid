package sa.growonline.footprint.fragment.checkout;

import sa.growonline.footprint.R;
import sa.growonline.footprint.adapter.AdapterPaymentMethod;
import sa.growonline.footprint.bean.checkout.PaymentMethods;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentCheckOutPaymentMethod extends Fragment
{
	private ListView mPaymentMethodListView;
	private TextView mPaymentMethodTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_payment_listing, container, false);
		InitUI(view);
		return view;
	}
	
	private void InitUI(View view)
	{
		this.mPaymentMethodListView = (ListView) view.findViewById(R.id.frag_payment_listing_listview);
		this.mPaymentMethodTextView = (TextView) view.findViewById(R.id.frag_payment_listing_reward_points_lbl);
		
	}
	
	public void updatePaymentMethods(PaymentMethods paymentMethods)
	{
		if (paymentMethods == null)
			return;

		if (mPaymentMethodListView != null)
		{

			mPaymentMethodTextView.setText(paymentMethods.getRewardPointsBalance() + " REWARD POINTS (" + paymentMethods.getRewardPointsAmount() + ").");
			if (mPaymentMethodListView.getAdapter() == null)
			{
				AdapterPaymentMethod adapter = new AdapterPaymentMethod(getActivity(), paymentMethods.getPaymentMethods());
				mPaymentMethodListView.setAdapter(adapter);
			}
			else
				((AdapterPaymentMethod) mPaymentMethodListView.getAdapter()).notifyLocationMethodsChanged(paymentMethods.getPaymentMethods());
		}
	}
}	
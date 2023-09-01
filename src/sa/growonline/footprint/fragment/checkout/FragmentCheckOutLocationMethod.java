package sa.growonline.footprint.fragment.checkout;

import sa.growonline.footprint.R;
import sa.growonline.footprint.adapter.AdapterLocationMethod;
import sa.growonline.footprint.bean.checkout.ShippingMethod;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentCheckOutLocationMethod extends Fragment
{
	private ListView mLocationMethodListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_location_method, container, false);
		InitUI(view);
		return view;
	}
	
	private void InitUI(View view)
	{
		this.mLocationMethodListView = (ListView) view.findViewById(R.id.frag_location_method_listview);
	}
	
//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser)
//	{
//		super.setUserVisibleHint(isVisibleToUser);
//		ZuniUtils.LogEvent("asd");
//		if (isVisibleToUser /*&& isResumed()*/ && getView() != null)
//		{
//			ZuniUtils.LogEvent("asd");
//			updateShippingMethods(mShippingMethod);
//		}
//	}
	
	public void updateShippingMethods(ShippingMethod shippingMethod)
	{
		if (shippingMethod == null)
			return;
	
		if (mLocationMethodListView != null)
		{
			if (mLocationMethodListView.getAdapter() == null)
			{
				AdapterLocationMethod adapter = new AdapterLocationMethod(getActivity(), shippingMethod.getShippingMethods());
				mLocationMethodListView.setAdapter(adapter);
			}
			else
				((AdapterLocationMethod) mLocationMethodListView.getAdapter()).notifyLocationMethodsChanged(shippingMethod.getShippingMethods());
		}
	}
}
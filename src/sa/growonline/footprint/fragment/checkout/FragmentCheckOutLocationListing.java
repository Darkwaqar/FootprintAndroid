package sa.growonline.footprint.fragment.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import sa.growonline.footprint.ActivityAddressEntry;
import sa.growonline.footprint.ActivityAddresses;
import sa.growonline.footprint.ActivityCheckOut;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterLocationListing;
import sa.growonline.footprint.asynctask.AsynctaskGetShippingLocation;
import sa.growonline.footprint.bean.checkout.ExistingAddress;
import sa.growonline.footprint.bean.checkout.NewAddress;
import sa.growonline.footprint.bean.checkout.ShippingLocation;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

public class FragmentCheckOutLocationListing extends Fragment implements OnClickListener {
    private ListView mLocationListingListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location_listing, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitUI(view);

        if (getActivity() instanceof ActivityAddresses)
            reload();

    }

    private void InitUI(View view) {
        this.mLocationListingListView = (ListView) view.findViewById(R.id.frag_location_listing_listview);
        Button mAddLocationButton = (Button) view.findViewById(R.id.frag_location_add_btn);

        mAddLocationButton.setOnClickListener(this);
    }

    public void updateLocationListing(List<ExistingAddress> shippingMethod) {
        if (shippingMethod == null)
            return;

        if (mLocationListingListView != null) {
            if (mLocationListingListView.getAdapter() == null) {
                AdapterLocationListing adapter = new AdapterLocationListing(getActivity(), shippingMethod);
                mLocationListingListView.setAdapter(adapter);
            } else
                ((AdapterLocationListing) mLocationListingListView.getAdapter()).notifyLocationMethodsChanged(shippingMethod);
        }
    }

    @Override
    public void onClick(View v) {
        if (getActivity() instanceof ActivityCheckOut) {
            ActivityAddressEntry.mIsEdit = false;
            getActivity().startActivityForResult(new Intent(getActivity(), ActivityAddressEntry.class), 12);
        } else if (getActivity() instanceof ActivityAddresses) {
            ActivityAddressEntry.mIsEdit = false;
            getActivity().startActivityForResult(new Intent(getActivity(), ActivityAddressEntry.class), 12);
        }
    }

    public void reload() {
        new AsynctaskGetShippingLocation(getActivity(), true, FragmentCheckOutLocationListing.this).execute();
    }

    public void updateLocationListing(ShippingLocation location) {
        updateLocationListing(location.getExistingAddresses());
        try {
            String jsonString = new Gson().toJson(location.getNewAddress(), NewAddress.class);
            ZuniUtils.LogEvent(jsonString);
            ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.SELECTED_NEW_LOCATION_JSON, jsonString).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
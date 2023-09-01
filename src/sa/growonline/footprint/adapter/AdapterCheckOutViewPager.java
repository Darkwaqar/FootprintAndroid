package sa.growonline.footprint.adapter;

import sa.growonline.footprint.bean.checkout.CheckOutBean;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutAddLocation;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutAddPayment;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutConfirmOrder;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutLocationListing;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutLocationMethod;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutPaymentMethod;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterCheckOutViewPager extends FragmentPagerAdapter {
    public static final int ADAPTER_CHECKOUT_LOCATION = 0;
    public static final int ADAPTER_CHECKOUT_LOCATION_METHOD = 1;
    public static final int ADAPTER_CHECKOUT_PAYMENT_METHOD = 2;
    private static final int ADAPTER_CHECKOUT_ADD_LOCATION = 3;
    private static final int ADAPTER_CHECKOUT_ADD_PAYMENT_METHOD = 4;
    static final int ADAPTER_CHECKOUT_CONFIRM_ORDER = 5;


    private FragmentCheckOutLocationListing mFragmentLocationListing;
    private FragmentCheckOutAddPayment mFragmentCheckOutAddPayment;
    private FragmentCheckOutAddLocation mFragmentCheckOutAddLocation;
    private FragmentCheckOutPaymentMethod mFragmentCheckOutPaymentMethod;
    private FragmentCheckOutLocationMethod mFragmentCheckOutLocationMethod;
    private FragmentCheckOutConfirmOrder mFragmentCheckOutConfirmOrder;

    public AdapterCheckOutViewPager(FragmentManager fm) {
        super(fm);
        mFragmentCheckOutLocationMethod = new FragmentCheckOutLocationMethod();
        mFragmentLocationListing = new FragmentCheckOutLocationListing();
        mFragmentCheckOutPaymentMethod = new FragmentCheckOutPaymentMethod();
        mFragmentCheckOutAddLocation = new FragmentCheckOutAddLocation();
        mFragmentCheckOutAddPayment = new FragmentCheckOutAddPayment();
        mFragmentCheckOutConfirmOrder = new FragmentCheckOutConfirmOrder();

    }

    @Override
    public Fragment getItem(int id) {
        switch (id) {

            case ADAPTER_CHECKOUT_LOCATION:
                return mFragmentLocationListing;

            case ADAPTER_CHECKOUT_LOCATION_METHOD:
                return mFragmentCheckOutLocationMethod;


            case ADAPTER_CHECKOUT_PAYMENT_METHOD:
                return mFragmentCheckOutPaymentMethod;

            case ADAPTER_CHECKOUT_ADD_LOCATION:
                return mFragmentCheckOutAddLocation;

            case ADAPTER_CHECKOUT_ADD_PAYMENT_METHOD:
                return mFragmentCheckOutAddPayment;

            case ADAPTER_CHECKOUT_CONFIRM_ORDER:
                return mFragmentCheckOutConfirmOrder;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

    public void updateAllFragments(CheckOutBean bean) {
        for (int i = 0; i < getCount(); i++) {
            Fragment fragment = getItem(i);

            if (fragment instanceof FragmentCheckOutAddLocation) {
                ((FragmentCheckOutAddLocation) fragment).updateAddLocation(bean.getShippingLocation().getNewAddress());
            } else if (fragment instanceof FragmentCheckOutLocationListing) {
                ((FragmentCheckOutLocationListing) fragment).updateLocationListing(bean.getShippingLocation());
            } else if (fragment instanceof FragmentCheckOutLocationMethod) {
                ((FragmentCheckOutLocationMethod) fragment).updateShippingMethods(bean.getShippingMethod());
            } else if (fragment instanceof FragmentCheckOutPaymentMethod) {
                ((FragmentCheckOutPaymentMethod) fragment).updatePaymentMethods(bean.getPaymentMethods());
            } else if (fragment instanceof FragmentCheckOutAddPayment) {
                ((FragmentCheckOutAddPayment) fragment).updatePaymentMethods(bean.getPaymentMethods());
            } else if (fragment instanceof FragmentCheckOutAddLocation) {
                ((FragmentCheckOutAddLocation) fragment).updateAddLocation(bean.getShippingLocation().getNewAddress());
            }
        }
    }
}
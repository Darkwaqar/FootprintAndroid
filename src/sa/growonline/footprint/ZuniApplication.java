package sa.growonline.footprint;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.facebook.FacebookSdk;
import com.urbanairship.UAirship;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.bean.GiftCard;
import sa.growonline.footprint.bean.NotFilteredItem;
import sa.growonline.footprint.bean.SpecFilter;
import sa.growonline.footprint.urbanair.UrbanAirContext;
import sa.growonline.footprint.utils.ImageCacheManager;

@ReportsCrashes(mailTo = "shaikh.ahmed1972@gmail.com", // my email here
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast)

public class ZuniApplication extends Application {
    private static ImageCacheManager mCacheManager;
    private static SharedPreferences mPreferences;
    private static ArrayList<GiftCard> mAvailableGftCards;
    private List<NotFilteredItem> refineItems;
    private static int mShoppingCartItemsCount;

    public int getNumberOfItemsInCart() {
        return numberOfItemsInCart;
    }

    public void setNumberOfItemsInCart(int numberOfItemsInCart) {
        this.numberOfItemsInCart = numberOfItemsInCart;
    }

    private int numberOfItemsInCart;

    @Override
    public void onCreate() {
        super.onCreate();

        ACRA.init(this);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setmCacheManager(ImageCacheManager.Initialize(getApplicationContext()));
        setmAppPreferences(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
//        UrbanAirContext.setUserNotificationsEnabled(true);
//        UrbanAirContext.setAutoTrackAdvertisingIdEnabled(true);
//        UrbanAirContext.getAnalyticsManager();
//        UrbanAirContext.getUrbanAirLocation();



        // Only available in ICS or newer
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                UrbanAirContext.getAnalyticsManager().trackScreen(activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

    public static ImageCacheManager getmCacheManager() {
        return mCacheManager;
    }

    public static void setmCacheManager(ImageCacheManager mCacheManager) {
        ZuniApplication.mCacheManager = mCacheManager;
    }

    public static SharedPreferences getmAppPreferences() {
        return mPreferences;
    }

    public static void setmAppPreferences(SharedPreferences mPreferences) {
        ZuniApplication.mPreferences = mPreferences;
    }

    public static Editor getmAppPrefEditor() {
        return mPreferences.edit();
    }

    public static void setmAvailableGftCards(ArrayList<GiftCard> mAvailableGftCards) {
        ZuniApplication.mAvailableGftCards = mAvailableGftCards;
    }

    public static ArrayList<GiftCard> getmAvailableGftCards() {
        return mAvailableGftCards;
    }

    public void setRefine(SpecFilter refine) {
        this.refineItems = refine.getNotFilteredItems();
    }

    public List<NotFilteredItem> getRefineItems() {
        return refineItems;
    }


    public static int getShoppingCartItemsCount() {
        return mShoppingCartItemsCount;
    }

    public static void setShoppingCartItemsCount(int shoppingCartItemsCount) {
        ZuniApplication.mShoppingCartItemsCount = shoppingCartItemsCount;
    }
}
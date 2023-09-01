package sa.growonline.footprint.urbanair;

import com.urbanairship.UAirship;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sa.growonline.footprint.ZuniApplication;

/**
 * Created by imac on 08/12/16.
 */
public class UrbanAirContext
{

    private static InAppMessengerConfigs mInAppMessengerConfigs;
    private static UrbanAirAnalytics mUrbanAirAnalytics;
    private static UrbanAirLocation mUrbanAirLocation;

    public static void setUserNotificationsEnabled(boolean b) {
        UAirship.shared().getPushManager().setUserNotificationsEnabled(true);
    }

    public static void setAutoTrackAdvertisingIdEnabled(boolean b) {
        UAirship.shared().getAnalytics().setAutoTrackAdvertisingIdEnabled(true);
    }

    public static UrbanAirAnalytics getAnalyticsManager()   
    {
        if (mUrbanAirAnalytics == null)
            mUrbanAirAnalytics = new UrbanAirAnalytics();

        if (mUrbanAirAnalytics.getAnalyticsStatus())
            mUrbanAirAnalytics.setAnalyticsEnabled(true);

        return mUrbanAirAnalytics;
    }

    public static InAppMessengerConfigs getInAppMessengerConfigs()
    {
        if (mInAppMessengerConfigs == null)
            mInAppMessengerConfigs = new InAppMessengerConfigs();

        return mInAppMessengerConfigs;
    }

    public static UrbanAirLocation getUrbanAirLocation()
    {
        if (mUrbanAirLocation == null)
            mUrbanAirLocation = new UrbanAirLocation();

        mUrbanAirLocation.enableLocationServices(true);
        return mUrbanAirLocation;
    }

    public static void applyTags(String tag)
    {
        UAirship.shared().getPushManager().editTags()
                .addTag(tag)
                .apply();
    }

    public static void removeTags(String tag)
    {
        UAirship.shared().getPushManager().editTags()
                .removeTag(tag)
                .apply();
    }

    public static void setDeviceName(String name)
    {
        UAirship.shared().getNamedUser().setId(name);
    }

    /**
     * Quiet time disables push notification sounds and prevents the phone from vibrating during the set time frame.
     *
     * <b>start and end time should be in 24 hours format</b>
     * @throws ParseException
     * @param starttime
     * @param endTime
     */
    public static void setSleepMode(String starttime, String endTime) throws ParseException {

        // Set the quiet time from 7:30pm - 7:30am
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm", Locale.US);
        Date startDate = formatter.parse(starttime);
        Date endDate = formatter.parse(endTime);

        UAirship.shared().getPushManager().setQuietTimeInterval(startDate, endDate);

        UAirship.shared().getPushManager().setQuietTimeEnabled(true);
    }


}
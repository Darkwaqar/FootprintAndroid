package sa.growonline.footprint.urbanair;

import com.urbanairship.Cancelable;
import com.urbanairship.UAirship;
import com.urbanairship.location.LocationCallback;
import com.urbanairship.location.LocationListener;
import com.urbanairship.location.LocationRequestOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by imac on 08/12/16.
 */
public class UrbanAirLocation {

    public void enableLocationServices(boolean isEnable) {
        // Enable location updates
        UAirship.shared().getLocationManager().setLocationUpdatesEnabled(isEnable);

        // Allow location updates to continue in the background
        UAirship.shared().getLocationManager().setBackgroundLocationAllowed(isEnable);
    }

    public void changeLocationOptions(int priority, int timeInMinutes, int distance) {
        LocationRequestOptions options = new LocationRequestOptions.Builder()
                .setPriority(priority)
                .setMinDistance(distance)
                .setMinTime(timeInMinutes, TimeUnit.MINUTES)
                .create();

        // Set the default continuous location request options
        UAirship.shared().getLocationManager().setLocationRequestOptions(options);
    }

    public void allowContiniousUpdates(boolean a) {
        UAirship.shared().getLocationManager().setLocationUpdatesEnabled(a);
    }

    public void allowBackgroundLocationUpdates(boolean s) {
        UAirship.shared().getLocationManager().setBackgroundLocationAllowed(s);
    }

    public void registerLocationCallback(LocationListener listener) {
        // Add the listener to UALocationManager
        UAirship.shared().getLocationManager().addLocationListener(listener);

        // Remove the listener when finished
        UAirship.shared().getLocationManager().removeLocationListener(listener);
    }

    public void requestForSingleLocation(LocationCallback locationCallback) {
        Cancelable locationRequest = UAirship.shared()
                .getLocationManager()
                .requestSingleLocation(locationCallback);

        // Optionally you can cancel the request and the callback will not be called
        locationRequest.cancel();
    }

    public LocationRequestOptions getLocationOptions(int priority, int timeInMinutes, int distance) {
        return new LocationRequestOptions.Builder()
                .setPriority(priority)
                .setMinDistance(distance)
                .setMinTime(timeInMinutes, TimeUnit.MINUTES)
                .create();
    }

    public void requestForSingleLocation(LocationRequestOptions a, LocationCallback locationCallback) {
            UAirship.shared()
                .getLocationManager()
                .requestSingleLocation(locationCallback, a);

    }
}
package sa.growonline.footprint.urbanair;

import com.urbanairship.UAirship;
import com.urbanairship.analytics.CustomEvent;

/**
 * Created by imac on 08/12/16.
 */
public class UrbanAirAnalytics {

    public void reportEvent(String eventName) {
        CustomEvent.Builder builder = new CustomEvent.Builder(eventName);
        // Record the event
        UAirship.shared().getAnalytics().addEvent(builder.create());
    }

    public void reportEvent(CustomEvent.Builder builder) {
        UAirship.shared().getAnalytics().addEvent(builder.create());
    }


    public CustomEvent.Builder createEventBuilder(String eventName) {
        return new CustomEvent.Builder(eventName);
    }

    public boolean getAnalyticsStatus() {
        return UAirship.shared().getAnalytics().isEnabled();
    }

    public void setAnalyticsEnabled(boolean analyticsEnabled) {
        UAirship.shared().getAnalytics().setEnabled(analyticsEnabled);
    }

    public void trackScreen(String simpleName) {
        UAirship.shared().getAnalytics().trackScreen(simpleName);
    }
}
package sa.growonline.footprint.urbanair;

import com.urbanairship.UAirship;

/**
 * Created by imac on 08/12/16.
 */
public class InAppMessengerConfigs
{
    public void applyDelayInShowingBanner(long secs)
    {
        UAirship.shared()
                .getInAppMessageManager()
                .setAutoDisplayDelay(secs); // 1 second
    }

    public void displayBannerImmidiately()
    {
        UAirship.shared()
                .getInAppMessageManager()
                .setDisplayAsapEnabled(true);
    }

    public void openInbox()
    {
        UAirship.shared().getInbox().startInboxActivity();
    }
}

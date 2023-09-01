package sa.growonline.footprint.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by imac on 22/09/16.
 */
public class DetailActivityViewPager extends ViewPager {

    private boolean isTouchEnabled = true;

    public DetailActivityViewPager(Context context) {
        super(context);
    }

    public DetailActivityViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (isTouchEnabled)
            return super.onTouchEvent(ev);
        else
            return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.isTouchEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }
    public void setViewPagerSwipe(boolean viewPager) {
        isTouchEnabled = viewPager;
    }
}

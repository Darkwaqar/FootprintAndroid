package sa.growonline.footprint.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonSwipeViewPager extends ViewPager {

	private PagerAdapter mPagerAdapter;

	public NonSwipeViewPager(Context context) {
		super(context);
	}

	public NonSwipeViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void setAdapter(PagerAdapter adapter){
		
	}
	public void storeAdapter(PagerAdapter adapter){
		mPagerAdapter = adapter;
	}
	@Override
	protected void onAttachedToWindow(){
		super.onAttachedToWindow();
		if (mPagerAdapter != null){
			super.setAdapter(mPagerAdapter);
		}
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event){
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		return false;
	}
}

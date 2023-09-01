package sa.growonline.footprint.base;

import sa.growonline.footprint.R;
import sa.growonline.footprint.utils.ZuniUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseSlidingActivity extends AppCompatActivity
{
	public static final String LEFT = "left";
	public static final String RIGHT = "right";

	private DrawerLayout mDrawerLayout;
	
	private FrameLayout mRightFrameLayout;
	private FrameLayout mLeftFrameLayout;
	private LinearLayout mMainFrameLayout;

	private FragmentManager mFragmentManager;
	private boolean mIsNotOpened = false;
	private CallBack mCallBack;
	private ActionBarType mActionBarType;
	private RelativeLayout mSimpleActionBarLayout;
	private Toolbar mSimpleActionBar;
	private TextView mSimpleActionBarTextView;
	private ImageView mSimpleCartImageView;
	
	public enum ActionBarType { SIMPLE, DRAWER }
	
	public void setActionBarTitle(String title)
	{
		mSimpleActionBarTextView.setText(title);
		ZuniUtils.LogEvent(mSimpleActionBarTextView.getText().toString() + "asd");
	}
	
	@Override
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		
		if (mActionBarType != null && mActionBarType == ActionBarType.DRAWER)
		{
			mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base_sliding, null);
			
			mLeftFrameLayout = (FrameLayout) mDrawerLayout.findViewById(R.id.left_frame_container);
			mRightFrameLayout = (FrameLayout) mDrawerLayout.findViewById(R.id.right_frame_container);
			mMainFrameLayout = (LinearLayout) mDrawerLayout.findViewById(R.id.main_frame_container);
			
			if (mFragmentManager == null)
				mFragmentManager = getSupportFragmentManager();
		}
		else
		{
			//!--- Simple ActionBar
			mSimpleActionBarLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_base_sliding_simple, null);
			mMainFrameLayout = (LinearLayout) mSimpleActionBarLayout.findViewById(R.id.simple_actionbar_framelayout);
			mSimpleActionBar = (Toolbar) mSimpleActionBarLayout.findViewById(R.id.simple_actionbar);
			mSimpleActionBarTextView = (TextView) mSimpleActionBarLayout.findViewById(R.id.toolbar_title);
			mSimpleCartImageView = (ImageView) mSimpleActionBarLayout.findViewById(R.id.top_bar_cart_imgview);
		}
	}
	
	public void setActionBarType(ActionBarType type)
	{
		this.mActionBarType = type;
	}
	
	public void setOnDrawerStateChange(CallBack callBack)
	{
		this.mCallBack = callBack;
	}
	
	protected void setEnableSwipeGesture(boolean isenable)
	{
		if (mDrawerLayout != null)
			if (isenable)
				mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
			else
				mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
	}

	public boolean isSecondaryMenuShowing()
	{
		return !mIsNotOpened;
	}

	public void toggle(String which)
	{
		if (mIsNotOpened)
		{
			mIsNotOpened = false;
			if (which.equalsIgnoreCase(BaseSlidingActivity.LEFT))
			{
				mDrawerLayout.openDrawer(mLeftFrameLayout);
			}
			else if (which.equalsIgnoreCase(BaseSlidingActivity.RIGHT)) 
			{
				mDrawerLayout.openDrawer(mRightFrameLayout);
			}
			
			if (mCallBack != null)
				mCallBack.onOpen();
		}
		else
		{
			mIsNotOpened = true;
			mDrawerLayout.closeDrawers();
		
			if (mCallBack != null)
				mCallBack.onClose();
		}
	}
	
	protected DrawerLayout getNavigationDrawer()
	{
		return mDrawerLayout;
	}
	
	protected void setFragmentToMenu(Fragment leftMenu, Fragment rightMenu)
	{
		if (leftMenu == null)
		{
			mDrawerLayout.removeView(mLeftFrameLayout);
		}
		else
		{
			mFragmentManager.beginTransaction().replace(R.id.left_frame_container, leftMenu).commitAllowingStateLoss();
		}
		
		if (rightMenu == null)
		{
			mDrawerLayout.removeView(mRightFrameLayout);
		}
		else
		{
			mFragmentManager.beginTransaction().replace(R.id.right_frame_container, rightMenu).commitAllowingStateLoss();
		}
	}
	
	@Override
	public void setContentView(int layoutResID)
	{
		
		if (mDrawerLayout == null || mSimpleActionBarLayout == null)
		{
			if (ActionBarType.DRAWER == mActionBarType)
			{
				mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base_sliding, null); 
				mMainFrameLayout = (LinearLayout) mDrawerLayout.findViewById(R.id.main_frame_container);
			}
			else
			{
				mSimpleActionBarLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_base_sliding_simple, null);
				mMainFrameLayout = (LinearLayout) mSimpleActionBarLayout.findViewById(R.id.simple_actionbar_framelayout);
				mSimpleActionBar = (Toolbar) mSimpleActionBarLayout.findViewById(R.id.simple_actionbar);
				mSimpleActionBarTextView = (TextView) mSimpleActionBarLayout.findViewById(R.id.toolbar_title);
			}
		}
		
		if (ActionBarType.DRAWER == mActionBarType)
		{
			setContentView(mDrawerLayout);
			getLayoutInflater().inflate(layoutResID, mMainFrameLayout, true);
		}
		else
		{
			setContentView(mSimpleActionBarLayout);
			getLayoutInflater().inflate(layoutResID, mMainFrameLayout, true);
		}
	}
	
	public static interface CallBack
	{
		public void onOpen();
		public void onClose();
	}
}

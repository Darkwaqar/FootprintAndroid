package sa.growonline.footprint.base;

import sa.growonline.footprint.ActivityCartDetails;
import sa.growonline.footprint.R;
import sa.growonline.footprint.utils.ZuniUtils;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseTopBar implements OnClickListener
{
	private BaseSlidingActivity mActivity;
	private TextView mTitleTextView;

	public BaseTopBar(BaseSlidingActivity activity)
	{
		this.mActivity = activity;
		InitUI();
	}
	
	private void InitUI()
	{
		ImageView mLeftSideImageView = (ImageView) mActivity.findViewById(R.id.top_bar_nav_imgview);
		ImageView mRightSideImageView = (ImageView) mActivity.findViewById(R.id.top_bar_person_imgview);
		mTitleTextView = (TextView) mActivity.findViewById(R.id.top_bar_title_text_view);
		ImageView mCartImageView = (ImageView) mActivity.findViewById(R.id.top_bar_cart_imgview);
		
		mLeftSideImageView.setOnClickListener(this);
		mCartImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent myIntent = new Intent(mActivity, ActivityCartDetails.class);
	              mActivity.startActivity(myIntent);   	
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		
		case R.id.top_bar_nav_imgview:
			mActivity.toggle(BaseSlidingActivity.LEFT);
			ZuniUtils.LogEvent("left menu");
			break;

		case R.id.top_bar_person_imgview:
			mActivity.toggle(BaseSlidingActivity.RIGHT);
			break;
		}
	}

	public void setTitle(String title)
	{
		mTitleTextView.setText(title);
	}
}
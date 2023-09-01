package sa.growonline.footprint;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import sa.growonline.footprint.asynctask.AsynctaskGetRewardPoints;
import sa.growonline.footprint.base.BaseActivityx;

public class ActivityRewardPoints extends BaseActivityx {

    private TextView mRewardPointsCountTextView;
    private TextView mRedeemRewardPointsAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_points);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setupToolBar();
        removeToolbar();
        updateTitle("Reward Points");

        new AsynctaskGetRewardPoints(ActivityRewardPoints.this).execute();

        InitUI();
    }

    private void InitUI() {
        mRewardPointsCountTextView = (TextView) findViewById(R.id.activity_reward_points_val);
        mRedeemRewardPointsAmountTextView = (TextView) findViewById(R.id.activity_redeem_points_val);
    }

    public void onRewardPointsObtained(String mRewardPointAmount,
                                       String mRewardPointCount) {
        if (mRewardPointAmount == null || mRewardPointCount == null) return;

        mRewardPointsCountTextView.setText(mRewardPointCount + " REWARD POINTS (" + mRewardPointAmount + ").");
        mRedeemRewardPointsAmountTextView.setText(0 + " REWARD (" + 0 + ").");
    }
}
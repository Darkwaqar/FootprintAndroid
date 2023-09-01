package sa.growonline.footprint.asynctask;

import org.json.JSONObject;

import sa.growonline.footprint.ActivityRewardPoints;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;

public class AsynctaskGetRewardPoints extends BaseAsynctask {

	private String mRewardPointAmount;
	private String mRewardPointCount;

	public AsynctaskGetRewardPoints(Activity activity)
	{
		super(activity, true);
	}

	@Override
	protected void onComplete(String output)
	{
		if (output.equalsIgnoreCase(""))
		{
			if (mActivity instanceof ActivityRewardPoints)
				((ActivityRewardPoints) mActivity).onRewardPointsObtained(mRewardPointAmount, mRewardPointCount);
		}
		else
		{
			ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
		}
			
	}

	@Override
	protected String doInBackground(String... params) {
		String responseModel = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_REWARD_POINTS, false);

		String checkPoint = onResponseReceived(responseModel);
		if (checkPoint.equalsIgnoreCase(""))
		{
			try
			{
				JSONObject jsonObject = new JSONObject(responseModel);
				String success = jsonObject.getString("success");
				
				if (success.equalsIgnoreCase("true"))
				{
					mRewardPointAmount = jsonObject.getString("rewardPointsAmount");
					mRewardPointCount = jsonObject.getString("rewardPointsBalance");
					return "";
				}
				else
				{
                    String msg = jsonObject.getString("message");
					return msg;
				}
			}
			catch (Exception exce)
			{
				exce.printStackTrace();
				return "Invalid response is coming from the server";
			}
		}
		else
		{
			return checkPoint;
		}
	}

}

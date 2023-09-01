package sa.growonline.footprint.asynctask;

import android.app.Activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import sa.growonline.footprint.ActivityGiftCard;
import sa.growonline.footprint.base.BaseAsynctask;
import sa.growonline.footprint.bean.GiftCard;
import sa.growonline.footprint.network.GetPostSender;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

/**
 * Created by Basit on 9/4/2016.
 */
public class AsynctaskGetGiftCards extends BaseAsynctask {

    private ArrayList<GiftCard> mGiftCardList;

    public AsynctaskGetGiftCards(Activity activity, boolean isProgressEnabled) {
        super(activity, isProgressEnabled);
    }

    @Override
    protected void onComplete(String output) {
        if (output.equalsIgnoreCase("")) {
            if (mActivity instanceof ActivityGiftCard) {
                ((ActivityGiftCard) mActivity).recievedGiftCards(mGiftCardList);
            }
        } else {
            ZuniUtils.showMsgDialog(mActivity, "", output, null, null);
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = new GetPostSender().sendGet(ZuniConstants.BASE_URL + ZuniConstants.GET_GIFT_CARDS, false);
        String checkPoint = onResponseReceived(response);

        if (checkPoint.equalsIgnoreCase("")) {
            try {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<GiftCard>>() {
                }.getType();

                JSONObject obj = new JSONObject(response);
                mGiftCardList = gson.fromJson(obj.getJSONArray("giftCards").toString(), type);

            } catch (Exception ex) {
                ex.printStackTrace();
                return ex.toString();
            }
        } else {
            return checkPoint;
        }
        return "";
    }


}

package sa.growonline.footprint;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;

import sa.growonline.footprint.adapter.AdapterGiftCard;
import sa.growonline.footprint.asynctask.AsynctaskAddToCart;
import sa.growonline.footprint.asynctask.AsynctaskGetGiftCards;
import sa.growonline.footprint.asynctask.AsynctaskGetUserDetails;
import sa.growonline.footprint.base.BaseToolbarx;
import sa.growonline.footprint.bean.BeanUserDetails;
import sa.growonline.footprint.bean.GiftCard;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityGiftCard extends AppCompatActivity {

    private BaseToolbarx mBaseToolbarx;
    Spinner giftCardSpinner;
    AdapterGiftCard adapterGiftCard;
    private EditText txtRcpName, txtRcpEmail, txtSndName, txtSndEmail, txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_card);


        initUI();
    }

    private void initUI() {

        mBaseToolbarx = new BaseToolbarx(ActivityGiftCard.this, BaseToolbarx.ToolBarType.CUSTOMIZABLE);
        mBaseToolbarx.setToolBarTitle("GIFT");
        mBaseToolbarx.setNavigationProps(R.drawable.backbutton_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new AsynctaskGetUserDetails(ActivityGiftCard.this, true).execute();
//        ImageView mCartDetails = new ImageView(this);
//        mCartDetails.setPadding(10, 10, 10, 10);
//        mCartDetails.setImageResource(R.drawable.bag);
//
//        mCartDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ActivityGiftCard.this, ActivityCartDetails.class));
//            }
//        });
//
//        mBaseToolbarx.setMenuViews(new View[]{mCartDetails});
        mBaseToolbarx.install((AppBarLayout) findViewById(R.id.appbar_gift_card));


        //git-card drop down
        giftCardSpinner = (Spinner) findViewById(R.id.spinner_gift_card_item);

        if (ZuniApplication.getmAvailableGftCards() == null) {
            adapterGiftCard = new AdapterGiftCard(this);
            new AsynctaskGetGiftCards(this, false).execute();
        } else {
            adapterGiftCard = new AdapterGiftCard(this, ZuniApplication.getmAvailableGftCards());
        }

        giftCardSpinner.setAdapter(adapterGiftCard);


        txtRcpName = (EditText) findViewById(R.id.txt_recepient_name);
        txtRcpEmail = (EditText) findViewById(R.id.txt_recepient_email);
        txtSndName = (EditText) findViewById(R.id.txt_from_name);
        txtSndEmail = (EditText) findViewById(R.id.txt_your_email);
        txtMessage = (EditText) findViewById(R.id.txt_message);


        //send now
        AppCompatButton btnSend = (AppCompatButton) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateInfo()) {

                    GiftCard selectedGiftCard = (GiftCard) giftCardSpinner.getSelectedItem();

                    addToCart(selectedGiftCard.getId(), txtRcpName.getText().toString(), txtRcpEmail.getText().toString(),
                            txtSndName.getText().toString(), txtSndEmail.getText().toString(), txtMessage.getText().toString());
                }
            }
        });

        AppCompatButton btnCancel = (AppCompatButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void recievedGiftCards(ArrayList<GiftCard> giftCards) {
        ZuniApplication.setmAvailableGftCards(giftCards);
        adapterGiftCard.setGiftCardItems(giftCards);

        adapterGiftCard.notifyDataSetChanged();
    }

    void addToCart(int productId, String recepientName, String recepientEmail, String senderName, String senderEmail, String message) {
        if (validateInfo()) {

            JSONObject mJsonRequest = new JSONObject();
            try {

                String attrPrefix = "giftcard_" + productId + ".";

                mJsonRequest.put("sQuantity", "1");
                mJsonRequest.put("productId", productId);

                JSONObject mAttrObj = new JSONObject();

                mAttrObj.put(attrPrefix + "SenderName", senderName);
                mAttrObj.put(attrPrefix + "SenderEmail", senderEmail);
                mAttrObj.put(attrPrefix + "Message", message);
                mAttrObj.put(attrPrefix + "RecipientName", recepientName);
                mAttrObj.put(attrPrefix + "RecipientEmail", recepientEmail);

                mJsonRequest.put("attributesJson", mAttrObj.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            new AsynctaskAddToCart(this, mJsonRequest.toString(), "1", "1", ActivityGiftCard.this).execute();

        }
    }

    boolean validateInfo() {

        String errorMessage = "";
        boolean valid = true;
        if (txtRcpName.getText().toString().isEmpty()) {
            errorMessage = "Please provide Recipient's name";
            valid = false;
        } else if (txtRcpEmail.getText().toString().isEmpty()) {
            errorMessage = "Please provide Recipient's email";
            valid = false;
        } else if (txtSndName.getText().toString().isEmpty()) {
            errorMessage = "Please provide Your name";
            valid = false;
        } else if (txtSndEmail.getText().toString().isEmpty()) {
            errorMessage = "Please provide Your email";
            valid = false;
        } else if (txtMessage.getText().toString().isEmpty()) {
            errorMessage = "Please provide Message";
            valid = false;
        }

        if (!valid) {
            ZuniUtils.showMsgDialog(this, "Error", errorMessage, null, null);
        }

        return valid;
    }

    public void onAddToCartComplete() {
        finish();
    }


    public void updateDetails(BeanUserDetails mBeanUserDetails) {

        if (mBeanUserDetails == null) return;


        txtSndName.setText(mBeanUserDetails.getFirstName());
        txtSndName.setEnabled(false);
        txtSndName.setFocusable(false);
        txtSndEmail.setText(mBeanUserDetails.getEmail());
        txtSndEmail.setEnabled(false);
        txtSndEmail.setFocusable(false);


    }
}

package sa.growonline.footprint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sa.growonline.footprint.asynctask.AsynctaskContactinfo;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.utils.ZuniUtils;

public class ActivityContact extends BaseActivityx implements View.OnClickListener {
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mQueryEditText;
    private Button mButtonSendText;
    private String mName;
    private String mEmail;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
removeToolbar();
        mNameEditText = (EditText) findViewById(R.id.activity_send_name);
        mEmailEditText = (EditText) findViewById(R.id.activity_send_email);
        mQueryEditText = (EditText) findViewById(R.id.activity_send_query);
        mButtonSendText = (Button) findViewById(R.id.messenger_send_button);

        findViewById(R.id.heading2).setOnClickListener(this);

        mButtonSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mName = ZuniUtils.getText(mNameEditText);
                mEmail = ZuniUtils.getText(mEmailEditText);
                mQuery = ZuniUtils.getText(mQueryEditText);


                if (mName.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityContact.this, "", getString(R.string.error_user_name), null, null);
                    return;
                }


                if (mEmail.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityContact.this, "", getString(R.string.error_email), null, null);
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
                    ZuniUtils.showMsgDialog(ActivityContact.this, "", "Please enter a valid email address...", null, null);
                    return;
                }

                if (mQuery.isEmpty()) {
                    ZuniUtils.showMsgDialog(ActivityContact.this, "", getString(R.string.error_query), null, null);
                    return;
                }

                if (ZuniUtils.isNetworkAvailable(getApplicationContext())) {
                    new AsynctaskContactinfo(ActivityContact.this, mName, mEmail, mQuery).execute();
                } else {
                    ZuniUtils.showMsgDialog(ActivityContact.this, getString(R.string.app_name), getString(R.string.no_internet), null, null);
                }
            }


        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.heading2:
                String smsNumber="+92331288193@s.whatsapp.net";
                Uri uri = Uri.parse("smsto:" + smsNumber);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", "Bilal Farooqui");
                i.setPackage("com.whatsapp");
                startActivity(i);
                break;
        }
    }
}
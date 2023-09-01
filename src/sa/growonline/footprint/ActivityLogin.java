package sa.growonline.footprint;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import sa.growonline.footprint.asynctask.AsynctaskForgetPassword;
import sa.growonline.footprint.asynctask.AsynctaskLogin;
import sa.growonline.footprint.asynctask.AsynctaskRegisterUser;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

public class ActivityLogin extends AppCompatActivity implements OnClickListener {
    private EditText mPasswordEditText;
    private EditText mEmailEditText;
    private String mEmail;
    private String mPassword;
    private Button mFacebookLoginButton,mGuestLoginButton;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCallbackManager = CallbackManager.Factory.create();
        InitUI();
    }

    private void registerWithFacebook()
    {
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object,GraphResponse response) {
                                        // Application code
                                        JSONObject object2 = response.getJSONObject();
                                        ZuniUtils.LogEvent(response.toString());
                                        try {
                                            new AsynctaskRegisterUser(ActivityLogin.this, object2.getString("email"), object2.getString("id"), object2.getString("first_name"), true, ZuniConstants.FACEBOOK).execute();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                        request.setParameters(parameters);
                        request.executeAsync();
                  }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        ZuniUtils.LogEvent("error: " + exception.getLocalizedMessage());
                        if (exception instanceof FacebookAuthorizationException) {
                            if (AccessToken.getCurrentAccessToken() != null) {
                                LoginManager.getInstance().logOut();
                            }
                        }

                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(ActivityLogin.this, Arrays.asList("public_profile", "email", "user_friends"));
    }

    private void InitUI() {
        TextView mRegisterTextView =(TextView) findViewById(R.id.activity_login_join_us);
        Button mLoginButton = (Button) findViewById(R.id.activity_login_button);
        Button mGuestButton = (Button) findViewById(R.id.gu_login_join_us);
        mPasswordEditText = (EditText) findViewById(R.id.activity_login_password_edittext);
        mEmailEditText = (EditText) findViewById(R.id.activity_login_email_edittext);
        mFacebookLoginButton = (Button) findViewById(R.id.fb_login_join_us);
        TextView mForgotPassword = (TextView) findViewById(R.id.activity_login_forget_password);

        mGuestButton.setOnClickListener(this);
        mFacebookLoginButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.gu_login_join_us:
                String uniqueId;
                try
                {
                    uniqueId = ZuniUtils.getDeviceId(ActivityLogin.this);
                }
                catch (SecurityException e)
                {
                    e.printStackTrace();
                    uniqueId = new BigInteger(130, new SecureRandom()).toString(32);
                }
                new AsynctaskRegisterUser(ActivityLogin.this,  uniqueId + "@growonlinepk.com", "growonlinepk", "Guest User", true, ZuniConstants.GUEST_USER).execute();
                break;

            case R.id.activity_login_join_us:
                startActivity(new Intent(ActivityLogin.this, ActivitySignUp.class));
                break;

            case R.id.activity_login_forget_password:
                AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityLogin.this);

                dialog.setTitle("Forgot password?");

                final EditText editText = new EditText(ActivityLogin.this);
                editText.setHint("Email");
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                dialog.setView(editText);

                dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String email = editText.getText().toString();
                        if (ZuniUtils.validateEmail(email)) {
                            new AsynctaskForgetPassword(ActivityLogin.this, true, email).execute();


                        } else
                            ZuniUtils.showMsgDialog(ActivityLogin.this, "", getString(R.string.invalid_email), null, null);
                    }
                });
                dialog.create().show();
                break;

            case R.id.activity_login_button:
                if (validateInfo())
                    new AsynctaskLogin(this, mEmail, mPassword).execute();
                break;

            case R.id.fb_login_join_us:
                registerWithFacebook();
                break;
        }
    }

    private boolean validateInfo() {
        mEmail = mEmailEditText.getText().toString();
        mPassword = mPasswordEditText.getText().toString();

        if (mEmail.isEmpty()) {
            ZuniUtils.showMsgDialog(this, "", getString(R.string.error_email), null, null);
            return false;
        }

        if (!ZuniUtils.validateEmail(mEmail)) {
            ZuniUtils.showMsgDialog(this, "", getString(R.string.invalid_email), null, null);
            return false;
        }

        if (mPassword.isEmpty()) {
            ZuniUtils.showMsgDialog(this, "", getString(R.string.error_password), null, null);
            return false;
        }
        return true;
    }
}
package sa.growonline.footprint;

import sa.growonline.footprint.asynctask.AsynctaskRegisterUser;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

 public class ActivitySignUp extends AppCompatActivity implements OnClickListener,Toolbar.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener {
	 private EditText mPasswordEditText;
	private EditText mEmailEditText;

	private String mEmail,mPassword,mDobDay, mDobMonth, mDobYear;
	private EditText mConfirmPasswordEditText;
	private EditText mSignUpName;
	private String mUserName;
	private TextView mTextviewDay;
	private TextView mTextviewMonth;
	private TextView mTextviewYear;
	private PopupMenu mDaysPopupMenu;
	private PopupMenu mMonthPopupMenu;
	private PopupMenu mYearPopupMenu;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		InitUI();
	}

	private void InitUI() 
	{
		TextView mSignUpButton = (TextView) findViewById(R.id.activity_registration_register_btn);
		mSignUpName = (EditText) findViewById(R.id.activity_registration_fullname);
		mEmailEditText = (EditText) findViewById(R.id.activity_registration_email);
		mPasswordEditText = (EditText) findViewById(R.id.activity_registration_password);
		mConfirmPasswordEditText = (EditText) findViewById(R.id.activity_registration_confirm_password);

		mTextviewDay 		=       (TextView) findViewById(R.id.activity_registration_days);
		mTextviewMonth 		=       (TextView) findViewById(R.id.activity_registration_month);
		mTextviewYear 		=       (TextView) findViewById(R.id.activity_registration_year);


		mDaysPopupMenu 		= new PopupMenu(ActivitySignUp.this, mTextviewDay);
		mMonthPopupMenu 	= new PopupMenu(ActivitySignUp.this, mTextviewMonth);
		mYearPopupMenu 		= new PopupMenu(ActivitySignUp.this, mTextviewYear);


		for(int i = 0; i < 31; i++)	mDaysPopupMenu.getMenu().add(1, R.id.activity_signup_day, i + 1, String.valueOf(i + 1));
		for(int i = 0; i < 12; i++) mMonthPopupMenu.getMenu().add(1, R.id.activity_signup_month, i + 1, String.valueOf(i + 1));
		for(int i = 0; i < 67; i++) mYearPopupMenu.getMenu().add(1, R.id.activity_signup_year, i + 1, String.valueOf(1950 + i));

		mTextviewDay.setOnClickListener(this);
		mTextviewMonth.setOnClickListener(this);
		mTextviewYear.setOnClickListener(this);
		mSignUpButton.setOnClickListener(this);

		mDaysPopupMenu.setOnMenuItemClickListener(this);
		mMonthPopupMenu.setOnMenuItemClickListener(this);
		mYearPopupMenu.setOnMenuItemClickListener(this);
	}

	
	private void registerUser()
	{
		if (ZuniUtils.isNetworkAvailable(getApplicationContext()))
		{
			if (validateInfo())
				new AsynctaskRegisterUser(ActivitySignUp.this, mEmail, mPassword, mUserName, false, ZuniConstants.MANUAL_SIGN_IN).execute();
		}
		else
		{
			ZuniUtils.showMsgDialog(this, getString(R.string.app_name), getString(R.string.no_internet), null, null);
		}
		
	}
	
	private boolean validateInfo() 
	{
		mEmail = ZuniUtils.getText(mEmailEditText);
		mPassword = ZuniUtils.getText(mPasswordEditText);
		mUserName = ZuniUtils.getText(mSignUpName);
		mDobDay =ZuniUtils.getText(mTextviewDay);
		mDobMonth =ZuniUtils.getText(mTextviewMonth);
		mDobYear =ZuniUtils.getText(mTextviewYear);


		String mConfirmPassword = ZuniUtils.getText(mConfirmPasswordEditText);

		if (mEmail.isEmpty())
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.error_email), null, null);
			return false;
		}

		if (mUserName.isEmpty())
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.error_user_name), null, null);
			return false;
		}

		if (!ZuniUtils.validateEmail(mEmail))
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.invalid_email), null, null);
			return false;
		}

		if (mPassword.isEmpty())
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.error_password), null, null);
			return false;
		}

        if (!mPassword.equalsIgnoreCase(mConfirmPassword))
        {
            ZuniUtils.showMsgDialog(this, "", getString(R.string.ConfirmPassword), null, null);
            return false;
        }
		if (mDobDay.isEmpty())
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.ValidDate), null, null);
			return false;
		}
		if (mDobMonth.isEmpty())
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.ValidMonth), null, null);
			return false;
		}
		if (mDobYear.isEmpty())
		{
			ZuniUtils.showMsgDialog(this, "", getString(R.string.ValidYear), null, null);
			return false;
		}
        return true;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId())
		{
			case R.id.activity_signup_day:
				mDobDay = item.getTitle().toString();
				mTextviewDay.setText(mDobDay);
				break;

			case R.id.activity_signup_month:
				mDobMonth = item.getTitle().toString();
				mTextviewMonth.setText(mDobMonth);
				break;

			case R.id.activity_signup_year:
				mDobYear = item.getTitle().toString();
				mTextviewYear.setText(mDobYear);
				break;
		}
		return false;
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.activity_registration_register_btn:
				registerUser();
				break;
			case R.id.activity_registration_days:
				mDaysPopupMenu.show();
				break;

			case R.id.activity_registration_year:
				mYearPopupMenu.show();
				break;

			case R.id.activity_registration_month:
				mMonthPopupMenu.show();
				break;
		}
	}

}
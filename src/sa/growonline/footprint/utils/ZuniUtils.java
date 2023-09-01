package sa.growonline.footprint.utils;

import sa.growonline.footprint.ActivityLogin;
import sa.growonline.footprint.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.PermissionChecker;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class ZuniUtils {

	public static void LogEvent(String logstring) 
	{
		if (ZuniConstants.isLogEnabled)
		{
			Log.e("Zuni:", logstring);
		}
	}
	public void setGridViewHeightBasedOnChildren(GridView gridView, int columns)
	{
        ListAdapter listAdapter = gridView.getAdapter(); 
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight;
        int items = listAdapter.getCount();
        int rows;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
	}

	public static boolean isNetworkAvailable(Context context)
	{
		final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

		return activeNetworkInfo != null;
	}
	
	/**** Method for Setting the Height of the ListView dynamically.
	 **** Hack to fix the issue of not showing all the items of the ListView
	 **** when placed inside a ScrollView  ****/
//	public static void setListViewHeightBasedOnChildren(GridView listView, int columns) {
//		ListAdapter listAdapter = listView.getAdapter();
//		if (listAdapter == null)
//		{
//			// pre-condition
//			return;
//		}
//
//		int totalHeight = 0;
//		for (int i = 0; i < listAdapter.getCount(); i++)
//		{
//			
//			View listItem = listAdapter.getView(i, null, listView);
//			Log.e("size:"+ listAdapter.getCount(), i+listItem.toString());
//			listItem.measure(0, 0);
//			totalHeight += listItem.getMeasuredHeight();
//		}
//
//		ViewGroup.LayoutParams params = listView.getLayoutParams();
//		params.height = totalHeight + ((listAdapter.getCount() - 1));
//		listView.setLayoutParams(params);
//	}
	/**
	 * This method converts dp unit to equivalent pixels, depending on device density. 
	 * 
	 * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on device density
	 */
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
		return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
	}

	/**
	 * This method converts device specific pixels to density independent pixels.
	 * 
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
		return px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
	}
	public static void showMsgDialog(Activity activity,
			String title, String msg, DialogInterface.OnClickListener positiveButton, DialogInterface.OnClickListener negativeButton)
	{
		try
		{
			if (positiveButton == null)
			{
				positiveButton = new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				};
			}
			
			new AlertDialog.Builder(activity).setTitle(title).setMessage(msg).setIcon(R.drawable.ic_launcher).setCancelable(false)
					.setPositiveButton(activity.getString(android.R.string.ok), positiveButton).create().show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean validateEmail(String mEmail) {
		return Patterns.EMAIL_ADDRESS.matcher(mEmail).matches();
	}

	public static String getText(TextView s)
	{
		return s.getText().toString();
	}

	public static void applyAppFont(TextView mCategoryNameTextView)
	{
		mCategoryNameTextView.setTypeface(Typeface.createFromAsset(mCategoryNameTextView.getContext().getAssets(), ZuniConstants.FONTS_DIR + "Uniform Medium.otf"));
	}

    public static void applyAppFontRegular(TextView mCategoryNameTextView)
    {
        mCategoryNameTextView.setTypeface(Typeface.createFromAsset(mCategoryNameTextView.getContext().getAssets(), ZuniConstants.FONTS_DIR + "Lato-Regular.ttf"));
    }

    public static void applyAppFontBold(TextView mCategoryNameTextView)
    {
        mCategoryNameTextView.setTypeface(Typeface.createFromAsset(mCategoryNameTextView.getContext().getAssets(), ZuniConstants.FONTS_DIR + "Lato-Bold.ttf"));
    }
	
	public static void applyAppThinFont(TextView mCategoryNameTextView)
	{
		mCategoryNameTextView.setTypeface(Typeface.createFromAsset(mCategoryNameTextView.getContext().getAssets(), ZuniConstants.FONTS_DIR + "Lato-Thin.ttf"));
	}
	
	public static void toggleNavigation(DrawerLayout layout, int gravity)
	{
        layout.closeDrawers();
		if (!layout.isDrawerOpen(gravity))
			layout.openDrawer(gravity);
	}

    public static String getDeviceId(ActivityLogin activityLogin)
    {
        final TelephonyManager tm = (TelephonyManager) activityLogin.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(activityLogin.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = "";
        try
        {
            deviceId = deviceUuid.toString();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
            deviceId = new BigInteger(130, new SecureRandom()).toString(32);
        }
        return deviceId;
    }
	public static void setListViewHeightBasedOnChildren(ListView listView)
	{
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
		{
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++)
		{

			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}


	public static int hasPermission(Activity activity, String permission)
    {
        return PermissionChecker.checkSelfPermission(activity, permission);
    }

	public static void applyHeaderAppFont(TextView mTitleTextView) {
		mTitleTextView.setTypeface(Typeface.createFromAsset(mTitleTextView.getContext().getAssets(), "fonts/Horsham-Serial-Light-Regular.ttf"));
	}
}
package sa.growonline.footprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sa.growonline.footprint.asynctask.AsynctaskGetAllColors;
import sa.growonline.footprint.asynctask.AsynctaskGetCartDetails;
import sa.growonline.footprint.utils.ZuniConstants;

public class ActivitySplash extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new AsynctaskGetAllColors(ActivitySplash.this, false).execute();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "sa.growonline.footprint",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException ignored) {

        }

        new AsynctaskGetCartDetails(ActivitySplash.this, true).execute();


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (ZuniApplication
//                        .getmAppPreferences()
//                        .getString(ZuniConstants.IS_USER_LOGGED_IN, "false")
//                        .equalsIgnoreCase("false")) {
//                    startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
//                } else {
//                    startActivity(new Intent(ActivitySplash.this, ActivityParallaxHome.class));
//                }
//                //  startActivity(new Intent(ActivitySplash.this, ActivityDashboard.class));
//
//                finish();
//            }
//        }, 3 * 1000); // 3 secs
    }

    public void startApp(int size) {
        ((ZuniApplication) this.getApplication()).setNumberOfItemsInCart(size);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("total", size);
        editor.apply();
        if (ZuniApplication
                .getmAppPreferences()
                .getString(ZuniConstants.IS_USER_LOGGED_IN, "false")
                .equalsIgnoreCase("false")) {
            startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
        } else {
            startActivity(new Intent(ActivitySplash.this, ActivityParallaxHome.class));

        }
        finish();
    }
}


//Email: pkgrowonline@gmail.com
//Password: growonline@123
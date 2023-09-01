package sa.growonline.footprint;

import sa.growonline.footprint.view.GestureImageView;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class ActivityImageDetail extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_detail);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GestureImageView imageView = (GestureImageView) findViewById(R.id.activity_image_detail_imgview);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_image_detail_progress_bar);
		
		if (getIntent().hasExtra("img"))
		{
			Bundle bundle = getIntent().getExtras();
			if (bundle != null)
			{
				ZuniApplication.getmCacheManager().loadImage(Uri.parse(bundle.getString("img")), imageView, progressBar);
			}
		}
	}
}
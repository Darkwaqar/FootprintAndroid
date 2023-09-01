package sa.growonline.footprint.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ImageCacheManager
{
	private static ImageCacheManager mInstance;
	private Picasso mPicasso;

	private ImageCacheManager(Context context)
	{
		Picasso.Builder b = new Picasso.Builder(context);
		b.listener(new Picasso.Listener() {
			@Override
			public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
				exception.printStackTrace();
			}
		});
		mPicasso = b.build();
	}
	
	public static ImageCacheManager Initialize(Context context)
	{
		if (mInstance == null)
		{
			mInstance = new ImageCacheManager(context);
			return mInstance;
		}
		else
			return mInstance;
	}
	
	public void loadImage(Uri uri, ImageView mFirstImageView, final ProgressBar progressBar)
	{
		mPicasso.load(uri).into(mFirstImageView, new Callback()
		{
			@Override
			public void onError()
			{
				
			}

			@Override
			public void onSuccess()
			{
				if (progressBar == null) return;
				progressBar.setVisibility(View.GONE);
			}
		});
	}

	public void loadImageWithCenterCrop(Uri uri, ImageView mFirstImageView, final ProgressBar progressBar)
	{
		mPicasso.load(uri).centerCrop().fit().into(mFirstImageView, new Callback()
		{
			@Override
			public void onError(){}

			@Override
			public void onSuccess()
			{
				if (progressBar == null) return;
				progressBar.setVisibility(View.GONE);
			}
		});
	}
	
	public void loadImageWithFit(Uri uri, ImageView mFirstImageView)
	{
		mPicasso.load(uri).centerCrop().fit().into(mFirstImageView);
	}

    public void loadImageWithTag(Uri parse, ImageView mFirstImageView, final ProgressBar progressBar, String adapter_product_list_tag)
    {
        mPicasso.load(parse).tag(adapter_product_list_tag).into(mFirstImageView, new Callback()
        {
            @Override
            public void onError()
            {

            }

            @Override
            public void onSuccess()
            {
                if (progressBar == null) return;
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void pauseAsync(String adapterProductListTag) {
        mPicasso.pauseTag(adapterProductListTag);
    }

    public void resumeAsync(String adapterProductListTag) {
        mPicasso.resumeTag(adapterProductListTag);
    }


    public class TopCenterTransform implements Transformation
	{
		private ImageView mImageView;

		public TopCenterTransform(ImageView imageview)
		{
			this.mImageView = imageview;
		}
		
		@Override
		public Bitmap transform(Bitmap source)
		{
			int targetWidth = mImageView.getWidth();

			double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
			int targetHeight = (int) (targetWidth * aspectRatio);
			Bitmap result = Bitmap.createScaledBitmap(source, targetHeight, targetWidth, false);
			if (result != source) {
				// Same bitmap is returned if sizes are the same
				source.recycle();
			}
			return result;
      }	

      @Override
      public String key() {
          return "transformation" + " desiredWidth";
      }
		
	}
}
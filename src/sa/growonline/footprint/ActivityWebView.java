package sa.growonline.footprint;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import sa.growonline.footprint.base.BaseActivityx;


public class ActivityWebView extends BaseActivityx implements View.OnClickListener {
    private ProgressDialog mProgressDialog;
    /* private ImageView mBackBtn;*/
    /*private TextView GetTitle;
    private FragmentManager mFragmentManagerObject;
    private FragmentCategoryMenu mFragmentCategoryMenu;
    private FragmentProfileMenu mFragmentUserProfileMenu;*/
    private View mBannerView;
    /* private DrawerLayout mDrawerLayout;*/
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_national);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
        removeToolbar();
        mBannerView = findViewById(R.id.header);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* mFragmentCategoryMenu.loadLastFetchCatrgories();*/
            }
        }, 500);


        WebView webView = (WebView) findViewById(R.id.webview);
        initWebView(webView);

        if (getIntent().hasExtra("url")) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                webView.loadUrl(bundle.getString("url"));
            }

        }
    }

    private void initWebView(WebView mWebView) {
        WebViewClient mWebViewClient = new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
            }
        };
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        };

        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(webChromeClient);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollbarFadingEnabled(false);
    }

    @Override

    public void onClick(View view) {

        switch (view.getId()) {
        }
    }
}

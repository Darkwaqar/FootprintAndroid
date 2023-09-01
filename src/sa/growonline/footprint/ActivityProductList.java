package sa.growonline.footprint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.adapter.AdapterProductListRecycler;
import sa.growonline.footprint.asynctask.AsynctaskGetCategoryDetail;
import sa.growonline.footprint.asynctask.AsynctaskGetDetailsBySpecId;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.bean.SpecFilter;
import sa.growonline.footprint.utils.DialogRefine;
import sa.growonline.footprint.utils.NotificationBadge;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.utils.ZuniUtils;
import sa.growonline.footprint.view.GridRecyclerView;
import sa.parallax.lib.ParallaxListView;

public class ActivityProductList extends BaseActivityx implements OnClickListener {
    private GridRecyclerView mGridView;
    private String mCategoryId;
    private List<BeanProductForCategory> mAllProductsList;
    private String mProductJsonArrayString;
    private LinearLayout mSpecsLayout;
    private TextView mRefineButton;
    private int mPageNumber;
    private boolean mIsFromFilter;
    private String mSpecUrl = "";
    private TextView mClearFilter;
    private boolean mIsLoaderShowing = true;
    private boolean mIsPagingCompleted = false;
    private ScaleGestureDetector mScaleDetector;



    private ImageView mGridStyleImageView;
    private boolean isRefineMode;
    private DialogRefine mDialogRefine;
    private String categoryMinPrice;
    private String categoryMaxPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        removeToolbar();

        iniHeader();
        mGridView = (GridRecyclerView) findViewById(R.id.activity_product_list_gridview);

        mSpecsLayout = (LinearLayout) findViewById(R.id.activity_product_list_filter_linear_layout);
        mRefineButton = (TextView) findViewById(R.id.search_filters_refine_button);
        mClearFilter = (TextView) findViewById(R.id.clear_filter);
        ZuniUtils.applyAppFontBold(mClearFilter);

        mSpecsLayout.setOnClickListener(this);

        mScaleDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            float mStartScale;

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                mStartScale = detector.getScaleFactor();
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                float endScale = detector.getScaleFactor();

                if (mStartScale > endScale) {
                    ((AdapterProductListRecycler) mGridView.getAdapter()).changeSpan(37);
                    mGridStyleImageView.setImageResource(R.drawable.boxes);
                } else if (mStartScale < endScale) {
                    ((AdapterProductListRecycler) mGridView.getAdapter()).changeSpan(25);
                    mGridStyleImageView.setImageResource(R.drawable.box);
                }
            }
        });

        mGridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //inspect the event.
                mScaleDetector.onTouchEvent(event);
                return false;
            }
        });

        mGridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (((LinearLayoutManager) mGridView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == mAllProductsList.size() - 1) {
                        onRefresh();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mClearFilter.setOnClickListener(this);
        mRefineButton.setOnClickListener(this);

        loadData(false);
        if (mIsFromFilter) {
            mRefineButton.setVisibility(View.GONE);
        }
    }

    public void Refine(String specs, String minPrice, String maxPrice) {
        isRefineMode = true;
        String uri = ZuniConstants.BASE_URL + ZuniConstants.GET_CATEGORY_PRODUCT_REFINE + mCategoryId
                + "&specs=" + specs + "&minval=" + minPrice + "&maxval=" + maxPrice;
        new AsynctaskGetDetailsBySpecId(ActivityProductList.this, uri, mPageNumber, true).execute();
    }

    public void ResetRefine() {
        isRefineMode = false;
        loadData(true);
    }

    private void loadData(boolean forceLoadFreshData) {
        String json = null;
        String postListJson = ZuniApplication.getmAppPreferences().getString(ZuniConstants.POST_LIST_JSON, "");

        if (!postListJson.equalsIgnoreCase("")) {
            mProductJsonArrayString = json;
            Type type = new TypeToken<List<BeanProductForCategory>>() {
            }.getType();
            mAllProductsList = new Gson().fromJson(postListJson, type);
        }
        mCategoryId = ZuniApplication.getmAppPreferences().getString(ZuniConstants.CATEGORY_ID_PREF_KEY, "0");

        if (mAllProductsList != null && !forceLoadFreshData) {
            mIsFromFilter = true;
        } else
            new AsynctaskGetCategoryDetail(ActivityProductList.this, mCategoryId, json).execute();

    }

    private void iniHeader() {



        findViewById(R.id.activity_product_filter).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialogRefine == null) {
                    try {
                        mDialogRefine = new DialogRefine(ActivityProductList.this, Float.valueOf(categoryMinPrice), Float.valueOf(categoryMaxPrice));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mDialogRefine.showDialog();
            }
        });
        mGridStyleImageView = (ImageView) findViewById(R.id.grid_style);

        mGridStyleImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mGridView.getAdapter() != null)
                    if (((AdapterProductListRecycler) mGridView.getAdapter()).getCurrentSpan() == 37) {
                        ((AdapterProductListRecycler) mGridView.getAdapter()).changeSpan(25);
                        mGridStyleImageView.setImageResource(R.drawable.boxes);
                    } else {
                        ((AdapterProductListRecycler) mGridView.getAdapter()).changeSpan(37);
                        mGridStyleImageView.setImageResource(R.drawable.box);
                    }
            }
        });
    }


    public void updateProducts(String mCartNumber, List<BeanProductForCategory> mAllProductsList2, String mStringProductArray, String title, int pageNumber) {

        int mPreviousSize = 0;

        if (mAllProductsList != null && !mIsFromFilter && !isRefineMode) {
            mPreviousSize = mAllProductsList.size();
            mAllProductsList.addAll(mAllProductsList2);
            mAllProductsList2 = mAllProductsList;
        }

        if (mAllProductsList != null && mPreviousSize == mAllProductsList.size()) {
            mIsPagingCompleted = true;
            Snackbar.make(mGridView, getString(R.string.NoMoreProducts), Snackbar.LENGTH_LONG).setAction("OK", new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //!-- Handle event on snackbar
                }
            }).show();
        }
        updateTitle(title);

        if (mAllProductsList2 == null) return;

        mPageNumber = pageNumber;

        mProductJsonArrayString = mStringProductArray;
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, mStringProductArray).commit();


        if (mAllProductsList == null)
            this.mAllProductsList = mAllProductsList2;


        if (mGridView.getAdapter() != null) {
            //Notify Data Changed
            ((AdapterProductListRecycler) mGridView.getAdapter()).notifyListChanged(mAllProductsList2);
        } else {
            AdapterProductListRecycler gridAdapter = new AdapterProductListRecycler(mGridView, ActivityProductList.this, mAllProductsList2);

            GridLayoutManager manager = new GridLayoutManager(this, AdapterProductListRecycler.totalSpan);

            mGridView.setAdapter(gridAdapter);

            mGridView.setLayoutManager(manager);
            manager.setSpanSizeLookup(gridAdapter.getScalableSpanSizeLookUp());
            mGridView.setAdapter(gridAdapter);
        }
    }

    public void updateProducts(List<BeanProductForCategory> mAllProductsList2, String mStringProductArray, String title, int pageNumber, String minPrice, String maxPrice) {

        int mPreviousSize = 0;

        if (minPrice != null && maxPrice != null) {
            this.categoryMinPrice = minPrice;
            this.categoryMaxPrice = maxPrice;
        }

        if (mAllProductsList != null && !mIsFromFilter) {
            mPreviousSize = mAllProductsList.size();
            mAllProductsList.addAll(mAllProductsList2);
            mAllProductsList2 = mAllProductsList;
        }
        updateTitle(title);


        if (mAllProductsList != null && mPreviousSize == mAllProductsList.size()) {
            mIsPagingCompleted = true;
            Snackbar.make(mGridView, R.string.NoMoreProducts, Snackbar.LENGTH_LONG).setAction("OK", new OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).show();
        }
        if (mAllProductsList2 == null) return;

        mPageNumber = pageNumber;

        mProductJsonArrayString = mStringProductArray;
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, mStringProductArray).commit();


        if (mAllProductsList == null)
            this.mAllProductsList = mAllProductsList2;


        if (mGridView.getAdapter() != null) {
            //Notify Data Changed
            ((AdapterProductListRecycler) mGridView.getAdapter()).notifyListChanged(mAllProductsList2);
        } else {
            AdapterProductListRecycler gridAdapter = new AdapterProductListRecycler(mGridView, ActivityProductList.this, mAllProductsList2);

            GridLayoutManager manager = new GridLayoutManager(this, AdapterProductListRecycler.totalSpan);

            mGridView.setAdapter(gridAdapter);

            mGridView.setLayoutManager(manager);
            manager.setSpanSizeLookup(gridAdapter.getScalableSpanSizeLookUp());
            mGridView.setAdapter(gridAdapter);
        }

        //!-- Here
        updatePrefs();
    }

    private void updatePrefs() {
        new Thread() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = ZuniApplication.getmAppPrefEditor();
                editor.putString(ZuniConstants.PRODUCTS_JSON_ARRAY_KEY, new Gson().toJson(mAllProductsList, ArrayList.class));
                editor.commit();
            }
        }.start();
    }

    public void updateFilter(SpecFilter beanPostAttributes) {

        if (beanPostAttributes == null) {
            mRefineButton.setVisibility(View.GONE);
            return;
        }
        ((ZuniApplication) getApplication()).setRefine(beanPostAttributes);
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* mCartNumberTextView.setText(ZuniApplication.getmAppPreferences().getString(ZuniConstants.CART_QUANTITY, "0"));*/
    }

    @Override
    public void onClick(View v) {
        if (v == mRefineButton) {
            if (mSpecsLayout.getVisibility() == View.VISIBLE) {
                mSpecsLayout.setVisibility(View.GONE);
                mRefineButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.nav_category_ico, 0);
            } else {
                mRefineButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.nav_category_ico_top, 0);
                mSpecsLayout.setVisibility(View.VISIBLE);
            }
        } else if (mClearFilter == v) {
            new AsynctaskGetCategoryDetail(ActivityProductList.this, mCategoryId, 0, true).execute();
        }
    }

    public void onRefresh() {
        mPageNumber++;
        if (!mIsFromFilter) {
            if (mSpecUrl.equalsIgnoreCase("")) {
                if (!mIsPagingCompleted)
                    new AsynctaskGetCategoryDetail(ActivityProductList.this, mCategoryId, mPageNumber, true).execute();
                mIsLoaderShowing = true;
            } else
                new AsynctaskGetDetailsBySpecId(ActivityProductList.this, mSpecUrl, mPageNumber, true).execute();
        }
    }


}
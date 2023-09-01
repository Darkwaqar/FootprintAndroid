package sa.growonline.footprint.fragment;

import sa.growonline.footprint.ActivityHomeNew;
import sa.growonline.footprint.ActivityProductList;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterHomeCategory;
import sa.growonline.footprint.adapter.AdapterHomeProduct;
import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.utils.ZuniConstants;
import sa.growonline.footprint.view.DynamicHeightGridView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;


public class FragmentMaterialRecyclerView extends Fragment implements View.OnClickListener
{
	private int mCategoryId;
    private String mServerCategoryId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_sub_categoryview, container, false);

        GridView mGridView = (DynamicHeightGridView) view.findViewById(R.id.grid_view);
        Button button = (Button) view.findViewById(R.id.refresh_view);


        BeanGetAllCategory beanGetAllCategory;

        try {
//        ZuniUtils.LogEvent((ActivityHomeNew) getActivity() + "abc");
            beanGetAllCategory = ((ActivityHomeNew) getActivity()).getCategoryBeans(mCategoryId);

//        if (beanGetAllCategory == null) {((ActivityHomeNew) getActivity()).updateBean(); return view;}

            mServerCategoryId = beanGetAllCategory.getmCategoryId();
            if (beanGetAllCategory.getProducts() != null && beanGetAllCategory.getProducts().size() != 0) {
                button.setVisibility(View.VISIBLE);
                mGridView.setNumColumns(2);
                mGridView.setAdapter(new AdapterHomeProduct(getActivity(), beanGetAllCategory.getProducts()));
            } else {
                button.setVisibility(View.GONE);
                mGridView.setNumColumns(1);
                mGridView.setAdapter(new AdapterHomeCategory(getActivity(), beanGetAllCategory.getSubCategories()));
            }
            button.setOnClickListener(this);

            try {
                SpannableString content = new SpannableString("Load more");
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                button.setText(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (NullPointerException e) //!-- To handle the exception on restart null beans
        {
            e.printStackTrace();



            Intent i = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getActivity().finish();
            startActivity(i);

        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        

    }

	public void setProductId(int getCategoryId)
	{
		this.mCategoryId = getCategoryId;
	}

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.refresh_view:
                Intent intent = new Intent(getActivity(), ActivityProductList.class);
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.POST_LIST_JSON, "").commit();
                ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.CATEGORY_ID_PREF_KEY, String.valueOf(mServerCategoryId)).commit();
                startActivity(intent);
                break;
        }
    }
}
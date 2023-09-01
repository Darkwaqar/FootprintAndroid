package sa.growonline.footprint.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.asynctask.AsynctaskGetAllColors;
import sa.growonline.footprint.bean.BeanPostAttributes;
import sa.growonline.footprint.fragment.FragmentDetailParent;
import sa.growonline.footprint.utils.ZuniConstants;

public class AdapterAttributeDetail extends BaseAdapter implements View.OnClickListener {
    private final FragmentActivity mActivity;
    private final FragmentDetailParent mFragment;
    private final ArrayList<BeanPostAttributes.BeanProductAttributeValues> mList;
    private final LayoutInflater mLayoutInflator;
    private final AttributeType mAttrType;
    private final BeanPostAttributes mBeanPostAttributes;
    private final ArrayList<AsynctaskGetAllColors.ColorModel> mUpdateColor;
    private TextView mProductAttrTextView;
    private ImageView mProductAttrImageView;
    private LinearLayout mLayoutRoot;

    public enum AttributeType {SIZE, COLOR}

    public AdapterAttributeDetail(FragmentActivity activity, ArrayList<BeanPostAttributes.BeanProductAttributeValues> colorAttributes, BeanPostAttributes beanPostAttributes, FragmentDetailParent fragmentDetailParent, AttributeType type) {
        this.mActivity = activity;
        this.mFragment = fragmentDetailParent;
        this.mList = colorAttributes;
        this.mBeanPostAttributes = beanPostAttributes;
        this.mLayoutInflator = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mAttrType = type;

        if (mAttrType == AttributeType.COLOR) {
            Type listType = new TypeToken<ArrayList<AsynctaskGetAllColors.ColorModel>>() {
            }.getType();
            mUpdateColor = new Gson().fromJson(ZuniApplication.getmAppPreferences().getString(ZuniConstants.COLOR_LIST_KEY, "[]"), listType);
        } else
            mUpdateColor = null;


    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Integer.valueOf(mList.get(i).getId());
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        if (view == null)
            view = mLayoutInflator.inflate(R.layout.adapter_attribute, viewGroup, false);


        mLayoutRoot = (LinearLayout) view.findViewById(R.id.layout_root_adapter_attribute);
        mProductAttrImageView = (ImageView) view.findViewById(R.id.detail_parent_product_attr_imgview);
        mProductAttrTextView = (TextView) view.findViewById(R.id.detail_parent_product_attr_txtview);


        BeanPostAttributes.BeanProductAttributeValues bean = mList.get(pos);
        if (mAttrType == AttributeType.SIZE) {
            mLayoutRoot.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            mProductAttrImageView.setVisibility(View.GONE);
            mProductAttrTextView.setText(bean.getName());
        } else if (mAttrType == AttributeType.COLOR) {
            mLayoutRoot.setGravity(Gravity.LEFT);
            mProductAttrImageView.setVisibility(View.VISIBLE);
            if (bean.getColorSquaresRgb() != null)
                mProductAttrImageView.setBackgroundColor(Color.parseColor(bean.getColorSquaresRgb()));
            else if (bean.getName() != null) {
                for (int i = 0; i < mUpdateColor.size(); i++) {
                    if (!mUpdateColor.get(i).getColor().equalsIgnoreCase(bean.getName())) {
                        try {
                            mProductAttrImageView.setBackgroundColor(Color.parseColor(bean.getName()));
                        } catch (Exception e) {
                            Log.e("AdapterAttributeDetails", "getView: Not a valid color ");
                        }
                    } else if (mUpdateColor.get(i).getColor().equalsIgnoreCase(bean.getName())) {
                        mProductAttrImageView.setBackgroundColor(Color.parseColor(mUpdateColor.get(i).getValue()));
                        mList.get(pos).setColorSquaresRgb(mUpdateColor.get(i).getValue());
                        break;
                    }
                }
            }
            mProductAttrTextView.setText(bean.getName());
        }
        view.setTag(pos);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int pos = (Integer) view.getTag();
        mFragment.onAttrbuteselected(mList.get(pos), mAttrType, mBeanPostAttributes);
    }
}

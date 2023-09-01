
package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.ActivitySingleProduct;
import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.asynctask.AsyncTaskDeleteProductFromCart;
import sa.growonline.footprint.asynctask.AsynctaskUpdateCartQuantity;
import sa.growonline.footprint.bean.BeanGetAllCartsProduct;
import sa.growonline.footprint.utils.ZuniUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCartDetail extends BaseAdapter implements OnClickListener
{
    private Activity mActivity;
    private List<BeanGetAllCartsProduct> mCartList;
    private LayoutInflater mLayoutInflater;

    public AdapterCartDetail(Activity activityCartDetails,
                             List<BeanGetAllCartsProduct> list)
    {
        this.mActivity = activityCartDetails;
        this.mLayoutInflater = (LayoutInflater) activityCartDetails.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCartList = list;
    }

    @Override
    public int getCount() {
        return mCartList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mCartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
            view = mLayoutInflater.inflate(R.layout.adapter_cart_details, parent, false);

        ImageView mImageView = (ImageView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_imgview);
        AppCompatTextView mTitleTextView = (AppCompatTextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_title);
        AppCompatTextView mTotalTextView = (AppCompatTextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_total_product_price_val);

        AppCompatTextView mDescTextView = (AppCompatTextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_desc);
        AppCompatTextView mPriceTextView = (AppCompatTextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_price_val);

        AppCompatButton mViewAppCompatButton = (AppCompatButton) view.findViewById(R.id.adapter_cartdetails_bottom_layout_view_textview);
        AppCompatButton mDeleteAppCompatButton = (AppCompatButton) view.findViewById(R.id.adapter_cartdetails_bottom_layout_delete_textview);

        TextView mAddQuantityTextView = (TextView) view.findViewById(R.id.add_quantity_txtview);
        TextView mDeductQuantityTextView = (TextView) view.findViewById(R.id.deduct_quantity_txtview);
        AppCompatTextView mTotalQuantityTextView = (AppCompatTextView) view.findViewById(R.id.total_quantity_textview);

        setContentView(mImageView, mCartList.get(position).getPicture().getThumbImageUrl());

        mTitleTextView.setText(mCartList.get(position).getProductName());
        mTotalTextView.setText(Html.fromHtml("<big>" + mCartList.get(position).getSubTotal().replace("PKR","") + ".00 PKR</big>"));
        mPriceTextView.setText(Html.fromHtml("<small>" + mCartList.get(position).getQuantity() + "x  " + "</small><big>" + mCartList.get(position).getUnitPrice().replace("PKR","") + ".00 PKR</big>"));
        mTotalQuantityTextView.setText(String.valueOf(mCartList.get(position).getQuantity()));

        try
        {
            if (mCartList.get(position).getAttributeInfo().contains("- "))
            {
                String[] mDescStrings = mCartList.get(position).getAttributeInfo().split("- ");
                mDescTextView.setText(mDescStrings[0] + "\n" + mDescStrings[1]);
            }
            else if (mCartList.get(position).getAttributeInfo().contains("<br />"))
            {
                String[] mDescStrings = mCartList.get(position).getAttributeInfo().split("<br />");
                mDescTextView.setText(mDescStrings[0] + "\n" + mDescStrings[1]);
            }
            else
            {
                mDescTextView.setText(mCartList.get(position).getAttributeInfo());
            }
        }
        catch (Exception e)
        {
            mDescTextView.setText(mCartList.get(position).getAttributeInfo());
            e.printStackTrace();
        }

        mViewAppCompatButton.setTag(position);
        mAddQuantityTextView.setTag(position);
        mDeductQuantityTextView.setTag(position);
        mDeleteAppCompatButton.setTag(position);

        mViewAppCompatButton.setOnClickListener(this);
        mAddQuantityTextView.setOnClickListener(this);
        mDeductQuantityTextView.setOnClickListener(this);
        mDeleteAppCompatButton.setOnClickListener(this);

        return view;
    }

    private void setContentView(ImageView mImageView, String getFullSizeImageUrl)
    {
        try
        {
            ZuniApplication.getmCacheManager().loadImage(Uri.parse(getFullSizeImageUrl), mImageView, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void notifyBeanChanged(List<BeanGetAllCartsProduct> list)
    {
        mCartList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v)
    {
        int position = (Integer) v.getTag();
        String id = String.valueOf(mCartList.get(position).getProductId());

        switch (v.getId())
        {

            //!-- Case for handling when view button pressed
            case R.id.adapter_cartdetails_bottom_layout_view_textview:
                Intent intent = new Intent(mActivity, ActivitySingleProduct.class);
                ZuniApplication.getmAppPrefEditor().putString("singleProductId", id).commit();
                mActivity.startActivity(intent);
                break;

            //!-- Case for handling when view button pressed
            case R.id.adapter_cartdetails_bottom_layout_delete_textview:
                new AsyncTaskDeleteProductFromCart(mActivity, id, String.valueOf(mCartList.get(position).getId())).execute();
                break;

            case R.id.add_quantity_txtview:
            {
                int mPreviousQuantity1 = mCartList.get(position).getQuantity();
                int updateQuantity1 = (v.getId() == R.id.add_quantity_txtview) ? mPreviousQuantity1 + 1 : mPreviousQuantity1 - 1;
                new AsynctaskUpdateCartQuantity(mActivity, true, id, String.valueOf(mCartList.get(position).getId()), updateQuantity1).execute();
                break;
            }

            case R.id.deduct_quantity_txtview:
                if (mCartList.get(position).getQuantity() != 1)
                {
                    int mPreviousQuantity = mCartList.get(position).getQuantity();
                    int updateQuantity = (v.getId() == R.id.add_quantity_txtview) ? mPreviousQuantity + 1 : mPreviousQuantity - 1;
                    new AsynctaskUpdateCartQuantity(mActivity, true, id, String.valueOf(mCartList.get(position).getId()), updateQuantity).execute();
                    return;
                }
                else
                    ZuniUtils.showMsgDialog(mActivity, "", mActivity.getString(R.string.QuantityNotNull), null, null);
                break;
        }
    }
}
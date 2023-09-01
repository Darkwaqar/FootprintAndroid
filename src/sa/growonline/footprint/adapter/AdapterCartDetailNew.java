package sa.growonline.footprint.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.asynctask.AsyncTaskDeleteProductFromCart;
import sa.growonline.footprint.asynctask.AsynctaskUpdateCartQuantity;
import sa.growonline.footprint.bean.BeanGetAllCartsProduct;
import sa.growonline.footprint.utils.ZuniUtils;

/**
 * Created by Jawed on 8/19/2016.
 */


public class AdapterCartDetailNew extends BaseAdapter implements View.OnClickListener
{
    private Activity mActivity;
    private List<BeanGetAllCartsProduct> mCartList;
    private LayoutInflater mLayoutInflator;

    public AdapterCartDetailNew(Activity activityCartDetails,
                             List<BeanGetAllCartsProduct> list)
    {
        this.mActivity = activityCartDetails;
        this.mLayoutInflator = (LayoutInflater) activityCartDetails.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        if (view == null)
            view = mLayoutInflator.inflate(R.layout.adapter_cart_detail_new, viewGroup, false);

        ImageView mImageView = (ImageView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_imgview);
        TextView mTitle   = (TextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_title_new);
        TextView mColor   = (TextView) view.findViewById(R.id.adapter_cartdetails_product_color);
        TextView mSize   = (TextView) view.findViewById(R.id.adapter_cartdetails_product_size);
        TextView mQty     = (TextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_desc_qty_count);
        TextView mPrice   = (TextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_price_detail);

        ZuniApplication.getmCacheManager().loadImage(Uri.parse(mCartList.get(position).getPicture().getThumbImageUrl()), mImageView, null);


        if(!mCartList.get(position).getAttributeInfo().isEmpty()) {

            mColor.setVisibility(View.VISIBLE);
            mSize.setVisibility(View.VISIBLE);

            String[] attributes = mCartList.get(position).getAttributeInfo().split("-");

            for (int i = 0; i < attributes.length; i++) {
                String[] singleAttrInfo = attributes[i].trim().split(":");

                String label = singleAttrInfo[0].toLowerCase();
                switch (label) {
                    case "color":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mColor.setText(Html.fromHtml("<small>COLOR: </small><b>" + singleAttrInfo[1] + "</b>"));
                        } else {
                            mColor.setText(Html.fromHtml("<small>COLOR: </small><b>" + singleAttrInfo[1] + "</b>"));
                        }
                        break;
                    case "size":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mSize.setText(Html.fromHtml("<small>SIZE: </small><b>" + singleAttrInfo[1] + "</b>"));
                        } else {
                            mSize.setText(Html.fromHtml("<small>SIZE: </small><b>" + singleAttrInfo[1] + "</b>"));
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        mTitle.setText(mCartList.get(position).getProductName());
        mPrice.setText(mCartList.get(position).getSubTotal());
        mQty.setText(String.valueOf(mCartList.get(position).getQuantity()));

        //String info = mCartList.get(position).get

        AppCompatButton mDeleteAppCompatButton = (AppCompatButton) view.findViewById(R.id.adapter_cartdetails_bottom_layout_delete_textview);
        TextView mAddQuantityTextView = (TextView) view.findViewById(R.id.add_quantity_txtview);
        TextView mDeductQuantityTextView = (TextView) view.findViewById(R.id.deduct_quantity_txtview);

        mAddQuantityTextView.setTag(position);
        mDeductQuantityTextView.setTag(position);
        mDeleteAppCompatButton.setTag(position);

        mAddQuantityTextView.setOnClickListener(this);
        mDeductQuantityTextView.setOnClickListener(this);
        mDeleteAppCompatButton.setOnClickListener(this);

        ZuniUtils.applyAppFontBold(mTitle);
        ZuniUtils.applyAppFontBold(mQty);
        ZuniUtils.applyAppFontBold(mPrice);

        ZuniUtils.applyAppFont((TextView) view.findViewById(R.id.adapter_cartdetails_bottom_layout_product_desc_qty));
        return view;
    }

    public void notifyBeanChanged(List<BeanGetAllCartsProduct> list)
    {
        mCartList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        String id = String.valueOf(mCartList.get(position).getProductId());

        switch (v.getId())
        {
            //!-- Case for handling when view button pressed
            case R.id.adapter_cartdetails_bottom_layout_view_textview:
                //Intent intent = new Intent(mActivity, ActivitySingleProduct.class);
                //intent.putExtra("productId", id);
                //mActivity.startActivity(intent);
                break;

            //!-- Case for handling when dELETE button pressed
            case R.id.adapter_cartdetails_bottom_layout_delete_textview:
                new AsyncTaskDeleteProductFromCart(mActivity, id, String.valueOf(mCartList.get(position).getId())).execute();
                break;

            case R.id.add_quantity_txtview:
                int mPreviousQuantity1 = Integer.valueOf(mCartList.get(position).getQuantity());
                int updateQuantity1 = (v.getId() == R.id.add_quantity_txtview) ? mPreviousQuantity1 + 1 : mPreviousQuantity1 - 1;
                new AsynctaskUpdateCartQuantity(mActivity, true, id, String.valueOf(mCartList.get(position).getId()), updateQuantity1).execute();
                break;

            case R.id.deduct_quantity_txtview:
                if (mCartList.get(position).getQuantity() != 1)
                {
                    int mPreviousQuantity = Integer.valueOf(mCartList.get(position).getQuantity());
                    int updateQuantity = (v.getId() == R.id.add_quantity_txtview) ? mPreviousQuantity + 1 : mPreviousQuantity - 1;
                    new AsynctaskUpdateCartQuantity(mActivity, true, id, String.valueOf(mCartList.get(position).getId()), updateQuantity).execute();
                    return;
                }
                else
                    ZuniUtils.showMsgDialog(mActivity, "", "Quantity can not be 0", null, null);

                break;
        }
    }
}

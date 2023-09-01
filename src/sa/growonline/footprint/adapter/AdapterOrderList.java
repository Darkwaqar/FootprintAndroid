package sa.growonline.footprint.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sa.growonline.footprint.ActivityOrderDetail;
import sa.growonline.footprint.R;
import sa.growonline.footprint.bean.BeanOrder;
import sa.growonline.footprint.utils.ZuniUtils;

public class AdapterOrderList extends BaseAdapter implements View.OnClickListener
{
    private Activity mActivity;
    private ArrayList<BeanOrder> mOrderListModel;
    private LayoutInflater mLayoutInflator;

    public AdapterOrderList (Activity activity,ArrayList<BeanOrder> order)
    {
        this.mActivity = activity;
        this.mOrderListModel = order;
        this.mLayoutInflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mOrderListModel.size();
    }

    @Override
    public Object getItem(int i) {
        return mOrderListModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        if(view == null)
            view = mLayoutInflator.inflate(R.layout.adapter_order_list, viewGroup, false);


        TextView titleTextView = (TextView) view.findViewById(R.id.adapter_order_list_bottom_layout_product_title);
        TextView OrderTotalTextView = (TextView) view.findViewById(R.id.adapter_order_total);
        TextView OrderDatetimeTextView = (TextView) view.findViewById(R.id.adapter_order_date_time);
        TextView orderStatusTextView = (TextView) view.findViewById(R.id.adapter_order_status);
        TextView orderDetailTextView = (TextView) view.findViewById(R.id.adapter_detail_lnk);

        titleTextView.setText(String.format(mActivity.getString(R.string.order_num), mOrderListModel.get(i).getId()));
        OrderDatetimeTextView.setText(String.format(mActivity.getString(R.string.order_date_time), mOrderListModel.get(i).getCreatedOn()));
        OrderTotalTextView.setText(String.format(mActivity.getString(R.string.order_total), mOrderListModel.get(i).getOrderTotal()));
        orderStatusTextView.setText(String.format(mActivity.getString(R.string.order_status), mOrderListModel.get(i).getOrderStatus()));

        ZuniUtils.applyAppFont(titleTextView);
        ZuniUtils.applyAppFont(OrderTotalTextView);
        ZuniUtils.applyAppFont(OrderDatetimeTextView);
        ZuniUtils.applyAppFont(orderStatusTextView);
        ZuniUtils.applyAppFont(orderDetailTextView);

        orderDetailTextView.setTag(i);
        orderDetailTextView.setOnClickListener(this);

        return view;
    }


    public void updateData(ArrayList<BeanOrder> mOrderListModel)
    {
        this.mOrderListModel = mOrderListModel;
        notifyDataSetChanged();
    }


    @Override
    public void onClick(View view)
    {
        int pos = (Integer) view.getTag();
        ActivityOrderDetail.mId = mOrderListModel.get(pos).getId();
        mActivity.startActivity(new Intent(mActivity, ActivityOrderDetail.class));
    }
}

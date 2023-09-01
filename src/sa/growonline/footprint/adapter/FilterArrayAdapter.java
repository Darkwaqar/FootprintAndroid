package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.bean.BeanGetAllCategory;
import sa.growonline.footprint.bean.ManufacturerBean;
import sa.growonline.footprint.utils.DialogFilter;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class FilterArrayAdapter extends ArrayAdapter implements OnClickListener
{
	private LayoutInflater mLayoutInflator;
	private List mList;
	private Object mFromWhere;
	private AlertDialog mAlertDialog;

	@SuppressWarnings("unchecked")
	public FilterArrayAdapter(Context context, int resource, List objects, AlertDialog alertDialog, Object mFrom)
	{
		super(context, resource, objects);
		mLayoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mList = objects;
		mFromWhere = mFrom;
		this.mAlertDialog = alertDialog;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent)
	{
		if (convertView == null)
			convertView = mLayoutInflator.inflate(android.R.layout.simple_list_item_1, parent, false);
	
		TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

		if (mList.get(position) instanceof ManufacturerBean)
			textView.setText(((ManufacturerBean) mList.get(position)).getName());
		else if (mList.get(position) instanceof BeanGetAllCategory)
			textView.setText(((BeanGetAllCategory) mList.get(position)).getmCategoryName());
		
		convertView.setTag(position);
		convertView.setOnClickListener(this);
		
		return convertView;
	}

	@Override
	public void onClick(View v)
	{
		mAlertDialog.dismiss();
		
		int pos = (Integer) v.getTag();
		((DialogFilter) mFromWhere).callBack(mList.get(pos));
	}
}
package sa.growonline.footprint.adapter;

import java.util.List;

import sa.growonline.footprint.bean.checkout.AvailableCountry;
import sa.growonline.footprint.bean.checkout.AvailableState;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RegionAdapter extends BaseAdapter {
    private List<?> mAvailableRegion;
    private List<?> mFilterList;
    private LayoutInflater mLayoutInflator;

    public RegionAdapter(FragmentActivity activity, List<?> availableCountries) {
        mAvailableRegion = availableCountries;
        mFilterList = availableCountries;
        mLayoutInflator = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mAvailableRegion.size();
    }

    @Override
    public Object getItem(int position) {
        return mAvailableRegion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflator.inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        String value = "";

        if (mAvailableRegion.get(position) instanceof AvailableCountry)
            value = ((List<AvailableCountry>) mAvailableRegion).get(position).getText();
        else
            value = ((List<AvailableState>) mAvailableRegion).get(position).getText();

        textView.setTag(position);
        textView.setText(value);

        return convertView;
    }

    public void notifyRegionsChanged(List<?> list) {
        this.mAvailableRegion = list;
        notifyDataSetChanged();
    }

/*	@Override
    public Filter getFilter()
	{
		if (mFilter == null) mFilter = new RegionFilter();
		
		return mFilter;
	}*/

//	private class RegionFilter extends Filter
//	{
//		@Override
//		protected FilterResults performFiltering(CharSequence constraint)
//		{
//			FilterResults results = new FilterResults();
//			
//	        if(constraint != null && constraint.length() > 0)
//	        {
//	            ArrayList filterList = new ArrayList();
//	            
//	            for (int i = 0; i < mFilterList.size(); i++)
//	            {
//	            	if (mFilterList.get(i) instanceof AvailableCountry)
//	            	{
//		                if(((AvailableCountry) mFilterList.get(i)).getValue().toLowerCase().contains(constraint.toString().toLowerCase())) 
//		                {
//		                    filterList.add(mFilterList.get(i));
//		                }
//	            	}
//	            	else
//	            	{
//	            		if(((AvailableState) mFilterList.get(i)).getValue().toLowerCase().contains(constraint.toString().toLowerCase())) 
//		                {
//		                    filterList.add(mFilterList.get(i));
//		                }
//					}
//				}
//	            results.count = filterList.size();
//	            results.values = filterList;
//	        }
//	        else
//	        {
//	            results.count = mFilterList.size();
//	            results.values = mFilterList;
//			}
//			return results;
//		}
//
//		@Override
//		protected void publishResults(CharSequence constraint,
//				FilterResults results)
//		{
//			mAvailableRegion = (ArrayList<?>) results.values;
//			notifyDataSetChanged();
//		}
//	}
}
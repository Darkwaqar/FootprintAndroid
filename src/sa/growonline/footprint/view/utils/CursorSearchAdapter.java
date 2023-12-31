package sa.growonline.footprint.view.utils;

import sa.growonline.footprint.R;
import sa.growonline.footprint.view.db.HistoryContract;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mauker on 19/04/2016.
 *
 * Default adapter used for the suggestion/history ListView.
 */
public class CursorSearchAdapter extends CursorAdapter {

    public CursorSearchAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ListViewHolder vh = new ListViewHolder(view);
        view.setTag(vh);

        String text = cursor.getString(cursor.getColumnIndexOrThrow(HistoryContract.HistoryEntry.COLUMN_QUERY));

        boolean isHistory = cursor.getInt(cursor.getColumnIndexOrThrow(
                                            HistoryContract.HistoryEntry.COLUMN_IS_HISTORY)) != 0;

        vh.tv_content.setText(text);

        if (isHistory) {
            vh.iv_icon.setImageResource(R.drawable.ic_history_white_24dp);
        }
        else {
            vh.iv_icon.setImageResource(R.drawable.ic_search_white_24dp);
        }
    }

    private class ListViewHolder {
        ImageView iv_icon;
        TextView tv_content;

        public ListViewHolder(View convertView) {
            iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            tv_content = (TextView) convertView.findViewById(R.id.tv_str);
        }
    }
}

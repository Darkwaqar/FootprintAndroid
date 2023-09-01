package sa.growonline.footprint.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import sa.growonline.footprint.R;
import sa.growonline.footprint.bean.GiftCard;

public class AdapterGiftCard extends BaseAdapter {

    private Context mContext;
    private ArrayList<GiftCard> mGiftCards = new ArrayList<>();
    private LayoutInflater mLayoutInflator;

    public AdapterGiftCard(Context context, ArrayList<GiftCard> giftCards) {
        this.mContext = context;
        this.mGiftCards = giftCards;
        this.mLayoutInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AdapterGiftCard(Context context) {
        this.mContext = context;
        this.mLayoutInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mGiftCards.size();
    }

    @Override
    public Object getItem(int i) {
        return mGiftCards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) mGiftCards.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
            view = mLayoutInflator.inflate(R.layout.single_spinner_item, viewGroup, false);

        TextView txtView = (TextView) view.findViewById(R.id.txt_name);
        txtView.setText(mGiftCards.get(i).getFormattedAmount());

        return view;
    }

    public void setGiftCardItems(ArrayList<GiftCard> giftCards) {
        this.mGiftCards = giftCards;
    }
}

package sa.growonline.footprint.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import sa.growonline.footprint.R;
import sa.growonline.footprint.bean.NotFilteredItem;
import sa.growonline.footprint.utils.DialogRefine;

public class AdapterRefineItem extends RecyclerView.Adapter<AdapterRefineItem.RefineItemViewHolder> {

   private DialogRefine mDialog;
   private Context mContext;
   private String mType;
   private ArrayList<NotFilteredItem> items = new ArrayList<>();

    class RefineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public String id;
        TextView singleAttributeText;

        RefineItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            singleAttributeText = (TextView) itemView.findViewById(R.id.single_refine_item_name);
        }

        @Override
        public void onClick(View view) {
            mDialog.adapterSingleItemClick(mType, this.singleAttributeText.getText().toString(), id);
        }
    }

    public AdapterRefineItem(Context context, List<NotFilteredItem> refineItems, String type, DialogRefine fromWhere) {
        this.mContext = context;
        this.mDialog = fromWhere;
        this.mType = type;
        items = createCustomizedCollection(refineItems, type);
    }

    @Override
    public RefineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_single_refine_item, parent, false);

        return new RefineItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RefineItemViewHolder holder, int position) {
        NotFilteredItem currentItem = items.get(position);

        holder.id = currentItem.getSpecificationAttributeOptionId();
        holder.singleAttributeText.setText(currentItem.getSpecificationAttributeOptionName());

        android.view.animation.Animation anim = android.view.animation.AnimationUtils.loadAnimation(mContext, R.anim.top_to_bottom_attr);
        anim.setStartOffset(100 * (long) Math.floor(position - 0.5));
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(200);
        holder.itemView.startAnimation(anim);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private ArrayList<NotFilteredItem> createCustomizedCollection(List<NotFilteredItem> originalCollection, String type) {

        ArrayList<NotFilteredItem> temp = new ArrayList<>();

        for (int i = 0; i < originalCollection.size(); i++) {
            NotFilteredItem x = originalCollection.get(i);
            if (x.getSpecificationAttributeName().equalsIgnoreCase(type)) {
                temp.add(originalCollection.get(i));
            }
        }

        return temp;
    }

    public void refresh(List<NotFilteredItem> refineItems, String type){
        if(items != null){
            items.clear();
        }

        this.mType = type;
        items = createCustomizedCollection(refineItems, type);
    }

    public void clear(){
        if(items != null){
            items.clear();
        }
    }

}

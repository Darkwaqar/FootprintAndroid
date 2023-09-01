package sa.growonline.footprint.adapter;

import java.util.ArrayList;

import sa.growonline.footprint.R;
import sa.growonline.footprint.ZuniApplication;
import sa.growonline.footprint.adapter.AdapterMaterialProduct.ProductViewHolder;
import sa.growonline.footprint.bean.BeanProductForCategory;
import sa.growonline.footprint.utils.ZuniUtils;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class AdapterMaterialProduct extends Adapter<ProductViewHolder>
{
	private ArrayList<BeanProductForCategory> mList;

	public AdapterMaterialProduct(ArrayList<BeanProductForCategory> mBeanList)
	{
		this.mList = mBeanList;
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	@Override
	public void onBindViewHolder(ProductViewHolder viewHolder, int pos)
	{
		ZuniApplication.getmCacheManager().loadImageWithCenterCrop(Uri.parse(mList.get(pos).getImageModel().getmFullSizeImageUrl()), viewHolder.mProductImageView, null);
 		viewHolder.mProductPriceView.setText(mList.get(pos).getProductPrice().getPrice());
		viewHolder.mProductTitleView.setText(mList.get(pos).getProductName());
		
		
		ZuniUtils.applyAppFont(viewHolder.mProductPriceView);
		ZuniUtils.applyAppFont(viewHolder.mProductTitleView);
	}

	@Override
	public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_material_product, viewGroup, false);
		return new ProductViewHolder(view);
	}

	static class ProductViewHolder extends RecyclerView.ViewHolder
	{
		ImageView mProductImageView;
		private TextView mProductTitleView;
		private TextView mProductPriceView;
		
		ProductViewHolder(View itemView)
		{
			super(itemView);
			mProductImageView = (ImageView) itemView.findViewById(R.id.materail_product_img);
			mProductTitleView = (TextView) 	itemView.findViewById(R.id.materail_product_name_txtview);
			mProductPriceView = (TextView) 	itemView.findViewById(R.id.materail_product_price_txtview);

			
		}
	}
}
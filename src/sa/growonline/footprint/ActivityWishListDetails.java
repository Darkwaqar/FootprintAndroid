/*
 * Copyright (c) 2016 Arsal Raza Imam
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sa.growonline.footprint.adapter.AdapterUserWishList;
import sa.growonline.footprint.asynctask.AynctaskGetWishList;
import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.bean.BeanWishListModel;

public class ActivityWishListDetails extends BaseActivityx {
    private ListView mWishListDetailGridView;
    private TextView mWishListEmptyProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setupToolBar();
        removeToolbar();
        mWishListDetailGridView = (ListView) findViewById(R.id.activity_wishlist_gridview);
        mWishListEmptyProduct = (TextView) findViewById(R.id.activity_whishlist_emptyproduct);

        new AynctaskGetWishList(ActivityWishListDetails.this, true).execute();
    }

    public void onWishListObtained(ArrayList<BeanWishListModel> mWishListModels, String mCustomerGuId) {
        if (mWishListModels == null) return;

        if (mWishListModels.size() == 0) {
            mWishListEmptyProduct.setText(R.string.WishListEmpty);
            mWishListEmptyProduct.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);
        }
        if (mWishListDetailGridView.getAdapter() == null) {
            AdapterUserWishList adapter = new AdapterUserWishList(this, mWishListModels);
            mWishListDetailGridView.setAdapter(adapter);
        } else
            ((AdapterUserWishList) mWishListDetailGridView.getAdapter()).notifyListChanged(mWishListModels);
    }

    public void updateList(boolean isProgressEnable) {
        new AynctaskGetWishList(ActivityWishListDetails.this, isProgressEnable).execute();
    }
}
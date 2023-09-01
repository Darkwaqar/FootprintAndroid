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
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import sa.growonline.footprint.fragment.FragmentDetailParent;

public class ActivitySingleProduct extends AppCompatActivity {
    private FragmentManager mFragmentManagerObj;
    private FragmentDetailParent mFragmentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mFragmentManagerObj = getSupportFragmentManager();
        String productId = ZuniApplication.getmAppPreferences().getString("singleProductId", "1");

        mFragmentDetail = new FragmentDetailParent();
        mFragmentDetail.setProductId(productId);

        mFragmentManagerObj.beginTransaction().replace(R.id.single_product_content_frame, mFragmentDetail).commitAllowingStateLoss();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentDetail.callAsync(true);
            }
        }, 500);
    }

    public void updateTitle(String s) {
        setTitle(s);
    }
}
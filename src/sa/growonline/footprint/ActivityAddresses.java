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

import sa.growonline.footprint.base.BaseActivityx;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutAddLocation;
import sa.growonline.footprint.fragment.checkout.FragmentCheckOutLocationListing;
import sa.growonline.footprint.utils.ZuniConstants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;

public class ActivityAddresses extends BaseActivityx {
    private FragmentCheckOutLocationListing mFragmentListing;
    private FragmentManager mFragmentManager;
    private FragmentCheckOutAddLocation mFragmentAddLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setupToolBar();
        removeToolbar();
        if (mFragmentListing == null)
            mFragmentListing = new FragmentCheckOutLocationListing();

        if (mFragmentAddLocation == null)
            mFragmentAddLocation = new FragmentCheckOutAddLocation();

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.activity_address_container, mFragmentListing).commitAllowingStateLoss();
    }

    public void edit(String jsonstring) {
        //!-- Edit Location
        ZuniApplication.getmAppPrefEditor().putString(ZuniConstants.SELECTED_LOCATION_FOR_EDIT, jsonstring).commit();
        FragmentCheckOutAddLocation.isEdit = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 12) {
                editComplete();
            }
        }
    }

    public void editComplete() {
        mFragmentListing.reload();
    }
}
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

package sa.growonline.footprint.bean.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOutBean
{
    @SerializedName("ShippingLocation")
    @Expose
    private sa.growonline.footprint.bean.checkout.ShippingLocation ShippingLocation;
    @SerializedName("ShippingMethod")
    @Expose
    private sa.growonline.footprint.bean.checkout.ShippingMethod ShippingMethod;
    @SerializedName("PaymentMethods")
    @Expose
    private sa.growonline.footprint.bean.checkout.PaymentMethods PaymentMethods;

    /**
     * 
     * @return
     *     The ShippingLocation
     */
    public sa.growonline.footprint.bean.checkout.ShippingLocation getShippingLocation() {
        return ShippingLocation;
    }

    /**
     * 
     * @param ShippingLocation
     *     The ShippingLocation
     */
    public void setShippingLocation(sa.growonline.footprint.bean.checkout.ShippingLocation ShippingLocation) {
        this.ShippingLocation = ShippingLocation;
    }

    /**
     * 
     * @return
     *     The ShippingMethod
     */
    public sa.growonline.footprint.bean.checkout.ShippingMethod getShippingMethod() {
        return ShippingMethod;
    }

    /**
     * 
     * @param ShippingMethod
     *     The ShippingMethod
     */
    public void setShippingMethod(sa.growonline.footprint.bean.checkout.ShippingMethod ShippingMethod) {
        this.ShippingMethod = ShippingMethod;
    }

    /**
     * 
     * @return
     *     The PaymentMethods
     */
    public sa.growonline.footprint.bean.checkout.PaymentMethods getPaymentMethods() {
        return PaymentMethods;
    }

    /**
     * 
     * @param PaymentMethods
     *     The PaymentMethods
     */
    public void setPaymentMethods(sa.growonline.footprint.bean.checkout.PaymentMethods PaymentMethods) {
        this.PaymentMethods = PaymentMethods;
    }

}
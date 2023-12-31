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

package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BeanGetAllCarts {

    @SerializedName("OnePageCheckoutEnabled")
    @Expose
    private Boolean OnePageCheckoutEnabled;
    @SerializedName("ShowSku")
    @Expose
    private Boolean ShowSku;
    @SerializedName("ShowProductImages")
    @Expose
    private Boolean ShowProductImages;
    @SerializedName("IsEditable")
    @Expose
    private Boolean IsEditable;

    @SerializedName("Items")
    @Expose
    private List<BeanGetAllCartsProduct> Items = new ArrayList<BeanGetAllCartsProduct>();

    @SerializedName("CheckoutAttributeInfo")
    @Expose

    private String CheckoutAttributeInfo;

//    @SerializedName("CheckoutAttributes")
//    @Expose
//    private List<CheckoutAttribute> CheckoutAttributes = new ArrayList<CheckoutAttribute>();

    @SerializedName("Warnings")
    @Expose
    private List<Object> Warnings = new ArrayList<Object>();
    @SerializedName("MinOrderSubtotalWarning")
    @Expose
    private Object MinOrderSubtotalWarning;
    @SerializedName("TermsOfServiceOnShoppingCartPage")
    @Expose
    private Boolean TermsOfServiceOnShoppingCartPage;
    @SerializedName("TermsOfServiceOnOrderConfirmPage")
    @Expose
    private Boolean TermsOfServiceOnOrderConfirmPage;

//    @SerializedName("EstimateShipping")
//    @Expose
//    private sa.etrendz.zuni.bean.EstimateShipping EstimateShipping;
//    @SerializedName("DiscountBox")
//    @Expose
//    private sa.etrendz.zuni.bean.DiscountBox DiscountBox;
//    @SerializedName("GiftCardBox")
//    @Expose
//    private sa.etrendz.zuni.bean.GiftCardBox GiftCardBox;
//    
//    @SerializedName("OrderReviewData")
//    @Expose
//    private sa.etrendz.zuni.bean.OrderReviewData OrderReviewData;

    @SerializedName("ButtonPaymentMethodActionNames")
    @Expose
    private List<Object> ButtonPaymentMethodActionNames = new ArrayList<Object>();
    @SerializedName("ButtonPaymentMethodControllerNames")
    @Expose
    private List<Object> ButtonPaymentMethodControllerNames = new ArrayList<Object>();
    @SerializedName("ButtonPaymentMethodRouteValues")
    @Expose
    private List<Object> ButtonPaymentMethodRouteValues = new ArrayList<Object>();

    private String mTotalPaymentPaidByUser;
    private String mShippingFees;

    /**
     * @return The OnePageCheckoutEnabled
     */
    public Boolean getOnePageCheckoutEnabled() {
        return OnePageCheckoutEnabled;
    }

    /**
     * @param OnePageCheckoutEnabled The OnePageCheckoutEnabled
     */
    public void setOnePageCheckoutEnabled(Boolean OnePageCheckoutEnabled) {
        this.OnePageCheckoutEnabled = OnePageCheckoutEnabled;
    }

    /**
     * @return The ShowSku
     */
    public Boolean getShowSku() {
        return ShowSku;
    }

    /**
     * @param ShowSku The ShowSku
     */
    public void setShowSku(Boolean ShowSku) {
        this.ShowSku = ShowSku;
    }

    /**
     * @return The ShowProductImages
     */
    public Boolean getShowProductImages() {
        return ShowProductImages;
    }

    /**
     * @param ShowProductImages The ShowProductImages
     */
    public void setShowProductImages(Boolean ShowProductImages) {
        this.ShowProductImages = ShowProductImages;
    }

    /**
     * @return The IsEditable
     */
    public Boolean getIsEditable() {
        return IsEditable;
    }

    /**
     * @param IsEditable The IsEditable
     */
    public void setIsEditable(Boolean IsEditable) {
        this.IsEditable = IsEditable;
    }

    /**
     * @return The Items
     */
    public List<BeanGetAllCartsProduct> getItems() {
        return Items;
    }

    /**
     * @param Items The Items
     */
    public void setItems(List<BeanGetAllCartsProduct> Items) {
        this.Items = Items;
    }

    /**
     * @return The CheckoutAttributeInfo
     */
    public String getCheckoutAttributeInfo() {
        return CheckoutAttributeInfo;
    }

    /**
     * @param CheckoutAttributeInfo The CheckoutAttributeInfo
     */
    public void setCheckoutAttributeInfo(String CheckoutAttributeInfo) {
        this.CheckoutAttributeInfo = CheckoutAttributeInfo;
    }

//    /**
//     * 
//     * @return
//     *     The CheckoutAttributes
//     */
//    public List<CheckoutAttribute> getCheckoutAttributes() {
//        return CheckoutAttributes;
//    }
//
//    /**
//     * 
//     * @param CheckoutAttributes
//     *     The CheckoutAttributes
//     */
//    public void setCheckoutAttributes(List<CheckoutAttribute> CheckoutAttributes) {
//        this.CheckoutAttributes = CheckoutAttributes;
//    }

    /**
     * @return The Warnings
     */
    public List<Object> getWarnings() {
        return Warnings;
    }

    /**
     * @param Warnings The Warnings
     */
    public void setWarnings(List<Object> Warnings) {
        this.Warnings = Warnings;
    }

    /**
     * @return The MinOrderSubtotalWarning
     */
    public Object getMinOrderSubtotalWarning() {
        return MinOrderSubtotalWarning;
    }

    /**
     * @param MinOrderSubtotalWarning The MinOrderSubtotalWarning
     */
    public void setMinOrderSubtotalWarning(Object MinOrderSubtotalWarning) {
        this.MinOrderSubtotalWarning = MinOrderSubtotalWarning;
    }

    /**
     * @return The TermsOfServiceOnShoppingCartPage
     */
    public Boolean getTermsOfServiceOnShoppingCartPage() {
        return TermsOfServiceOnShoppingCartPage;
    }

    /**
     * @param TermsOfServiceOnShoppingCartPage The TermsOfServiceOnShoppingCartPage
     */
    public void setTermsOfServiceOnShoppingCartPage(Boolean TermsOfServiceOnShoppingCartPage) {
        this.TermsOfServiceOnShoppingCartPage = TermsOfServiceOnShoppingCartPage;
    }

    /**
     * @return The TermsOfServiceOnOrderConfirmPage
     */
    public Boolean getTermsOfServiceOnOrderConfirmPage() {
        return TermsOfServiceOnOrderConfirmPage;
    }

    /**
     * @param TermsOfServiceOnOrderConfirmPage The TermsOfServiceOnOrderConfirmPage
     */
    public void setTermsOfServiceOnOrderConfirmPage(Boolean TermsOfServiceOnOrderConfirmPage) {
        this.TermsOfServiceOnOrderConfirmPage = TermsOfServiceOnOrderConfirmPage;
    }

//    /**
//     * 
//     * @return
//     *     The EstimateShipping
//     */
//    public sa.etrendz.zuni.bean.EstimateShipping getEstimateShipping() {
//        return EstimateShipping;
//    }
//
//    /**
//     * 
//     * @param EstimateShipping
//     *     The EstimateShipping
//     */
//    public void setEstimateShipping(sa.etrendz.zuni.bean.EstimateShipping EstimateShipping) {
//        this.EstimateShipping = EstimateShipping;
//    }
//
//    /**
//     * 
//     * @return
//     *     The DiscountBox
//     */
//    public sa.etrendz.zuni.bean.DiscountBox getDiscountBox() {
//        return DiscountBox;
//    }
//
//    /**
//     * 
//     * @param DiscountBox
//     *     The DiscountBox
//     */
//    public void setDiscountBox(sa.etrendz.zuni.bean.DiscountBox DiscountBox) {
//        this.DiscountBox = DiscountBox;
//    }
//
//    /**
//     * 
//     * @return
//     *     The GiftCardBox
//     */
//    public sa.etrendz.zuni.bean.GiftCardBox getGiftCardBox() {
//        return GiftCardBox;
//    }
//
//    /**
//     * 
//     * @param GiftCardBox
//     *     The GiftCardBox
//     */
//    public void setGiftCardBox(sa.etrendz.zuni.bean.GiftCardBox GiftCardBox) {
//        this.GiftCardBox = GiftCardBox;
//    }
//
//    /**
//     * 
//     * @return
//     *     The OrderReviewData
//     */
//    public sa.etrendz.zuni.bean.OrderReviewData getOrderReviewData() {
//        return OrderReviewData;
//    }
//
//    /**
//     * 
//     * @param OrderReviewData
//     *     The OrderReviewData
//     */
//    public void setOrderReviewData(sa.etrendz.zuni.bean.OrderReviewData OrderReviewData) {
//        this.OrderReviewData = OrderReviewData;
//    }

    /**
     * @return The ButtonPaymentMethodActionNames
     */
    public List<Object> getButtonPaymentMethodActionNames() {
        return ButtonPaymentMethodActionNames;
    }

    /**
     * @param ButtonPaymentMethodActionNames The ButtonPaymentMethodActionNames
     */
    public void setButtonPaymentMethodActionNames(List<Object> ButtonPaymentMethodActionNames) {
        this.ButtonPaymentMethodActionNames = ButtonPaymentMethodActionNames;
    }

    /**
     * @return The ButtonPaymentMethodControllerNames
     */
    public List<Object> getButtonPaymentMethodControllerNames() {
        return ButtonPaymentMethodControllerNames;
    }

    /**
     * @param ButtonPaymentMethodControllerNames The ButtonPaymentMethodControllerNames
     */
    public void setButtonPaymentMethodControllerNames(List<Object> ButtonPaymentMethodControllerNames) {
        this.ButtonPaymentMethodControllerNames = ButtonPaymentMethodControllerNames;
    }

    /**
     * @return The ButtonPaymentMethodRouteValues
     */
    public List<Object> getButtonPaymentMethodRouteValues() {
        return ButtonPaymentMethodRouteValues;
    }

    /**
     * @param ButtonPaymentMethodRouteValues The ButtonPaymentMethodRouteValues
     */
    public void setButtonPaymentMethodRouteValues(List<Object> ButtonPaymentMethodRouteValues) {
        this.ButtonPaymentMethodRouteValues = ButtonPaymentMethodRouteValues;
    }

    public String getmTotalPaymentPaidByUser() {
        return mTotalPaymentPaidByUser;
    }

    public void setmTotalPaymentPaidByUser(String mTotalPaymentPaidByUser) {
        this.mTotalPaymentPaidByUser = mTotalPaymentPaidByUser;
    }

    public String getmShippingFees() {
        return mShippingFees;
    }

    public void setmShippingFees(String mShippingFees) {
        this.mShippingFees = mShippingFees;
    }

//    /**
//     * 
//     * @return
//     *     The CustomProperties
//     */
//    public CustomProperties__________ getCustomProperties() {
//        return CustomProperties;
//    }
//
//    /**
//     * 
//     * @param CustomProperties
//     *     The CustomProperties
//     */
//    public void setCustomProperties(CustomProperties__________ CustomProperties) {
//        this.CustomProperties = CustomProperties;
//    }

}

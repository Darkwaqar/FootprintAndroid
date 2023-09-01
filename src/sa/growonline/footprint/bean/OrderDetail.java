package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import sa.growonline.footprint.bean.checkout.ExistingAddress;

public class OrderDetail {

    @SerializedName("PrintMode")
    @Expose
    private Boolean printMode;
    @SerializedName("PdfInvoiceDisabled")
    @Expose
    private Boolean pdfInvoiceDisabled;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;
    @SerializedName("OrderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("IsReOrderAllowed")
    @Expose
    private Boolean isReOrderAllowed;
    @SerializedName("IsReturnRequestAllowed")
    @Expose
    private Boolean isReturnRequestAllowed;
    @SerializedName("IsShippable")
    @Expose
    private Boolean isShippable;
    @SerializedName("PickUpInStore")
    @Expose
    private Boolean pickUpInStore;
    @SerializedName("ShippingStatus")
    @Expose
    private String shippingStatus;
    @SerializedName("ShippingAddress")
    @Expose
    private ExistingAddress shippingAddress;
    @SerializedName("ShippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("Shipments")
    @Expose
    private List<Object> shipments = new ArrayList<Object>();
    @SerializedName("BillingAddress")
    @Expose
    private ExistingAddress billingAddress;
    @SerializedName("VatNumber")
    @Expose
    private Object vatNumber;
    @SerializedName("PaymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("PaymentMethodStatus")
    @Expose
    private String paymentMethodStatus;
    @SerializedName("CanRePostProcessPayment")
    @Expose
    private Boolean canRePostProcessPayment;


    @SerializedName("OrderSubtotal")
    @Expose
    private String orderSubtotal;
    @SerializedName("OrderSubTotalDiscount")
    @Expose
    private Object orderSubTotalDiscount;
    @SerializedName("OrderShipping")
    @Expose
    private String orderShipping;
    @SerializedName("PaymentMethodAdditionalFee")
    @Expose
    private Object paymentMethodAdditionalFee;
    @SerializedName("CheckoutAttributeInfo")
    @Expose
    private String checkoutAttributeInfo;
    @SerializedName("PricesIncludeTax")
    @Expose
    private Boolean pricesIncludeTax;
    @SerializedName("DisplayTaxShippingInfo")
    @Expose
    private Boolean displayTaxShippingInfo;
    @SerializedName("Tax")
    @Expose
    private String tax;
    @SerializedName("DisplayTax")
    @Expose
    private Boolean displayTax;
    @SerializedName("DisplayTaxRates")
    @Expose
    private Boolean displayTaxRates;
    @SerializedName("OrderTotalDiscount")
    @Expose
    private Object orderTotalDiscount;
    @SerializedName("RedeemedRewardPoints")
    @Expose
    private Integer redeemedRewardPoints;
    @SerializedName("RedeemedRewardPointsAmount")
    @Expose
    private Object redeemedRewardPointsAmount;
    @SerializedName("OrderTotal")
    @Expose
    private String orderTotal;
    @SerializedName("GiftCards")
    @Expose
    private List<Object> giftCards = new ArrayList<Object>();
    @SerializedName("ShowSku")
    @Expose
    private Boolean showSku;
    @SerializedName("Items")
    @Expose
    private List<SkuObject> items = new ArrayList<SkuObject>();
    @SerializedName("OrderNotes")
    @Expose
    private List<Object> orderNotes = new ArrayList<Object>();
    @SerializedName("Id")
    @Expose
    private Integer id;

    /**
     * @return The printMode
     */
    public Boolean getPrintMode() {
        return printMode;
    }

    /**
     * @param printMode The PrintMode
     */
    public void setPrintMode(Boolean printMode) {
        this.printMode = printMode;
    }

    /**
     * @return The pdfInvoiceDisabled
     */
    public Boolean getPdfInvoiceDisabled() {
        return pdfInvoiceDisabled;
    }

    /**
     * @param pdfInvoiceDisabled The PdfInvoiceDisabled
     */
    public void setPdfInvoiceDisabled(Boolean pdfInvoiceDisabled) {
        this.pdfInvoiceDisabled = pdfInvoiceDisabled;
    }

    /**
     * @return The createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn The CreatedOn
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return The orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus The OrderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return The isReOrderAllowed
     */
    public Boolean getIsReOrderAllowed() {
        return isReOrderAllowed;
    }

    /**
     * @param isReOrderAllowed The IsReOrderAllowed
     */
    public void setIsReOrderAllowed(Boolean isReOrderAllowed) {
        this.isReOrderAllowed = isReOrderAllowed;
    }

    /**
     * @return The isReturnRequestAllowed
     */
    public Boolean getIsReturnRequestAllowed() {
        return isReturnRequestAllowed;
    }

    /**
     * @param isReturnRequestAllowed The IsReturnRequestAllowed
     */
    public void setIsReturnRequestAllowed(Boolean isReturnRequestAllowed) {
        this.isReturnRequestAllowed = isReturnRequestAllowed;
    }

    /**
     * @return The isShippable
     */
    public Boolean getIsShippable() {
        return isShippable;
    }

    /**
     * @param isShippable The IsShippable
     */
    public void setIsShippable(Boolean isShippable) {
        this.isShippable = isShippable;
    }

    /**
     * @return The pickUpInStore
     */
    public Boolean getPickUpInStore() {
        return pickUpInStore;
    }

    /**
     * @param pickUpInStore The PickUpInStore
     */
    public void setPickUpInStore(Boolean pickUpInStore) {
        this.pickUpInStore = pickUpInStore;
    }

    /**
     * @return The shippingStatus
     */
    public String getShippingStatus() {
        return shippingStatus;
    }

    /**
     * @param shippingStatus The ShippingStatus
     */
    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * @return The shippingAddress
     */
    public ExistingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress The ShippingAddress
     */
    public void setShippingAddress(ExistingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return The shippingMethod
     */
    public String getShippingMethod() {
        return shippingMethod;
    }

    /**
     * @param shippingMethod The ShippingMethod
     */
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * @return The shipments
     */
    public List<Object> getShipments() {
        return shipments;
    }

    /**
     * @param shipments The Shipments
     */
    public void setShipments(List<Object> shipments) {
        this.shipments = shipments;
    }

    /**
     * @return The billingAddress
     */
    public ExistingAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress The BillingAddress
     */
    public void setBillingAddress(ExistingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return The vatNumber
     */
    public Object getVatNumber() {
        return vatNumber;
    }

    /**
     * @param vatNumber The VatNumber
     */
    public void setVatNumber(Object vatNumber) {
        this.vatNumber = vatNumber;
    }

    /**
     * @return The paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod The PaymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return The paymentMethodStatus
     */
    public String getPaymentMethodStatus() {
        return paymentMethodStatus;
    }

    /**
     * @param paymentMethodStatus The PaymentMethodStatus
     */
    public void setPaymentMethodStatus(String paymentMethodStatus) {
        this.paymentMethodStatus = paymentMethodStatus;
    }

    /**
     * @return The canRePostProcessPayment
     */
    public Boolean getCanRePostProcessPayment() {
        return canRePostProcessPayment;
    }

    /**
     * @param canRePostProcessPayment The CanRePostProcessPayment
     */
    public void setCanRePostProcessPayment(Boolean canRePostProcessPayment) {
        this.canRePostProcessPayment = canRePostProcessPayment;
    }

    /**
     * @return The orderSubtotal
     */
    public String getOrderSubtotal() {
        return orderSubtotal;
    }

    /**
     * @param orderSubtotal The OrderSubtotal
     */
    public void setOrderSubtotal(String orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    /**
     * @return The orderSubTotalDiscount
     */
    public Object getOrderSubTotalDiscount() {
        return orderSubTotalDiscount;
    }

    /**
     * @param orderSubTotalDiscount The OrderSubTotalDiscount
     */
    public void setOrderSubTotalDiscount(Object orderSubTotalDiscount) {
        this.orderSubTotalDiscount = orderSubTotalDiscount;
    }

    /**
     * @return The orderShipping
     */
    public String getOrderShipping() {
        return orderShipping;
    }

    /**
     * @param orderShipping The OrderShipping
     */
    public void setOrderShipping(String orderShipping) {
        this.orderShipping = orderShipping;
    }

    /**
     * @return The paymentMethodAdditionalFee
     */
    public Object getPaymentMethodAdditionalFee() {
        return paymentMethodAdditionalFee;
    }

    /**
     * @param paymentMethodAdditionalFee The PaymentMethodAdditionalFee
     */
    public void setPaymentMethodAdditionalFee(Object paymentMethodAdditionalFee) {
        this.paymentMethodAdditionalFee = paymentMethodAdditionalFee;
    }

    /**
     * @return The checkoutAttributeInfo
     */
    public String getCheckoutAttributeInfo() {
        return checkoutAttributeInfo;
    }

    /**
     * @param checkoutAttributeInfo The CheckoutAttributeInfo
     */
    public void setCheckoutAttributeInfo(String checkoutAttributeInfo) {
        this.checkoutAttributeInfo = checkoutAttributeInfo;
    }

    /**
     * @return The pricesIncludeTax
     */
    public Boolean getPricesIncludeTax() {
        return pricesIncludeTax;
    }

    /**
     * @param pricesIncludeTax The PricesIncludeTax
     */
    public void setPricesIncludeTax(Boolean pricesIncludeTax) {
        this.pricesIncludeTax = pricesIncludeTax;
    }

    /**
     * @return The displayTaxShippingInfo
     */
    public Boolean getDisplayTaxShippingInfo() {
        return displayTaxShippingInfo;
    }

    /**
     * @param displayTaxShippingInfo The DisplayTaxShippingInfo
     */
    public void setDisplayTaxShippingInfo(Boolean displayTaxShippingInfo) {
        this.displayTaxShippingInfo = displayTaxShippingInfo;
    }

    /**
     * @return The tax
     */
    public String getTax() {
        return tax;
    }

    /**
     * @param tax The Tax
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

//    /**
//     *
//     * @return
//     *     The taxRates
//     */
//    public List<TaxRate> getTaxRates() {
//        return taxRates;
//    }
//
//    /**
//     *
//     * @param taxRates
//     *     The TaxRates
//     */
//    public void setTaxRates(List<TaxRate> taxRates) {
//        this.taxRates = taxRates;
//    }

    /**
     * @return The displayTax
     */
    public Boolean getDisplayTax() {
        return displayTax;
    }

    /**
     * @param displayTax The DisplayTax
     */
    public void setDisplayTax(Boolean displayTax) {
        this.displayTax = displayTax;
    }

    /**
     * @return The displayTaxRates
     */
    public Boolean getDisplayTaxRates() {
        return displayTaxRates;
    }

    /**
     * @param displayTaxRates The DisplayTaxRates
     */
    public void setDisplayTaxRates(Boolean displayTaxRates) {
        this.displayTaxRates = displayTaxRates;
    }

    /**
     * @return The orderTotalDiscount
     */
    public Object getOrderTotalDiscount() {
        return orderTotalDiscount;
    }

    /**
     * @param orderTotalDiscount The OrderTotalDiscount
     */
    public void setOrderTotalDiscount(Object orderTotalDiscount) {
        this.orderTotalDiscount = orderTotalDiscount;
    }

    /**
     * @return The redeemedRewardPoints
     */
    public Integer getRedeemedRewardPoints() {
        return redeemedRewardPoints;
    }

    /**
     * @param redeemedRewardPoints The RedeemedRewardPoints
     */
    public void setRedeemedRewardPoints(Integer redeemedRewardPoints) {
        this.redeemedRewardPoints = redeemedRewardPoints;
    }

    /**
     * @return The redeemedRewardPointsAmount
     */
    public Object getRedeemedRewardPointsAmount() {
        return redeemedRewardPointsAmount;
    }

    /**
     * @param redeemedRewardPointsAmount The RedeemedRewardPointsAmount
     */
    public void setRedeemedRewardPointsAmount(Object redeemedRewardPointsAmount) {
        this.redeemedRewardPointsAmount = redeemedRewardPointsAmount;
    }

    /**
     * @return The orderTotal
     */
    public String getOrderTotal() {
        return orderTotal;
    }

    /**
     * @param orderTotal The OrderTotal
     */
    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * @return The giftCards
     */
    public List<Object> getGiftCards() {
        return giftCards;
    }

    /**
     * @param giftCards The GiftCards
     */
    public void setGiftCards(List<Object> giftCards) {
        this.giftCards = giftCards;
    }

    /**
     * @return The showSku
     */
    public Boolean getShowSku() {
        return showSku;
    }

    /**
     * @param showSku The ShowSku
     */
    public void setShowSku(Boolean showSku) {
        this.showSku = showSku;
    }

    /**
     * @return The items
     */
    public List<SkuObject> getItems() {
        return items;
    }

    /**
     * @param items The Items
     */
    public void setItems(List<SkuObject> items) {
        this.items = items;
    }

    /**
     * @return The orderNotes
     */
    public List<Object> getOrderNotes() {
        return orderNotes;
    }

    /**
     * @param orderNotes The OrderNotes
     */
    public void setOrderNotes(List<Object> orderNotes) {
        this.orderNotes = orderNotes;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

}

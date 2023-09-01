package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkuObject {

    @SerializedName("OrderItemGuid")
    @Expose
    private String orderItemGuid;
    @SerializedName("Sku")
    @Expose
    private String sku;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductSeName")
    @Expose
    private String productSeName;
    @SerializedName("UnitPrice")
    @Expose
    private String unitPrice;
    @SerializedName("SubTotal")
    @Expose
    private String subTotal;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("AttributeInfo")
    @Expose
    private String attributeInfo;
    @SerializedName("RentalInfo")
    @Expose
    private Object rentalInfo;
    @SerializedName("DownloadId")
    @Expose
    private Integer downloadId;
    @SerializedName("LicenseId")
    @Expose
    private Integer licenseId;
    @SerializedName("Id")
    @Expose
    private Integer id;

    /**
     * @return The orderItemGuid
     */
    public String getOrderItemGuid() {
        return orderItemGuid;
    }

    /**
     * @param orderItemGuid The OrderItemGuid
     */
    public void setOrderItemGuid(String orderItemGuid) {
        this.orderItemGuid = orderItemGuid;
    }

    /**
     * @return The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku The Sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return The productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId The ProductId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName The ProductName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return The productSeName
     */
    public String getProductSeName() {
        return productSeName;
    }

    /**
     * @param productSeName The ProductSeName
     */
    public void setProductSeName(String productSeName) {
        this.productSeName = productSeName;
    }

    /**
     * @return The unitPrice
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice The UnitPrice
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return The subTotal
     */
    public String getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal The SubTotal
     */
    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The Quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return The attributeInfo
     */
    public String getAttributeInfo() {
        return attributeInfo;
    }

    /**
     * @param attributeInfo The AttributeInfo
     */
    public void setAttributeInfo(String attributeInfo) {
        this.attributeInfo = attributeInfo;
    }

    /**
     * @return The rentalInfo
     */
    public Object getRentalInfo() {
        return rentalInfo;
    }

    /**
     * @param rentalInfo The RentalInfo
     */
    public void setRentalInfo(Object rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    /**
     * @return The downloadId
     */
    public Integer getDownloadId() {
        return downloadId;
    }

    /**
     * @param downloadId The DownloadId
     */
    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    /**
     * @return The licenseId
     */
    public Integer getLicenseId() {
        return licenseId;
    }

    /**
     * @param licenseId The LicenseId
     */
    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
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
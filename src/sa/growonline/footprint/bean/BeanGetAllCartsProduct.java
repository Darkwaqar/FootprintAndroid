package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BeanGetAllCartsProduct {

    @SerializedName("Sku")
    @Expose
    private Object Sku;
    @SerializedName("Picture")
    @Expose
    private BeanServerImage Picture;
    @SerializedName("ProductId")
    @Expose
    private Integer ProductId;
    @SerializedName("ProductName")
    @Expose
    private String ProductName;
    @SerializedName("ProductSeName")
    @Expose
    private String ProductSeName;
    @SerializedName("UnitPrice")
    @Expose
    private String UnitPrice;
    @SerializedName("SubTotal")
    @Expose
    private String SubTotal;
    @SerializedName("Discount")
    @Expose
    private Object Discount;
    @SerializedName("Quantity")
    @Expose
    private Integer Quantity;
    @SerializedName("AllowedQuantities")
    @Expose
    private List<Object> AllowedQuantities = new ArrayList<Object>();
    @SerializedName("AttributeInfo")
    @Expose
    private String AttributeInfo;
    @SerializedName("RecurringInfo")
    @Expose
    private Object RecurringInfo;
    @SerializedName("RentalInfo")
    @Expose
    private Object RentalInfo;
    @SerializedName("AllowItemEditing")
    @Expose
    private Boolean AllowItemEditing;
    @SerializedName("Warnings")
    @Expose
    private List<Object> Warnings = new ArrayList<Object>();
    @SerializedName("Id")
    @Expose
    private Integer Id;

    /**
     * @return The Sku
     */
    public Object getSku() {
        return Sku;
    }

    /**
     * @param Sku The Sku
     */
    public void setSku(Object Sku) {
        this.Sku = Sku;
    }

    /**
     * @return The Picture
     */
    public BeanServerImage getPicture() {
        return Picture;
    }

    /**
     * @param Picture The Picture
     */
    public void setPicture(BeanServerImage Picture) {
        this.Picture = Picture;
    }

    /**
     * @return The ProductId
     */
    public Integer getProductId() {
        return ProductId;
    }

    /**
     * @param ProductId The ProductId
     */
    public void setProductId(Integer ProductId) {
        this.ProductId = ProductId;
    }

    /**
     * @return The ProductName
     */
    public String getProductName() {
        return ProductName;
    }

    /**
     * @param ProductName The ProductName
     */
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    /**
     * @return The ProductSeName
     */
    public String getProductSeName() {
        return ProductSeName;
    }

    /**
     * @param ProductSeName The ProductSeName
     */
    public void setProductSeName(String ProductSeName) {
        this.ProductSeName = ProductSeName;
    }

    /**
     * @return The UnitPrice
     */
    public String getUnitPrice() {
        return UnitPrice;
    }

    /**
     * @param UnitPrice The UnitPrice
     */
    public void setUnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    /**
     * @return The SubTotal
     */
    public String getSubTotal() {
        return SubTotal;
    }

    /**
     * @param SubTotal The SubTotal
     */
    public void setSubTotal(String SubTotal) {
        this.SubTotal = SubTotal;
    }

    /**
     * @return The Discount
     */
    public Object getDiscount() {
        return Discount;
    }

    /**
     * @param Discount The Discount
     */
    public void setDiscount(Object Discount) {
        this.Discount = Discount;
    }

    /**
     * @return The Quantity
     */
    public Integer getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity The Quantity
     */
    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

//    /**
//     * 
//     * @return
//     *     The AllowedQuantities
//     */
//    public List<Object> getAllowedQuantities() {
//        return AllowedQuantities;
//    }
//
//    /**
//     * 
//     * @param AllowedQuantities
//     *     The AllowedQuantities
//     */
//    public void setAllowedQuantities(List<Object> AllowedQuantities) {
//        this.AllowedQuantities = AllowedQuantities;
//    }

    /**
     * @return The AttributeInfo
     */
    public String getAttributeInfo() {
        return AttributeInfo;
    }

    /**
     * @param AttributeInfo The AttributeInfo
     */
    public void setAttributeInfo(String AttributeInfo) {
        this.AttributeInfo = AttributeInfo;
    }

    /**
     * @return The RecurringInfo
     */
    public Object getRecurringInfo() {
        return RecurringInfo;
    }

    /**
     * @param RecurringInfo The RecurringInfo
     */
    public void setRecurringInfo(Object RecurringInfo) {
        this.RecurringInfo = RecurringInfo;
    }

    /**
     * @return The RentalInfo
     */
    public Object getRentalInfo() {
        return RentalInfo;
    }

    /**
     * @param RentalInfo The RentalInfo
     */
    public void setRentalInfo(Object RentalInfo) {
        this.RentalInfo = RentalInfo;
    }

    /**
     * @return The AllowItemEditing
     */
    public Boolean getAllowItemEditing() {
        return AllowItemEditing;
    }

    /**
     * @param AllowItemEditing The AllowItemEditing
     */
    public void setAllowItemEditing(Boolean AllowItemEditing) {
        this.AllowItemEditing = AllowItemEditing;
    }

//    /**
//     * 
//     * @return
//     *     The Warnings
//     */
//    public List<Object> getWarnings() {
//        return Warnings;
//    }
//
//    /**
//     * 
//     * @param Warnings
//     *     The Warnings
//     */
//    public void setWarnings(List<Object> Warnings) {
//        this.Warnings = Warnings;
//    }

    /**
     * @return The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }
}

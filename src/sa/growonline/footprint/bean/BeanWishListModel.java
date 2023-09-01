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


public class BeanWishListModel {
    private BeanServerImage Picture;

    private String RecurringInfo;

    private String Quantity;

    private String RentalInfo;

    private String AttributeInfo;

    private String UnitPrice;

    private String[] Warnings;

    private String Sku;

    private String ProductId;

    private String ProductSeName;

    private String SubTotal;

    private String[] AllowedQuantities;

    private String Id;

    private String ProductName;

    private String Discount;

    private String ProductType;

    private Object CustomProperties;

    public BeanServerImage getPicture() {
        return Picture;
    }

    public void setPicture(BeanServerImage Picture) {
        this.Picture = Picture;
    }

    public String getRecurringInfo() {
        return RecurringInfo;
    }

    public void setRecurringInfo(String RecurringInfo) {
        this.RecurringInfo = RecurringInfo;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getRentalInfo() {
        return RentalInfo;
    }

    public void setRentalInfo(String RentalInfo) {
        this.RentalInfo = RentalInfo;
    }

    public String getAttributeInfo() {
        return AttributeInfo;
    }

    public void setAttributeInfo(String AttributeInfo) {
        this.AttributeInfo = AttributeInfo;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String[] getWarnings() {
        return Warnings;
    }

    public void setWarnings(String[] Warnings) {
        this.Warnings = Warnings;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String Sku) {
        this.Sku = Sku;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getProductSeName() {
        return ProductSeName;
    }

    public void setProductSeName(String ProductSeName) {
        this.ProductSeName = ProductSeName;
    }

    public String getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(String SubTotal) {
        this.SubTotal = SubTotal;
    }

    public String[] getAllowedQuantities() {
        return AllowedQuantities;
    }

    public void setAllowedQuantities(String[] AllowedQuantities) {
        this.AllowedQuantities = AllowedQuantities;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }

//    public String getCustomProperties ()
//    {
//        return CustomProperties;
//    }

//    public void setCustomProperties (String CustomProperties)
//    {
//        this.CustomProperties = CustomProperties;
//    }

    @Override
    public String toString() {
        return "ClassPojo [Picture = " + Picture + ", RecurringInfo = " + RecurringInfo + ", Quantity = " + Quantity + ", RentalInfo = " + RentalInfo + ", AttributeInfo = " + AttributeInfo + ", UnitPrice = " + UnitPrice + ", Warnings = " + Warnings + ", Sku = " + Sku + ", ProductId = " + ProductId + ", ProductSeName = " + ProductSeName + ", SubTotal = " + SubTotal + ", AllowedQuantities = " + AllowedQuantities + ", Id = " + Id + ", ProductName = " + ProductName + ", Discount = " + Discount + ", CustomProperties = " + CustomProperties + "]";
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }
}
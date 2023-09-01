package sa.growonline.footprint.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanProductDetail {
    private List<BeanProductForCategory> AssociatedProducts;

    public String getWishListItemId() {
        return WishListItemId;
    }

    public void setWishListItemId(String wishListItemId) {
        WishListItemId = wishListItemId;
    }

    public String getStockAvailability() {
        return StockAvailability;
    }

    public void setStockAvailability(String stockAvailability) {
        StockAvailability = stockAvailability;
    }

    public String getIsSoldOut() {
        return IsSoldOut;
    }

    public void setIsSoldOut(String isSoldOut) {
        IsSoldOut = isSoldOut;
    }

    public List<BeanProductForCategory> getAssociatedProducts() {
        return AssociatedProducts;
    }

    public void setAssociatedProducts(List<BeanProductForCategory> associatedProducts) {
        AssociatedProducts = associatedProducts;
    }

    public class BeanPostSpecs {
        private String SpecificationAttributeId, SpecificationAttributeName, ValueRaw, IsWishListItem;

        public String getSpecificationAttributeName() {
            return SpecificationAttributeName;
        }

        public void setSpecificationAttributeName(
                String specificationAttributeName) {
            SpecificationAttributeName = specificationAttributeName;
        }

        public String getProductSpecValue() {
            return ValueRaw;
        }

        public void setProductSpecValue(String valueRaw) {
            ValueRaw = valueRaw;
        }
    }

    private ArrayList<BeanServerImage> PictureModels;

    private String Name, ShortDescription, FullDescription, MetaKeywords, MetaDescription, MetaTitle, SeName, Id, WishListItemId, StockAvailability, IsSoldOut;

    private boolean IsWishListItem;
    private BeanAddToCart AddToCart;
    private ArrayList<TierPrice> TierPrices;

    private ArrayList<BeanPostAttributes> ProductAttributes;
    private ArrayList<BeanPostSpecs> ProductSpecifications;
    private BeanProductPrice ProductPrice;

    private List<BeanProductForCategory> mRelatedProducts;


    public boolean getIsWishListItem() {
        return IsWishListItem;
    }

    public void setIsWishListItem(boolean isWishListItem) {
        IsWishListItem = isWishListItem;
    }

    public String getmProductId() {
        return Id;
    }

    public void setmProductId(String id) {
        Id = id;
    }

    public BeanProductPrice getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(BeanProductPrice productPrice) {
        ProductPrice = productPrice;
    }

    public ArrayList<BeanServerImage> getmProductImageModel() {
        return PictureModels;
    }

    public void setmProductImageModel(ArrayList<BeanServerImage> pictureModels) {
        PictureModels = pictureModels;
    }

    public String getmProductName() {
        return Name;
    }

    public void setmProductName(String name) {
        Name = name;
    }

    public List<BeanProductForCategory> getmRelatedProducts() {
        return mRelatedProducts;
    }

    public void setmRelatedProducts(List<BeanProductForCategory> mRelatedProducts) {
        this.mRelatedProducts = mRelatedProducts;
    }

    public ArrayList<BeanPostAttributes> getProductAttributes() {
        return ProductAttributes;
    }

    public void setProductAttributes(ArrayList<BeanPostAttributes> productAttributes) {
        ProductAttributes = productAttributes;
    }

    public ArrayList<BeanPostSpecs> getProductSpecifications() {
        return ProductSpecifications;
    }

    public void setProductSpecifications(ArrayList<BeanPostSpecs> productSpecifications) {
        ProductSpecifications = productSpecifications;
    }

    public String getSharingName() {
        return SeName;
    }

    public void setSharingName(String seName) {
        SeName = seName;
    }

    public BeanAddToCart getAddToCart() {
        return AddToCart;
    }

    public void setAddToCart(BeanAddToCart addToCart) {
        AddToCart = addToCart;
    }

    public ArrayList<TierPrice> getTierPrices() {
        return TierPrices;
    }

    public void setTierPrices(ArrayList<TierPrice> tierPrices) {
        TierPrices = tierPrices;
    }

    public String getFullDescription() {
        return FullDescription;
    }

    public void setFullDescription(String fullDescription) {
        FullDescription = fullDescription;
    }
}
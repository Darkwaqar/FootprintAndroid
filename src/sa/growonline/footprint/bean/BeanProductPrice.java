package sa.growonline.footprint.bean;

public class BeanProductPrice {
    private String OldPrice, Price;

    private String AvailableForPreOrder, DisableAddToCompareListButton, DisableBuyButton, DisableWishlistButton, DisplayTaxShippingInfo, ForceRedirectionAfterAddingToCart, IsRental, PriceValue, CurrencyCode;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getmProductOldPrice() {
        return OldPrice;
    }

    public void setmProductOldPrice(String oldPrice) {
        OldPrice = oldPrice;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getPriceValue() {
        return PriceValue;
    }

    public void setPriceValue(String priceValue) {
        PriceValue = priceValue;
    }
}

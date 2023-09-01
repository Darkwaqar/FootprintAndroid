package sa.growonline.footprint.bean;


public class BeanProductForCategory {
    private String Name, ShortDescription, FullDescription, Id, SeName, StockAvailability, IsSoldOut;
    private BeanProductPrice ProductPrice;
    private BeanServerImage DefaultPictureModel;

    public BeanServerImage getImageModel() {
        return DefaultPictureModel;
    }

    public void setImageModel(BeanServerImage defaultPictureModel) {
        DefaultPictureModel = defaultPictureModel;
    }

    public String getProductName() {
        return Name;
    }

    public void setProductName(String name) {
        Name = name;
    }

    public String getStockAvailability() {
        return StockAvailability;
    }

    public void setStockAvailability(String s) {
        StockAvailability = s;
    }

    public BeanProductPrice getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(BeanProductPrice productPrice) {
        ProductPrice = productPrice;
    }

    public String getmProductId() {
        return Id;
    }

    public void setmProductId(String id) {
        Id = id;
    }

    public String getIsSoldOut() {
        return IsSoldOut;
    }

    public void setIsSoldOut(String isSoldOut) {
        IsSoldOut = isSoldOut;
    }
}
package sa.growonline.footprint.bean;

/**
 * Created by Jawed on 9/1/2016.
 */
public class BeanOrder {

    private String OrderStatus;

    private String OrderTotal;

    private String ShippingStatus;

    private String CreatedOn;

    private String OrderStatusEnum;

    private String Id;

    private String CustomProperties;

    private String IsReturnRequestAllowed;

    private String PaymentStatus;

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(String OrderTotal) {
        this.OrderTotal = OrderTotal;
    }

    public String getShippingStatus() {
        return ShippingStatus;
    }

    public void setShippingStatus(String ShippingStatus) {
        this.ShippingStatus = ShippingStatus;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public String getOrderStatusEnum() {
        return OrderStatusEnum;
    }

    public void setOrderStatusEnum(String OrderStatusEnum) {
        this.OrderStatusEnum = OrderStatusEnum;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCustomProperties() {
        return CustomProperties;
    }

    public void setCustomProperties(String CustomProperties) {
        this.CustomProperties = CustomProperties;
    }

    public String getIsReturnRequestAllowed() {
        return IsReturnRequestAllowed;
    }

    public void setIsReturnRequestAllowed(String IsReturnRequestAllowed) {
        this.IsReturnRequestAllowed = IsReturnRequestAllowed;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    @Override
    public String toString() {
        return "ClassPojo [OrderStatus = " + OrderStatus + ", OrderTotal = " + OrderTotal + ", ShippingStatus = " + ShippingStatus + ", CreatedOn = " + CreatedOn + ", OrderStatusEnum = " + OrderStatusEnum + ", Id = " + Id + ", CustomProperties = " + CustomProperties + ", IsReturnRequestAllowed = " + IsReturnRequestAllowed + ", PaymentStatus = " + PaymentStatus + "]";
    }
}

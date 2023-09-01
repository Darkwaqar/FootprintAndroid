
package sa.growonline.footprint.bean.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethod {

    @SerializedName("PaymentMethodSystemName")
    @Expose
    private String PaymentMethodSystemName;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Fee")
    @Expose
    private Object Fee;
    @SerializedName("Selected")
    @Expose
    private Boolean Selected;
    @SerializedName("LogoUrl")
    @Expose
    private String LogoUrl;

    /**
     * 
     * @return
     *     The PaymentMethodSystemName
     */
    public String getPaymentMethodSystemName() {
        return PaymentMethodSystemName;
    }

    /**
     * 
     * @param PaymentMethodSystemName
     *     The PaymentMethodSystemName
     */
    public void setPaymentMethodSystemName(String PaymentMethodSystemName) {
        this.PaymentMethodSystemName = PaymentMethodSystemName;
    }

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The Fee
     */
    public Object getFee() {
        return Fee;
    }

    /**
     * 
     * @param Fee
     *     The Fee
     */
    public void setFee(Object Fee) {
        this.Fee = Fee;
    }

    /**
     * 
     * @return
     *     The Selected
     */
    public Boolean getSelected() {
        return Selected;
    }

    /**
     * 
     * @param Selected
     *     The Selected
     */
    public void setSelected(Boolean Selected) {
        this.Selected = Selected;
    }

    /**
     * 
     * @return
     *     The LogoUrl
     */
    public String getLogoUrl() {
        return LogoUrl;
    }

    /**
     * 
     * @param LogoUrl
     *     The LogoUrl
     */
    public void setLogoUrl(String LogoUrl) {
        this.LogoUrl = LogoUrl;
    }
}
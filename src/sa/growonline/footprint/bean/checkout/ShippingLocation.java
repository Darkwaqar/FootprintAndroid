
package sa.growonline.footprint.bean.checkout;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingLocation
{
    @SerializedName("ExistingAddresses")
    @Expose
    private List<ExistingAddress> ExistingAddresses = new ArrayList<ExistingAddress>();
    @SerializedName("NewAddress")
    @Expose
    private sa.growonline.footprint.bean.checkout.NewAddress NewAddress;
    @SerializedName("NewAddressPreselected")
    @Expose
    private Boolean NewAddressPreselected;
    @SerializedName("AllowPickUpInStore")
    @Expose
    private Boolean AllowPickUpInStore;
    @SerializedName("PickUpInStoreFee")
    @Expose
    private Object PickUpInStoreFee;
    @SerializedName("PickUpInStore")
    @Expose
    private Boolean PickUpInStore;

    /**
     * 
     * @return
     *     The ExistingAddresses
     */
    public List<ExistingAddress> getExistingAddresses() {
        return ExistingAddresses;
    }

    /**
     * 
     * @param ExistingAddresses
     *     The ExistingAddresses
     */
    public void setExistingAddresses(List<ExistingAddress> ExistingAddresses) {
        this.ExistingAddresses = ExistingAddresses;
    }

    /**
     * 
     * @return
     *     The NewAddress
     */
    public sa.growonline.footprint.bean.checkout.NewAddress getNewAddress() {
        return NewAddress;
    }

    /**
     * 
     * @param NewAddress
     *     The NewAddress
     */
    public void setNewAddress(sa.growonline.footprint.bean.checkout.NewAddress NewAddress) {
        this.NewAddress = NewAddress;
    }

    /**
     * 
     * @return
     *     The NewAddressPreselected
     */
    public Boolean getNewAddressPreselected() {
        return NewAddressPreselected;
    }

    /**
     * 
     * @param NewAddressPreselected
     *     The NewAddressPreselected
     */
    public void setNewAddressPreselected(Boolean NewAddressPreselected) {
        this.NewAddressPreselected = NewAddressPreselected;
    }

    /**
     * 
     * @return
     *     The AllowPickUpInStore
     */
    public Boolean getAllowPickUpInStore() {
        return AllowPickUpInStore;
    }

    /**
     * 
     * @param AllowPickUpInStore
     *     The AllowPickUpInStore
     */
    public void setAllowPickUpInStore(Boolean AllowPickUpInStore) {
        this.AllowPickUpInStore = AllowPickUpInStore;
    }

    /**
     * 
     * @return
     *     The PickUpInStoreFee
     */
    public Object getPickUpInStoreFee() {
        return PickUpInStoreFee;
    }

    /**
     * 
     * @param PickUpInStoreFee
     *     The PickUpInStoreFee
     */
    public void setPickUpInStoreFee(Object PickUpInStoreFee) {
        this.PickUpInStoreFee = PickUpInStoreFee;
    }

    /**
     * 
     * @return
     *     The PickUpInStore
     */
    public Boolean getPickUpInStore() {
        return PickUpInStore;
    }

    /**
     * 
     * @param PickUpInStore
     *     The PickUpInStore
     */
    public void setPickUpInStore(Boolean PickUpInStore) {
        this.PickUpInStore = PickUpInStore;
    }
}
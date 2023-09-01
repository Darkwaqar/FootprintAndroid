package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpecFilter {
    @SerializedName("Enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("AlreadyFilteredItems")
    @Expose
    private List<Object> alreadyFilteredItems = new ArrayList<Object>();
    @SerializedName("NotFilteredItems")
    @Expose
    private List<NotFilteredItem> notFilteredItems = new ArrayList<NotFilteredItem>();
    private ArrayList<BeanPostAttributes> mClientFilterModel;

    /**
     * @return The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The Enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return The alreadyFilteredItems
     */
    public List<Object> getAlreadyFilteredItems() {
        return alreadyFilteredItems;
    }

    /**
     * @param alreadyFilteredItems The AlreadyFilteredItems
     */
    public void setAlreadyFilteredItems(List<Object> alreadyFilteredItems) {
        this.alreadyFilteredItems = alreadyFilteredItems;
    }

    /**
     * @return The notFilteredItems
     */
    public List<NotFilteredItem> getNotFilteredItems() {
        return notFilteredItems;
    }

    /**
     * @param notFilteredItems The NotFilteredItems
     */
    public void setNotFilteredItems(List<NotFilteredItem> notFilteredItems) {
        this.notFilteredItems = notFilteredItems;
    }

    public ArrayList<BeanPostAttributes> getmClientFilterModel() {
        return mClientFilterModel;
    }

    public void setmClientFilterModel(ArrayList<BeanPostAttributes> mClientFilterModel) {
        this.mClientFilterModel = mClientFilterModel;
    }

}

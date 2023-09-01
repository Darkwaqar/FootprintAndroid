package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotFilteredItem {

    @SerializedName("SpecificationAttributeName")
    @Expose
    private String specificationAttributeName;
    @SerializedName("SpecificationAttributeOptionName")
    @Expose
    private String specificationAttributeOptionName;
    @SerializedName("FilterUrl")
    @Expose
    private String filterUrl;
    @SerializedName("SpecificationAttributeOptionId")
    @Expose
    private String specificationAttributeOptionId;
    @SerializedName("selected")
    @Expose
    private Boolean selected;

    /**
     * @return The specificationAttributeName
     */
    public String getSpecificationAttributeName() {
        return specificationAttributeName;
    }

    /**
     * @param specificationAttributeName The SpecificationAttributeName
     */
    public void setSpecificationAttributeName(String specificationAttributeName) {
        this.specificationAttributeName = specificationAttributeName;
    }

    /**
     * @return The specificationAttributeOptionName
     */
    public String getSpecificationAttributeOptionName() {
        return specificationAttributeOptionName;
    }

    /**
     * @param specificationAttributeOptionName The SpecificationAttributeOptionName
     */
    public void setSpecificationAttributeOptionName(String specificationAttributeOptionName) {
        this.specificationAttributeOptionName = specificationAttributeOptionName;
    }

    /**
     * @return The filterUrl
     */
    public String getFilterUrl() {
        return filterUrl;
    }

    /**
     * @param filterUrl The FilterUrl
     */
    public void setFilterUrl(String filterUrl) {
        this.filterUrl = filterUrl;
    }

    /**
     * @return The specificationAttributeOptionId
     */
    public String getSpecificationAttributeOptionId() {
        return specificationAttributeOptionId;
    }

    /**
     * @param specificationAttributeOptionId The SpecificationAttributeOptionId
     */
    public void setSpecificationAttributeOptionId(String specificationAttributeOptionId) {
        this.specificationAttributeOptionId = specificationAttributeOptionId;
    }

    /**
     * @return The selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * @param selected The selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
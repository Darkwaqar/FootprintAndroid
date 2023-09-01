
package sa.growonline.footprint.bean.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailableCountry {

    @SerializedName("Disabled")
    @Expose
    private Boolean Disabled;
    @SerializedName("Group")
    @Expose
    private Object Group;
    @SerializedName("Selected")
    @Expose
    private Boolean Selected;
    @SerializedName("Text")
    @Expose
    private String Text;
    @SerializedName("Value")
    @Expose
    private String Value;

    /**
     * 
     * @return
     *     The Disabled
     */
    public Boolean getDisabled() {
        return Disabled;
    }

    /**
     * 
     * @param Disabled
     *     The Disabled
     */
    public void setDisabled(Boolean Disabled) {
        this.Disabled = Disabled;
    }

    /**
     * 
     * @return
     *     The Group
     */
    public Object getGroup() {
        return Group;
    }

    /**
     * 
     * @param Group
     *     The Group
     */
    public void setGroup(Object Group) {
        this.Group = Group;
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
     *     The Text
     */
    public String getText() {
        return Text;
    }

    /**
     * 
     * @param Text
     *     The Text
     */
    public void setText(String Text) {
        this.Text = Text;
    }

    /**
     * 
     * @return
     *     The Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * 
     * @param Value
     *     The Value
     */
    public void setValue(String Value) {
        this.Value = Value;
    }
}
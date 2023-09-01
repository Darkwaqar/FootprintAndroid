
package sa.growonline.footprint.bean.checkout;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethods {

    @SerializedName("PaymentMethods")
    @Expose
    private List<PaymentMethod> PaymentMethods = new ArrayList<PaymentMethod>();
    @SerializedName("DisplayRewardPoints")
    @Expose
    private Boolean DisplayRewardPoints;
    @SerializedName("RewardPointsBalance")
    @Expose
    private Integer RewardPointsBalance;
    @SerializedName("RewardPointsAmount")
    private String RewardPointsAmount;
    @SerializedName("UseRewardPoints")
    @Expose
    private Boolean UseRewardPoints;

    /**
     * 
     * @return
     *     The PaymentMethods
     */
    public List<PaymentMethod> getPaymentMethods() {
        return PaymentMethods;
    }

    /**
     * 
     * @param PaymentMethods
     *     The PaymentMethods
     */
    public void setPaymentMethods(List<PaymentMethod> PaymentMethods) {
        this.PaymentMethods = PaymentMethods;
    }

    /**
     * 
     * @return
     *     The DisplayRewardPoints
     */
    public Boolean getDisplayRewardPoints() {
        return DisplayRewardPoints;
    }

    /**
     * 
     * @param DisplayRewardPoints
     *     The DisplayRewardPoints
     */
    public void setDisplayRewardPoints(Boolean DisplayRewardPoints) {
        this.DisplayRewardPoints = DisplayRewardPoints;
    }

    /**
     * 
     * @return
     *     The RewardPointsBalance
     */
    public Integer getRewardPointsBalance() {
        return RewardPointsBalance;
    }

    /**
     * 
     * @param RewardPointsBalance
     *     The RewardPointsBalance
     */
    public void setRewardPointsBalance(Integer RewardPointsBalance) {
        this.RewardPointsBalance = RewardPointsBalance;
    }

    /**
     * 
     * @return
     *     The RewardPointsAmount
     */
    public String getRewardPointsAmount() {
        return RewardPointsAmount;
    }

    /**
     * 
     * @param RewardPointsAmount
     *     The RewardPointsAmount
     */
    public void setRewardPointsAmount(String RewardPointsAmount) {
        this.RewardPointsAmount = RewardPointsAmount;
    }

    /**
     * 
     * @return
     *     The UseRewardPoints
     */
    public Boolean getUseRewardPoints() {
        return UseRewardPoints;
    }

    /**
     * 
     * @param UseRewardPoints
     *     The UseRewardPoints
     */
    public void setUseRewardPoints(Boolean UseRewardPoints) {
        this.UseRewardPoints = UseRewardPoints;
    }
}
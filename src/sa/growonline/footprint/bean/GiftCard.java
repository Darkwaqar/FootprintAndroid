package sa.growonline.footprint.bean;

/**
 * Created by Basit on 9/4/2016.
 */
public class GiftCard {

    private int id;
    private String name, formattedAmount;
    private Float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedAmount() {
        return formattedAmount;
    }

    public void setFormattedAmount(String formattedAmount) {
        this.formattedAmount = formattedAmount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", name = " + name + ", formattedAmount = " + formattedAmount + ", amount = " + amount + "]";
    }
}

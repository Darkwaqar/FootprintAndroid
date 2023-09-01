package sa.growonline.footprint.bean;

import java.util.ArrayList;

public class BeanPostAttributes {
    private String Id, ProductAttributeId, IsRequired, AttributeControlType, Name;
    private ArrayList<BeanProductAttributeValues> Values;

    public static class BeanProductAttributeValues {
        private String Id, ColorSquaresRgb, IsPreSelected, Name, PriceAdjustment, PriceAdjustmentValue;
        private BeanServerImage PictureModel;

        public String getColorSquaresRgb() {
            return ColorSquaresRgb;
        }

        public void setColorSquaresRgb(String colorSquaresRgb) {
            ColorSquaresRgb = colorSquaresRgb;
        }

        public BeanServerImage getPictureModel() {
            return PictureModel;
        }

        public void setPictureModel(BeanServerImage pictureModel) {
            PictureModel = pictureModel;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getPriceAdjustment() {
            return PriceAdjustment;
        }

        public void setPriceAdjustment(String priceAdjustment) {
            PriceAdjustment = priceAdjustment;
        }

        public String getPriceAdjustmentValue() {
            return PriceAdjustmentValue;
        }

        public void setPriceAdjustmentValue(String priceAdjustmentValue) {
            PriceAdjustmentValue = priceAdjustmentValue;
        }

        public String getIsPreSelected() {
            return IsPreSelected;
        }

        public void setIsPreSelected(String isPreSelected) {
            IsPreSelected = isPreSelected;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }
    }

    public String getAttributeControlType() {
        return AttributeControlType;
    }

    public void setAttributeControlType(String attributeControlType) {
        AttributeControlType = attributeControlType;
    }

    public ArrayList<BeanProductAttributeValues> getAttributeValues() {
        return Values;
    }

    public void setAttributeValues(ArrayList<BeanProductAttributeValues> values) {
        Values = values;
    }

    public String getProductAttributeId() {
        return ProductAttributeId;
    }

    public void setProductAttributeId(String productAttributeId) {
        ProductAttributeId = productAttributeId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getmName() {
        return Name;
    }

    public void setmName(String mName) {
        this.Name = mName;
    }
}
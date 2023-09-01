
package sa.growonline.footprint.bean;

import java.util.ArrayList;

public class BeanGetAllCategory {
    private String Name, Description, MetaKeywords, MetaDescription, MetaTitle, SeName, HasChild = "false", DisplayCategoryBreadcrumb, Id;
    /* Below Parameter is only available in GetTopBarCategory service */
    private String CategoryPictureUrl;
    private ArrayList<BeanGetAllCategory> SubCategories;
    private ArrayList<BeanProductForCategory> Products;
    private SpecFilter SpecsFilter;

    private BeanServerImage PictureModel;

    private Object CustomProperties;
    private Object PriceRanges;
    private Object PageSizeOptions;
    private int mResourceId;

    public BeanGetAllCategory() {
//		HasChild = "true";
    }

    public BeanGetAllCategory(String id, String name, int navCategoryHome) {
        this.Id = id;
        this.Name = name;
        this.setmResourceId(navCategoryHome);
        HasChild = "false";
    }

    public BeanServerImage getImageModel() {
        return PictureModel;
    }

    public void setImageModel(BeanServerImage pictureModel) {
        this.PictureModel = pictureModel;
    }

    public String getmCategoryName() {
        return Name;
    }

    public void setmCategoryName(String name) {
        Name = name;
    }

    public String getmCategoryId() {
        return Id;
    }

    public void setmCategoryId(String id) {
        Id = id;
    }

    public String getmHasSubCategory() {
        return HasChild;
    }

    public void setmHasSubCategory(String hasChild) {
        HasChild = hasChild;
    }

    public ArrayList<BeanGetAllCategory> getSubCategories() {
        return SubCategories;
    }

    public void setSubCategories(ArrayList<BeanGetAllCategory> subCategories) {
        SubCategories = subCategories;
    }

    public String getCategoryPictureUrl() {
        return CategoryPictureUrl;
    }

    public void setCategoryPictureUrl(String categoryPictureUrl) {
        CategoryPictureUrl = categoryPictureUrl;
    }

    public ArrayList<BeanProductForCategory> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<BeanProductForCategory> products) {
        Products = products;
    }

    public int getmResourceId() {
        return mResourceId;
    }

    public void setmResourceId(int mResourceId) {
        this.mResourceId = mResourceId;
    }
}
package sa.growonline.footprint.bean;

import java.util.ArrayList;
import java.util.List;


public class CategoryDetailBean {
    private String Name, Description, MetaKeywords, MetaDescription, MetaTitle, SeName, HasChild, DisplayCategoryBreadcrumb, Id;

    private BeanServerImage PictureModel;

    private Object CustomProperties;
    private Object PriceRanges;
    private Object PageSizeOptions;

    private SpecFilter SpecsFilter;
    private ArrayList<BeanProductForCategory> Products;
    private ArrayList<BeanGetAllCategory> SubCategories;
    private List<BeanSubCategoryProductForAdapter> mBeanListForAdapter;
    private int PageNumber;
    private String MinPrice;
    private String MaxPrice;


    public String getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(String minPrice) {
        MinPrice = minPrice;
    }

    public String getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        MaxPrice = maxPrice;
    }

    public ArrayList<BeanProductForCategory> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<BeanProductForCategory> products) {
        Products = products;
    }

    public ArrayList<BeanGetAllCategory> getSubCategories() {
        return SubCategories;
    }

    public void setSubCategories(ArrayList<BeanGetAllCategory> subCategories) {
        SubCategories = subCategories;
    }

    public List<BeanSubCategoryProductForAdapter> getmBeanListForAdapter() {
        return mBeanListForAdapter;
    }

    public void setmBeanListForAdapter(List<BeanSubCategoryProductForAdapter> mBeanListForAdapter) {
        this.mBeanListForAdapter = mBeanListForAdapter;
    }

    public String getCategoryName() {
        return Name;
    }

    public void setCategoryName(String name) {
        Name = name;
    }

    public SpecFilter getSpecsFilter() {
        return SpecsFilter;
    }

    public void setSpecsFilter(SpecFilter specsFilter) {
        SpecsFilter = specsFilter;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }
}
package sa.growonline.footprint.utils;

public class ZuniConstants {

    //    public static final String BASE_DOMAIN	 						= "http://www.footprintbespoke.com/";
//    public static final String BASE_DOMAIN	 						= "http://staging.growonlinepk.com/";
//    public static final String BASE_DOMAIN	 						= "http://footprint.pk/";
//    public static final String BASE_DOMAIN = "http://footprint-pk.growonlinepk.com/";
    public static final String BASE_DOMAIN = "http://qsohail-001-site4.atempurl.com/";
    public static final String BRAND_DOMAIN = BASE_DOMAIN;//"http://www.footprintbespoke.com/";

    public static final String POST_LIST_JSON = "POST_LIST_JSON";
    public static final String CATEGORY_ID_PREF_KEY = "CATEGORY_ID_PREF_KEY";
    public static final String DETAILED_PRODUCT_ID = "DETAILED_PRODUCT_ID";
    public static final String SIGNED_IN_FROM = "SIGNED_IN_FROM";
    public static final String MANUAL_SIGN_IN = "MANUAL_SIGN_IN";
    public static final String GUEST_USER = "GUEST_USER";
    public static final String FACEBOOK = "FACEBOOK";
    public static final String LOGIN_MANUAL = "LOGIN_MANUAL";
    public static final String SHOP_THE_LOOK_ID = "shop the";
    public static final String BRAND_ID = "99";
    public static final String BESPOKE_ID = "118";
    public static final String GIFT_ID = "109";

    public static final String COLOR_LIST_KEY = "COLOR_LIST_KEY";
    public static final String PRODUCT_TYPE_SIMPLE = "simple";
    public static boolean isLogEnabled = true;

    public static final String ATTRIBUTE_SIZE_TYPE = "size";
    public static final String ATTRIBUTE_COLOR_TYPE = "color";

    public static final String FONTS_DIR = "fonts/";

    public static final String BASE_URL = BASE_DOMAIN + "Uservices/";
    public static final String SHARE_URL = BASE_DOMAIN;

    private static final String CONTROLLER_CATALOG = "catalog/";
    private static final String CONTROLLER_PRODUCT = "Product/";
    private static final String CONTROLLER_CUSTOMER = "Customer/";
    private static final String CONTROLLER_SHOPPING = "ShoppingCart/";
    private static final String CONTROLLER_ORDER = "CustomerOrder/";
    private static final String CONTROLLER_SHOPPING_X = "ShoppingCart/";

    public static final String GET_BRAND = CONTROLLER_CATALOG + "/getbrandbespoke";
    private static final String CONTROLLER_CHECKOUT = "SerCheckout/";

    public static final String FAQS = "faqs";

    public static final String GET_CATEGORY_PRODUCT_REFINE = CONTROLLER_CATALOG + "Categorywithspecs?categoryId=";
    public static final String GET_ALL_NAV_CATEGORY_URL = CONTROLLER_CATALOG + "TopMenu";
    public static final String GET_ALL_CATEGORY_URL = CONTROLLER_CATALOG + "GetAllCategories";
    public static final String GET_CATEGORY_DETAILS = CONTROLLER_CATALOG + "Category?categoryId=";
    public static final String GET_ALL_FILTER_PRODUCTS = CONTROLLER_CATALOG + "Search";
    public static final String GET_ALL_MANUFACTURERS = CONTROLLER_CATALOG + "ManufacturerAll";

    public static final String GET_CART_DETAILS = CONTROLLER_SHOPPING + "ShopCart";
    public static final String ADD_TO_CART = CONTROLLER_SHOPPING + "AddProductToCart_Details";
    public static final String DELETE_FROM_CART = CONTROLLER_SHOPPING + "RemoveCartProduct";
    public static final String UPDATE_CART = CONTROLLER_SHOPPING + "UpdateCartProduct";
    public static final String CALCULATE_CART_TOTAL = CONTROLLER_SHOPPING + "OrderTotals";
    public static final String GET_WISHLIST_DETAILS = CONTROLLER_SHOPPING + "Wishlist";
    public static final String WISHLIST_TO_CART = CONTROLLER_SHOPPING_X + "AddItemToCartFromWishlist";
    public static final String DELETE_FROM_WISHLIST = CONTROLLER_SHOPPING_X + "RmoveWishlistItem";

    public static final String GET_PRODUCT_WITH_RELATED_PRODUCTS = CONTROLLER_PRODUCT + "ProductDetailsWithRelatedProducts";

    public static final String LOGIN_URL = CONTROLLER_CUSTOMER + "LoginUser";
    public static final String LOGOUT = CONTROLLER_CUSTOMER + "Logout";
    public static final String REGISTER_USER = CONTROLLER_CUSTOMER + "RegisterUser";
    public static final String USER_INFO = CONTROLLER_CUSTOMER + "info";
    public static final String EDIT_SHIPPING_LOCATION = CONTROLLER_CUSTOMER + "addressedit";
    public static final String RECOVER_PASSWORD = CONTROLLER_CUSTOMER + "RecoverPassword?email=";
    public static final String LOGIN_AS_FACEBOOK = CONTROLLER_CUSTOMER + "LoginFacebookUser";
    public static final String CONTACTUS = CONTROLLER_CUSTOMER + "contactus";
    public static final String EDIT_INFO = CONTROLLER_CUSTOMER + "info";
    public static final String Change_Password = CONTROLLER_CUSTOMER + "ChangePassword";
    public static final String GET_REWARD_POINTS = CONTROLLER_CUSTOMER + "GetRewardPoints";
    public static final String GET_GIFT_CARDS = CONTROLLER_PRODUCT + "GetActiveGiftCard";

    public static final String GET_CHECKOUT_DETAILS = CONTROLLER_CHECKOUT + "GetAllDetailsForCheckOut";
    public static final String CONFIRM_ORDER = CONTROLLER_CHECKOUT + "ConfirmOrder";
    public static final String SAVE_SHIPPING_LOCATION = CONTROLLER_CHECKOUT + "OpcSaveShippingAndBilling";
    public static final String GET_SHIPPING_LOCATIONS = CONTROLLER_CHECKOUT + "ShippingAddress";
    public static final String APPLY_GIFT_VOUCHER = CONTROLLER_SHOPPING + "ApplyGiftCardAndDiscountCoupon";

    public static final String GET_ORDER_lIST = CONTROLLER_ORDER + "CustomerOrders";
    public static final String GET_ORDER_DETAILS = CONTROLLER_ORDER + "details?orderid=";

    public static final String SELECTED_CATEGORY_NAME = "SELECTED_CATEGORY_NAME";

    public static final String USER_EMAIL_PREFS_KEY = "USER_EMAIL_KEY";
    public static final String USER_NAME_PREFS_KEY = "USER_NAME_KEY";
    public static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    public static final String COOKIE_HANDLER = "COOKIE_HANDLER";
    public static final String CATEGORY_JSON = "CATEGORY_JSON";
    public static final String PRODUCTS_JSON_ARRAY_KEY = "PRODUCTS_JSON_ARRAY_KEY";
    public static final String CART_QUANTITY = "CART_QUANTITY";

    public static final String SHARING_TEXT = "Hi..! Check out, what I found on Zuni App!!! \n\n";

    public static final String HELPLINE_NUMBER = "+923312888190";

    public static final String SELECTED_NEW_LOCATION_JSON = "SELECTED_NEW_LOCATION_JSON";
    public static final String SELECTED_PAYMENT_JSON = "SELECTED_PAYMENT_JSON";
    public static final String SELECTED_LOCATION_METHOD_JSON = "SELECTED_LOCATION_METHOD_JSON";
    public static final String SELECTED_LOCATION_LISTING_JSON = "SELECTED_LOCATION_LISTING_JSON";
    public static final String MANUFACTURER_JSON = "MANUFACTURER_JSON";

    public static final String SIGNING_IN = "SIGNING_IN";
    public static final String SELECTED_LOCATION_FOR_EDIT = "SELECTED_LOCATION_FOR_EDIT";

    public static final String COMPLETE_CATEGORY_JSON = "COMPLETE_CATEGORY_JSON";
    public static final String PDF_URL = "https://drive.google.com/viewerng/viewer?embedded=true&url=";

    //Refine Attributes
    public static final String REFINE_ATTRIBUTE_STYLE = "style";
    public static final String REFINE_ATTRIBUTE_SIZE = "size";
    public static final String REFINE_ATTRIBUTE_COLOR = "color";
    public static final String REFINE_ATTRIBUTE_MATERIAL = "material";
    public static final String REFINE_ATTRIBUTE_SOLE = "sole";





}
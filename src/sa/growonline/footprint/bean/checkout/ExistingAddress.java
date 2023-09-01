
package sa.growonline.footprint.bean.checkout;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExistingAddress {

    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("CompanyEnabled")
    @Expose
    private Boolean CompanyEnabled;
    @SerializedName("CompanyRequired")
    @Expose
    private Boolean CompanyRequired;
    @SerializedName("Company")
    @Expose
    private String Company;
    @SerializedName("CountryEnabled")
    @Expose
    private Boolean CountryEnabled;
    @SerializedName("CountryId")
    @Expose
    private String CountryId;
    @SerializedName("CountryName")
    @Expose
    private String CountryName;
    @SerializedName("StateProvinceEnabled")
    @Expose
    private Boolean StateProvinceEnabled;
    @SerializedName("StateProvinceId")
    @Expose
    private String StateProvinceId;
    @SerializedName("StateProvinceName")
    @Expose
    private String StateProvinceName;
    @SerializedName("CityEnabled")
    @Expose
    private Boolean CityEnabled;
    @SerializedName("CityRequired")
    @Expose
    private Boolean CityRequired;
    @SerializedName("City")
    @Expose
    private String City;
    @SerializedName("StreetAddressEnabled")
    @Expose
    private Boolean StreetAddressEnabled;
    @SerializedName("StreetAddressRequired")
    @Expose
    private Boolean StreetAddressRequired;
    @SerializedName("Address1")
    @Expose
    private String Address1;
    @SerializedName("StreetAddress2Enabled")
    @Expose
    private Boolean StreetAddress2Enabled;
    @SerializedName("StreetAddress2Required")
    @Expose
    private Boolean StreetAddress2Required;
    @SerializedName("Address2")
    @Expose
    private String Address2;
    @SerializedName("ZipPostalCodeEnabled")
    @Expose
    private Boolean ZipPostalCodeEnabled;
    @SerializedName("ZipPostalCodeRequired")
    @Expose
    private Boolean ZipPostalCodeRequired;
    @SerializedName("ZipPostalCode")
    @Expose
    private String ZipPostalCode;
    @SerializedName("PhoneEnabled")
    @Expose
    private Boolean PhoneEnabled;
    @SerializedName("PhoneRequired")
    @Expose
    private Boolean PhoneRequired;
    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber;
    @SerializedName("FaxEnabled")
    @Expose
    private Boolean FaxEnabled;
    @SerializedName("FaxRequired")
    @Expose
    private Boolean FaxRequired;
    @SerializedName("FaxNumber")
    @Expose
    private Object FaxNumber;
    @SerializedName("AvailableCountries")
    @Expose
    private List<AvailableCountry> AvailableCountries = new ArrayList<AvailableCountry>();
    @SerializedName("AvailableStates")
    @Expose
    private List<AvailableState> AvailableStates = new ArrayList<AvailableState>();
    @SerializedName("FormattedCustomAddressAttributes")
    @Expose
    private String FormattedCustomAddressAttributes;
    @SerializedName("CustomAddressAttributes")
    @Expose
    private List<Object> CustomAddressAttributes = new ArrayList<Object>();
    @SerializedName("Id")
    @Expose
    private Integer Id;

    @SerializedName("AllStates")
    @Expose
    private List<AvailableState> AllStates = new ArrayList<AvailableState>();

    /**
     * 
     * @return
     *     The FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * 
     * @param FirstName
     *     The FirstName
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * 
     * @return
     *     The LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * 
     * @param LastName
     *     The LastName
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * 
     * @return
     *     The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * 
     * @param Email
     *     The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * 
     * @return
     *     The CompanyEnabled
     */
    public Boolean getCompanyEnabled() {
        return CompanyEnabled;
    }

    /**
     * 
     * @param CompanyEnabled
     *     The CompanyEnabled
     */
    public void setCompanyEnabled(Boolean CompanyEnabled) {
        this.CompanyEnabled = CompanyEnabled;
    }

    /**
     * 
     * @return
     *     The CompanyRequired
     */
    public Boolean getCompanyRequired() {
        return CompanyRequired;
    }

    /**
     * 
     * @param CompanyRequired
     *     The CompanyRequired
     */
    public void setCompanyRequired(Boolean CompanyRequired) {
        this.CompanyRequired = CompanyRequired;
    }

    /**
     * 
     * @return
     *     The Company
     */
    public String getCompany() {
        return Company;
    }

    /**
     * 
     * @param Company
     *     The Company
     */
    public void setCompany(String Company) {
        this.Company = Company;
    }

    /**
     * 
     * @return
     *     The CountryEnabled
     */
    public Boolean getCountryEnabled() {
        return CountryEnabled;
    }

    /**
     * 
     * @param CountryEnabled
     *     The CountryEnabled
     */
    public void setCountryEnabled(Boolean CountryEnabled) {
        this.CountryEnabled = CountryEnabled;
    }

    /**
     * 
     * @return
     *     The CountryId
     */
    public String getCountryId() {
        return CountryId;
    }

    /**
     * 
     * @param CountryId
     *     The CountryId
     */
    public void setCountryId(String CountryId) {
        this.CountryId = CountryId;
    }

    /**
     * 
     * @return
     *     The CountryName
     */
    public String getCountryName() {
        return CountryName;
    }

    /**
     * 
     * @param CountryName
     *     The CountryName
     */
    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    /**
     * 
     * @return
     *     The StateProvinceEnabled
     */
    public Boolean getStateProvinceEnabled() {
        return StateProvinceEnabled;
    }

    /**
     * 
     * @param StateProvinceEnabled
     *     The StateProvinceEnabled
     */
    public void setStateProvinceEnabled(Boolean StateProvinceEnabled) {
        this.StateProvinceEnabled = StateProvinceEnabled;
    }

    /**
     * 
     * @return
     *     The StateProvinceId
     */
    public String getStateProvinceId() {
        return StateProvinceId;
    }

    /**
     * 
     * @param StateProvinceId
     *     The StateProvinceId
     */
    public void setStateProvinceId(String StateProvinceId) {
        this.StateProvinceId = StateProvinceId;
    }

    /**
     * 
     * @return
     *     The StateProvinceName
     */
    public String getStateProvinceName() {
        return StateProvinceName;
    }

    /**
     * 
     * @param StateProvinceName
     *     The StateProvinceName
     */
    public void setStateProvinceName(String StateProvinceName) {
        this.StateProvinceName = StateProvinceName;
    }

    /**
     * 
     * @return
     *     The CityEnabled
     */
    public Boolean getCityEnabled() {
        return CityEnabled;
    }

    /**
     * 
     * @param CityEnabled
     *     The CityEnabled
     */
    public void setCityEnabled(Boolean CityEnabled) {
        this.CityEnabled = CityEnabled;
    }

    /**
     * 
     * @return
     *     The CityRequired
     */
    public Boolean getCityRequired() {
        return CityRequired;
    }

    /**
     * 
     * @param CityRequired
     *     The CityRequired
     */
    public void setCityRequired(Boolean CityRequired) {
        this.CityRequired = CityRequired;
    }

    /**
     * 
     * @return
     *     The City
     */
    public String getCity() {
        return City;
    }

    /**
     * 
     * @param City
     *     The City
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * 
     * @return
     *     The StreetAddressEnabled
     */
    public Boolean getStreetAddressEnabled() {
        return StreetAddressEnabled;
    }

    /**
     * 
     * @param StreetAddressEnabled
     *     The StreetAddressEnabled
     */
    public void setStreetAddressEnabled(Boolean StreetAddressEnabled) {
        this.StreetAddressEnabled = StreetAddressEnabled;
    }

    /**
     * 
     * @return
     *     The StreetAddressRequired
     */
    public Boolean getStreetAddressRequired() {
        return StreetAddressRequired;
    }

    /**
     * 
     * @param StreetAddressRequired
     *     The StreetAddressRequired
     */
    public void setStreetAddressRequired(Boolean StreetAddressRequired) {
        this.StreetAddressRequired = StreetAddressRequired;
    }

    /**
     * 
     * @return
     *     The Address1
     */
    public String getAddress1() {
        return Address1;
    }

    /**
     * 
     * @param Address1
     *     The Address1
     */
    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    /**
     * 
     * @return
     *     The StreetAddress2Enabled
     */
    public Boolean getStreetAddress2Enabled() {
        return StreetAddress2Enabled;
    }

    /**
     * 
     * @param StreetAddress2Enabled
     *     The StreetAddress2Enabled
     */
    public void setStreetAddress2Enabled(Boolean StreetAddress2Enabled) {
        this.StreetAddress2Enabled = StreetAddress2Enabled;
    }

    /**
     * 
     * @return
     *     The StreetAddress2Required
     */
    public Boolean getStreetAddress2Required() {
        return StreetAddress2Required;
    }

    /**
     * 
     * @param StreetAddress2Required
     *     The StreetAddress2Required
     */
    public void setStreetAddress2Required(Boolean StreetAddress2Required) {
        this.StreetAddress2Required = StreetAddress2Required;
    }

    /**
     * 
     * @return
     *     The Address2
     */
    public String getAddress2() {
        return Address2;
    }

    /**
     * 
     * @param Address2
     *     The Address2
     */
    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    /**
     * 
     * @return
     *     The ZipPostalCodeEnabled
     */
    public Boolean getZipPostalCodeEnabled() {
        return ZipPostalCodeEnabled;
    }

    /**
     * 
     * @param ZipPostalCodeEnabled
     *     The ZipPostalCodeEnabled
     */
    public void setZipPostalCodeEnabled(Boolean ZipPostalCodeEnabled) {
        this.ZipPostalCodeEnabled = ZipPostalCodeEnabled;
    }

    /**
     * 
     * @return
     *     The ZipPostalCodeRequired
     */
    public Boolean getZipPostalCodeRequired() {
        return ZipPostalCodeRequired;
    }

    /**
     * 
     * @param ZipPostalCodeRequired
     *     The ZipPostalCodeRequired
     */
    public void setZipPostalCodeRequired(Boolean ZipPostalCodeRequired) {
        this.ZipPostalCodeRequired = ZipPostalCodeRequired;
    }

    /**
     * 
     * @return
     *     The ZipPostalCode
     */
    public String getZipPostalCode() {
        return ZipPostalCode;
    }

    /**
     * 
     * @param ZipPostalCode
     *     The ZipPostalCode
     */
    public void setZipPostalCode(String ZipPostalCode) {
        this.ZipPostalCode = ZipPostalCode;
    }

    /**
     * 
     * @return
     *     The PhoneEnabled
     */
    public Boolean getPhoneEnabled() {
        return PhoneEnabled;
    }

    /**
     * 
     * @param PhoneEnabled
     *     The PhoneEnabled
     */
    public void setPhoneEnabled(Boolean PhoneEnabled) {
        this.PhoneEnabled = PhoneEnabled;
    }

    /**
     * 
     * @return
     *     The PhoneRequired
     */
    public Boolean getPhoneRequired() {
        return PhoneRequired;
    }

    /**
     * 
     * @param PhoneRequired
     *     The PhoneRequired
     */
    public void setPhoneRequired(Boolean PhoneRequired) {
        this.PhoneRequired = PhoneRequired;
    }

    /**
     * 
     * @return
     *     The PhoneNumber
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    /**
     * 
     * @param PhoneNumber
     *     The PhoneNumber
     */
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    /**
     * 
     * @return
     *     The FaxEnabled
     */
    public Boolean getFaxEnabled() {
        return FaxEnabled;
    }

    /**
     * 
     * @param FaxEnabled
     *     The FaxEnabled
     */
    public void setFaxEnabled(Boolean FaxEnabled) {
        this.FaxEnabled = FaxEnabled;
    }

    /**
     * 
     * @return
     *     The FaxRequired
     */
    public Boolean getFaxRequired() {
        return FaxRequired;
    }

    /**
     * 
     * @param FaxRequired
     *     The FaxRequired
     */
    public void setFaxRequired(Boolean FaxRequired) {
        this.FaxRequired = FaxRequired;
    }

    /**
     * 
     * @return
     *     The FaxNumber
     */
    public Object getFaxNumber() {
        return FaxNumber;
    }

    /**
     * 
     * @param FaxNumber
     *     The FaxNumber
     */
    public void setFaxNumber(Object FaxNumber) {
        this.FaxNumber = FaxNumber;
    }

    /**
     * 
     * @return
     *     The AvailableCountries
     */
    public List<AvailableCountry> getAvailableCountries() {
        return AvailableCountries;
    }

    /**
     * 
     * @param AvailableCountries
     *     The AvailableCountries
     */
    public void setAvailableCountries(List<AvailableCountry> AvailableCountries) {
        this.AvailableCountries = AvailableCountries;
    }

    /**
     * 
     * @return
     *     The AvailableStates
     */
    public List<AvailableState> getAvailableStates() {
        return AvailableStates;
    }

    /**
     * 
     * @param AvailableStates
     *     The AvailableStates
     */
    public void setAvailableStates(List<AvailableState> AvailableStates) {
        this.AvailableStates = AvailableStates;
    }

    /**
     * 
     * @return
     *     The FormattedCustomAddressAttributes
     */
    public String getFormattedCustomAddressAttributes() {
        return FormattedCustomAddressAttributes;
    }

    /**
     * 
     * @param FormattedCustomAddressAttributes
     *     The FormattedCustomAddressAttributes
     */
    public void setFormattedCustomAddressAttributes(String FormattedCustomAddressAttributes) {
        this.FormattedCustomAddressAttributes = FormattedCustomAddressAttributes;
    }

    /**
     * 
     * @return
     *     The CustomAddressAttributes
     */
    public List<Object> getCustomAddressAttributes() {
        return CustomAddressAttributes;
    }

    /**
     * 
     * @param CustomAddressAttributes
     *     The CustomAddressAttributes
     */
    public void setCustomAddressAttributes(List<Object> CustomAddressAttributes) {
        this.CustomAddressAttributes = CustomAddressAttributes;
    }

    /**
     * 
     * @return
     *     The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    public List<AvailableState> getAllStates() {
        return AllStates;
    }

    public void setAllStates(List<AvailableState> allStates) {
        AllStates = allStates;
    }
}

package sa.growonline.footprint.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BeanUserDetails {
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("CheckUsernameAvailabilityEnabled")
    @Expose
    private Boolean checkUsernameAvailabilityEnabled;
    @SerializedName("AllowUsersToChangeUsernames")
    @Expose
    private Boolean allowUsersToChangeUsernames;
    @SerializedName("UsernamesEnabled")
    @Expose
    private Boolean usernamesEnabled;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("GenderEnabled")
    @Expose
    private Boolean genderEnabled;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("DateOfBirthEnabled")
    @Expose
    private Boolean dateOfBirthEnabled;
    @SerializedName("DateOfBirthDay")
    @Expose
    private Integer dateOfBirthDay;
    @SerializedName("DateOfBirthMonth")
    @Expose
    private Integer dateOfBirthMonth;
    @SerializedName("DateOfBirthYear")
    @Expose
    private Integer dateOfBirthYear;
    @SerializedName("DateOfBirthRequired")
    @Expose
    private Boolean dateOfBirthRequired;
    @SerializedName("CompanyEnabled")
    @Expose
    private Boolean companyEnabled;
    @SerializedName("CompanyRequired")
    @Expose
    private Boolean companyRequired;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("StreetAddressEnabled")
    @Expose
    private Boolean streetAddressEnabled;
    @SerializedName("StreetAddressRequired")
    @Expose
    private Boolean streetAddressRequired;
    @SerializedName("StreetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("StreetAddress2Enabled")
    @Expose
    private Boolean streetAddress2Enabled;
    @SerializedName("StreetAddress2Required")
    @Expose
    private Boolean streetAddress2Required;
    @SerializedName("StreetAddress2")
    @Expose
    private Object streetAddress2;
    @SerializedName("ZipPostalCodeEnabled")
    @Expose
    private Boolean zipPostalCodeEnabled;
    @SerializedName("ZipPostalCodeRequired")
    @Expose
    private Boolean zipPostalCodeRequired;
    @SerializedName("ZipPostalCode")
    @Expose
    private Object zipPostalCode;
    @SerializedName("CityEnabled")
    @Expose
    private Boolean cityEnabled;
    @SerializedName("CityRequired")
    @Expose
    private Boolean cityRequired;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("CountryEnabled")
    @Expose
    private Boolean countryEnabled;
    @SerializedName("CountryRequired")
    @Expose
    private Boolean countryRequired;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("StateProvinceEnabled")
    @Expose
    private Boolean stateProvinceEnabled;
    @SerializedName("StateProvinceRequired")
    @Expose
    private Boolean stateProvinceRequired;
    @SerializedName("StateProvinceId")
    @Expose
    private Integer stateProvinceId;
    @SerializedName("AvailableStates")
    @Expose
    private List<Object> availableStates = new ArrayList<Object>();
    @SerializedName("PhoneEnabled")
    @Expose
    private Boolean phoneEnabled;
    @SerializedName("PhoneRequired")
    @Expose
    private Boolean phoneRequired;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("FaxEnabled")
    @Expose
    private Boolean faxEnabled;
    @SerializedName("FaxRequired")
    @Expose
    private Boolean faxRequired;
    @SerializedName("Fax")
    @Expose
    private String fax;
    @SerializedName("NewsletterEnabled")
    @Expose
    private Boolean newsletterEnabled;
    @SerializedName("Newsletter")
    @Expose
    private Boolean newsletter;
    @SerializedName("SignatureEnabled")
    @Expose
    private Boolean signatureEnabled;
    @SerializedName("Signature")
    @Expose
    private Object signature;
    @SerializedName("TimeZoneId")
    @Expose
    private Object timeZoneId;
    @SerializedName("AllowCustomersToSetTimeZone")
    @Expose
    private Boolean allowCustomersToSetTimeZone;
    @SerializedName("AvailableTimeZones")
    @Expose
    private List<Object> availableTimeZones = new ArrayList<Object>();
    @SerializedName("VatNumber")
    @Expose
    private Object vatNumber;
    @SerializedName("VatNumberStatusNote")
    @Expose
    private String vatNumberStatusNote;
    @SerializedName("DisplayVatNumber")
    @Expose
    private Boolean displayVatNumber;
    @SerializedName("AssociatedExternalAuthRecords")
    @Expose
    private List<Object> associatedExternalAuthRecords = new ArrayList<Object>();
    @SerializedName("NumberOfExternalAuthenticationProviders")
    @Expose
    private Integer numberOfExternalAuthenticationProviders;

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The checkUsernameAvailabilityEnabled
     */
    public Boolean getCheckUsernameAvailabilityEnabled() {
        return checkUsernameAvailabilityEnabled;
    }

    /**
     * @param checkUsernameAvailabilityEnabled The CheckUsernameAvailabilityEnabled
     */
    public void setCheckUsernameAvailabilityEnabled(Boolean checkUsernameAvailabilityEnabled) {
        this.checkUsernameAvailabilityEnabled = checkUsernameAvailabilityEnabled;
    }

    /**
     * @return The allowUsersToChangeUsernames
     */
    public Boolean getAllowUsersToChangeUsernames() {
        return allowUsersToChangeUsernames;
    }

    /**
     * @param allowUsersToChangeUsernames The AllowUsersToChangeUsernames
     */
    public void setAllowUsersToChangeUsernames(Boolean allowUsersToChangeUsernames) {
        this.allowUsersToChangeUsernames = allowUsersToChangeUsernames;
    }

    /**
     * @return The usernamesEnabled
     */
    public Boolean getUsernamesEnabled() {
        return usernamesEnabled;
    }

    /**
     * @param usernamesEnabled The UsernamesEnabled
     */
    public void setUsernamesEnabled(Boolean usernamesEnabled) {
        this.usernamesEnabled = usernamesEnabled;
    }

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The genderEnabled
     */
    public Boolean getGenderEnabled() {
        return genderEnabled;
    }

    /**
     * @param genderEnabled The GenderEnabled
     */
    public void setGenderEnabled(Boolean genderEnabled) {
        this.genderEnabled = genderEnabled;
    }

    /**
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The Gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The FirstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The LastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The dateOfBirthEnabled
     */
    public Boolean getDateOfBirthEnabled() {
        return dateOfBirthEnabled;
    }

    /**
     * @param dateOfBirthEnabled The DateOfBirthEnabled
     */
    public void setDateOfBirthEnabled(Boolean dateOfBirthEnabled) {
        this.dateOfBirthEnabled = dateOfBirthEnabled;
    }

    /**
     * @return The dateOfBirthDay
     */
    public Integer getDateOfBirthDay() {
        return dateOfBirthDay;
    }

    /**
     * @param dateOfBirthDay The DateOfBirthDay
     */
    public void setDateOfBirthDay(Integer dateOfBirthDay) {
        this.dateOfBirthDay = dateOfBirthDay;
    }

    /**
     * @return The dateOfBirthMonth
     */
    public Integer getDateOfBirthMonth() {
        return dateOfBirthMonth;
    }

    /**
     * @param dateOfBirthMonth The DateOfBirthMonth
     */
    public void setDateOfBirthMonth(Integer dateOfBirthMonth) {
        this.dateOfBirthMonth = dateOfBirthMonth;
    }

    /**
     * @return The dateOfBirthYear
     */
    public Integer getDateOfBirthYear() {
        return dateOfBirthYear;
    }

    /**
     * @param dateOfBirthYear The DateOfBirthYear
     */
    public void setDateOfBirthYear(Integer dateOfBirthYear) {
        this.dateOfBirthYear = dateOfBirthYear;
    }

    /**
     * @return The dateOfBirthRequired
     */
    public Boolean getDateOfBirthRequired() {
        return dateOfBirthRequired;
    }

    /**
     * @param dateOfBirthRequired The DateOfBirthRequired
     */
    public void setDateOfBirthRequired(Boolean dateOfBirthRequired) {
        this.dateOfBirthRequired = dateOfBirthRequired;
    }

    /**
     * @return The companyEnabled
     */
    public Boolean getCompanyEnabled() {
        return companyEnabled;
    }

    /**
     * @param companyEnabled The CompanyEnabled
     */
    public void setCompanyEnabled(Boolean companyEnabled) {
        this.companyEnabled = companyEnabled;
    }

    /**
     * @return The companyRequired
     */
    public Boolean getCompanyRequired() {
        return companyRequired;
    }

    /**
     * @param companyRequired The CompanyRequired
     */
    public void setCompanyRequired(Boolean companyRequired) {
        this.companyRequired = companyRequired;
    }

    /**
     * @return The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company The Company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return The streetAddressEnabled
     */
    public Boolean getStreetAddressEnabled() {
        return streetAddressEnabled;
    }

    /**
     * @param streetAddressEnabled The StreetAddressEnabled
     */
    public void setStreetAddressEnabled(Boolean streetAddressEnabled) {
        this.streetAddressEnabled = streetAddressEnabled;
    }

    /**
     * @return The streetAddressRequired
     */
    public Boolean getStreetAddressRequired() {
        return streetAddressRequired;
    }

    /**
     * @param streetAddressRequired The StreetAddressRequired
     */
    public void setStreetAddressRequired(Boolean streetAddressRequired) {
        this.streetAddressRequired = streetAddressRequired;
    }

    /**
     * @return The streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress The StreetAddress
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return The streetAddress2Enabled
     */
    public Boolean getStreetAddress2Enabled() {
        return streetAddress2Enabled;
    }

    /**
     * @param streetAddress2Enabled The StreetAddress2Enabled
     */
    public void setStreetAddress2Enabled(Boolean streetAddress2Enabled) {
        this.streetAddress2Enabled = streetAddress2Enabled;
    }

    /**
     * @return The streetAddress2Required
     */
    public Boolean getStreetAddress2Required() {
        return streetAddress2Required;
    }

    /**
     * @param streetAddress2Required The StreetAddress2Required
     */
    public void setStreetAddress2Required(Boolean streetAddress2Required) {
        this.streetAddress2Required = streetAddress2Required;
    }

    /**
     * @return The streetAddress2
     */
    public Object getStreetAddress2() {
        return streetAddress2;
    }

    /**
     * @param streetAddress2 The StreetAddress2
     */
    public void setStreetAddress2(Object streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    /**
     * @return The zipPostalCodeEnabled
     */
    public Boolean getZipPostalCodeEnabled() {
        return zipPostalCodeEnabled;
    }

    /**
     * @param zipPostalCodeEnabled The ZipPostalCodeEnabled
     */
    public void setZipPostalCodeEnabled(Boolean zipPostalCodeEnabled) {
        this.zipPostalCodeEnabled = zipPostalCodeEnabled;
    }

    /**
     * @return The zipPostalCodeRequired
     */
    public Boolean getZipPostalCodeRequired() {
        return zipPostalCodeRequired;
    }

    /**
     * @param zipPostalCodeRequired The ZipPostalCodeRequired
     */
    public void setZipPostalCodeRequired(Boolean zipPostalCodeRequired) {
        this.zipPostalCodeRequired = zipPostalCodeRequired;
    }

    /**
     * @return The zipPostalCode
     */
    public Object getZipPostalCode() {
        return zipPostalCode;
    }

    /**
     * @param zipPostalCode The ZipPostalCode
     */
    public void setZipPostalCode(Object zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    /**
     * @return The cityEnabled
     */
    public Boolean getCityEnabled() {
        return cityEnabled;
    }

    /**
     * @param cityEnabled The CityEnabled
     */
    public void setCityEnabled(Boolean cityEnabled) {
        this.cityEnabled = cityEnabled;
    }

    /**
     * @return The cityRequired
     */
    public Boolean getCityRequired() {
        return cityRequired;
    }

    /**
     * @param cityRequired The CityRequired
     */
    public void setCityRequired(Boolean cityRequired) {
        this.cityRequired = cityRequired;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The City
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The countryEnabled
     */
    public Boolean getCountryEnabled() {
        return countryEnabled;
    }

    /**
     * @param countryEnabled The CountryEnabled
     */
    public void setCountryEnabled(Boolean countryEnabled) {
        this.countryEnabled = countryEnabled;
    }

    /**
     * @return The countryRequired
     */
    public Boolean getCountryRequired() {
        return countryRequired;
    }

    /**
     * @param countryRequired The CountryRequired
     */
    public void setCountryRequired(Boolean countryRequired) {
        this.countryRequired = countryRequired;
    }

    /**
     * @return The countryId
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * @param countryId The CountryId
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * @return The stateProvinceEnabled
     */
    public Boolean getStateProvinceEnabled() {
        return stateProvinceEnabled;
    }

    /**
     * @param stateProvinceEnabled The StateProvinceEnabled
     */
    public void setStateProvinceEnabled(Boolean stateProvinceEnabled) {
        this.stateProvinceEnabled = stateProvinceEnabled;
    }

    /**
     * @return The stateProvinceRequired
     */
    public Boolean getStateProvinceRequired() {
        return stateProvinceRequired;
    }

    /**
     * @param stateProvinceRequired The StateProvinceRequired
     */
    public void setStateProvinceRequired(Boolean stateProvinceRequired) {
        this.stateProvinceRequired = stateProvinceRequired;
    }

    /**
     * @return The stateProvinceId
     */
    public Integer getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * @param stateProvinceId The StateProvinceId
     */
    public void setStateProvinceId(Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    /**
     * @return The availableStates
     */
    public List<Object> getAvailableStates() {
        return availableStates;
    }

    /**
     * @param availableStates The AvailableStates
     */
    public void setAvailableStates(List<Object> availableStates) {
        this.availableStates = availableStates;
    }

    /**
     * @return The phoneEnabled
     */
    public Boolean getPhoneEnabled() {
        return phoneEnabled;
    }

    /**
     * @param phoneEnabled The PhoneEnabled
     */
    public void setPhoneEnabled(Boolean phoneEnabled) {
        this.phoneEnabled = phoneEnabled;
    }

    /**
     * @return The phoneRequired
     */
    public Boolean getPhoneRequired() {
        return phoneRequired;
    }

    /**
     * @param phoneRequired The PhoneRequired
     */
    public void setPhoneRequired(Boolean phoneRequired) {
        this.phoneRequired = phoneRequired;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The Phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The faxEnabled
     */
    public Boolean getFaxEnabled() {
        return faxEnabled;
    }

    /**
     * @param faxEnabled The FaxEnabled
     */
    public void setFaxEnabled(Boolean faxEnabled) {
        this.faxEnabled = faxEnabled;
    }

    /**
     * @return The faxRequired
     */
    public Boolean getFaxRequired() {
        return faxRequired;
    }

    /**
     * @param faxRequired The FaxRequired
     */
    public void setFaxRequired(Boolean faxRequired) {
        this.faxRequired = faxRequired;
    }

    /**
     * @return The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax The Fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return The newsletterEnabled
     */
    public Boolean getNewsletterEnabled() {
        return newsletterEnabled;
    }

    /**
     * @param newsletterEnabled The NewsletterEnabled
     */
    public void setNewsletterEnabled(Boolean newsletterEnabled) {
        this.newsletterEnabled = newsletterEnabled;
    }

    /**
     * @return The newsletter
     */
    public Boolean getNewsletter() {
        return newsletter;
    }

    /**
     * @param newsletter The Newsletter
     */
    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    /**
     * @return The signatureEnabled
     */
    public Boolean getSignatureEnabled() {
        return signatureEnabled;
    }

    /**
     * @param signatureEnabled The SignatureEnabled
     */
    public void setSignatureEnabled(Boolean signatureEnabled) {
        this.signatureEnabled = signatureEnabled;
    }

    /**
     * @return The signature
     */
    public Object getSignature() {
        return signature;
    }

    /**
     * @param signature The Signature
     */
    public void setSignature(Object signature) {
        this.signature = signature;
    }

    /**
     * @return The timeZoneId
     */
    public Object getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * @param timeZoneId The TimeZoneId
     */
    public void setTimeZoneId(Object timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    /**
     * @return The allowCustomersToSetTimeZone
     */
    public Boolean getAllowCustomersToSetTimeZone() {
        return allowCustomersToSetTimeZone;
    }

    /**
     * @param allowCustomersToSetTimeZone The AllowCustomersToSetTimeZone
     */
    public void setAllowCustomersToSetTimeZone(Boolean allowCustomersToSetTimeZone) {
        this.allowCustomersToSetTimeZone = allowCustomersToSetTimeZone;
    }

    /**
     * @return The availableTimeZones
     */
    public List<Object> getAvailableTimeZones() {
        return availableTimeZones;
    }

    /**
     * @param availableTimeZones The AvailableTimeZones
     */
    public void setAvailableTimeZones(List<Object> availableTimeZones) {
        this.availableTimeZones = availableTimeZones;
    }

    /**
     * @return The vatNumber
     */
    public Object getVatNumber() {
        return vatNumber;
    }

    /**
     * @param vatNumber The VatNumber
     */
    public void setVatNumber(Object vatNumber) {
        this.vatNumber = vatNumber;
    }

    /**
     * @return The vatNumberStatusNote
     */
    public String getVatNumberStatusNote() {
        return vatNumberStatusNote;
    }

    /**
     * @param vatNumberStatusNote The VatNumberStatusNote
     */
    public void setVatNumberStatusNote(String vatNumberStatusNote) {
        this.vatNumberStatusNote = vatNumberStatusNote;
    }

    /**
     * @return The displayVatNumber
     */
    public Boolean getDisplayVatNumber() {
        return displayVatNumber;
    }

    /**
     * @param displayVatNumber The DisplayVatNumber
     */
    public void setDisplayVatNumber(Boolean displayVatNumber) {
        this.displayVatNumber = displayVatNumber;
    }

    /**
     * @return The associatedExternalAuthRecords
     */
    public List<Object> getAssociatedExternalAuthRecords() {
        return associatedExternalAuthRecords;
    }

    /**
     * @param associatedExternalAuthRecords The AssociatedExternalAuthRecords
     */
    public void setAssociatedExternalAuthRecords(List<Object> associatedExternalAuthRecords) {
        this.associatedExternalAuthRecords = associatedExternalAuthRecords;
    }

    /**
     * @return The numberOfExternalAuthenticationProviders
     */
    public Integer getNumberOfExternalAuthenticationProviders() {
        return numberOfExternalAuthenticationProviders;
    }

    /**
     * @param numberOfExternalAuthenticationProviders The NumberOfExternalAuthenticationProviders
     */
    public void setNumberOfExternalAuthenticationProviders(Integer numberOfExternalAuthenticationProviders) {
        this.numberOfExternalAuthenticationProviders = numberOfExternalAuthenticationProviders;
    }

}

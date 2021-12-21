package com.fci.colors_app.data.models;


import com.google.gson.annotations.SerializedName;

public class SettingsModel {
    @SerializedName("id")
    private int id;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("language")
    private String language;
    @SerializedName("app_title")
    private String appTitle;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("tweeter")
    private String tweeter;
    @SerializedName("instagram")
    private String instagram;
    @SerializedName("linkedin")
    private String linkedin;
    @SerializedName("snapchat")
    private String snapchat;
    @SerializedName("website")
    private String website;
    @SerializedName("phone_1")
    private String phone1;
    @SerializedName("mail")
    private String mail;
    @SerializedName("address")
    private String address;
    @SerializedName("app_android_lnk")
    private String appAndroidLnk;
    @SerializedName("app_ios_link")
    private String appIosLink;
    @SerializedName("app_share_note")
    private String appShareNote;
    @SerializedName("work_times")
    private String workTimes;
    @SerializedName("logo")
    private String logo;
    @SerializedName("register_st_1")
    private String registerSt1;
    @SerializedName("register_st_2")
    private String registerSt2;
    @SerializedName("register_st_3")
    private String registerSt3;
    @SerializedName("about_us")
    private String aboutUs;
    @SerializedName("about_us_image")
    private String aboutUsImage;
    @SerializedName("terms")
    private String terms;
    @SerializedName("privacy")
    private String privacy;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;
    @SerializedName("commission_percent")
    private String commissionPercent;
    @SerializedName("addition_service_cost")
    private String additionServiceCost;
    @SerializedName("banner")
    private Object banner;
    @SerializedName("telegram")
    private String telegram;
    @SerializedName("customer_service")
    private String customerService;
    @SerializedName("unified_number")
    private String unifiedNumber;
    @SerializedName("geofire_radius")
    private String geofireRadius;
    @SerializedName("youtube")
    private String youtube;

    public String getGeofireRadius() {
        return geofireRadius;
    }

    public void setGeofireRadius(String geofireRadius) {
        this.geofireRadius = geofireRadius;
    }
    @SerializedName("minimum_shipping")
    private String minimumShipping;


    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTweeter() {
        return tweeter;
    }

    public void setTweeter(String tweeter) {
        this.tweeter = tweeter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppAndroidLnk() {
        return appAndroidLnk;
    }

    public void setAppAndroidLnk(String appAndroidLnk) {
        this.appAndroidLnk = appAndroidLnk;
    }

    public String getAppIosLink() {
        return appIosLink;
    }

    public void setAppIosLink(String appIosLink) {
        this.appIosLink = appIosLink;
    }

    public String getAppShareNote() {
        return appShareNote;
    }

    public void setAppShareNote(String appShareNote) {
        this.appShareNote = appShareNote;
    }

    public String getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(String workTimes) {
        this.workTimes = workTimes;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRegisterSt1() {
        return registerSt1;
    }

    public void setRegisterSt1(String registerSt1) {
        this.registerSt1 = registerSt1;
    }

    public String getRegisterSt2() {
        return registerSt2;
    }

    public void setRegisterSt2(String registerSt2) {
        this.registerSt2 = registerSt2;
    }

    public String getRegisterSt3() {
        return registerSt3;
    }

    public void setRegisterSt3(String registerSt3) {
        this.registerSt3 = registerSt3;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getAboutUsImage() {
        return aboutUsImage;
    }

    public void setAboutUsImage(String aboutUsImage) {
        this.aboutUsImage = aboutUsImage;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(String commissionPercent) {
        this.commissionPercent = commissionPercent;
    }

    public String getAdditionServiceCost() {
        return additionServiceCost;
    }

    public void setAdditionServiceCost(String additionServiceCost) {
        this.additionServiceCost = additionServiceCost;
    }

    public Object getBanner() {
        return banner;
    }

    public void setBanner(Object banner) {
        this.banner = banner;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }

    public String getUnifiedNumber() {
        return unifiedNumber;
    }

    public void setUnifiedNumber(String unifiedNumber) {
        this.unifiedNumber = unifiedNumber;
    }

    public String getMinimumShipping() {
        return minimumShipping;
    }

    public void setMinimumShipping(String minimumShipping) {
        this.minimumShipping = minimumShipping;
    }
}

package com.fci.colors_app.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {

    /**
     * id : 180
     * name : salem saber
     * email : null
     * phone : 01140366864
     * type_id : 2
     * image : null
     * verification_code : 5403
     * access_token : null
     * ip : 127.0.0.1
     * created_at : 2020-09-29T13:43:06.000000Z
     * rate : 0
     * rate_count : 0
     * is_active : 1
     * is_verified : 1
     * unseen_notifications_count : 0
     *
     */

    @SerializedName("id_number")
    private String idNumber;

    @SerializedName("city")
    private int city;

    @SerializedName("area")
    private int area;

    @SerializedName("id_card")
    private String idCard;

    @SerializedName("license")
    private String license;

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("type_id")
    private int typeId;
    @SerializedName("image")
    private String image;
    @SerializedName("verification_code")
    private int verificationCode;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("ip")
    private String ip;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("rate")
    private String rate;
    @SerializedName("rate_count")
    private String rateCount;
    @SerializedName("is_active")
    private int isActive;
    @SerializedName("is_verified")
    private int isVerified;
    @SerializedName("unseen_notifications_count")
    private int unseenNotificationsCount;
    @SerializedName("gender")
    private String gender;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public int getUnseenNotificationsCount() {
        return unseenNotificationsCount;
    }

    public void setUnseenNotificationsCount(int unseenNotificationsCount) {
        this.unseenNotificationsCount = unseenNotificationsCount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

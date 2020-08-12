
package com.alpha.malukhiah.model.MyFavAdvListPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userdata {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("user_fullname")
    @Expose
    private String userFullname;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_bdate")
    @Expose
    private String userBdate;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("user_country")
    @Expose
    private String userCountry;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("varification_code")
    @Expose
    private String varificationCode;
    @SerializedName("user_image")
    @Expose
    private String userImage;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("socity_id")
    @Expose
    private String socityId;
    @SerializedName("house_no")
    @Expose
    private String houseNo;
    @SerializedName("mobile_verified")
    @Expose
    private String mobileVerified;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("user_gcm_code")
    @Expose
    private String userGcmCode;
    @SerializedName("user_ios_token")
    @Expose
    private String userIosToken;
    @SerializedName("varified_token")
    @Expose
    private String varifiedToken;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reg_code")
    @Expose
    private String regCode;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("rewards")
    @Expose
    private String rewards;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("otp_status")
    @Expose
    private String otpStatus;
    @SerializedName("social")
    @Expose
    private String social;
    @SerializedName("facebookID")
    @Expose
    private String facebookID;
    @SerializedName("instaID")
    @Expose
    private String instaID;
    @SerializedName("gmailId")
    @Expose
    private String gmailId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Userdata() {
    }

    /**
     * 
     * @param userCity
     * @param firstname
     * @param userPassword
     * @param gender
     * @param userPhone
     * @param facebookID
     * @param mobileVerified
     * @param regCode
     * @param varifiedToken
     * @param userCountry
     * @param userImage
     * @param instaID
     * @param userFullname
     * @param userBdate
     * @param houseNo
     * @param modified
     * @param userEmail
     * @param otpStatus
     * @param userGcmCode
     * @param pincode
     * @param wallet
     * @param social
     * @param created
     * @param socityId
     * @param otp
     * @param userId
     * @param userIosToken
     * @param lastname
     * @param deviceToken
     * @param gmailId
     * @param varificationCode
     * @param rewards
     * @param status
     */
    public Userdata(String userId, String userPhone, String firstname, String lastname, String userFullname, String userEmail, String userBdate, String userPassword, String gender, String userCountry, String userCity, String varificationCode, String userImage, String pincode, String socityId, String houseNo, String mobileVerified, String deviceToken, String userGcmCode, String userIosToken, String varifiedToken, String status, String regCode, String wallet, String rewards, String created, String modified, String otp, String otpStatus, String social, String facebookID, String instaID, String gmailId) {
        super();
        this.userId = userId;
        this.userPhone = userPhone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userFullname = userFullname;
        this.userEmail = userEmail;
        this.userBdate = userBdate;
        this.userPassword = userPassword;
        this.gender = gender;
        this.userCountry = userCountry;
        this.userCity = userCity;
        this.varificationCode = varificationCode;
        this.userImage = userImage;
        this.pincode = pincode;
        this.socityId = socityId;
        this.houseNo = houseNo;
        this.mobileVerified = mobileVerified;
        this.deviceToken = deviceToken;
        this.userGcmCode = userGcmCode;
        this.userIosToken = userIosToken;
        this.varifiedToken = varifiedToken;
        this.status = status;
        this.regCode = regCode;
        this.wallet = wallet;
        this.rewards = rewards;
        this.created = created;
        this.modified = modified;
        this.otp = otp;
        this.otpStatus = otpStatus;
        this.social = social;
        this.facebookID = facebookID;
        this.instaID = instaID;
        this.gmailId = gmailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBdate() {
        return userBdate;
    }

    public void setUserBdate(String userBdate) {
        this.userBdate = userBdate;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getVarificationCode() {
        return varificationCode;
    }

    public void setVarificationCode(String varificationCode) {
        this.varificationCode = varificationCode;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getSocityId() {
        return socityId;
    }

    public void setSocityId(String socityId) {
        this.socityId = socityId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(String mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUserGcmCode() {
        return userGcmCode;
    }

    public void setUserGcmCode(String userGcmCode) {
        this.userGcmCode = userGcmCode;
    }

    public String getUserIosToken() {
        return userIosToken;
    }

    public void setUserIosToken(String userIosToken) {
        this.userIosToken = userIosToken;
    }

    public String getVarifiedToken() {
        return varifiedToken;
    }

    public void setVarifiedToken(String varifiedToken) {
        this.varifiedToken = varifiedToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpStatus() {
        return otpStatus;
    }

    public void setOtpStatus(String otpStatus) {
        this.otpStatus = otpStatus;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getInstaID() {
        return instaID;
    }

    public void setInstaID(String instaID) {
        this.instaID = instaID;
    }

    public String getGmailId() {
        return gmailId;
    }

    public void setGmailId(String gmailId) {
        this.gmailId = gmailId;
    }

}

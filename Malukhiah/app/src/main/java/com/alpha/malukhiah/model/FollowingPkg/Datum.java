
package com.alpha.malukhiah.model.FollowingPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("f_id")
    @Expose
    private String fId;
    @SerializedName("from_id")
    @Expose
    private String fromId;
    @SerializedName("to_id")
    @Expose
    private String toId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("request_status")
    @Expose
    private String requestStatus;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_fullname")
    @Expose
    private String userFullname;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_image")
    @Expose
    private String userImage;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param fId
     * @param toId
     * @param userImage
     * @param userPhone
     * @param userFullname
     * @param userEmail
     * @param fromId
     * @param requestStatus
     * @param status
     */
    public Datum(String fId, String fromId, String toId, String status, String requestStatus, String userPhone, String userFullname, String userEmail, String userImage) {
        super();
        this.fId = fId;
        this.fromId = fromId;
        this.toId = toId;
        this.status = status;
        this.requestStatus = requestStatus;
        this.userPhone = userPhone;
        this.userFullname = userFullname;
        this.userEmail = userEmail;
        this.userImage = userImage;
    }

    public String getFId() {
        return fId;
    }

    public void setFId(String fId) {
        this.fId = fId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}

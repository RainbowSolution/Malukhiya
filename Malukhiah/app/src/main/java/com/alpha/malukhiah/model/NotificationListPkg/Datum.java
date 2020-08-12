
package com.alpha.malukhiah.model.NotificationListPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("notification")
    @Expose
    private String notification;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("watched")
    @Expose
    private String watched;
    @SerializedName("user_fullname")
    @Expose
    private String userFullname;
    @SerializedName("firstname")
    @Expose
    private String firstname;
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
     * @param date
     * @param firstname
     * @param message
     * @param userId
     * @param deviceId
     * @param notification
     * @param watched
     * @param userImage
     * @param userFullname
     * @param invoiceId
     * @param id
     * @param time
     * @param status
     */
    public Datum(String id, String userId, String notification, String message, String invoiceId, String status, String deviceId, String date, String time, String watched, String userFullname, String firstname, String userImage) {
        super();
        this.id = id;
        this.userId = userId;
        this.notification = notification;
        this.message = message;
        this.invoiceId = invoiceId;
        this.status = status;
        this.deviceId = deviceId;
        this.date = date;
        this.time = time;
        this.watched = watched;
        this.userFullname = userFullname;
        this.firstname = firstname;
        this.userImage = userImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

}

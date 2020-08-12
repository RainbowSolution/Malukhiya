
package com.alpha.malukhiah.model.signupPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpPozo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

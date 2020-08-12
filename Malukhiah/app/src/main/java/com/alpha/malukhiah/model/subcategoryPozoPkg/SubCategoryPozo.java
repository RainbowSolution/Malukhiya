
package com.alpha.malukhiah.model.subcategoryPozoPkg;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryPozo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SubDatum> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubCategoryPozo() {
    }

    /**
     * 
     * @param data
     * @param message
     * @param status
     */
    public SubCategoryPozo(Boolean status, String message, List<SubDatum> data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SubDatum> getData() {
        return data;
    }

    public void setData(List<SubDatum> data) {
        this.data = data;
    }

}

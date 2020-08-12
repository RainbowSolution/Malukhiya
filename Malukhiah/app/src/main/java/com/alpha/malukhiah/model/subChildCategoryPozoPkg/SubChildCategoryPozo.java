
package com.alpha.malukhiah.model.subChildCategoryPozoPkg;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubChildCategoryPozo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SubChildDatum> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubChildCategoryPozo() {
    }

    /**
     * 
     * @param data
     * @param message
     * @param status
     */
    public SubChildCategoryPozo(Boolean status, String message, List<SubChildDatum> data) {
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

    public List<SubChildDatum> getData() {
        return data;
    }

    public void setData(List<SubChildDatum> data) {
        this.data = data;
    }

}

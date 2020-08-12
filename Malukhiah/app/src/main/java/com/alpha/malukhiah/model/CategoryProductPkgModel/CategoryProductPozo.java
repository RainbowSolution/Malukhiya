
package com.alpha.malukhiah.model.CategoryProductPkgModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryProductPozo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("base_url")
    @Expose
    private String baseUrl;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoryProductPozo() {
    }

    /**
     * 
     * @param baseUrl
     * @param data
     * @param message
     * @param status
     */
    public CategoryProductPozo(Boolean status, String message, String baseUrl, List<Datum> data) {
        super();
        this.status = status;
        this.message = message;
        this.baseUrl = baseUrl;
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}

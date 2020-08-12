
package com.alpha.malukhiah.model.wishilistModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWishListResponseModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("getWishlistData")
    @Expose
    private List<GetWishlistDatum> getWishlistData = null;

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

    public List<GetWishlistDatum> getGetWishlistData() {
        return getWishlistData;
    }

    public void setGetWishlistData(List<GetWishlistDatum> getWishlistData) {
        this.getWishlistData = getWishlistData;
    }

}

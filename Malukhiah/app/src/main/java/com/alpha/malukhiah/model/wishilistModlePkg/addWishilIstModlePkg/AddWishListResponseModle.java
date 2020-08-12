
package com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddWishListResponseModle {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("addWishlistData")
    @Expose
    private AddWishlistData addWishlistData;

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

    public AddWishlistData getAddWishlistData() {
        return addWishlistData;
    }

    public void setAddWishlistData(AddWishlistData addWishlistData) {
        this.addWishlistData = addWishlistData;
    }

}

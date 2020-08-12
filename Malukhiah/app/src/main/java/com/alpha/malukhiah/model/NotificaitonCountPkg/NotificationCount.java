
package com.alpha.malukhiah.model.NotificaitonCountPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationCount {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NotificationCount() {
    }

    /**
     * 
     * @param count
     * @param status
     */
    public NotificationCount(Boolean status, Integer count) {
        super();
        this.status = status;
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}

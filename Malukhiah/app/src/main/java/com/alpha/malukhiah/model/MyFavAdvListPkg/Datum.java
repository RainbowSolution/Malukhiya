
package com.alpha.malukhiah.model.MyFavAdvListPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("fav_id ")
    @Expose
    private String favId;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("cat_name")
    @Expose
    private String cat_name;
    @SerializedName("postdetails")
    @Expose
    private Postdetails postdetails;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("userdata")
    @Expose
    private Userdata userdata;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fav_status")
    @Expose
    private String favStatus;
    @SerializedName("follower_status")
    @Expose
    private String followerStatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param userdata
     * @param followerStatus
     * @param favStatus
     * @param postdetails
     * @param postId
     * @param favId
     * @param userId
     * @param status
     */
    public Datum(String favId, String postId,String cat_name, Postdetails postdetails, String userId, Userdata userdata, String status, String favStatus, String followerStatus) {
        super();
        this.favId = favId;
        this.postId = postId;
        this.cat_name =cat_name;
        this.postdetails = postdetails;
        this.userId = userId;
        this.userdata = userdata;
        this.status = status;
        this.favStatus = favStatus;
        this.followerStatus = followerStatus;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getFavId() {
        return favId;
    }

    public void setFavId(String favId) {
        this.favId = favId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Postdetails getPostdetails() {
        return postdetails;
    }

    public void setPostdetails(Postdetails postdetails) {
        this.postdetails = postdetails;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getFollowerStatus() {
        return followerStatus;
    }

    public void setFollowerStatus(String followerStatus) {
        this.followerStatus = followerStatus;
    }

}

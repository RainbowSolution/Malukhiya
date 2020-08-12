
package com.alpha.malukhiah.model.GetProfilePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user_id")
    @Expose
    private String userId;
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
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("following_count")
    @Expose
    private Integer followingCount;
    @SerializedName("follower_count")
    @Expose
    private Integer followerCount;
    @SerializedName("my_adds")
    @Expose
    private Integer myAdds;
    @SerializedName("viewed")
    @Expose
    private Integer viewed;
    @SerializedName("is_paid")
    @Expose
    private String isPaid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param isPaid
     * @param userImage
     * @param gender
     * @param myAdds
     * @param userPhone
     * @param viewed
     * @param userFullname
     * @param userEmail
     * @param userId
     * @param followingCount
     * @param followerCount
     * @param status
     */
    public Data(String userId, String userPhone, String userFullname, String userEmail, String userImage, String gender, String status, Integer followingCount, Integer followerCount, Integer myAdds, Integer viewed, String isPaid) {
        super();
        this.userId = userId;
        this.userPhone = userPhone;
        this.userFullname = userFullname;
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.gender = gender;
        this.status = status;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
        this.myAdds = myAdds;
        this.viewed = viewed;
        this.isPaid = isPaid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getMyAdds() {
        return myAdds;
    }

    public void setMyAdds(Integer myAdds) {
        this.myAdds = myAdds;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

}

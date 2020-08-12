
package com.alpha.malukhiah.model.CategoryProductPkgModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("child_subcat_id")
    @Expose
    private String childSubcatId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longnitue")
    @Expose
    private String longnitue;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("thumbnails_image")
    @Expose
    private String thumbnailsImage;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("is_status")
    @Expose
    private String isStatus;
    @SerializedName("created_At")
    @Expose
    private String createdAt;
    @SerializedName("fav_status")
    @Expose
    private String favStatus;
    @SerializedName("follower_status")
    @Expose
    private String followerStatus;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("subcat_name")
    @Expose
    private String subcatName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param dateTime
     * @param images
     * @param followerStatus
     * @param favStatus
     * @param isStatus
     * @param latitude
     * @param userPhone
     * @param subCatId
     * @param description
     * @param postId
     * @param title
     * @param userId
     * @param subcatName
     * @param catId
     * @param thumbnailsImage
     * @param createdAt
     * @param price
     * @param location
     * @param childSubcatId
     * @param longnitue
     */
    public Datum(String postId, String userId, String title, String catId, String subCatId, String childSubcatId, String price, String location, String latitude, String longnitue, String description, String images, String thumbnailsImage, String dateTime, String isStatus, String createdAt, String favStatus, String followerStatus, String userPhone, String subcatName) {
        super();
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.catId = catId;
        this.subCatId = subCatId;
        this.childSubcatId = childSubcatId;
        this.price = price;
        this.location = location;
        this.latitude = latitude;
        this.longnitue = longnitue;
        this.description = description;
        this.images = images;
        this.thumbnailsImage = thumbnailsImage;
        this.dateTime = dateTime;
        this.isStatus = isStatus;
        this.createdAt = createdAt;
        this.favStatus = favStatus;
        this.followerStatus = followerStatus;
        this.userPhone = userPhone;
        this.subcatName = subcatName;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getChildSubcatId() {
        return childSubcatId;
    }

    public void setChildSubcatId(String childSubcatId) {
        this.childSubcatId = childSubcatId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongnitue() {
        return longnitue;
    }

    public void setLongnitue(String longnitue) {
        this.longnitue = longnitue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getThumbnailsImage() {
        return thumbnailsImage;
    }

    public void setThumbnailsImage(String thumbnailsImage) {
        this.thumbnailsImage = thumbnailsImage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

}

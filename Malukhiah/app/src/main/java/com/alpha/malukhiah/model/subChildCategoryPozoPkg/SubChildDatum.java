
package com.alpha.malukhiah.model.subChildCategoryPozoPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubChildDatum {
    private boolean isChecked = false;

    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("subcat_name")
    @Expose
    private String subcatName;
    @SerializedName("subcat_img")
    @Expose
    private String subcatImg;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubChildDatum() {
    }

    /**
     * 
     * @param catId
     * @param createdAt
     * @param subCatId
     * @param subcatImg
     * @param parentId
     * @param subcatName
     * @param status
     */
    public SubChildDatum(String subCatId, String catId, String parentId, String subcatName, String subcatImg, String status, String createdAt) {
        super();
        this.subCatId = subCatId;
        this.catId = catId;
        this.parentId = parentId;
        this.subcatName = subcatName;
        this.subcatImg = subcatImg;
        this.status = status;
        this.createdAt = createdAt;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

    public String getSubcatImg() {
        return subcatImg;
    }

    public void setSubcatImg(String subcatImg) {
        this.subcatImg = subcatImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

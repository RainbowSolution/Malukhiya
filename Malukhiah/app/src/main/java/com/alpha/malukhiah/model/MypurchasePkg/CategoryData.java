
package com.alpha.malukhiah.model.MypurchasePkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("leval")
    @Expose
    private String leval;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoryData() {
    }

    /**
     * 
     * @param parent
     * @param image
     * @param leval
     * @param description
     * @param id
     * @param title
     * @param slug
     * @param status
     */
    public CategoryData(String id, String title, String slug, String parent, String leval, String description, String image, String status) {
        super();
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.parent = parent;
        this.leval = leval;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLeval() {
        return leval;
    }

    public void setLeval(String leval) {
        this.leval = leval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

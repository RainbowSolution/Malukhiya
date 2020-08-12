
package com.alpha.malukhiah.model.ContactUsPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactData {

    @SerializedName("contact_id")
    @Expose
    private Boolean contactId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getContactId() {
        return contactId;
    }

    public void setContactId(Boolean contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

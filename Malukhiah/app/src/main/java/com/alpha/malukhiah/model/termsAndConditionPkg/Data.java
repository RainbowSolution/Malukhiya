
package com.alpha.malukhiah.model.termsAndConditionPkg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("pg_descri")
    @Expose
    private String pgDescri;

    public String getPgDescri() {
        return pgDescri;
    }

    public void setPgDescri(String pgDescri) {
        this.pgDescri = pgDescri;
    }

}

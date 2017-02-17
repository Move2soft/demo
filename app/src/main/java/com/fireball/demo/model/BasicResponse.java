package com.fireball.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by UV on 31-Jan-17.
 */
public class BasicResponse {
    @SerializedName("output")
    @Expose
    public String output;
    @SerializedName("status")
    @Expose
    public boolean status;

    public BasicResponse withOutput(String output) {
        this.output = output;
        return this;
    }

    public BasicResponse withStatus(boolean status) {
        this.status = status;
        return this;
    }
}

package com.fireball.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by UV on 13-Feb-17.
 */
public class District {

    @SerializedName("output")
    @Expose
    public List<Output> output = null;
    @SerializedName("status")
    @Expose
    public boolean status;

    public District withOutput(List<Output> output) {
        this.output = output;
        return this;
    }

    public District withStatus(boolean status) {
        this.status = status;
        return this;
    }


    public class Output {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("state_Id")
        @Expose
        public int stateId;
        @SerializedName("state_Name")
        @Expose
        public String stateName;

        public Output withId(int id) {
            this.id = id;
            return this;
        }

        public Output withName(String name) {
            this.name = name;
            return this;
        }

        public Output withStateId(int stateId) {
            this.stateId = stateId;
            return this;
        }

        public Output withStateName(String stateName) {
            this.stateName = stateName;
            return this;
        }

    }
}

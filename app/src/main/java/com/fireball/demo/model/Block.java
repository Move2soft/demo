package com.fireball.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by UV on 13-Feb-17.
 */
public class Block {
    @SerializedName("output")
    @Expose
    public List<Output> output = null;
    @SerializedName("status")
    @Expose
    public boolean status;

    public Block withOutput(List<Output> output) {
        this.output = output;
        return this;
    }

    public Block withStatus(boolean status) {
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
        @SerializedName("district_Id")
        @Expose
        public int districtId;
        @SerializedName("state_Name")
        @Expose
        public String stateName;
        @SerializedName("district_Name")
        @Expose
        public String districtName;

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

        public Output withDistrictId(int districtId) {
            this.districtId = districtId;
            return this;
        }

        public Output withStateName(String stateName) {
            this.stateName = stateName;
            return this;
        }

        public Output withDistrictName(String districtName) {
            this.districtName = districtName;
            return this;
        }

    }


}

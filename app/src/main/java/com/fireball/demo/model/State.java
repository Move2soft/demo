package com.fireball.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MS on 2/3/2017.
 */
public class State {
    @SerializedName("output")
    @Expose
    public List<Output> output = null;
    @SerializedName("status")
    @Expose
    public boolean status;

    public State withOutput(List<Output> output) {
        this.output = output;
        return this;
    }

    public State withStatus(boolean status) {
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

        public Output withId(int id) {
            this.id = id;
            return this;
        }

        public Output withName(String name) {
            this.name = name;
            return this;
        }

    }
}

package com.fireball.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by UV on 31-Jan-17.
 */
public class Token {

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("expires_in")
    @Expose
    public int expiresIn;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("as:client_id")
    @Expose
    public String asClientId;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName(".issued")
    @Expose
    public String issued;
    @SerializedName(".expires")
    @Expose
    public String expires;

    public Token withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Token withTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public Token withExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Token withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Token withAsClientId(String asClientId) {
        this.asClientId = asClientId;
        return this;
    }

    public Token withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Token withIssued(String issued) {
        this.issued = issued;
        return this;
    }

    public Token withExpires(String expires) {
        this.expires = expires;
        return this;
    }
}

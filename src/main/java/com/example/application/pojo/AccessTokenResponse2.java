package com.example.application.pojo;

import lombok.Getter;
import lombok.Setter;

public class AccessTokenResponse2 {
    @Setter @Getter private String access_token;
    @Setter @Getter private String refresh_token;
    @Setter @Getter private String ims_user_id;
    @Setter @Getter private String token_type;
    @Setter @Getter private String expires_in;
    @Setter @Getter private String expiration;
    @Setter @Getter private String scope;
}

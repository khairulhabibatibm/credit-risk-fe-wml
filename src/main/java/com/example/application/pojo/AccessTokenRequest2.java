package com.example.application.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AccessTokenRequest2 {

    @Setter @Getter private String grantType;
    @Setter @Getter private String apikey;
    
}

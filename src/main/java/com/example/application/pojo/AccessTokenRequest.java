package com.example.application.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AccessTokenRequest {

    @Setter @Getter private String username;
    @Setter @Getter private String password;
    
}

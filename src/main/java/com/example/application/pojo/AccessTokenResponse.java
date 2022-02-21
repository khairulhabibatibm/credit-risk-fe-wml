package com.example.application.pojo;

import lombok.Getter;
import lombok.Setter;

public class AccessTokenResponse {
    @Setter @Getter private String _messageCode_;
    @Setter @Getter private String message;
    @Setter @Getter private String token;
}

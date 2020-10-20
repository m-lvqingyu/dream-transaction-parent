package com.dream.seata.auth.server.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Oauth2Token {

    private String token;

    private String refreshToken;

    private int expiresIn;

}

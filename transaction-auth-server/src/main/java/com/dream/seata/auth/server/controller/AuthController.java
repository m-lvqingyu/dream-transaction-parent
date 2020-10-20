package com.dream.seata.auth.server.controller;

import com.dream.seata.auth.server.model.Oauth2Token;
import com.dream.seata.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author Lv.QingYu
 */
@RestController
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * 获取Token
     *
     * @param principal
     * @param parameters grant_type:表示授权类型 client_id:客户端唯一标识 client_secret:客户端秘钥 username:用户名 password:密码
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/oauth/token")
    public Result postAccessToken(Principal principal,
                                  @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2Token oauth2Token = Oauth2Token
                .builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .build();
        return Result.success(oauth2Token);
    }

}

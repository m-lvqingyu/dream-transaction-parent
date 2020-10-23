package com.dream.seata.auth.server.service.impl;

import com.dream.seata.auth.server.model.OauthUserInfo;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.core.enums.UserInfoStatus;
import com.dream.seata.user.api.SysUserInfoApi;
import com.dream.seata.user.api.output.UserInfoOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;


/**
 * 自定义用户认证和授权
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserInfoApi sysUserInfoApi;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserInfoOutPut userInfoOutPut = sysUserInfoApi.loadUserByUsername(username);
        if (userInfoOutPut == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        userInfoOutPut.setClientId(clientId);
        OauthUserInfo oauthUserInfo = oauthUserInfoConversion(userInfoOutPut);
        if (!oauthUserInfo.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!oauthUserInfo.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!oauthUserInfo.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        } else if (!oauthUserInfo.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录!");
        }
        return oauthUserInfo;
    }

    private OauthUserInfo oauthUserInfoConversion(UserInfoOutPut userInfoOutPut){
        OauthUserInfo oauthUserInfo = new OauthUserInfo();
        oauthUserInfo.setUserUid(userInfoOutPut.getUserUid());
        oauthUserInfo.setUsername(userInfoOutPut.getUsername());
        oauthUserInfo.setPassword(AuthConstants.BCRYPT + userInfoOutPut.getPassword());
        oauthUserInfo.setEnabled(userInfoOutPut.getStatus().equals(UserInfoStatus.EFFECTIVE.getKey()));
        oauthUserInfo.setClientId(userInfoOutPut.getClientId());
        if (userInfoOutPut.getRoleList() != null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            userInfoOutPut.getRoleList().forEach(roleId -> authorities.add(new SimpleGrantedAuthority(String.valueOf(roleId))));
            oauthUserInfo.setAuthorities(authorities);
        }
        return oauthUserInfo;
    }

}

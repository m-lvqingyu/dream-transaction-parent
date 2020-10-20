package com.dream.seata.user.server.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.user.api.SysUserInfoApi;
import com.dream.seata.user.api.output.UserInfoOutPut;
import com.dream.seata.user.server.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lv.QingYu
 */
@RestController
public class SysUserInfoController implements SysUserInfoApi {

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Override
    public UserInfoOutPut loadUserByUsername(String username) {
        return sysUserInfoService.loadUserByUsername(username);
    }

    @Override
    public UserInfoOutPut loginUserInfoDetails(HttpServletRequest request) {
        String payload = request.getHeader(AuthConstants.JWT_PAYLOAD_KEY);
        JSONObject jsonObject = JSONUtil.parseObj(payload);
        Integer userId = jsonObject.getInt("id");
        return sysUserInfoService.loginUserInfoDetails(userId);
    }

}

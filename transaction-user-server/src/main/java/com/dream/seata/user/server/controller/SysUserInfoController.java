package com.dream.seata.user.server.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dream.seata.core.constant.AuthConstants;
import com.dream.seata.core.result.Result;
import com.dream.seata.user.api.SysUserInfoApi;
import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.api.output.UserInfoOutPut;
import com.dream.seata.user.server.service.SysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Lv.QingYu
 */
@RestController
public class SysUserInfoController implements SysUserInfoApi {

    @Autowired
    public SysUserInfoController(SysUserInfoService sysUserInfoService) {
        this.sysUserInfoService = sysUserInfoService;
    }

    private final SysUserInfoService sysUserInfoService;

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

    @Override
    public UserInfoOutPut userInfoDetailsById(Integer userId) {
        return sysUserInfoService.loginUserInfoDetails(userId);
    }

    @Override
    public UserInfoAmountOutPut userInfoAmountDetailsByUid(String userUid) {
        return sysUserInfoService.userInfoAmountDetailsByUid(userUid);
    }

    @Override
    public Result userInfoSettlement(String userUid, BigDecimal deductionAmount) {
        return sysUserInfoService.userInfoSettlement(userUid, deductionAmount);
    }


}

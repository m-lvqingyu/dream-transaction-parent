package com.dream.sharding.sphere.controller;

import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import com.dream.sharding.sphere.convert.UserInfoConvert;
import com.dream.sharding.sphere.dto.UserInfoDTO;
import com.dream.sharding.sphere.from.UserInfoFrom;
import com.dream.sharding.sphere.service.IUserAmountInfoService;
import com.dream.sharding.sphere.service.IUserInfoService;
import com.dream.sharding.sphere.vo.UserAmountInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户账户信息接口
 * </p>
 *
 * @author Lv.QingYu
 * @since 2021-02-01
 */
@Slf4j
@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class V1UserAmountInfoController {

    private final UserInfoConvert userInfoConvert;
    private final IUserInfoService userInfoService;
    private final IUserAmountInfoService userAmountInfoService;

    @GetMapping("amount/{id}")
    public Result amount(@PathVariable("id") Long id) {
        if (id == null || id < 0) {
            log.warn("[查询账户信息]-用户ID:{}不合法", id);
            return Result.custom(ResultCode.USER_REQUEST_PARAM_ERROR);
        }
        UserAmountInfoVO userAmountInfoVO = userAmountInfoService.getUserAmountById(id);
        return Result.success(userAmountInfoVO);
    }

    @PostMapping("info")
    public Result info(@RequestBody UserInfoFrom userInfoFrom) {
        UserInfoDTO userInfoDTO = userInfoConvert.convert(userInfoFrom);
        Result result = userInfoService.saveUser(userInfoDTO);
        return result;
    }

}


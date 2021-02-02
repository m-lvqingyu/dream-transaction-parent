package com.dream.sharding.sphere.controller;


import com.dream.sharding.sphere.entity.UserAmountInfo;
import com.dream.sharding.sphere.service.IUserAmountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAmountInfoController {

    private final IUserAmountInfoService userAmountInfoService;

    @GetMapping("/{id}")
    public UserAmountInfo get(@PathVariable("id") long id) {
        UserAmountInfo amountInfo = userAmountInfoService.getById(id);
        return amountInfo;
    }
    
}


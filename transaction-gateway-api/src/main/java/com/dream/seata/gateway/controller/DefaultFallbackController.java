package com.dream.seata.gateway.controller;

import com.dream.seata.core.result.Result;
import com.dream.seata.core.result.ResultCode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lv.QingYu
 */
@RestController
public class DefaultFallbackController {

    @GetMapping(value = "/defaultFallback", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result defaultFallbackForGet() {
        return Result.custom(ResultCode.SYSTEM_FUNCTION_DEGRADATION);
    }

    @PostMapping(value = "/defaultFallback", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result defaultFallbackForPost() {
        return Result.custom(ResultCode.SYSTEM_FUNCTION_DEGRADATION);
    }

}

package com.dream.seata.user.server.controller.outward;

import com.dream.seata.user.api.output.UserInfoAmountOutPut;
import com.dream.seata.user.server.service.at.UserAmountInfoForAtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lv.QingYu
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/outward/user")
public class UserAmountInfoOutwardController {

    private final UserAmountInfoForAtService userAmountInfoForAtService;

    @GetMapping("amountDetails/{userId}")
    public UserInfoAmountOutPut amountDetails(@PathVariable("userId") String userUid) {
        return userAmountInfoForAtService.amountDetails(userUid);
    }
}

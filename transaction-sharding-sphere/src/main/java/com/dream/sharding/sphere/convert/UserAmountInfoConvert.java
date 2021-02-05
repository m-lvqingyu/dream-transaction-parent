package com.dream.sharding.sphere.convert;

import cn.hutool.core.bean.BeanUtil;
import com.dream.sharding.sphere.entity.UserAmountInfo;
import com.dream.sharding.sphere.vo.UserAmountInfoVO;
import org.springframework.stereotype.Component;

/**
 * @author Lv.QingYu
 */
@Component
public class UserAmountInfoConvert {

    /**
     * @param userAmountInfo
     * @return
     */
    public UserAmountInfoVO userAmountConvert(UserAmountInfo userAmountInfo) {
        UserAmountInfoVO userAmountInfoVO = new UserAmountInfoVO();
        BeanUtil.copyProperties(userAmountInfo, userAmountInfoVO);
        return userAmountInfoVO;

    }
}

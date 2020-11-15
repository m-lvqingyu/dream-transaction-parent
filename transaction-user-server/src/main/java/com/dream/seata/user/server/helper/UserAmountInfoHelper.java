package com.dream.seata.user.server.helper;

import com.dream.seata.user.server.dao.UserAmountInfoMapper;
import com.dream.seata.user.server.entity.UserAmountInfo;
import com.dream.seata.user.server.entity.UserAmountInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lv.QingYu
 */
@Slf4j
@Component
public class UserAmountInfoHelper {

    @Autowired
    private UserAmountInfoMapper userAmountInfoMapper;

    /**
     * 根据用户UID获取用户账户信息
     *
     * @param userUid 用户唯一ID
     * @return
     */
    public UserAmountInfo getUserAmountInfo(String userUid) {
        UserAmountInfoExample example = new UserAmountInfoExample();
        example.createCriteria().andUserUidEqualTo(userUid);
        example.setLimit(1);
        List<UserAmountInfo> userAmountInfoList = userAmountInfoMapper.selectByExample(example);
        if (userAmountInfoList == null || userAmountInfoList.isEmpty()) {
            return null;
        }
        return userAmountInfoList.get(0);
    }

    /**
     * 根据用户唯一ID和版本号，更新用户账户信息
     *
     * @param userUid              用户唯一ID
     * @param version              版本号
     * @param updateUserAmountInfo 需要更新的内容
     * @return
     */
    public int updateUserAmountInfo(String userUid, Integer version, UserAmountInfo updateUserAmountInfo) {
        UserAmountInfoExample example = new UserAmountInfoExample();
        example.createCriteria().andUserUidEqualTo(userUid).andVersionEqualTo(version);
        int result = userAmountInfoMapper.updateByExampleSelective(updateUserAmountInfo, example);
        return result;
    }

}

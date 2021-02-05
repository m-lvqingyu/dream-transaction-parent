package com.dream.seata.core.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author Lv.QingYu
 */
public class ServerUidUtils {

    public static String findUid(long workerId, long dataCenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
        long nextId = snowflake.nextId();
        String uid = String.valueOf(nextId);
        return uid;
    }
}

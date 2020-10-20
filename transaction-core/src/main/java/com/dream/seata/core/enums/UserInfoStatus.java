package com.dream.seata.core.enums;

/**
 * @author Lv.QingYu
 */
public enum UserInfoStatus {

    EFFECTIVE(0, "有效|未删除"),
    INVALID(1, "无效|已删除");

    private int key;
    private String value;

    UserInfoStatus(int key, String value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

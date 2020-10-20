package com.dream.seata.user.api.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Lv.QingYu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoOutPut {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户所属clientID
     */
    private String clientId;

    /**
     * 用户所属角色集合
     */
    private List<Integer> roleList;

}

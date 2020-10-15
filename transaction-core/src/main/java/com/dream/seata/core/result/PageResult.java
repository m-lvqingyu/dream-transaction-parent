package com.dream.seata.core.result;

import lombok.Data;


@Data
public class PageResult<T> extends Result {

    private long total;

    public static <T> PageResult<T> success(T data, Long total) {
        PageResult<T> pageResult = new PageResult();
        pageResult.setCode(ResultCode.SUCCESS.getCode());
        pageResult.setMsg(ResultCode.SUCCESS.getMsg());
        pageResult.setData(data);
        pageResult.setTotal(total);
        return pageResult;
    }

}

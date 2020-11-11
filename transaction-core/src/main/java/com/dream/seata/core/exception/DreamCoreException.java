package com.dream.seata.core.exception;

import com.dream.seata.core.result.ResultCode;

/**
 * @author Lv.QingYu
 */
public class DreamCoreException extends RuntimeException {

    private ResultCode resultCode;

    public DreamCoreException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public DreamCoreException(String message) {
        super(message);
    }

    public DreamCoreException(Throwable cause) {
        super(cause);
    }

    public DreamCoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultCode getErrorCode() {
        return resultCode;
    }
}

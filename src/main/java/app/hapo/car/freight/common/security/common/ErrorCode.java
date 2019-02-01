package app.hapo.car.freight.common.security.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * freight
 * Class: ErrorCode
 * Created by hapo on 2019-02-01.
 * Description: ErrorCode
 */
public enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11);
    
    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}

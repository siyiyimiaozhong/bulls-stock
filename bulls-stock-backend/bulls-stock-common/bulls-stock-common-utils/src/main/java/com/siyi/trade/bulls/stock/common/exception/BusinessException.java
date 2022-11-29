package com.siyi.trade.bulls.stock.common.exception;

import com.siyi.trade.bulls.stock.common.exception.constants.IErrorCodeEnum;
import lombok.Getter;

/**
 * @Author: siyiyimiaozhong
 * @Description: 自定义业务异常
 * @Project: bulls-stock-backend
 * @Package: com.siyi.trade.bulls.stock.common.exception
 * @ClassName: BusinessException.java
 * @CreateTime: 2022-11-13  16:27
 * @Version: 1.0
 */
@Getter
public class BusinessException extends AbstractException {

    /**
     *
     */
    private static final long serialVersionUID = -1L;

    /**
     * 错误码
     */
    private IErrorCodeEnum errorCodeEnum;


    public BusinessException(IErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getCode() + ":" + errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public IErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

}


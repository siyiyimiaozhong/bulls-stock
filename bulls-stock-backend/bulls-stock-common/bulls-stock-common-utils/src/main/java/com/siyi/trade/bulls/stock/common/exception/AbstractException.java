package com.siyi.trade.bulls.stock.common.exception;

/**
 * @Author: siyiyimiaozhong
 * @Description: 抽象异常类
 * @Project: bulls-stock-backend
 * @Package: com.siyi.trade.bulls.stock.common.exception
 * @ClassName: AbstractException.java
 * @CreateTime: 2022-11-13  16:26
 * @Version: 1.0
 */
public abstract class AbstractException extends Exception {
    private static final long serialVersionUUID = -1L;

    public AbstractException(String message) {
        super(message);
    }
}
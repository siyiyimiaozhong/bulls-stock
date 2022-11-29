package com.siyi.bulls.stock.common.web.vo;

import com.siyi.bulls.stock.common.exception.constants.ApplicationErrorCodeEnum;
import com.siyi.bulls.stock.common.exception.constants.IErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: siyiyimiaozhong
 * @Description: 统一API接口数据返回对象
 * @Project: bulls-stock-backend
 * @Package: com.siyi.bulls.stock.common.web.vo
 * @ClassName: Result.java
 * @CreateTime: 2022-11-13  23:35
 * @Version: 1.0
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 结果码
     */
    private String code = ApplicationErrorCodeEnum.SUCCESS.getCode();

    /**
     * 结果信息
     */
    private String msg = ApplicationErrorCodeEnum.SUCCESS.getMessage();

    /**
     * 扩展对象(放置分页信息、其他信息等)
     */
    private Object extendData;

    /**
     * 返回结果的数据对象
     */
    private T data;

    public Result() {
    }

    public Result(String code) {
        this.code = code;
    }

    public Result(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Result(IErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
    }

    /**
     * 错误信息封装方法
     *
     * @param errorCodeEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(IErrorCodeEnum errorCodeEnum) {
        return new Result<T>(errorCodeEnum);
    }

    public static <T> Result<T> sysError(String exceptionMsg) {
        Result error = new Result<T>(ApplicationErrorCodeEnum.FAILURE);
        error.setMsg(error.getMsg() + ":" + exceptionMsg);
        return error;
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>(code, msg);
        result.setData(data);
        return result;
    }

    /**
     * 用于参数校验提示信息
     *
     * @param exceptionMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> validError(String exceptionMsg) {
        Result error = new Result<T>(ApplicationErrorCodeEnum.PARAMS_NOT_VALID);
        error.setMsg(error.getMsg() + ":" + exceptionMsg);
        return error;
    }

    public static Result<Void> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success(data, null);
    }

    public static <T> Result<T> success(T data, Object extendData) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setExtendData(extendData);
        return result;
    }

    public Boolean isSuccess() {
        return ApplicationErrorCodeEnum.SUCCESS.getCode().equals(getCode());
    }
}

package com.cl.cloud.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author LC
 * @created by LC on 2022/12/19 17:38
 * @description
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> ResultResponse<T> ok() {
        return restResult(null, CommonConst.SUCCESS, null);
    }

    public static <T> ResultResponse<T> ok(T data) {
        return restResult(data, CommonConst.SUCCESS, null);
    }

    public static <T> ResultResponse<T> ok(T data, String msg) {
        return restResult(data, CommonConst.SUCCESS, msg);
    }

    public static <T> ResultResponse<T> failed() {
        return restResult(null, CommonConst.FAIL, null);
    }

    public static <T> ResultResponse<T> failed(String msg) {
        return restResult(null, CommonConst.FAIL, msg);
    }

    public static <T> ResultResponse<T> failed(T data) {
        return restResult(data, CommonConst.FAIL, null);
    }

    public static <T> ResultResponse<T> failed(T data, String msg) {
        return restResult(data, CommonConst.FAIL, msg);
    }


    private static <T> ResultResponse<T> restResult(T data, int code, String msg) {
        ResultResponse<T> apiResult = new ResultResponse<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}


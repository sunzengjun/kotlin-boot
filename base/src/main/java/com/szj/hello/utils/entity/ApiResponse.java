package com.szj.hello.utils.entity;


import com.szj.hello.utils.constants.CommonConstants;
import java.io.Serializable;

/**
 * Created by sunzengjun on 2018/2/28.
 */
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1717717987255349409L;
    protected String code = CommonConstants.SUCCESS_CODE;
    protected String msg = CommonConstants.SUCCESS_MESSAGE;
    protected T data;

    public boolean isSuccess() {
        return CommonConstants.SUCCESS_CODE.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

package com.offcn.dycommon.enums;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.dycommon.enums
 * @date 2020/11/30 19:29
 * @Copyright © 2020-2021 中公教育
 */
public enum ResponseCodeEnume {

    SUCCESS(0,"操作成功"),
    FAIL(1,"服务器异常"),
    NOT_FOUNT(404,"资源未找到"),
    NOT_AUTHED(403,"无权限,访问拒绝"),
    PARAM_INVAILD(400,"提交参数非法");


    private Integer code; //响应码
    private String msg; //话术

    ResponseCodeEnume(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

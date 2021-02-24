package com.offcn.user.enums;

/**
 * @Auther: lhq
 * @Date: 2020/12/1 09:16
 * @Description: 用户异常枚举
 */
public enum UserExceptionEnum {
    LOGINACCT_EXIST(1, "登录账号已经存在"),
    EMAIL_EXIST(2, "邮箱已经存在"),
    LOGINACCT_LOCKED(3, "账号已经被锁定");


    private Integer code;
    private String msg;

    private UserExceptionEnum(Integer code, String msg) {
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

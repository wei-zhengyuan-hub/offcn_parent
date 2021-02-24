package com.offcn.dycommon.enums;

/**
 * @author wzy
 * @version 0.0.3
 * @description OrderStatusEnum
 * @since 2020/12/3 15:22
 * 订单枚举类
 */
public enum OrderStatusEnum {

    UNPAY(0, "未支付"),
    CANCEL(1, "已取消"),
    PAYED(2, "支付成功"),
    WAITING(3, "等待发货"),
    SEND(4, "已发货"),
    SENDED(5, "已送达"),
    SUCCESS(6, "交易完成"),
    FAIL(7, "交易未完成");

    private Integer code;
    private String status;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    private OrderStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }


}

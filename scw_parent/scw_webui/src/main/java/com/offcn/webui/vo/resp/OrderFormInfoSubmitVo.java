package com.offcn.webui.vo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wzy
 * @version 0.0.3
 * @description OrderFormInfoSubmitVo
 * @since 2020/12/9 16:41
 */
@Data
public class OrderFormInfoSubmitVo implements Serializable {

    //收货地址id
    private String address;
    // 0代表不开发票  1-代表开发票
    private Byte invoice;
    //发票抬头
    private String invoictitle;
    //订单的备注
    private String remark;


    private Integer rtncount;
    private String accessToken;
    private Integer projectid;
    private Integer returnid;


}

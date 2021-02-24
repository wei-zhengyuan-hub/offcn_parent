package com.offcn.webui.vo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wzy
 * @version 0.0.3
 * @description UserAddressVo
 * @since 2020/12/9 16:06
 */
@Data
public class UserAddressVo implements Serializable {

    //地址id
    private Integer id;

    //会员地址
    private String address;


}

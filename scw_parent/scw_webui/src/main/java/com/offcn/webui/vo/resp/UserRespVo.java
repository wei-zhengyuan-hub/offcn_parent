package com.offcn.webui.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: lhq
 * @Date: 2020/12/1 10:28
 * @Description: 用户响应的封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户登录成功后响应VO") //Swagger的注解
public class UserRespVo implements Serializable {
    @ApiModelProperty("访问令牌，请妥善保管，以后每次请求都要带上")
    private String accessToken;//访问令牌
    private String loginacct; //存储手机号
    private String username;
    private String email;
    private String authstatus;
    private String usertype;
    private String realname;
    private String cardnum;
    private String accttype;
}

package com.offcn.webui.service;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.config.FeignConfig;
import com.offcn.webui.service.impl.UserServiceFeignException;
import com.offcn.webui.vo.resp.UserAddressVo;
import com.offcn.webui.vo.resp.UserRespVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "SCWUSER",configuration = FeignConfig.class,fallback = UserServiceFeignException.class)
public interface UserServiceFeign {

    /**
     * 用户登录
     * @param loginAcct 用户名
     * @param password 密码
     * @return
     *  //注意：Feign远程服务调用接口时，如果不使用占位符的传值方式，并且参数超过两个以上，需要加@RequestParam
     */
    @GetMapping("/user/login")
    public AppResponse<UserRespVo> login(@RequestParam(name = "loginAcct") String loginAcct,@RequestParam(name = "password") String password);

    /**
     * 用户信息
     * @param id 用户id
     * @return
     */
    @GetMapping("/user/findMemberById/{id}")
    public AppResponse<UserRespVo> findMemberById(@PathVariable(name = "id") Integer id);

    /**
     * 用户地址
     * @param accessToken 登录令牌
     * @return
     */
    @GetMapping("/user/findAddressList")
    public AppResponse<List<UserAddressVo>> findAddressList(@RequestParam("accessToken") String accessToken);
}

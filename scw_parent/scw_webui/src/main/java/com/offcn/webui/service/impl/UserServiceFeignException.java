package com.offcn.webui.service.impl;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.service.UserServiceFeign;
import com.offcn.webui.vo.resp.UserAddressVo;
import com.offcn.webui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description UserServiceFeignException
 * @since 2020/12/7 17:04
 */
@Slf4j
@Component
public class UserServiceFeignException implements UserServiceFeign {

    @Override
    public AppResponse<UserRespVo> login(String loginAcct, String password) {
        AppResponse<UserRespVo> fail = AppResponse.fail(null);
        fail.setMsg("远程服务调用失败【用户登录】");
        return fail;
    }

    @Override
    public AppResponse<UserRespVo> findMemberById(Integer id) {
        AppResponse<UserRespVo> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用服务失败【获取用户信息】");
        return appResponse;
    }

    @Override
    public AppResponse<List<UserAddressVo>> findAddressList(String accessToken) {
        AppResponse<List<UserAddressVo>> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用服务失败【获取用户地址信息】");
        return appResponse;
    }
}

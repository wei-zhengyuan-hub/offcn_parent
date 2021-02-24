package com.offcn.user.controller;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.user.pojo.TMemberAddress;
import com.offcn.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description UserInfoController
 * @since 2020/12/3 18:36
 *
 */
@Api(tags = "获取会员信息、更新个人信息、获取用户收货地址")
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户收货地址")
    @GetMapping("/findAddressList")
    @ApiImplicitParam(name = "accessToken",value = "用户访问令牌")
    public AppResponse<List<TMemberAddress>> findAddressList(String accessToken){

        String memeberId = redisTemplate.opsForValue().get(accessToken);
        if (!StringUtils.isEmpty(memeberId)) {
            List<TMemberAddress> addressList = userService.findAddressList(Integer.parseInt(memeberId));
            return AppResponse.ok(addressList);
        }
        return AppResponse.fail(null);
    }

}

package com.offcn.order.controller;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.order.pojo.TOrder;
import com.offcn.order.service.OrderService;
import com.offcn.order.vo.req.OrderInfoSubmitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzy
 * @version 0.0.3
 * @description OrderController
 * @since 2020/12/3 16:42
 */
@Api(tags = "保存订单")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    @ApiOperation("订单保存")
    @PostMapping("/createOrder")
    public AppResponse<TOrder> createOrder(@RequestBody OrderInfoSubmitVo vo){

        //1.判断用户是否登录
        String memberId = redisTemplate.opsForValue().get(vo.getAccessToken());
        if (StringUtils.isEmpty(memberId)){
            AppResponse response = new AppResponse();
            response.setMsg("没有操作权限，请先登录");
            return response;
        }
        //2.执行保存
        try {
            TOrder tOrder = orderService.saveOrder(vo);
            return AppResponse.ok(tOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return AppResponse.fail(null);
        }
    }


}

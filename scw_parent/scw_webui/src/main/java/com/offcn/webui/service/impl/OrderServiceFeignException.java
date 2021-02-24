package com.offcn.webui.service.impl;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.service.OrderServiceFeign;
import com.offcn.webui.vo.resp.OrderFormInfoSubmitVo;
import com.offcn.webui.vo.resp.TOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wzy
 * @version 0.0.3
 * @description OrderServiceFeign
 * @since 2020/12/9 16:44
 */
@Component
@Slf4j
public class OrderServiceFeignException implements OrderServiceFeign {


    @Override
    public AppResponse<TOrder> createOrder(OrderFormInfoSubmitVo vo) {
        AppResponse<TOrder> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用服务【订单创建】");
        return appResponse;
    }
}

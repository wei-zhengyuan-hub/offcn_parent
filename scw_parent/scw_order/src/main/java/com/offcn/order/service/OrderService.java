package com.offcn.order.service;

import com.offcn.order.pojo.TOrder;
import com.offcn.order.vo.req.OrderInfoSubmitVo;

public interface OrderService {

    /**
     * 保存订单方法
     * @param vo 订单信息提交
     * @return 订单数据
     */
    TOrder saveOrder(OrderInfoSubmitVo vo);

}

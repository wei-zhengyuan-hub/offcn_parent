package com.offcn.order.service.impl;

import com.offcn.dycommon.enums.OrderStatusEnum;
import com.offcn.dycommon.response.AppResponse;
import com.offcn.order.mapper.TOrderMapper;
import com.offcn.order.pojo.TOrder;
import com.offcn.order.service.OrderService;
import com.offcn.order.service.ProjectInfoServiceFeign;
import com.offcn.order.vo.req.OrderInfoSubmitVo;
import com.offcn.order.vo.resp.TReturn;
import com.offcn.utils.AppDateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author wzy
 * @version 0.0.3
 * @description OrderServiceImpl
 * @since 2020/12/3 16:17
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private ProjectInfoServiceFeign projectServiceFeign;
    @Resource
    private TOrderMapper orderMapper;

    /**
     * 保存订单方法
     * @param vo 订单信息提交
     * @return
     */
    @Override
    public TOrder saveOrder(OrderInfoSubmitVo vo) {
        //1.创建订单对象，通过页面传回的accesstoken获取用户id
        TOrder order = new TOrder();
        String memberId = redisTemplate.opsForValue().get(vo.getAccessToken());

            order.setMemberid(Integer.parseInt(memberId));

            //2.复制对象，UUID产生订单id
            BeanUtils.copyProperties(vo,order);
            String code = UUID.randomUUID().toString().replace("-","");
            order.setOrdernum(code);//订单编号
            order.setStatus(OrderStatusEnum.UNPAY.getCode()+"");//支付状态 未支付
            order.setInvoice(vo.getInvoice().toString());//发票类型
            order.setCreatedate(AppDateUtils.getFormatTime());//订单创建时间

            //3.服务远程调用，查询增量列表
            AppResponse<List<TReturn>> response = projectServiceFeign.getReturnList(vo.getProjectid());
            List<TReturn> returns = response.getData();
        //if (!CollectionUtils.isEmpty(returns)) {
            //默认取得第一个回报增量信息
            TReturn tReturn = returns.get(0);
            //支持金额  价格*数量+运费
            order.setMoney((vo.getRtncount()*tReturn.getSupportmoney()+tReturn.getFreight()));
        //}
        //4.保存订单
            orderMapper.insertSelective(order);
            return order;
    }
}

package com.offcn.webui.controller;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.service.OrderServiceFeign;
import com.offcn.webui.vo.resp.OrderFormInfoSubmitVo;
import com.offcn.webui.vo.resp.ReturnPayConfirmVo;
import com.offcn.webui.vo.resp.TOrder;
import com.offcn.webui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author wzy
 * @version 0.0.3
 * @description OrderController
 * @since 2020/12/9 16:51
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderServiceFeign orderServiceFeign;

    @RequestMapping("/save")
    public String orderPay(OrderFormInfoSubmitVo vo, HttpSession session){

        //从session中获取用户对象
        UserRespVo userRespVo = (UserRespVo) session.getAttribute("sessionMember");
        //如果用户没登录
        if (userRespVo==null){
            return "redirect:/login.html";
        }
        //如果用户登录获取票据
        String accessToken = userRespVo.getAccessToken();
        vo.setAccessToken(accessToken);
        //获取回报增量对象信息
        ReturnPayConfirmVo returnPayConfirmVo = (ReturnPayConfirmVo) session.getAttribute("returnConfirmSession");
        if (returnPayConfirmVo==null){
            return "redirect:/login.html";
        }
        vo.setProjectid(returnPayConfirmVo.getProjectId());
        vo.setReturnid(returnPayConfirmVo.getId());
        vo.setRtncount(returnPayConfirmVo.getNum());

        AppResponse<TOrder> order = orderServiceFeign.createOrder(vo);
        TOrder data = order.getData();

        //下单成功，打印相关信息待处理
        log.info("orderNum:{}",data.getOrdernum());
        log.info("money:{}",data.getMoney());
        log.info("orderName:{}",returnPayConfirmVo.getProjectName());
        log.info("remark:{}",data.getRemark());

        return "member/minecrowdfunding";
    }

}

package com.offcn.webui.controller;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.service.ProjectServiceFeign;
import com.offcn.webui.service.UserServiceFeign;
import com.offcn.webui.vo.resp.ProjectDetailVo;
import com.offcn.webui.vo.resp.ReturnPayConfirmVo;
import com.offcn.webui.vo.resp.UserAddressVo;
import com.offcn.webui.vo.resp.UserRespVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description ProjectController
 * @since 2020/12/9 15:11
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectServiceFeign projectServiceFeign;
    @Resource
    private UserServiceFeign userServiceFeign;

    /**
     * 项目详情
     * @param id 项目id
     * @return
     */
    @RequestMapping("/projectInfo")
    public String index(Integer id, Model model, HttpSession session){
        AppResponse<ProjectDetailVo> projectInfo = projectServiceFeign.findProjectInfo(id);
        ProjectDetailVo data = projectInfo.getData();
        model.addAttribute("DetailVo",data);
        session.setAttribute("DetailVo",data);
        return "project/project";
    }


    /**
     * 项目回报信息展示
     * @param projectId 项目id
     * @param returnId 项目增量id
     * @return
     */
    @RequestMapping("/confirm/returns/{projectId}/{returnId}")
    public String findReturnInfo(@PathVariable("projectId") Integer projectId,@PathVariable("returnId") Integer returnId, Model model, HttpSession session){

        //从session中获取项目详情
        ProjectDetailVo projectDetailVo = (ProjectDetailVo) session.getAttribute("DetailVo");
        //获取项目增量信息
        AppResponse<ReturnPayConfirmVo> aReturn = projectServiceFeign.findReturn(returnId);
        ReturnPayConfirmVo data = aReturn.getData();

        data.setProjectId(projectId);
        //设置项目发起名称
        data.setProjectName(projectDetailVo.getName());
        //根据项目发起方的id获取项目的发起方名称
        AppResponse<UserRespVo> memberById = userServiceFeign.findMemberById(projectDetailVo.getMemberid());
        UserRespVo userRespVo = memberById.getData();
        //设置项目发起方的真是名称
        data.setMemberName(userRespVo.getRealname());
        //添加项目信息到session
        session.setAttribute("returnConfirm",data);
        //添加项目回报信息到model
        model.addAttribute("returnConfirm",data);
        return "project/pay-step-1";

    }

    /**
     * 跳转支付确认页面
     * @param num 回报增量数量
     * @return
     */
    @RequestMapping("/confirm/order/{num}")
    public String confirOrder(@PathVariable("num") Integer num,Model model,HttpSession session){

        //session中获取用户信息
        UserRespVo userRespVo = (UserRespVo) session.getAttribute("sessionMember");
        //如果用户未登录
        if (userRespVo==null){
            session.setAttribute("preUrl","project/confirm/order/"+num);
            return "redirect:/login.html";
        }
        //用户已登录
        String accessToken = userRespVo.getAccessToken();
        //获取地址列表
        AppResponse<List<UserAddressVo>> addressList = userServiceFeign.findAddressList(accessToken);
        List<UserAddressVo> data = addressList.getData();
        //把地址存到request域中
        model.addAttribute("addresses",data);
        //将回报增量数量添加到回报增量对象中
        ReturnPayConfirmVo returnPayConfirmVo = (ReturnPayConfirmVo) session.getAttribute("returnConfirm");
        returnPayConfirmVo.setNum(num);

        returnPayConfirmVo.setTotalPrice(new BigDecimal(num*returnPayConfirmVo.getSupportmoney()+returnPayConfirmVo.getFreight()));
        //再把对象存回session
        session.setAttribute("returnConfirmSession",returnPayConfirmVo);
        return "project/pay-step-2";
    }


}

package com.offcn.webui.controller;

import com.alibaba.fastjson.JSON;
import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.service.ProjectServiceFeign;
import com.offcn.webui.service.UserServiceFeign;
import com.offcn.webui.vo.resp.ProjectVo;
import com.offcn.webui.vo.resp.UserRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @version 0.0.3
 * @description DispathcherController
 * @since 2020/12/7 16:15
 */
@Controller
public class DispathcherController {

    @Resource
    private UserServiceFeign userServiceFeign;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private ProjectServiceFeign projectServiceFeign;

    /**
     * 首页加载项目列表
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String toIndex(Model model) {

        //从redis中读取项目信息
        String projectList = redisTemplate.opsForValue().get("projectList");
        List<ProjectVo> projectVos = JSON.parseArray(projectList, ProjectVo.class);
        //没有再执行远程调用
        if (projectVos == null) {
            projectVos = projectServiceFeign.findAllProject().getData();
            redisTemplate.opsForValue().set("projectList", JSON.toJSONString(projectVos),6, TimeUnit.HOURS);
        }
        //返回数据
        model.addAttribute("projectList", projectVos);
        return "index";
    }

    /**
     * 登录
     * @param loginacct 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(String loginacct, String password, HttpSession session) {
        //调用远程服务
        AppResponse<UserRespVo> login = userServiceFeign.login(loginacct, password);
        //获取响应数据
        UserRespVo data = login.getData();
        if (data == null) {
            return "redirect:/login.html";//用户不存在，重定向到登录页面
        }
        //用户存在登录成功，把信息存储到session
        session.setAttribute("sessionMember", data);
        String preUrl = (String) session.getAttribute("preUrl");//获取url前缀
        //如果url前缀不存在
        if (StringUtils.isEmpty(preUrl)) {
            return "redirect:/";
        }
        return "redirect:/" + preUrl;
    }




}

package com.offcn.webui.service.impl;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.service.ProjectServiceFeign;
import com.offcn.webui.vo.resp.ProjectDetailVo;
import com.offcn.webui.vo.resp.ProjectVo;
import com.offcn.webui.vo.resp.ReturnPayConfirmVo;
import com.offcn.webui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description ProjectServiceFeignException
 * @since 2020/12/9 11:02
 */
@Component
@Slf4j
public class ProjectServiceFeignException implements ProjectServiceFeign {


    @Override
    public AppResponse<List<ProjectVo>> findAllProject() {
        AppResponse<List<ProjectVo>> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用服务失败【查询项目列表】");
        return appResponse;
    }

    @Override
    public AppResponse<ProjectDetailVo> findProjectInfo(Integer projectId) {
        AppResponse<ProjectDetailVo> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用服务失败【查询项目详情】");
        return appResponse;
    }

    @Override
    public AppResponse<ReturnPayConfirmVo> findReturn(Integer returnId) {
        AppResponse<ReturnPayConfirmVo> appResponse = AppResponse.fail(null);
        appResponse.setMsg("远程调用服务失败【项目回报信息】");
        return appResponse;
    }


}

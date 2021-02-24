package com.offcn.webui.service;


import com.offcn.dycommon.response.AppResponse;
import com.offcn.webui.config.FeignConfig;
import com.offcn.webui.service.impl.ProjectServiceFeignException;
import com.offcn.webui.vo.resp.ProjectDetailVo;
import com.offcn.webui.vo.resp.ProjectVo;
import com.offcn.webui.vo.resp.ReturnPayConfirmVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "SCWPROJECT",configuration = FeignConfig.class,fallback = ProjectServiceFeignException.class)
public interface ProjectServiceFeign {

    /**
     * 获取项目列表
     * @return
     */
    @GetMapping("/project/all")
    public AppResponse<List<ProjectVo>> findAllProject();


    /**
     * 查询项目详情
     * @param projectId 项目id
     * @return
     */
    @GetMapping("/project/findProjectInfo/{projectId}")
    public AppResponse<ProjectDetailVo> findProjectInfo(@PathVariable("projectId") Integer projectId);

    /**
     * 项目增量信息
     * @param returnId 回报id
     * @return
     */
    @GetMapping("/project/returns/info/{returnId}")
    public AppResponse<ReturnPayConfirmVo> findReturn(@PathVariable("returnId") Integer returnId);




}

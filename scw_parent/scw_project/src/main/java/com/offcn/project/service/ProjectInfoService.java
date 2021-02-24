package com.offcn.project.service;


import com.offcn.project.pojo.*;

import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description ProjectInfoService
 * @since 2020/12/3 15:25
 */
public interface ProjectInfoService {

    /**
     * 获取项目回报列表
     * @param projrctId 项目id
     * @return
     */
    public List<TReturn> getReturnList(Integer projrctId);

    /**
     * 获取系统所有项目
     * @return
     */
    public List<TProject> findProjectList();

    /**
     * 获取项目图片
     * @param id 项目id
     * @return
     */
    public List<TProjectImages> getProjectImages(Integer id);

    /**
     * 获取项目详细信息
     * @param projectId 项目id
     * @return
     */
    public TProject findProjectInfo(Integer projectId);

    /**
     * 获取所有项目标签
     * @return
     */
    public List<TTag> findAllTag();

    /**
     * 获取所有项目类别
     * @return
     */
    public List<TType> findAllType();

    /**
     * 获取项目增量信息
     * @param returnId 增量信息id
     * @return
     */
    public TReturn findReturnInfo(Integer returnId);
}

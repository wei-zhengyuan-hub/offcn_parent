package com.offcn.project.service.impl;


import com.offcn.project.mapper.*;
import com.offcn.project.pojo.*;
import com.offcn.project.service.ProjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description ProjectInfoServiceImpl
 * @since 2020/12/3 15:27
 */
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {

    @Resource
    private TReturnMapper returnMapper;
    @Resource//注入接口，autowired注入类
    private TProjectMapper projectMapper;
    @Resource
    private TProjectImagesMapper imagesMapper;
    @Resource
    private TTagMapper tagMapper;
    @Resource
    private TTypeMapper typeMapper;


    /**
     * 获取项目的回报列表
     * @param projrctId 项目id
     * @return
     */
    @Override
    public List<TReturn> getReturnList(Integer projrctId) {
        TReturnExample tReturnExample = new TReturnExample();
        TReturnExample.Criteria criteria = tReturnExample.createCriteria();
        criteria.andProjectidEqualTo(projrctId);
        return returnMapper.selectByExample(tReturnExample);
    }

    /**
     * 查询所有项目
     * @return
     */
    @Override
    public List<TProject> findProjectList() {
        return projectMapper.selectByExample(null);
    }

    /**
     * 获取项目图片
     * @param id 项目id
     * @return
     */
    @Override
    public List<TProjectImages> getProjectImages(Integer id) {
        TProjectImagesExample tProjectImagesExample = new TProjectImagesExample();
        TProjectImagesExample.Criteria criteria = tProjectImagesExample.createCriteria();
        criteria.andProjectidEqualTo(id);
        return imagesMapper.selectByExample(tProjectImagesExample);
    }

    /**
     * 获取项目详细信息
     * @param projectId 项目id
     * @return
     */
    @Override
    public TProject findProjectInfo(Integer projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    /**
     * 获取所有项目标签
     * @return
     */
    @Override
    public List<TTag> findAllTag() {
        return tagMapper.selectByExample(null);
    }

    /**
     * 获取所有项目类别
     * @return
     */
    @Override
    public List<TType> findAllType() {

        return typeMapper.selectByExample(null);
    }

    /**
     * 获取增量信息
     * @param returnId 增量信息id
     * @return
     */
    @Override
    public TReturn findReturnInfo(Integer returnId) {
        return returnMapper.selectByPrimaryKey(returnId);
    }

}

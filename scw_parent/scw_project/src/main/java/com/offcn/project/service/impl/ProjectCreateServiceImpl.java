package com.offcn.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.offcn.dycommon.enums.ProjectStatusEnume;
import com.offcn.project.contants.ProjectConstant;
import com.offcn.project.enums.ProjectImageTypeEnume;
import com.offcn.project.mapper.*;
import com.offcn.project.pojo.*;
import com.offcn.project.service.ProjectCreateService;
import com.offcn.project.vo.req.ProjectRedisStorageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private TProjectMapper projectMapper;

    @Resource
    private TProjectImagesMapper projectImagesMapper;

    @Resource
    private TProjectTagMapper projectTagMapper;

    @Resource
    private TProjectTypeMapper projectTypeMapper;

    @Resource
    private TReturnMapper tReturnMapper;


    /**
     * 初始化项目
     * @param memberId 会员id
     * @return token 项目令牌
     */
    @Override
    public String initCreateProject(Integer memberId) {

        String token = UUID.randomUUID().toString().replace("-", "");//项目的令牌
        //项目的临时对象
        ProjectRedisStorageVo vo = new ProjectRedisStorageVo();
        vo.setMemberid(memberId);
        //把对象转成字符串
        String jsonString = JSON.toJSONString(vo);
        //存到redis
        stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROFECT_PREFIX+token,jsonString);

        return token;
    }

    /**
     * 保存项目信息
     * @param auth  项目状态信息
     * @param project  项目全部信息
     */
    @Override
    public void saveProjectInfo(ProjectStatusEnume auth, ProjectRedisStorageVo project) {

        //1.项目信息保存
        TProject tProject = new TProject();
        BeanUtils.copyProperties(project,tProject);
        tProject.setStatus(auth.getCode()+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        tProject.setCreatedate(sdf.format(new Date()));
        tProject.setMoney(project.getMoney().longValue());
        projectMapper.insertSelective(tProject);


        //获取新保存的项目id
        //2.图片信息保存--头图片
        Integer id = tProject.getId();
        TProjectImages headerImages = new TProjectImages(null, id, project.getHeaderImage(), ProjectImageTypeEnume.HEADER.getCode().byteValue());
        projectImagesMapper.insertSelective(headerImages);

        //2.图片信息保存--详情图片
        if (!CollectionUtils.isEmpty(project.getDetailsImage())){
            for (String detailsUrl : project.getDetailsImage()){
                TProjectImages detailsImage = new TProjectImages(null,id,detailsUrl,ProjectImageTypeEnume.DETAILS.getCode().byteValue());
                projectImagesMapper.insertSelective(detailsImage);
            }
        }

        //3.保存分类信息
        if(!CollectionUtils.isEmpty(project.getTypeids())){
            for(Integer typeId:project.getTypeids()){
                TProjectType projectType = new TProjectType(null,id,typeId);
                projectTypeMapper.insertSelective(projectType);
            }
        }
        //4.保存标签信息
        if(!CollectionUtils.isEmpty(project.getTagids())){
            for(Integer tagId:project.getTagids()){
                TProjectTag projectTag = new TProjectTag(null,id,tagId);
                projectTagMapper.insertSelective(projectTag);
            }
        }
        //5.保存回报增量信息
        List<TReturn> returnList = project.getProjectReturns();
        for(TReturn tReturn:returnList){
            tReturn.setProjectid(id);
            tReturnMapper.insertSelective(tReturn);
        }

        //6.清空缓存
        stringRedisTemplate.delete(ProjectConstant.TEMP_PROFECT_PREFIX+project.getProjectToken());

    }

}

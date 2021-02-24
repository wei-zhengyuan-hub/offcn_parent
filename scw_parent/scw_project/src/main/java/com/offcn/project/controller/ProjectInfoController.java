package com.offcn.project.controller;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.project.pojo.*;
import com.offcn.project.service.ProjectInfoService;
import com.offcn.project.vo.resp.ProjectDetailVo;
import com.offcn.project.vo.resp.ProjectVo;
import com.offcn.utils.OssTemplate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lhq
 * @Date: 2020/12/1 14:33
 * @Description:
 */
@RestController
@RequestMapping("/project")
public class ProjectInfoController {

    @Autowired
    private OssTemplate ossTemplate;
    @Autowired
    private ProjectInfoService projectInfoService;

    @ApiOperation("上传图片")
    @PostMapping("/uploadFile")
    public AppResponse<Map<String, Object>> uploadFile(@RequestParam(name = "file") MultipartFile[] multipartFiles) {

        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        if (multipartFiles != null && multipartFiles.length > 0) {
            for (MultipartFile file : multipartFiles) {
                String oldName = file.getOriginalFilename();

                try {
                    String url = ossTemplate.upload(file.getInputStream(),oldName);
                    list.add(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        map.put("urls",list);

        return AppResponse.ok(map);
    }

    @ApiOperation("获取项目回报列表")
    @GetMapping("/details/returns/{projectId}")
    public AppResponse<List<TReturn>> getReturnList(@PathVariable("projectId") Integer projectId){
        List<TReturn> returnList = projectInfoService.getReturnList(projectId);
        return AppResponse.ok(returnList);
    }


    @ApiOperation("获取系统所有项目")
    @GetMapping("/all")
    public AppResponse<List<ProjectVo>> findAllProject(){
        //1.创建projectVO对象
        List<ProjectVo> projectVos = new ArrayList<>();
        //2.查询全部的项目
        List<TProject> projectList = projectInfoService.findProjectList();
        //3.便利项目集合
        for (TProject project : projectList){
            List<TProjectImages> projectImages = projectInfoService.getProjectImages(project.getId());
            //创建新的实体类
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(project,projectVo);
            //便利图片
            for (TProjectImages image:projectImages){
                //如果是项目头图片
                if(image.getImgtype()==0){
                    projectVo.setHeaderImage(image.getImgurl());
                }

            }
            //4.将项目添加到项目vo中
            projectVos.add(projectVo);
        }
        return AppResponse.ok(projectVos);
    }

    /**
     * 获取项目详细信息
     * @param projectId 项目id
     * @return
     */
    @ApiOperation("获取项目详细信息")
    @GetMapping("/findProjectInfo/{projectId}")
    public AppResponse<ProjectDetailVo> findProjectInfo(@PathVariable("projectId") Integer projectId){
        //获取项目信息
        TProject projectInfo = projectInfoService.findProjectInfo(projectId);
        ProjectDetailVo projectVo = new ProjectDetailVo();
        //获取图片列表
        List<TProjectImages> projectImages = projectInfoService.getProjectImages(projectInfo.getId());
        //保存图片
        List<String> detailsImage = projectVo.getDetailsImage();
        if (detailsImage==null){
            detailsImage = new ArrayList<>();
        }
        for (TProjectImages image:projectImages){
            //头部图片
            if (image.getImgtype()==0){
                projectVo.setHeaderImage(image.getImgurl());
            }else {
                detailsImage.add(image.getImgurl());
            }
        }
        projectVo.setDetailsImage(detailsImage);

        //项目所有支付回报
        List<TReturn> returnList = projectInfoService.getReturnList(projectId);
        projectVo.setProjectReturns(returnList);
        BeanUtils.copyProperties(projectInfo,projectVo);
        return AppResponse.ok(projectVo);
    }

    @ApiOperation("获取所有项目标签")
    @GetMapping("/findAllTag")
    public AppResponse<List<TTag>> findAllTag(){
        List<TTag> allTag = projectInfoService.findAllTag();
        return AppResponse.ok(allTag);
    }

    @ApiOperation("获取所有项目分类")
    @GetMapping("/findAllType")
    public AppResponse<List<TType>> findAllType(){
        List<TType> allType = projectInfoService.findAllType();
        return AppResponse.ok(allType);
    }

    /**
     * 查询项目回报信息
     * @param returnId 回报信息id
     * @return
     */
    @ApiOperation("获取项目回报信息")
    @GetMapping("/returns/info/{returnId}")
    public AppResponse<TReturn> findReturn(@PathVariable("returnId") Integer returnId){
        TReturn returnInfo = projectInfoService.findReturnInfo(returnId);
        return AppResponse.ok(returnInfo);
    }


}

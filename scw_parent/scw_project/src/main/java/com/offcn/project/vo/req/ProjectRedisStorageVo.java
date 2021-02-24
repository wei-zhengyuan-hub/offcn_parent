package com.offcn.project.vo.req;

import com.offcn.project.pojo.TReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.project.vo.req
 * @date 2020/12/1 20:33
 * @Copyright © 2020-2021 中公教育
 * 将项目数据存到redis
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRedisStorageVo {

    private String projectToken;//项目的临时token
    private Integer memberid;//会员id
    private List<Integer> typeids; //项目的分类id
    private List<Integer> tagids; //项目的标签id
    private String name;//项目名称
    private String remark;//项目简介
    private Integer money;//筹资金额
    private Integer day;//筹资天数
    private String headerImage;//项目头部图片
    private List<String> detailsImage;//项目详情图片
    private List<TReturn> projectReturns;//项目回报

    
}

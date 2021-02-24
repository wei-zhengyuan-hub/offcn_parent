package com.offcn.webui.vo.resp;

import com.offcn.vo.BaseVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wzy
 * @version 0.0.3
 * @description 回报增量的Vo
 * @since 2020/12/2 19:22
 *
 */
@Data
public class ProjectReturnVo extends BaseVo implements Serializable {

    //项目token
    private String projectToken;

    private Integer id;

    private Integer projectId;

    private Byte type;

    private Integer supportmoney;

    private String content;

    private Integer signalpurchase;

    private Integer purchase;

    private Integer freight;

    private Byte invoice;

    private Integer rtndate;



}

package com.offcn.order.service.impl;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.order.service.ProjectInfoServiceFeign;
import com.offcn.order.vo.resp.TReturn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wzy
 * @version 0.0.3
 * @description ProjectServiceFeignException
 * @since 2020/12/3 16:10
 * 熔断处理类
 */
@Component
public class ProjectServiceFeignException implements ProjectInfoServiceFeign {

    @Override
    public AppResponse<List<TReturn>> getReturnList(Integer projectId) {
        AppResponse<List<TReturn>> fail = AppResponse.fail(null);
        fail.setMsg("调用远程服务失败");
        return fail;
    }

}

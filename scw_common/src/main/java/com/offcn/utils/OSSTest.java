package com.offcn.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.utils
 * @date 2020/12/1 18:08
 * @Copyright © 2020-2021 中公教育
 */
public class OSSTest {

    public static void main(String[] args) throws IOException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GGU9u3kPT66C8K3siDk";
        String accessKeySecret = "XAYCh8a3FCLdoHufUNdZKJ0CVB67Mx";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream(new File("D:\\img\\1.jpg"));
        ossClient.putObject("scw20201201-wzy", "pic/001.jpg", inputStream);


        // 关闭OSSClient。
        ossClient.shutdown();

        System.out.println("测试完成");

    }

}

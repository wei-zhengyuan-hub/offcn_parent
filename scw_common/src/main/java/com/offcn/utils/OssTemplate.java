package com.offcn.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 魏正源
 * @version V1.0
 * @Package com.offcn.utils
 * @date 2020/12/1 18:47
 * @Copyright © 2020-2021 中公教育
 */
@ToString
@Data
public class OssTemplate {

    private String endpoint;
    private String bucketDomain;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String upload(InputStream inputStream,String fileName){
        //加工文件夹和文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        fileName = UUID.randomUUID().toString().replace("-","")+"_"+fileName;

        //创建OSSClient实例
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        oss.putObject(bucketName,"pic/"+format+"/"+fileName,inputStream);

        //关闭资源
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        oss.shutdown();

        String url = "https://"+bucketDomain+"/pic/"+format+"/"+fileName;
        System.out.println("上传文件路径:"+url);
        return url;
    }

}

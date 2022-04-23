package com.visionvera.psychologist.c.utils.cos;

/**
 * @author 刘传政
 * @date 2020/3/10 17:41
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class SavePathRequestBean {
    /*
    * prefix	string
N
生成文件名前缀,例如医生头像，这个字段就是：doctorIcon
followUpImg：随访图片
userIcon:C端用户头像
feedback：反馈图片

description	string
Y
文件描述

type	int
N
上传文件类型 1医生头像 6随访图片7意见反馈8用户头像 9 预约咨询图片 10预约诊疗

originalFilename	string
N
源文件名，带后缀

filePath	string
N
上传接口返回的路径
    * */
    public String prefix;
    public String description;
    public int type;
    public String originalFilename;
    public String filePath;

}

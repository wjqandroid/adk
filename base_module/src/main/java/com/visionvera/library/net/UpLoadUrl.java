package com.visionvera.library.net;


import com.visionvera.library.base.Constant;

import rxhttp.wrapper.annotation.DefaultDomain;

/**
 * @author 刘传政
 * @date 2019-09-02 14:26
 * QQ:1052374416
 * 电话:18501231486
 * 作用:上传图片用了一个rxhttp库，要求这样写
 * 注意事项:
 */
public class UpLoadUrl {
    @DefaultDomain() //设置为默认域名
    public static String baseUrl = Constant.Url.request_base_url;
}

package com.visionvera.psychologist.c.module.knowledge_library.bean

/**
 * @author 刘传政
 * @date 2/3/21 4:51 PM
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
data class ArticleListResponseBean(
        var dataList: List<ArticleBean>? = null,
        var pageIndex: Int? = null, // 1
        var pageSize: Int? = null, // 2
        var totalPageCnt: Int? = null, // 3
        var totalRecords: Int? = null, // 5
) {

}

data class ArticleBean(
        var author: Int? = null, // 978
        var authorName: String? = null, // 超级管理员
        var authorPhoto: String? = null, // 超级管理员
        var collectNumber: Int? = null, // 5
        var content: String? = null,
        var coverImageUrls: String? = null, // http://slyl-rmcp-cloud-1301295327.cos.ap-beijing.myqcloud.com/upload/images//MedicalCircle/1608269115266-2731-973.jpeg
        var createTime: Long? = null, // 1608280971000
        var fileUrl: String? = null, // http://mhsp-cdn.51vision.com//upload/articals/1608280971255_978.html
        var id: Int? = null, // 6
        var isAuthentication: Int? = null,  //是否认证 0未认证 1已认证
        var isDelete: Int? = null, // 0
        var orderBy: Int? = null, // 0
        var parentId: Int? = null, // 0
        var status: Int? = null, // 1
        var summary: String? = null, // ***木木木嘟嘟嘟嘟￼
        var thumbsUpNumber: Int? = null, // 6
        var collectStatus: Int = 0, //   "collectStatus":0,//收藏状态 0未收藏 0以上收藏
        var thumbsUpStatus: Int = 0, // "thumbsUpStatus":0,//点赞状态 0未点赞 0以上点赞
        var title: String? = null, // 习近平啊啦啦啦他嘟嘟嘟
        var titleStr: String? = null,
        var updateTime: Any? = null, // null
        var commentNumber: Int = 0,
)
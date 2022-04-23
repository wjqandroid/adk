package com.visionvera.psychologist.c.module.knowledge_library.bean

/**
 * @author 刘传政
 * @date 2/3/21 4:51 PM
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
data class AddCommentResponseBean(
        var articalId: Int? = null, // 1
        var childrenList: List<Any?>? = null,
        var content: String? = null, // 测一测评论内容
        var createTime: String = "", // 2021-02-23 11:00:29
        var id: Int? = null, // 3
        var isDelete: Int? = null, // 0
        var parentId: Int? = null, // 2
        var parentUserId: Int = -1, // 0
        var parentName: String? = null, // 管理员
        var userId: Int? = null, // 1259
        var userName: String? = null, // 新明3
        var thumbsUpNumber: Int = 0,
        var thumbsUpStatus: Int = 0, //这个人对这文章的点赞状态 0 没点赞 1及以上点赞
        var userImgUrl: String? = null, //头像
)

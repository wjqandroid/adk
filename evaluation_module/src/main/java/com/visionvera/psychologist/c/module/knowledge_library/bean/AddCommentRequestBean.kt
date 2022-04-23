package com.visionvera.psychologist.c.module.knowledge_library.bean

/**
 * @author 刘传政
 * @date 2/3/21 4:51 PM
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
data class AddCommentRequestBean(
        var parentId: Int = 0, // 上级评论ID 如果没有填0
        var parentUserId: Int = 0, // 上级评论的用户id
        var content: String = "", // 评论内容
        var parentName: String? = null, //上级评论人的姓名
        var articalId: Int = -1, //文章ID

)

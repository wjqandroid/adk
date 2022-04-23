package com.visionvera.psychologist.c.module.knowledge_library.bean

/**
 * @author 刘传政
 * @date 2/3/21 4:51 PM
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
data class AddArticleRequestBean(
        var title: String? = null, // 文章标题
        var summary: String? = null, // 文章摘要
        var content: String? = null, //文章内容（带html标签的字符串）
        var parentId: Int? = null, //父级id，写问答使用
        var coverImageUrls: String? = null, //文章首图路径

)

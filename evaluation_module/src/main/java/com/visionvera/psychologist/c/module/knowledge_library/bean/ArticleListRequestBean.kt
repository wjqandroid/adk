package com.visionvera.psychologist.c.module.knowledge_library.bean

/**
 * @author 刘传政
 * @date 2/3/21 4:51 PM
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
data class ArticleListRequestBean(
        var pageIndex: Int? = null, // 1
        var pageSize: Int = 20, // 2
        var titleStr: String? = null, //文章标题模糊查询
        var id: Int? = null, //文章ID（传入ID获取文章详情）
        var parentId: Int? = null, //文章id 用来查询文章下的回答文章
        var orderBy: Int = 1, //排序方式 1 创建时间倒序 2 收藏数 3点赞数 4 评论数
) {

}

package com.eegets.cartoon.model.bean

data class ListsBean(
    val `data`: List<DataBean>,
    val errorCode: String,
    val errorMsg: String
)
data class DataBean(
    val available: Boolean,
    val convertSimple: Boolean,
    val createTime: String,
    val endOrNot: Any,
    val id: Int,
    val novelAuthor: String,
    val novelImg: String,
    val novelName: String,
    val novelSummary: String,
    val novelWebsite: String,
    val payOrNot: Any,
    val readNum: Any,
    val score: Any,
    val tbChapterId: Any,
    val tbNovelTypeId: Int,
    val top: Boolean
)
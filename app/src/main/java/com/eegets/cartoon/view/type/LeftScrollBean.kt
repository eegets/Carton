package com.eegets.cartoon.view.type

data class LeftScrollBean(
    val `data`: List<LeftScrollData>,
    val errorCode: String,
    val errorMsg: String
)
class LeftScrollData{
    val available: Boolean = false
    val createTime: String = ""
    var select: Boolean = false
    val id: Int = 0
    val typeCode: String = ""
    val typeName: String = ""
}
package com.eegets.cartoon.model.bean

data class WeTestBean(
    val code: Int,
    val `data`: Data
)
data class Data(
    val area: String,
    val area_id: String,
    val city: String,
    val city_id: String,
    val country: String,
    val country_id: String,
    val county: String,
    val county_id: String,
    val ip: String,
    val isp: String,
    val isp_id: String,
    val region: String,
    val region_id: String
)
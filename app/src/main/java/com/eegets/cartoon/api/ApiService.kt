package com.eegets.cartoon.api

import com.eegets.cartoon.model.bean.ListsBean
import com.eegets.cartoon.model.bean.TestBean
import com.eegets.cartoon.view.type.LeftScrollBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    /**
     *
     */
//    @Headers(DOMAIN_NAME_HEADER + LAS_DOMAIN_NAME)
    @POST("unified/novel/list")
    fun requireHome(@Body body: RequestBody): Observable<ListsBean>

    /**
     *
     */
//    @Headers(DOMAIN_NAME_HEADER + LAS_DOMAIN_NAME)
    @GET("novel/category/list")
    fun requireCartory(): Observable<LeftScrollBean>




    /**
     * 获取图形验证码
     */
//    @Headers(DOMAIN_NAME_HEADER + USER_DOMAIN_NAME)
    @FormUrlEncoded
    @POST("/captcha/v1")
    fun getImageReCode(
        @Query("bizType") bizType: String,
        @Field("blackbox") blackbox: String
    ): Observable<TestBean>

}
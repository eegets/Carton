package com.eegets.cartoon.api

import com.eegets.cartoon.model.bean.ListsBean
import com.eegets.cartoon.utils.RequestBodyUtils
import com.eegets.cartoon.view.type.LeftScrollBean
import com.secoo.bbc.common.api.ApiServer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import retrofit2.http.Query
import okhttp3.RequestBody


fun requireHomeResponse (type: String, categoryId: String="", pageSize: Int = 10): Observable<ListsBean> {
    val map = HashMap<String, Any>()
    map["appShowEnum"] = type
    map["limit"] = 0
    map["pageNum"] = 1
    map["pageSize"] = pageSize
    map["randomChange"] = "true"
    map["categoryId"] = categoryId
    val body = RequestBodyUtils.getRequestBody(map)
 return  ApiServer(ApiService::class.java).requireHome(body)
     .subscribeOn(Schedulers.newThread())
     .observeOn(AndroidSchedulers.mainThread())

}


fun requireCategoryResponse (): Observable<LeftScrollBean> {
    return  ApiServer(ApiService::class.java).requireCartory()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

}

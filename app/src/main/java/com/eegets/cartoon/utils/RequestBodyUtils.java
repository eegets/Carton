package com.eegets.cartoon.utils;

import com.moving.kotlin.frame.ext.GsonExtKt;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestBodyUtils {
    public static RequestBody getRequestBody(HashMap<String, Object> hashMap) {
        String jsonString = GsonExtKt.mapFromJson(hashMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonString);

        return requestBody;
    }
}

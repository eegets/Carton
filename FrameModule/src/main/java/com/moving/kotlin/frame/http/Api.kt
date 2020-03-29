package com.secoo.bbc.common.api


import com.moving.kotlin.frame.http.ipConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.*

    private fun ignoreSSLCheck(): OkHttpClient.Builder {
        //首先创建OkHttpClient.build进行信任所有证书配置
        val okhttpClient = OkHttpClient().newBuilder()
        //信任所有服务器地址
        okhttpClient.hostnameVerifier { s, sslSession ->
            //设置为true
            true
        }
        //创建管理器
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return null
            }


            override fun checkClientTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String) {
            }

            override fun checkServerTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String) {
            }
        })
        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            //为OkHttpClient设置sslSocketFactory
            okhttpClient.sslSocketFactory(sslContext.socketFactory)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return okhttpClient

    }
    private val retrofit = Retrofit.Builder()
            .baseUrl(ipConfig)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(ignoreSSLCheck().build())
            .build()

     fun <T> ApiServer(clzz: Class<T>): T {
        return retrofit.create(clzz)
    }
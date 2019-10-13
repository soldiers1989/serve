package com.easyhome.serve.api.service

import com.easyhome.serve.api.Api
import com.easyhome.serve.mvp.model.entity.HttpResult
import com.easyhome.serve.mvp.model.entity.LoginInfo
import com.easyhome.serve.mvp.model.javabean.MeasureHome
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*


interface JRService {


    /**
     * 用户登录
     *
     * @param phone    手机号
     * @param password 密码
     * @param app_os    操作系统
     */
    @FormUrlEncoded
    @POST("${Api.APP_BASE_URL}${Api.LOGIN}")
    fun login(
        @Field("account") account: String,
        @Field("password") password: String,
        @Field("osType") osType: Int
    ): Observable<HttpResult<LoginInfo>>


    /**
     * 登出接口
     * @param userId   用户id
     */
    @FormUrlEncoded
    @POST("${Api.APP_BASE_URL}${Api.LOGOUT}")
    fun loginOut(
        @Field("userId") userId: String
    ): Observable<HttpResult<Any>>

    /**
     * 验证码
     * @param phone   手机号
     */
    @FormUrlEncoded
    @POST("${Api.APP_BASE_URL}${Api.SMSCODE}")
    fun smsCode(
        @Field("phone") phone: String
    ): Observable<HttpResult<Any>>

    /**
     * 测试
     * @param phone   手机号
     */
    @FormUrlEncoded
    @POST("${Api.APP_BASE_URL}${Api.TEST}")
    fun test(
        @Field("username") username: String,
        @Field("size") size: String
    ): Observable<HttpResult<Any>>

    /**
     * 验证码校验
     * @param phone   手机号
     */
    @GET("${Api.APP_BASE_URL}${Api.CODECHECK}")
    fun codeCheck(
        @Query("phone") phone: String,
        @Query("code") code: String
    ): Observable<HttpResult<Any>>

    /**
     * 添加动态
     */
    @POST("${Api.ADD_DYNAMIC}")
    fun addDynamic(@Body requestBody: RequestBody): Observable<HttpResult<Any>>
    /**
     * 添加动态
     */
    @POST("${Api.MEASURE_HOME}")
    fun measureHome(@Body requestBody: RequestBody): Observable<HttpResult<List<MeasureHome>>>

}

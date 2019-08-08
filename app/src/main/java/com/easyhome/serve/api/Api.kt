package com.easyhome.serve.api

/**
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 */
object Api {

    const val BASE = "api-user/"
    const val APP_BASE_URL = "http://api.qizhekeji.com/${BASE}"


    const val RequestSuccess = 0
    const val KEY_UUID = "KEY_UUID"
    const val USERAPI = "userapi"
    const val APP_LOGO_URl = "${APP_BASE_URL}${USERAPI}"
    const val LOGIN = "user/user/login"
    const val LOGOUT = "userapi/userCenter/logOut"

    //验证码接口
    const val SMSCODE = "captcha/captcha/add"
    const val TEST = "user/list"
    //短信验证码校验
    const val CODECHECK = "captcha/captcha/check"
//http://192.168.2.2:8763/captcha/captcha/check?phone=15010118286&code=456467
}

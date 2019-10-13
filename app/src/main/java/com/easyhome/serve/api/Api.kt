package com.easyhome.serve.api

/**
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 */
object Api {

    const val BASE = "api-user/"
    const val APP_BASE_URL = "http://10.240.10.185:8080/decorate_unexpectedly/"

    //  http://10.240.10.185:8080/decorate_unexpectedly/global/inlet
    const val RequestSuccess = 0
    const val KEY_UUID = "KEY_UUID"
    const val USERAPI = "userapi"
    const val APP_LOGO_URl = "${APP_BASE_URL}${USERAPI}"
    //  const val LOGIN = "user/user/login"
    const val LOGOUT = "userapi/userCenter/logOut"

    //验证码接口
    const val SMSCODE = "captcha/captcha/add"
    const val TEST = "user/list"
    //短信验证码校验
    const val CODECHECK = "captcha/captcha/check"
//http://192.168.2.2:8763/captcha/captcha/check?phone=15010118286&code=456467


    //添加动态
    const val ADD_DYNAMIC = "/api/add/dynamic"

    //http://10.240.10.81:8080/decorate_unexpectedly/api/staffF/staffFLogin?account=10000&password=10000&osType=1
    //登录
    const val LOGIN = "api/staffF/staffFLogin"

    //分支-量房-查看
    const val MEASURE_HOME = "${APP_BASE_URL}global/branchs"


}

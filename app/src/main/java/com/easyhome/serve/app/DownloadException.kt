package com.easyhome.serve.app

/**
 *下载异常
 *@Author GF
 *CreationTime 2018/10/18 13:57
 */
class DownloadException(val e: String) : RuntimeException(e)
package com.easyhome.serve.mvp.ui.service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface DownloadService {
    @GET()
    fun download(@Url url: String): Observable<ResponseBody>

    @GET()
    fun downloadHead(@Url url: String): Observable<Response<ResponseBody>>
}
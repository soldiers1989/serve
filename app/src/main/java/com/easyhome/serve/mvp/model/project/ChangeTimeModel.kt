package com.easyhome.serve.mvp.model.project

import android.app.Application
import com.easyhome.serve.api.service.JRService
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.project.ChangeTimeContract
import com.easyhome.serve.mvp.model.entity.HttpResult
import com.easyhome.serve.mvp.model.javabean.MeasureHome
import io.reactivex.Observable
import okhttp3.RequestBody


@ActivityScope
class ChangeTimeModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), ChangeTimeContract.Model {
    override fun measure(requestBody: RequestBody): Observable<HttpResult<List<MeasureHome>>> {
        return mRepositoryManager.obtainRetrofitService(JRService::class.java).measureHome(requestBody)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

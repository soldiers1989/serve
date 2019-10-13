package com.easyhome.serve.mvp.model.project

import android.app.Application
import com.easyhome.serve.api.service.JRService
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.project.AddDynamicContract
import com.easyhome.serve.mvp.model.entity.HttpResult
import io.reactivex.Observable
import okhttp3.RequestBody


@ActivityScope
class AddDynamicModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), AddDynamicContract.Model {
    override fun addDynamic(requestBody: RequestBody): Observable<HttpResult<Any>> {
        return mRepositoryManager.obtainRetrofitService(JRService::class.java).addDynamic(requestBody)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

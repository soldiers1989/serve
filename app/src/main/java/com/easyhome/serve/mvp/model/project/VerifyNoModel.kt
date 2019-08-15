package com.easyhome.serve.mvp.model.project

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.project.VerifyNoContract


@ActivityScope
class VerifyNoModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), VerifyNoContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

package com.easyhome.serve.mvp.model.setting

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.setting.CoupleBackContract


@ActivityScope
class CoupleBackModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), CoupleBackContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

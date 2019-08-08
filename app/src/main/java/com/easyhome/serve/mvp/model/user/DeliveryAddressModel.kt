package com.easyhome.serve.mvp.model.user

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.user.DeliveryAddressContract


@ActivityScope
class DeliveryAddressModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), DeliveryAddressContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

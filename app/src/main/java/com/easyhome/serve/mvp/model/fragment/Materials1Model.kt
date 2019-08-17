package com.easyhome.serve.mvp.model.fragment

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.fragment.Materials1Contract


@FragmentScope
class Materials1Model
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), Materials1Contract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

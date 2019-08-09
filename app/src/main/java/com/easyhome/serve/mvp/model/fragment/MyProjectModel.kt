package com.easyhome.serve.mvp.model.fragment

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.easyhome.serve.mvp.contract.fragment.MyProjectContract


@FragmentScope
class MyProjectModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), MyProjectContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}

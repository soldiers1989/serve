package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.RetrievePasswordContract
import com.easyhome.serve.mvp.model.RetrievePasswordModel


@Module
//构建RetrievePasswordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class RetrievePasswordModule(private val view: RetrievePasswordContract.View) {
    @ActivityScope
    @Provides
    fun provideRetrievePasswordView(): RetrievePasswordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideRetrievePasswordModel(model: RetrievePasswordModel): RetrievePasswordContract.Model {
        return model
    }
}

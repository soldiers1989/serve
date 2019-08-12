package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.VerifyContract
import com.easyhome.serve.mvp.model.project.VerifyModel


@Module
//构建VerifyModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class VerifyModule(private val view: VerifyContract.View) {
    @ActivityScope
    @Provides
    fun provideVerifyView(): VerifyContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideVerifyModel(model: VerifyModel): VerifyContract.Model {
        return model
    }
}

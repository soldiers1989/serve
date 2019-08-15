package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.VerifyPassContract
import com.easyhome.serve.mvp.model.project.VerifyPassModel


@Module
//构建VerifyPassModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class VerifyPassModule(private val view: VerifyPassContract.View) {
    @ActivityScope
    @Provides
    fun provideVerifyPassView(): VerifyPassContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideVerifyPassModel(model: VerifyPassModel): VerifyPassContract.Model {
        return model
    }
}

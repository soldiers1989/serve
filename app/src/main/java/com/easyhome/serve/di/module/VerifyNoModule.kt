package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.VerifyNoContract
import com.easyhome.serve.mvp.model.project.VerifyNoModel


@Module
//构建VerifyNoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class VerifyNoModule(private val view: VerifyNoContract.View) {
    @ActivityScope
    @Provides
    fun provideVerifyNoView(): VerifyNoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideVerifyNoModel(model: VerifyNoModel): VerifyNoContract.Model {
        return model
    }
}

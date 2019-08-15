package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.ApplyPostponeContract
import com.easyhome.serve.mvp.model.project.ApplyPostponeModel


@Module
//构建ApplyPostponeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ApplyPostponeModule(private val view: ApplyPostponeContract.View) {
    @ActivityScope
    @Provides
    fun provideApplyPostponeView(): ApplyPostponeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideApplyPostponeModel(model: ApplyPostponeModel): ApplyPostponeContract.Model {
        return model
    }
}

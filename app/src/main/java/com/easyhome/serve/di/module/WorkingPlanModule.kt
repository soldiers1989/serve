package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.WorkingPlanContract
import com.easyhome.serve.mvp.model.project.WorkingPlanModel


@Module
//构建WorkingPlanModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WorkingPlanModule(private val view: WorkingPlanContract.View) {
    @ActivityScope
    @Provides
    fun provideWorkingPlanView(): WorkingPlanContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideWorkingPlanModel(model: WorkingPlanModel): WorkingPlanContract.Model {
        return model
    }
}

package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.WorkOrderInfoContract
import com.easyhome.serve.mvp.model.project.WorkOrderInfoModel


@Module
//构建WorkOrderInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WorkOrderInfoModule(private val view: WorkOrderInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideWorkOrderInfoView(): WorkOrderInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideWorkOrderInfoModel(model: WorkOrderInfoModel): WorkOrderInfoContract.Model {
        return model
    }
}

package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.WorkOrderContract
import com.easyhome.serve.mvp.model.project.WorkOrderModel


@Module
//构建WorkOrderModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WorkOrderModule(private val view: WorkOrderContract.View) {
    @ActivityScope
    @Provides
    fun provideWorkOrderView(): WorkOrderContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideWorkOrderModel(model: WorkOrderModel): WorkOrderContract.Model {
        return model
    }
}

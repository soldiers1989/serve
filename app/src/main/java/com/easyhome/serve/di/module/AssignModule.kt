package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.AssignContract
import com.easyhome.serve.mvp.model.project.AssignModel


@Module
//构建AssignModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AssignModule(private val view: AssignContract.View) {
    @ActivityScope
    @Provides
    fun provideAssignView(): AssignContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAssignModel(model: AssignModel): AssignContract.Model {
        return model
    }
}

package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.AddTaskContract
import com.easyhome.serve.mvp.model.project.AddTaskModel


@Module
//构建AddTaskModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AddTaskModule(private val view: AddTaskContract.View) {
    @ActivityScope
    @Provides
    fun provideAddTaskView(): AddTaskContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAddTaskModel(model: AddTaskModel): AddTaskContract.Model {
        return model
    }
}

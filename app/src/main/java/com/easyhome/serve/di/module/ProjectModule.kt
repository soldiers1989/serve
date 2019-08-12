package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.ProjectContract
import com.easyhome.serve.mvp.model.project.ProjectModel


@Module
//构建ProjectModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ProjectModule(private val view: ProjectContract.View) {
    @ActivityScope
    @Provides
    fun provideProjectView(): ProjectContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideProjectModel(model: ProjectModel): ProjectContract.Model {
        return model
    }
}

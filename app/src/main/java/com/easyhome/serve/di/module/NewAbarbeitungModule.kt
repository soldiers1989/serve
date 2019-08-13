package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.NewAbarbeitungContract
import com.easyhome.serve.mvp.model.project.NewAbarbeitungModel


@Module
//构建NewAbarbeitungModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class NewAbarbeitungModule(private val view: NewAbarbeitungContract.View) {
    @ActivityScope
    @Provides
    fun provideNewAbarbeitungView(): NewAbarbeitungContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideNewAbarbeitungModel(model: NewAbarbeitungModel): NewAbarbeitungContract.Model {
        return model
    }
}

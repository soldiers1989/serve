package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.AbarbeitungContract
import com.easyhome.serve.mvp.model.project.AbarbeitungModel


@Module
//构建AbarbeitungModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AbarbeitungModule(private val view: AbarbeitungContract.View) {
    @ActivityScope
    @Provides
    fun provideAbarbeitungView(): AbarbeitungContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAbarbeitungModel(model: AbarbeitungModel): AbarbeitungContract.Model {
        return model
    }
}

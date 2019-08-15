package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.PostponeContract
import com.easyhome.serve.mvp.model.project.PostponeModel


@Module
//构建PostponeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class PostponeModule(private val view: PostponeContract.View) {
    @ActivityScope
    @Provides
    fun providePostponeView(): PostponeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun providePostponeModel(model: PostponeModel): PostponeContract.Model {
        return model
    }
}

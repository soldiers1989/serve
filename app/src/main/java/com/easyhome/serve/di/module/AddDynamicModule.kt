package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.AddDynamicContract
import com.easyhome.serve.mvp.model.project.AddDynamicModel


@Module
//构建AddDynamicModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AddDynamicModule(private val view: AddDynamicContract.View) {
    @ActivityScope
    @Provides
    fun provideAddDynamicView(): AddDynamicContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAddDynamicModel(model: AddDynamicModel): AddDynamicContract.Model {
        return model
    }
}

package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.DynamicContract
import com.easyhome.serve.mvp.model.project.DynamicModel


@Module
//构建DynamicModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class DynamicModule(private val view: DynamicContract.View) {
    @ActivityScope
    @Provides
    fun provideDynamicView(): DynamicContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideDynamicModel(model: DynamicModel): DynamicContract.Model {
        return model
    }
}

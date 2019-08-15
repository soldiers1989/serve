package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.CustomerTagContract
import com.easyhome.serve.mvp.model.project.CustomerTagModel


@Module
//构建CustomerTagModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class CustomerTagModule(private val view: CustomerTagContract.View) {
    @ActivityScope
    @Provides
    fun provideCustomerTagView(): CustomerTagContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideCustomerTagModel(model: CustomerTagModel): CustomerTagContract.Model {
        return model
    }
}

package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.H5Contract
import com.easyhome.serve.mvp.model.H5Model


@Module
//构建H5Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class H5Module(private val view: H5Contract.View) {
    @ActivityScope
    @Provides
    fun provideH5View(): H5Contract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideH5Model(model: H5Model): H5Contract.Model {
        return model
    }
}

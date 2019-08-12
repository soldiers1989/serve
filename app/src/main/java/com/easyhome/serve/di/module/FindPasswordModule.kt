package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.FindPasswordContract
import com.easyhome.serve.mvp.model.FindPasswordModel


@Module
//构建FindPasswordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class FindPasswordModule(private val view: FindPasswordContract.View) {
    @ActivityScope
    @Provides
    fun provideFindPasswordView(): FindPasswordContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideFindPasswordModel(model: FindPasswordModel): FindPasswordContract.Model {
        return model
    }
}

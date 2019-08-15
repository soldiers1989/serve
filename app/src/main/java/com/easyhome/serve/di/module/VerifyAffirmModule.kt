package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.VerifyAffirmContract
import com.easyhome.serve.mvp.model.project.VerifyAffirmModel


@Module
//构建VerifyAffirmModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class VerifyAffirmModule(private val view: VerifyAffirmContract.View) {
    @ActivityScope
    @Provides
    fun provideVerifyAffirmView(): VerifyAffirmContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideVerifyAffirmModel(model: VerifyAffirmModel): VerifyAffirmContract.Model {
        return model
    }
}

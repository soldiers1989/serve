package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.ScoreContract
import com.easyhome.serve.mvp.model.ScoreModel


@Module
//构建ScoreModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ScoreModule(private val view: ScoreContract.View) {
    @ActivityScope
    @Provides
    fun provideScoreView(): ScoreContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideScoreModel(model: ScoreModel): ScoreContract.Model {
        return model
    }
}

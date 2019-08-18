package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.ScheduleContract
import com.easyhome.serve.mvp.model.ScheduleModel


@Module
//构建ScheduleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ScheduleModule(private val view: ScheduleContract.View) {
    @ActivityScope
    @Provides
    fun provideScheduleView(): ScheduleContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideScheduleModel(model: ScheduleModel): ScheduleContract.Model {
        return model
    }
}

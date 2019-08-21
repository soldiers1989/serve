package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.SettlementContract
import com.easyhome.serve.mvp.model.project.SettlementModel


@Module
//构建SettlementModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class SettlementModule(private val view: SettlementContract.View) {
    @ActivityScope
    @Provides
    fun provideSettlementView(): SettlementContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideSettlementModel(model: SettlementModel): SettlementContract.Model {
        return model
    }
}

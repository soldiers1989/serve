package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.projet.AbarbeitungInfoContract
import com.easyhome.serve.mvp.model.projet.AbarbeitungInfoModel


@Module
//构建AbarbeitungInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AbarbeitungInfoModule(private val view: AbarbeitungInfoContract.View) {
    @ActivityScope
    @Provides
    fun provideAbarbeitungInfoView(): AbarbeitungInfoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAbarbeitungInfoModel(model: AbarbeitungInfoModel): AbarbeitungInfoContract.Model {
        return model
    }
}

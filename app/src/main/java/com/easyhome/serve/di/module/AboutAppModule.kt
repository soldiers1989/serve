package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.setting.AboutAppContract
import com.easyhome.serve.mvp.model.setting.AboutAppModel


@Module
//构建AboutAppModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class AboutAppModule(private val view: AboutAppContract.View) {
    @ActivityScope
    @Provides
    fun provideAboutAppView(): AboutAppContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideAboutAppModel(model: AboutAppModel): AboutAppContract.Model {
        return model
    }
}

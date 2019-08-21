package com.easyhome.serve.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.easyhome.serve.mvp.contract.project.MapContract
import com.easyhome.serve.mvp.model.project.MapModel


@Module
//构建MapModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MapModule(private val view: MapContract.View) {
    @ActivityScope
    @Provides
    fun provideMapView(): MapContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideMapModel(model: MapModel): MapContract.Model {
        return model
    }
}

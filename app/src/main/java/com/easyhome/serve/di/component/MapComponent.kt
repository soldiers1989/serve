package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.MapModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.MapActivity

@ActivityScope
@Component(modules = arrayOf(MapModule::class), dependencies = arrayOf(AppComponent::class))
interface MapComponent {
    fun inject(activity: MapActivity)
}

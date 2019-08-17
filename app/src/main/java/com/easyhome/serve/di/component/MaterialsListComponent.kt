package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.MaterialsListModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.MaterialsListActivity

@ActivityScope
@Component(modules = arrayOf(MaterialsListModule::class), dependencies = arrayOf(AppComponent::class))
interface MaterialsListComponent {
    fun inject(activity: MaterialsListActivity)
}

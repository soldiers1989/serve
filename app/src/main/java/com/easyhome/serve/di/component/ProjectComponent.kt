package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.ProjectModule

import com.jess.arms.di.scope.ActivityScope
import com.easyhome.serve.mvp.ui.activity.project.ProjectActivity

@ActivityScope
@Component(modules = arrayOf(ProjectModule::class), dependencies = arrayOf(AppComponent::class))
interface ProjectComponent {
    fun inject(activity: ProjectActivity)
}

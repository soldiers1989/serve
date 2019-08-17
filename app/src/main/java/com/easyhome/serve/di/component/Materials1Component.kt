package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.Materials1Module

import com.jess.arms.di.scope.FragmentScope
import com.easyhome.serve.mvp.ui.fragment.Materials1Fragment

@FragmentScope
@Component(modules = arrayOf(Materials1Module::class), dependencies = arrayOf(AppComponent::class))
interface Materials1Component {
    fun inject(fragment: Materials1Fragment)
}

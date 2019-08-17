package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.Materials2Module

import com.jess.arms.di.scope.FragmentScope
import com.easyhome.serve.mvp.ui.fragment.Materials2Fragment

@FragmentScope
@Component(modules = arrayOf(Materials2Module::class), dependencies = arrayOf(AppComponent::class))
interface Materials2Component {
    fun inject(fragment: Materials2Fragment)
}

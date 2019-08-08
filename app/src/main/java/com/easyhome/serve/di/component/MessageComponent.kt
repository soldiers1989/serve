package com.easyhome.serve.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.easyhome.serve.di.module.MessageModule

import com.jess.arms.di.scope.FragmentScope
import com.easyhome.serve.mvp.ui.fragment.MessageFragment

@FragmentScope
@Component(modules = arrayOf(MessageModule::class), dependencies = arrayOf(AppComponent::class))
interface MessageComponent {
    fun inject(fragment: MessageFragment)
}

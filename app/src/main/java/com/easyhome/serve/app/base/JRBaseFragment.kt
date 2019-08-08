package com.easyhome.serve.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyf.barlibrary.ImmersionBar
import com.jess.arms.base.BaseFragment
import com.jess.arms.mvp.IPresenter
import com.easyhome.serve.app.extension.singleClick

/**
 * Created by 宗传
 * Time 2018/10/18  6:02 PM
 *
 * JRBaseFragment
 **/
abstract class JRBaseFragment<P : IPresenter> : BaseFragment<P>(), IView {

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init()
        val netErrorLayout = (activity as JRBaseActivity<*>).netErrorLayout
        netErrorLayout.singleClick {
            netErrorLayout.visibility = View.GONE
            initData(savedInstanceState)
        }
        rootView = initView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun showNetError() {
        (myself as JRBaseActivity<*>).showNetError()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden)
            ImmersionBar.with(this).init()
    }


}
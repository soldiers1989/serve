package com.easyhome.serve.mvp.ui.activity.user

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAddAddressComponent
import com.easyhome.serve.di.module.AddAddressModule
import com.easyhome.serve.mvp.contract.user.AddAddressContract
import com.easyhome.serve.mvp.presenter.user.AddAddressPresenter

import com.easyhome.serve.R


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class AddAddressActivity : BaseActivity<AddAddressPresenter>(), AddAddressContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAddAddressComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .addAddressModule(AddAddressModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_add_address //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}

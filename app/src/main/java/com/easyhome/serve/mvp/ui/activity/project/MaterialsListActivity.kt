package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerMaterialsListComponent
import com.easyhome.serve.di.module.MaterialsListModule
import com.easyhome.serve.mvp.contract.project.MaterialsListContract
import com.easyhome.serve.mvp.presenter.project.MaterialsListPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.MaterialsOrderAdapter
import com.easyhome.serve.mvp.ui.adapter.MaterialsTabAdapter
import com.easyhome.serve.mvp.ui.adapter.MessageTabAdapter
import com.easyhome.serve.mvp.ui.fragment.Materials1Fragment
import com.easyhome.serve.mvp.ui.fragment.Materials2Fragment
import kotlinx.android.synthetic.main.activity_materials_list.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 材料详情
 */
class MaterialsListActivity : JRBaseActivity<MaterialsListPresenter>(), MaterialsListContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMaterialsListComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .materialsListModule(MaterialsListModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_materials_list //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "材料详情"
        val tabadapter = MaterialsTabAdapter(arrayListOf("代购", "外购"))
        tabadapter.setOnItemChildClickListener { adapter, view, position ->
            vpProjectDataPage.currentItem = position
        }
        tabRV.adapter = tabadapter
        val adapter = MyPageAdapter(supportFragmentManager)
        vpProjectDataPage.adapter = adapter
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


    class MyPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val title = 2//
        override fun getPageTitle(position: Int): CharSequence? {
            return ""
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> Materials1Fragment.newInstance()
                else -> Materials2Fragment.newInstance()
            }

        }

        override fun getCount(): Int {
            return title
        }


    }
}

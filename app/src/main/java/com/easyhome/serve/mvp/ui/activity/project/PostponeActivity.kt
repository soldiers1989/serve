package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerPostponeComponent
import com.easyhome.serve.di.module.PostponeModule
import com.easyhome.serve.mvp.contract.project.PostponeContract
import com.easyhome.serve.mvp.presenter.project.PostponePresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.openH5ForUrl
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.PostponeAdapter
import kotlinx.android.synthetic.main.activity_postpone.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


/**
 *延期
 */
class PostponeActivity : JRBaseActivity<PostponePresenter>(), PostponeContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerPostponeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .postponeModule(PostponeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_postpone //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "延期"
        tvPageRight.text = "发起"
        tvPageRight.visibility = View.VISIBLE
        tvPageRight.setBackgroundResource(R.drawable.white_side)
        tvPageRight.singleClick {
            startActivity<ApplyPostponeActivity>()
        }
        val adapter=PostponeAdapter(arrayListOf("", "", ""))
        adapter.setOnItemClickListener { adapter, view, position ->
            openH5ForUrl("https://lanhuapp.com/web/#/item/project/board/detail?pid=5a7197ec-9132-42fb-bd02-8393ada85204&project_id=5a7197ec-9132-42fb-bd02-8393ada85204&image_id=eb7e4009-3e36-4ccf-b8fe-95efa38a783a&child=79c42c15-436e-46ec-9bd9-3eb92afce30b")
        }
        postpone.adapter = adapter

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

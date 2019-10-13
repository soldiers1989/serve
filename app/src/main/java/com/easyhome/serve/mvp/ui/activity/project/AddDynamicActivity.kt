package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerAddDynamicComponent
import com.easyhome.serve.di.module.AddDynamicModule
import com.easyhome.serve.mvp.contract.project.AddDynamicContract
import com.easyhome.serve.mvp.presenter.project.AddDynamicPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.getRequestBody
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.AddDynamicAdapter
import com.easyhome.serve.mvp.ui.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_add_dynamic.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


/**
 * 发布动态
 */
class AddDynamicActivity : JRBaseActivity<AddDynamicPresenter>(), AddDynamicContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAddDynamicComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .addDynamicModule(AddDynamicModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_add_dynamic //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "发布动态"
        ivPageRight.visibility = View.VISIBLE
        ivPageRight.setImageResource(R.mipmap.question_icon)
        ivPageRight.singleClick {

        }
        node.singleClick {
            startActivity<AddNodeActivity>()
        }
        photoRV.adapter = PhotoAdapter(arrayListOf("", "", "", "", "", "", "", ""))


        val arg = BrandArguments("测试一下发布动态", 1,1,"测试", arrayListOf("http://sjdlaj/11.jpg"))//分类列表数据
        mPresenter!!.addDynamic(arg.getRequestBody()) {


        }
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

    /*{
        "content": "string",
        "photosList": [
        "string"
        ],
        "projectId": 0,
        "staffId": 0,
        "title": "string"
    }*/

    private data class BrandArguments(
        val content: String,
        val projectId: Int,
        val staffId: Int,
        val title: String,
        val photosList: List<String>
    )
}

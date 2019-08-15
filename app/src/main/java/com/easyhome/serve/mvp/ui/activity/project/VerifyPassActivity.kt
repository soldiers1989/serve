package com.easyhome.serve.mvp.ui.activity.project

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.easyhome.serve.di.component.DaggerVerifyPassComponent
import com.easyhome.serve.di.module.VerifyPassModule
import com.easyhome.serve.mvp.contract.project.VerifyPassContract
import com.easyhome.serve.mvp.presenter.project.VerifyPassPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.singleClick
import com.easyhome.serve.mvp.ui.adapter.VerifyPassAdapter
import kotlinx.android.synthetic.main.activity_verify_pass.*
import kotlinx.android.synthetic.main.layout_title.*


/**
 * 验收通过
 */
class VerifyPassActivity : JRBaseActivity<VerifyPassPresenter>(), VerifyPassContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerVerifyPassComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .verifyPassModule(VerifyPassModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_verify_pass //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        ivPageBack.singleClick {
            killMyself()
        }
        tvPageTitle.text = "验收"
        if(intent.getIntExtra("position",0)%2==0) {
            contentRV.adapter = VerifyPassAdapter(
                arrayListOf(
                    VerifyPassAdapter.Bean("节点验收:", "中期验收"),
                    VerifyPassAdapter.Bean("实际验收日期：", "2019-07-20"),
                    VerifyPassAdapter.Bean("验收照片：", "", arrayListOf("", "", "", "")),
                    VerifyPassAdapter.Bean("整改建议/备注：", "整改建议/备注：")
                )
            )
        }else{

            contentRV.adapter = VerifyPassAdapter(
                arrayListOf(
                    VerifyPassAdapter.Bean("节点验收:", "中期验收"),
                    VerifyPassAdapter.Bean("验收人：", "郑哈哈哈哈哈"),
                    VerifyPassAdapter.Bean("实际验收日期：", "2019-07-20"),
                    VerifyPassAdapter.Bean("验收结果：", "验收不通过"),
                    VerifyPassAdapter.Bean("验收不通过", " 类型一类型一类型一类型一 \n" +
                            "-类型二类型二类型二类型二类型二\n-类型三类型三类型三"),
                    VerifyPassAdapter.Bean("备注: ", "备注备注备注"),
                    VerifyPassAdapter.Bean("下次验收日期：", "2019-07-22"),
                    VerifyPassAdapter.Bean("！验收照片：", "", arrayListOf("", "", "", "")),
                    VerifyPassAdapter.Bean("整改建议/备注：", "打扫的要干净利索！")
                )
            )

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
}

package com.easyhome.serve.mvp.ui.activity.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia

import com.easyhome.serve.di.component.DaggerUserDataComponent
import com.easyhome.serve.di.module.UserDataModule
import com.easyhome.serve.mvp.contract.user.UserDataContract
import com.easyhome.serve.mvp.presenter.user.UserDataPresenter

import com.easyhome.serve.R
import com.easyhome.serve.app.base.JRBaseActivity
import com.easyhome.serve.app.extension.loadImage
import com.easyhome.serve.app.extension.singleClick
import kotlinx.android.synthetic.main.activity_user_data.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity


/**
 * 用户资料
 */
class UserDataActivity : JRBaseActivity<UserDataPresenter>(), UserDataContract.View {
    override fun getMyself(): BaseActivity<*> = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerUserDataComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .userDataModule(UserDataModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_user_data //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tvPageTitle.text = "个人资料"
        userHeadIV.singleClick {
            selectPicture(this)
        }
       /* moreIV5.singleClick {
            startActivity<DeliveryAddressActivity>()
        }*/
    }

    /**
     * 带相机 Fragment
     */
    fun selectPicture(activity: Activity) {
        return PictureSelector.create(activity)
            .openGallery(PictureMimeType.ofImage())
            .compress(true)
            .selectionMode(PictureConfig.SINGLE) // 多选 or 单选
            .previewImage(true) // 是否可预览图片 true or false
            .cropCompressQuality(50) // 图片裁剪质量,默认无损
            .imageSpanCount(4) // 每行个数
            .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
            .enableCrop(true)// 是否裁剪 true or false
            .compress(true)// 是否压缩 true or false
            .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
            .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    /* override fun showLoading() {

     }

     override fun hideLoading() {

     }*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            val selectList = PictureSelector.obtainMultipleResult(data) as ArrayList<LocalMedia>
            if (selectList.size > 0) {
                userHeadIV.loadImage(selectList[0].compressPath, R.mipmap.user_icon_1, true)
            }
        }
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

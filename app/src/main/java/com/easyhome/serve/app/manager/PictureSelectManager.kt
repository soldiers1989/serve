package com.easyhome.serve.app.manager

import android.app.Activity
import android.support.v4.app.Fragment
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia

/**
 * Created by EVE
 * Time 2018/8/22  下午4:57
 *
 * PictureSelectManager
 **/
object PictureSelectManager {

    /**
     * 带相机 Activity
     */
    fun selectPicture(activity: Activity, list: List<LocalMedia>?, maxSelectNum: Int = 9) {
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .selectionMode(PictureConfig.MULTIPLE) // 多选 or 单选
                .maxSelectNum(maxSelectNum)
                .previewImage(true) // 是否可预览图片 true or false
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
                .selectionMedia(list)
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    /**
     * 视频
     */
    fun selectVideo(activity: Activity) {
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofVideo())
                .compress(true)
                .previewVideo(true)// 是否可预览视频 true or false
                .selectionMode(PictureConfig.SINGLE)
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .recordVideoSecond(15)  //视频秒数
                .openClickSound(true) // 是否开启点击声音
                .recordVideoSecond(60)//视频秒数录制 默认 60s int
                .videoQuality(1)// 视频录制质量 0 or 1 int
                .forResult(PictureConfig.REQUEST_CAMERA)
    }

    /**
     * 语音
     */
    fun selectAudio(activity: Activity) {
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofAudio())
                .compress(true)
                .selectionMode(PictureConfig.SINGLE)//多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .enablePreviewAudio(true) // 是否可播放音频 true or false
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .openClickSound(true)// 是否开启点击声音 true or false
                .recordVideoSecond(15)  //视频秒数
                .openClickSound(true) // 是否开启点击声音
                .recordVideoSecond(60)//视频秒数录制 默认 60s int
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    /**
     * 带相机 Fragment
     */
    fun selectPicture(activity: Activity) {
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .selectionMode(PictureConfig.MULTIPLE) // 多选 or 单选
                .previewImage(true) // 是否可预览图片 true or false
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    /**
     * 无相机 Fragment
     */
    fun selectPictureNO(activity: Activity) {
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .selectionMode(PictureConfig.SINGLE) // 多选 or 单选
                .previewImage(true) // 是否可预览图片 true or false
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(true)// 是否裁剪 true or false
                .isCamera(false)
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    /**
     * 带相机 Fragment
     */
    fun selectPicture(fragment: Fragment) {
        return PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .selectionMode(PictureConfig.SINGLE) // 多选 or 单选
                .previewImage(true) // 是否可预览图片 true or false
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(1024) // 小于 100kb 的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    /**
     * 无相机 Fragment
     */
    fun selectPictureNO(fragment: Fragment) {
        return PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .selectionMode(PictureConfig.SINGLE) // 多选 or 单选
                .previewImage(true) // 是否可预览图片 true or false
                .cropCompressQuality(50) // 图片裁剪质量,默认无损
                .imageSpanCount(4) // 每行个数
                .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .enableCrop(true)// 是否裁剪 true or false
                .isCamera(false)
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(100) // 小于 100kb 的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }
}
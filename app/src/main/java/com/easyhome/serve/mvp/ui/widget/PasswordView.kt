@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.easyhome.serve.mvp.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Parcelable
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.BaseInputConnection
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager
import com.easyhome.serve.R
import java.util.*
import java.util.regex.Pattern




class PasswordView : View {

    private var mode: Mode? = null //样式模式
    private var passwordLength: Int = 0//密码个数
    private var cursorFlashTime: Long = 0//光标闪动间隔时间
    private var passwordPadding: Int = 0//每个密码间的间隔
    private var passwordSize = dp2px(30f)//单个密码大小
    private var borderColor: Int = 0//边框颜色
    private var borderWidth: Int = 0//下划线粗细
    private var cursorPosition: Int = 0//光标位置
    private var cursorWidth: Int = 0//光标粗细
    private var cursorHeight: Int = 0//光标长度
    private var cursorColor: Int = 0//光标颜色
    private var isCursorShowing: Boolean = false//光标是否正在显示
    private var isCursorEnable: Boolean = false//是否开启光标
    private var isInputComplete: Boolean = false//是否输入完毕
    private var cipherTextSize: Int = 0//密文符号大小
    private var cipherEnable: Boolean = false//是否开启密文
    private var password: Array<String?>? = null//密码数组
    private var inputManager: InputMethodManager? = null
    private var passwordListener: PasswordListener? = null
    private var paint: Paint? = null
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    constructor(context: Context) : super(context)

    fun getMode(): Mode? {
        return mode
    }

    fun setMode(mode: Mode) {
        this.mode = mode
        postInvalidate()
    }

    enum class Mode(val mode: Int) {
        /**
         * 下划线样式
         */
        UNDERLINE(0),

        /**
         * 边框样式
         */
        RECT(1);


        companion object {

            internal fun formMode(mode: Int): Mode {
                for (m in values()) {
                    if (mode == m.mode) {
                        return m
                    }
                }
                throw IllegalArgumentException()
            }
        }
    }

    /**
     * 当前只支持从xml中构建该控件
     *
     * @param context
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        readAttribute(attrs)
    }

    private fun readAttribute(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordView)
            mode = Mode.formMode(typedArray.getInteger(R.styleable.PasswordView_mode, Mode.UNDERLINE.mode))
            passwordLength = typedArray.getInteger(R.styleable.PasswordView_passwordLength, 6)
            cursorFlashTime = typedArray.getInteger(R.styleable.PasswordView_cursorFlashTime, 500).toLong()
            borderWidth = typedArray.getDimensionPixelSize(R.styleable.PasswordView_borderWidth, dp2px(3f))
            borderColor = typedArray.getColor(R.styleable.PasswordView_borderColor, Color.GRAY)
            cursorColor = typedArray.getColor(R.styleable.PasswordView_cursorColor, Color.GRAY)
            isCursorEnable = typedArray.getBoolean(R.styleable.PasswordView_isCursorEnable, true)
            //如果为边框样式，则padding 默认置为0
            passwordPadding = if (mode == Mode.UNDERLINE) {
                typedArray.getDimensionPixelSize(R.styleable.PasswordView_passwordPadding, dp2px(15f))
            } else {
                typedArray.getDimensionPixelSize(R.styleable.PasswordView_passwordPadding, 0)
            }
            cipherEnable = typedArray.getBoolean(R.styleable.PasswordView_cipherEnable, true)
            typedArray.recycle()
        }
        password = arrayOfNulls(passwordLength)
        init()
    }

    private fun init() {
        isFocusableInTouchMode = true
        isFocusable = true
        setOnKeyListener(MyKeyListener())
        inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        paint = Paint()
        paint!!.isAntiAlias = true
        timerTask = object : TimerTask() {
            override fun run() {
                isCursorShowing = !isCursorShowing
                postInvalidate()
            }
        }
        timer = Timer()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        var width = 0
        when (widthMode) {
            View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.AT_MOST ->
                //没有指定大小，宽度 = 单个密码框大小 * 密码位数 + 密码框间距 *（密码位数 - 1）
                width = passwordSize * passwordLength + passwordPadding * (passwordLength - 1)
            View.MeasureSpec.EXACTLY -> {
                //指定大小，宽度 = 指定的大小
                width = View.MeasureSpec.getSize(widthMeasureSpec)
                //密码框大小等于 (宽度 - 密码框间距 *(密码位数 - 1)) / 密码位数
                passwordSize = (width - passwordPadding * (passwordLength - 1)) / passwordLength
            }
        }
        setMeasuredDimension(width, passwordSize)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //文本大小
        cipherTextSize = passwordSize / 2
        //光标宽度
        cursorWidth = dp2px(2f)
        //光标长度
        cursorHeight = passwordSize / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mode == Mode.UNDERLINE) {
            //绘制下划线
            drawUnderLine(canvas, paint!!)
        } else {
            //绘制方框
            drawRect(canvas, paint!!)
        }
        //绘制光标
        drawCursor(canvas, paint!!)
        //绘制密码文本
        drawCipherText(canvas, paint!!)
    }

    internal inner class MyKeyListener : View.OnKeyListener {

        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            val action = event.action
            if (action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    /**
                     * 删除操作
                     */
                    if (TextUtils.isEmpty(password!![0])) {
                        return true
                    }
                    val deleteText = delete()
                    if (passwordListener != null && !TextUtils.isEmpty(deleteText)) {
                        passwordListener!!.passwordChange(deleteText!!)
                    }
                    postInvalidate()
                    return true
                }
                if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                    /**
                     * 只支持数字
                     */
                    if (isInputComplete) {
                        return true
                    }
                    val addText = add((keyCode - 7).toString() + "")
                    if (passwordListener != null && !TextUtils.isEmpty(addText)) {
                        passwordListener!!.passwordChange(addText!!)
                    }
                    postInvalidate()
                    return true
                }
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    /**
                     * 确认键
                     */
                    if (passwordListener != null) {
                        passwordListener!!.keyEnterPress(getPassword(), isInputComplete)
                    }
                    return true
                }
            }
            return false
        }
    }

    /**
     * 删除
     */
    private fun delete(): String? {
        var deleteText: String? = null
        if (cursorPosition > 0) {
            deleteText = password!![cursorPosition - 1]
            password!![cursorPosition - 1] = ""
            cursorPosition--
        } else if (cursorPosition == 0) {
            deleteText = password!![cursorPosition]
            password!![cursorPosition] = ""
        }
        isInputComplete = false
        return deleteText
    }

    /**
     * 增加
     */
    private fun add(c: String): String? {
        var addText: String? = null
        if (cursorPosition < passwordLength) {
            addText = c
            password?.set(cursorPosition, c)
            cursorPosition++
            if (cursorPosition == passwordLength) {
                isInputComplete = true
                if (passwordListener != null) {
                    passwordListener!!.passwordComplete()
                }
            }
        }
        return addText
    }

    /**
     * 获取密码
     */
    fun getPassword(): String {
        val stringBuffer = StringBuffer()
        for (c in password!!) {
            if (TextUtils.isEmpty(c)) {
                continue
            }
            stringBuffer.append(c)
        }
        return stringBuffer.toString()
    }

    /**
     * 绘制密码替代符号
     *
     * @param canvas
     * @param paint
     */
    private fun drawCipherText(canvas: Canvas, paint: Paint) {
        //画笔初始化
        paint.color = Color.BLACK
        paint.textSize = cipherTextSize.toFloat()
        paint.textAlign = Paint.Align.CENTER
        paint.style = Paint.Style.FILL
        //文字居中的处理
        val r = Rect()
        canvas.getClipBounds(r)
        val cHeight = r.height()
        paint.getTextBounds(CIPHER_TEXT, 0, CIPHER_TEXT.length, r)
        val y = cHeight / 2f + r.height() / 2f - r.bottom

        //根据输入的密码位数，进行for循环绘制
        for (i in password!!.indices) {
            if (!TextUtils.isEmpty(password!![i])) {
                // x = paddingLeft + 单个密码框大小/2 + ( 密码框大小 + 密码框间距 ) * i
                // y = paddingTop + 文字居中所需偏移量
                if (cipherEnable) {
                    //没有开启明文显示，绘制密码密文
                    canvas.drawText(CIPHER_TEXT,
                            (paddingLeft + passwordSize / 2 + (passwordSize + passwordPadding) * i).toFloat(),
                            paddingTop + y, paint)
                } else {
                    //明文显示，直接绘制密码
                    canvas.drawText(password!![i],
                            (paddingLeft + passwordSize / 2 + (passwordSize + passwordPadding) * i).toFloat(),
                            paddingTop + y, paint)
                }
            }
        }
    }

    /**
     * 绘制光标
     *
     * @param canvas
     * @param paint
     */
    private fun drawCursor(canvas: Canvas, paint: Paint) {
        //画笔初始化
        paint.color = cursorColor
        paint.strokeWidth = cursorWidth.toFloat()
        paint.style = Paint.Style.FILL
        //光标未显示 && 开启光标 && 输入位数未满 && 获得焦点
        if (!isCursorShowing && isCursorEnable && !isInputComplete && hasFocus()) {
            // 起始点x = paddingLeft + 单个密码框大小 / 2 + (单个密码框大小 + 密码框间距) * 光标下标
            // 起始点y = paddingTop + (单个密码框大小 - 光标大小) / 2
            // 终止点x = 起始点x
            // 终止点y = 起始点y + 光标高度
            canvas.drawLine((paddingLeft + passwordSize / 2 + (passwordSize + passwordPadding) * cursorPosition).toFloat(),
                    (paddingTop + (passwordSize - cursorHeight) / 2).toFloat(),
                    (paddingLeft + passwordSize / 2 + (passwordSize + passwordPadding) * cursorPosition).toFloat(),
                    (paddingTop + (passwordSize + cursorHeight) / 2).toFloat(),
                    paint)
        }
    }

    /**
     * 绘制密码框下划线
     *
     * @param canvas
     * @param paint
     */
    private fun drawUnderLine(canvas: Canvas, paint: Paint) {
        //画笔初始化
        paint.color = borderColor
        paint.strokeWidth = borderWidth.toFloat()
        paint.style = Paint.Style.FILL
        for (i in 0 until passwordLength) {
            //根据密码位数for循环绘制直线
            // 起始点x为paddingLeft + (单个密码框大小 + 密码框边距) * i , 起始点y为paddingTop + 单个密码框大小
            // 终止点x为 起始点x + 单个密码框大小 , 终止点y与起始点一样不变
            canvas.drawLine((paddingLeft + (passwordSize + passwordPadding) * i).toFloat(), (paddingTop + passwordSize).toFloat(),
                    (paddingLeft + (passwordSize + passwordPadding) * i + passwordSize).toFloat(), (paddingTop + passwordSize).toFloat(),
                    paint)
        }
    }

    private fun drawRect(canvas: Canvas, paint: Paint) {
        paint.color = borderColor
        paint.strokeWidth = 0f
        paint.style = Paint.Style.STROKE
        var rect: Rect
        for (i in 0 until passwordLength) {
            val startX = paddingLeft + (passwordSize + passwordPadding) * i
            val startY = paddingTop
            val stopX = paddingLeft + (passwordSize + passwordPadding) * i + passwordSize
            val stopY = paddingTop + passwordSize
            rect = Rect(startX, startY, stopX, stopY)
            canvas.drawRect(rect, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            /**
             * 弹出软键盘
             */
            requestFocus()
            inputManager!!.showSoftInput(this, InputMethodManager.SHOW_FORCED)
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (!hasWindowFocus) {
            inputManager!!.hideSoftInputFromWindow(this.windowToken, 0)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //cursorFlashTime为光标闪动的间隔时间
        timer!!.scheduleAtFixedRate(timerTask, 0, cursorFlashTime)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timer!!.cancel()
    }

    private fun dp2px(dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun sp2px(spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER //输入类型为数字
        return InnerInputConnecttion(false)//super.onCreateInputConnection(outAttrs),
    }

    fun setPasswordListener(passwordListener: PasswordListener) {
        this.passwordListener = passwordListener
    }

    fun setPasswordSize(passwordSize: Int) {
        this.passwordSize = passwordSize
        postInvalidate()
    }

    fun setPasswordLength(passwordLength: Int) {
        this.passwordLength = passwordLength
        postInvalidate()
    }

    fun setCursorColor(cursorColor: Int) {
        this.cursorColor = cursorColor
        postInvalidate()
    }

    fun setCursorEnable(cursorEnable: Boolean) {
        isCursorEnable = cursorEnable
        postInvalidate()
    }

    fun setCipherEnable(cipherEnable: Boolean) {
        this.cipherEnable = cipherEnable
        postInvalidate()
    }

    @SuppressLint("ParcelCreator")
    internal inner class InnerInputConnecttion(mutable: Boolean) : BaseInputConnection(this, mutable), InputConnection {

        /**
         * 对输入的内容进行拦截
         *
         * @param text
         * @param newCursorPosition
         * @return
         */
        override fun commitText(text: CharSequence, newCursorPosition: Int): Boolean {
            // 只能输入字母或者数字
            return if (!Pattern.compile("[0-9]*").matcher(text.toString()).matches()) {
                false
            } else super.commitText(text, newCursorPosition)
        }
    }

    /**
     * 密码监听者
     */
    interface PasswordListener {
        /**
         * 输入/删除监听
         *
         * @param changeText 输入/删除的字符
         */
        fun passwordChange(changeText: String)

        /**
         * 输入完成
         */
        fun passwordComplete()

        /**
         * 确认键后的回调
         *
         * @param password   密码
         * @param isComplete 是否达到要求位数
         */
        fun keyEnterPress(password: String, isComplete: Boolean)

    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())
        bundle.putStringArray("password", password)
        bundle.putInt("cursorPosition", cursorPosition)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var states = state
        if (states is Bundle) {
            val bundle = states as Bundle?
            password = bundle!!.getStringArray("password")
            cursorPosition = bundle.getInt("cursorPosition")
            states = bundle.getParcelable("superState")
        }
        super.onRestoreInstanceState(states)
    }

    companion object {
        private val CIPHER_TEXT = "*" //密文符号
    }
}

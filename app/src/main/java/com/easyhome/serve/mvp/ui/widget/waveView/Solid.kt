package com.easyhome.serve.mvp.ui.widget.waveView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout




internal class Solid @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var aboveWavePaint: Paint? = null
    private var blowWavePaint: Paint? = null

    init {
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1f
        layoutParams = params
    }

    fun setAboveWavePaint(aboveWavePaint: Paint) {
        this.aboveWavePaint = aboveWavePaint
    }

    fun setBlowWavePaint(blowWavePaint: Paint) {
        this.blowWavePaint = blowWavePaint
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(left.toFloat(), 0F, right.toFloat(), bottom.toFloat(), blowWavePaint)
        canvas.drawRect(left.toFloat(), 0F, right.toFloat(), bottom.toFloat(), aboveWavePaint)
    }
}
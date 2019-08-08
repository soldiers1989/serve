package com.easyhome.serve.mvp.ui.widget.waveView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.easyhome.serve.R


internal class Wave @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = R.attr.waveViewStyle) : View(context, attrs, defStyle) {
    private val WAVE_HEIGHT_LARGE = 16
    private val WAVE_HEIGHT_MIDDLE = 8
    private val WAVE_HEIGHT_LITTLE = 5

    private val WAVE_LENGTH_MULTIPLE_LARGE = 1.5f
    private val WAVE_LENGTH_MULTIPLE_MIDDLE = 1f
    private val WAVE_LENGTH_MULTIPLE_LITTLE = 0.5f

    private val WAVE_HZ_FAST = 0.13f
    private val WAVE_HZ_NORMAL = 0.09f
    private val WAVE_HZ_SLOW = 0.05f

    val DEFAULT_ABOVE_WAVE_ALPHA = 50
    val DEFAULT_BLOW_WAVE_ALPHA = 30

    private val X_SPACE = 20f
    private val PI2 = 2 * Math.PI

    private val mAboveWavePath = Path()
    private val mBlowWavePath = Path()

    val aboveWavePaint = Paint()
    val blowWavePaint = Paint()

    private var mAboveWaveColor: Int = 0
    private var mBlowWaveColor: Int = 0

    private var mWaveMultiple: Float = 0.toFloat()
    private var mWaveLength: Float = 0.toFloat()
    private var mWaveHeight: Int = 0
    private var mMaxRight: Float = 0.toFloat()
    private var mWaveHz: Float = 0.toFloat()

    // wave animation
    private var mAboveOffset = 0.0f
    private var mBlowOffset: Float = 0.toFloat()

    private var mRefreshProgressRunnable: RefreshProgressRunnable? = null

    private var left: Float = 0F
    private var right: Float = 0F
    private var bottom: Float = 0F
    // ω
    private var omega: Double = 0.toDouble()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(mBlowWavePath, blowWavePaint)
        canvas.drawPath(mAboveWavePath, aboveWavePaint)
    }

    fun setAboveWaveColor(aboveWaveColor: Int) {
        this.mAboveWaveColor = aboveWaveColor
    }

    fun setBlowWaveColor(blowWaveColor: Int) {
        this.mBlowWaveColor = blowWaveColor
    }

    fun initializeWaveSize(waveMultiple: Int, waveHeight: Int, waveHz: Int) {
        mWaveMultiple = getWaveMultiple(waveMultiple)
        mWaveHeight = getWaveHeight(waveHeight)
        mWaveHz = getWaveHz(waveHz)
        mBlowOffset = mWaveHeight * 0.4f
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mWaveHeight * 2)
        layoutParams = params
    }

    fun initializePainters() {
        aboveWavePaint.color = mAboveWaveColor
        aboveWavePaint.alpha = DEFAULT_ABOVE_WAVE_ALPHA
        aboveWavePaint.style = Paint.Style.FILL
        aboveWavePaint.isAntiAlias = true

        blowWavePaint.color = mBlowWaveColor
        blowWavePaint.alpha = DEFAULT_BLOW_WAVE_ALPHA
        blowWavePaint.style = Paint.Style.FILL
        blowWavePaint.isAntiAlias = true
    }

    private fun getWaveMultiple(size: Int): Float {
        when (size) {
            WaveView.LARGE -> return WAVE_LENGTH_MULTIPLE_LARGE
            WaveView.MIDDLE -> return WAVE_LENGTH_MULTIPLE_MIDDLE
            WaveView.LITTLE -> return WAVE_LENGTH_MULTIPLE_LITTLE
        }
        return 0f
    }

    private fun getWaveHeight(size: Int): Int {
        when (size) {
            WaveView.LARGE -> return WAVE_HEIGHT_LARGE
            WaveView.MIDDLE -> return WAVE_HEIGHT_MIDDLE
            WaveView.LITTLE -> return WAVE_HEIGHT_LITTLE
        }
        return 0
    }

    private fun getWaveHz(size: Int): Float {
        when (size) {
            WaveView.LARGE -> return WAVE_HZ_FAST
            WaveView.MIDDLE -> return WAVE_HZ_NORMAL
            WaveView.LITTLE -> return WAVE_HZ_SLOW
        }
        return 0f
    }

    /**
     * calculate wave track
     */
    private fun calculatePath() {
        mAboveWavePath.reset()
        mBlowWavePath.reset()

        getWaveOffset()

        var y: Float
        mAboveWavePath.moveTo(left, bottom)
        run {
            var x = 0f
            while (x <= mMaxRight) {
                y = (mWaveHeight * Math.sin(omega * x + mAboveOffset) + mWaveHeight).toFloat()
                mAboveWavePath.lineTo(x, y)
                x += X_SPACE
            }
        }
        mAboveWavePath.lineTo(right, bottom)

        mBlowWavePath.moveTo(left, bottom)
        var x = 0f
        while (x <= mMaxRight) {
            y = (mWaveHeight * Math.sin(omega * x + mBlowOffset) + mWaveHeight).toFloat()
            mBlowWavePath.lineTo(x, y)
            x += X_SPACE
        }
        mBlowWavePath.lineTo(right, bottom)
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (View.GONE == visibility) {
            removeCallbacks(mRefreshProgressRunnable)
        } else {
            removeCallbacks(mRefreshProgressRunnable)
            mRefreshProgressRunnable = RefreshProgressRunnable()
            post(mRefreshProgressRunnable)
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            if (mWaveLength == 0f) {
                startWave()
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mWaveLength == 0f) {
            startWave()
        }
    }

    private fun startWave() {
        if (width != 0) {
            val width = width
            mWaveLength = width * mWaveMultiple
            left = getLeft().toFloat()
            right = getRight().toFloat()
            bottom = (getBottom() + 2).toFloat()
            mMaxRight = right + X_SPACE
            omega = PI2 / mWaveLength
        }
    }

    private fun getWaveOffset() {
        if (mBlowOffset > java.lang.Float.MAX_VALUE - 100) {
            mBlowOffset = 0f
        } else {
            mBlowOffset += mWaveHz
        }

        if (mAboveOffset > java.lang.Float.MAX_VALUE - 100) {
            mAboveOffset = 0f
        } else {
            mAboveOffset += mWaveHz
        }
    }

    private inner class RefreshProgressRunnable : Runnable {
        override fun run() {
            synchronized(this@Wave) {
                val start = System.currentTimeMillis()

                calculatePath()

                invalidate()

                val gap = 16 - (System.currentTimeMillis() - start)
                postDelayed(this, if (gap < 0) 0 else gap)
            }
        }
    }

}
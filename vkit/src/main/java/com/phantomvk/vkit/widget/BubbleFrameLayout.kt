package com.phantomvk.vkit.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class BubbleFrameLayout
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    /**
     * Foreground mask.
     */
    private var mMask = Color.parseColor("#30000000")

    /**
     * Record if the user is touching the layout.
     */
    private var mTouching = false

    /**
     * Intercept touch event.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouching = true
                invalidate()
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mTouching = false
                invalidate()
            }
        }

        return super.onTouchEvent(event)
    }

    /**
     * Draw the foreground mask after dispatchDraw.
     */
    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        // Draw foreground here.
        if (mTouching) canvas.drawColor(mMask)
    }
}
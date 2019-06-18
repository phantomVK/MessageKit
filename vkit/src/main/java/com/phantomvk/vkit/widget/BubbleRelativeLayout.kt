package com.phantomvk.vkit.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.phantomvk.vkit.bubble.BubbleDrawer
import com.phantomvk.vkit.bubble.Direction

class BubbleRelativeLayout
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr), IBubbleLayout {

    /**
     * Foreground mask.
     */
    private val mMask = Color.parseColor("#30000000")

    /**
     * Bubble drawer.
     */
    private val mDrawer = BubbleDrawer()

    /**
     * Record if the user is touching the layout.
     */
    private var mTouching = false

    init {
        // Force ViewGroup to draw.
        setWillNotDraw(false)
    }

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

    override fun draw(canvas: Canvas) {
        val count = canvas.save()
        canvas.clipPath(mDrawer.path)
        super.draw(canvas)
        mDrawer.draw(canvas)
        canvas.restoreToCount(count)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mDrawer.resize(w.toFloat(), h.toFloat())
    }

    override fun setBubbleDirection(@Direction arrowDirection: Int) {
        mDrawer.arrowDirection = arrowDirection
    }
}
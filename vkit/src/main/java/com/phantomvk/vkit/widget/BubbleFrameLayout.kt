/**
 * MIT License
 *
 * Copyright (c) 2019 Wenkang Tan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.phantomvk.vkit.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.phantomvk.vkit.R
import com.phantomvk.vkit.bubble.BubbleDrawer
import com.phantomvk.vkit.bubble.Direction

open class BubbleFrameLayout
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr), IBubbleLayout {

    /**
     * Foreground mask.
     */
    private var mMask: Int

    /**
     * Bubble drawer.
     */
    private val mDrawer = BubbleDrawer()

    /**
     * Record if the user is touching the layout.
     */
    private var mTouching = false

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.BubbleFrameLayout)
        mMask = ta.getColor(R.styleable.BubbleFrameLayout_vkit_color_mask_bubble, 0x30000000)
        ta.recycle()
    }

    /**
     * Force ViewGroup to draw.
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setWillNotDraw(false)
    }

    /**
     * Intercept touch event.
     */
    @SuppressLint("ClickableViewAccessibility")
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
        canvas.clipPath(mDrawer.path)
        super.draw(canvas)
        mDrawer.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mDrawer.resize(w.toFloat(), h.toFloat())
    }

    override fun setBubbleDirection(@Direction arrowDirection: Int) {
        mDrawer.arrowDirection = arrowDirection
    }
}

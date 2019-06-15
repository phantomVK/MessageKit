package com.phantomvk.vkit.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout

class InterceptTouchRelativeLayout
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    /**
     * Intercept all touch events to this layout.
     */
    var intercepted = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return intercepted || super.onInterceptTouchEvent(ev)
    }
}

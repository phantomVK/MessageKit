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

package com.phantomvk.messagekit.listener

import android.app.Activity
import android.graphics.Rect
import android.view.GestureDetector
import android.view.MotionEvent
import com.phantomvk.messagekit.adapter.MessageAdapter
import com.phantomvk.messagekit.adapter.holder.BaseViewHolder
import com.phantomvk.vkit.listener.IMessageItemListener

class OnGestureListener(private val holder: BaseViewHolder,
                        private val listener: IMessageItemListener) :
    GestureDetector.SimpleOnGestureListener() {

    private val location = IntArray(2)
    private var statusBarHeight = 0

    init {
        val rect = Rect()
        val activity = holder.contentView.context as Activity
        activity.window.decorView.getWindowVisibleDisplayFrame(rect)
        statusBarHeight = rect.top
    }

    override fun onLongPress(e: MotionEvent?) {
        holder.contentView.getLocationInWindow(location)
        holder.point.offset(location[0].toFloat(), location[1].toFloat() - statusBarHeight)

        listener.onContentLongClick(
            holder.itemView,
            holder.point,
            holder.adapter,
            holder.layoutPosition)
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        listener.onContentDoubleClick(holder.itemView)
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        val adapter = holder.adapter as MessageAdapter
        val message = adapter.getMessage(holder) ?: return false
        listener.onContentClick(holder.itemView, message)
        return true
    }

    override fun onDown(e: MotionEvent?) = true

    override fun onDoubleTapEvent(e: MotionEvent?) = true

    override fun onSingleTapUp(e: MotionEvent?) = true
}

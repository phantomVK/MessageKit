package com.phantomvk.vkit.listener

import android.view.GestureDetector
import android.view.MotionEvent
import com.phantomvk.vkit.adapter.holder.BaseViewHolder

class OnGestureListener(private val viewHolder: BaseViewHolder,
                        private val listener: IMessageItemListener) : GestureDetector.SimpleOnGestureListener() {

    private val location = IntArray(2)

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        viewHolder.contentView.getLocationInWindow(location)
        viewHolder.point.offset(location[0].toFloat(), location[1].toFloat())

        listener.onContentLongClick(
            viewHolder.itemView,
            viewHolder.point,
            viewHolder.adapter,
            viewHolder.adapterPosition)
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        listener.onContentDoubleClick(viewHolder.itemView)
        return true
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        listener.onContentClick(viewHolder.itemView)
        return true
    }
}
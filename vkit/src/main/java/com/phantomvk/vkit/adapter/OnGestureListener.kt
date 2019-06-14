package com.phantomvk.vkit.adapter

import android.view.GestureDetector
import android.view.MotionEvent
import com.phantomvk.vkit.adapter.holder.BaseViewHolder
import com.phantomvk.vkit.listener.IMessageItemListener

class OnGestureListener(private val viewHolder: BaseViewHolder,
                        private val listener: IMessageItemListener) :
    GestureDetector.SimpleOnGestureListener() {

    private val location = IntArray(2)

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        viewHolder.getContentView().getLocationInWindow(location)
        viewHolder.getPoint().offset(
            location[0].toFloat(),
            location[1].toFloat() - viewHolder.getContentView().measuredHeight)

        listener.onContentLongClick(
            viewHolder.itemView,
            viewHolder.getPoint(),
            viewHolder.getAdapter(),
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
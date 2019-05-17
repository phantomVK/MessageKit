package com.phantomvk.messagekit.tools

import android.app.Activity
import android.graphics.PointF
import android.view.View
import android.view.ViewGroup
import com.phantomvk.vkit.listener.IMessageItemListener

object MessageItemListener : IMessageItemListener {

    override fun onAvatarClick(itemView: View) {
    }

    override fun onAvatarLongClick(itemView: View): Boolean {
        return true
    }

    override fun onContentClick(itemView: View) {
    }

    override fun onContentLongClick(itemView: View, point: PointF, adapterPosition: Int): Boolean {
        return true
    }

    override fun onContentDoubleClick(itemView: View) {
    }

    override fun onContentAction(itemView: View, adapterPosition: Int) {
    }

    override fun onSelectionChanged(isSelecting: Boolean) {
    }

    private fun createPopupMenu(activity: Activity, pointF: PointF) {
        val anchor = View(activity)
        anchor.layoutParams = ViewGroup.LayoutParams(0, 0)
        anchor.x = pointF.x
        anchor.y = pointF.y

        // Add the view to Activity's content.
        activity.findViewById<ViewGroup>(android.R.id.content).addView(anchor)
    }
}

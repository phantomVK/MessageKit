package com.phantomvk.messagekit.tools

import android.app.Activity
import android.graphics.PointF
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.R
import com.phantomvk.vkit.adapter.AbstractMessageAdapter
import com.phantomvk.vkit.listener.IMessageItemListener

class MessageItemListener(private val activity: Activity) : IMessageItemListener {

    override fun onAvatarClick(itemView: View) {
    }

    override fun onAvatarLongClick(itemView: View): Boolean {
        return true
    }

    override fun onContentClick(itemView: View) {
        Toast.makeText(activity, "click", Toast.LENGTH_LONG).show()
    }

    override fun onContentLongClick(itemView: View,
                                    point: PointF,
                                    adapter: AbstractMessageAdapter<RecyclerView.ViewHolder>,
                                    adapterPosition: Int): Boolean {

        val anchor = View(activity)
        anchor.layoutParams = ViewGroup.LayoutParams(0, 0)
        anchor.x = point.x
        anchor.y = point.y

        val root = activity.findViewById<ViewGroup>(android.R.id.content)
        root.addView(anchor)

        val popupMenu = PopupMenu(activity, anchor, Gravity.CENTER)
        popupMenu.menuInflater.inflate(R.menu.vkit_menu_message_long_click, popupMenu.menu)

        popupMenu.setOnDismissListener { menu ->
            root.removeView(anchor)
            menu.dismiss()
        }

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.redact -> {
                    adapter.remove(adapterPosition)
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()

        return true
    }

    override fun onContentDoubleClick(itemView: View) {
    }

    override fun onContentAction(itemView: View, adapterPosition: Int) {
    }

    override fun onSelectionChanged(isSelecting: Boolean) {
    }
}

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

package com.phantomvk.messagekit.tools

import android.app.Activity
import android.graphics.PointF
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.R
import com.phantomvk.vkit.adapter.AbstractMessageAdapter
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.util.toast

class MessageItemListener(private val activity: Activity) : IMessageItemListener {

    override fun onAvatarClick(itemView: View) {
        activity.toast("onAvatarClick")
    }

    override fun onAvatarLongClick(itemView: View): Boolean {
        activity.toast("onAvatarLongClick")
        return true
    }

    override fun onContentClick(itemView: View) {
        activity.toast("onContentClick")
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
        activity.toast("onDoubleTapEvent")
    }

    override fun onContentAction(itemView: View, adapterPosition: Int) {
        activity.toast("onContentAction")
    }

    override fun onContentResent(itemView: View) {
        activity.toast("onContentResent")
    }

    override fun onStatesChanged(isSelecting: Boolean) {
        activity.toast("onStatesChanged, isSelecting: $isSelecting")
    }
}

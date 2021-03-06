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
import android.content.Intent
import android.graphics.PointF
import android.net.Uri
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.model.ImageMessage
import com.phantomvk.messagekit.model.UrlMessage
import com.phantomvk.vkit.adapter.AbstractMessageAdapter
import com.phantomvk.vkit.adapter.AbstractViewHolder
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.util.toast

class MessageItemListener(private val recyclerView: RecyclerView) : IMessageItemListener {

    override fun onAvatarClick(itemView: View) {
        itemView.toast("onAvatarClick")
    }

    override fun onAvatarLongClick(itemView: View): Boolean {
        itemView.toast("onAvatarLongClick")
        return true
    }

    override fun onContentClick(itemView: View, message: IMessage) {
        when (message) {
            is UrlMessage -> {
                Intent(Intent.ACTION_VIEW, Uri.parse(message.url))
                    .run { itemView.context.startActivity(this) }
            }

            is ImageMessage -> {
                Intent().setAction(Intent.ACTION_VIEW)
                    .setDataAndType(Uri.parse(message.url), "image/*")
                    .run { itemView.context.startActivity(this) }
            }

            else -> {
                itemView.toast("onContentClick, body: ${message.getBody()}")
            }
        }
    }

    override fun onContentLongClick(itemView: View,
                                    point: PointF,
                                    adapter: AbstractMessageAdapter<AbstractViewHolder>,
                                    layoutPosition: Int): Boolean {

        val anchor = View(itemView.context)
        anchor.layoutParams = ViewGroup.LayoutParams(0, 0)
        anchor.setWillNotDraw(true)
        anchor.x = point.x
        anchor.y = point.y

        val root = ((itemView.context) as Activity).findViewById<ViewGroup>(android.R.id.content)
        root.addView(anchor)

        val popupMenu = PopupMenu(itemView.context, anchor, Gravity.CENTER)
        popupMenu.menuInflater.inflate(R.menu.vkit_menu_message_long_click, popupMenu.menu)

        popupMenu.setOnDismissListener { menu ->
            menu.dismiss()
            root.removeView(anchor)
        }

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.redact -> adapter.remove(layoutPosition)
                R.id.selection -> adapter.setSelecting(itemView, true)
                R.id.duplicate -> {
                    val msg = adapter.getMessage(layoutPosition)
                    adapter.add(msg ?: return@setOnMenuItemClickListener false, true)
                    recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()

        return true
    }

    override fun onContentDoubleClick(itemView: View) {
        itemView.toast("onDoubleTapEvent")
    }

    override fun onContentAction(itemView: View, layoutPosition: Int) {
        itemView.toast("onContentAction")
    }

    override fun onContentResend(itemView: View) {
        itemView.toast("onContentResend")
    }

    override fun onStatesChanged(itemView: View, isSelecting: Boolean) {
    }
}

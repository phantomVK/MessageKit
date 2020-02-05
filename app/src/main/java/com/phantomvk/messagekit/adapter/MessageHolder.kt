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

package com.phantomvk.messagekit.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.adapter.HolderRegister.HOLDER_NOTICE
import com.phantomvk.messagekit.view.MessagesActivity.Companion.STYLE_ANKO
import com.phantomvk.messagekit.view.MessagesActivity.Companion.STYLE_LAYOUT_INFLATER
import com.phantomvk.messagekit.widget.MessageFrameIncoming
import com.phantomvk.messagekit.widget.MessageFrameOutgoing
import com.phantomvk.messagekit.widget.layout.*
import com.phantomvk.vkit.adapter.AbstractMessageHolder
import com.phantomvk.vkit.adapter.AbstractViewHolder
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.IMessage
import org.jetbrains.anko.AnkoContext
import java.util.*
import kotlin.math.abs

class MessageHolder(inflater: LayoutInflater,
                    itemListener: IMessageItemListener,
                    resLoader: IMessageResLoader,
                    private val style: Int)
    : AbstractMessageHolder<MessageAdapter>(inflater, itemListener, resLoader) {

    /**
     * Formatter
     */
    private val formatter = Formatter(StringBuilder(20), Locale.getDefault())

    /**
     * Bind view.
     */
    override fun onBind(activity: Activity, holder: AbstractViewHolder, message: IMessage) {
        holder.onBind(activity, message)
    }

    /**
     * Get view type by Message's msgType.
     */
    override fun getViewType(message: IMessage, isSender: Boolean): Int {
        val viewType = HolderRegister.getViewType(message.getMsgType())
        return if (isSender) viewType else -viewType
    }

    /**
     * Get view holder.
     */
    override fun getHolder(parent: ViewGroup,
                           viewType: Int,
                           adapter: MessageAdapter): AbstractViewHolder {

        val isHost = viewType > 0
        val absViewType = abs(viewType)
        val config = HolderRegister.getContentType(absViewType)

        return when (style) {
            STYLE_LAYOUT_INFLATER -> xmlStyle(parent, config.layoutId, adapter, config.holder, absViewType, isHost)
            STYLE_ANKO -> ankoStyle(parent, config.layoutId, adapter, config.holder, absViewType, isHost)
            else -> ankoStyle(parent, config.layoutId, adapter, config.holder, absViewType, isHost)
        }
    }

    /**
     * Inflate layouts using LayoutInflater with xml.
     */
    private inline fun xmlStyle(parent: ViewGroup,
                                @LayoutRes layoutResId: Int,
                                adapter: MessageAdapter,
                                holder: (View) -> AbstractViewHolder,
                                viewType: Int,
                                isHost: Boolean): AbstractViewHolder {

        if (viewType == HOLDER_NOTICE) {
            return holder.invoke(inflater.inflate(layoutResId, parent, false))
        }

        val frame = if (isHost) {
            R.layout.vkit_item_msg_frame_outgoing
        } else {
            R.layout.vkit_item_msg_frame_incoming
        }

        val frameView = inflater.inflate(frame, parent, false)
        val container = frameView.findViewById<ViewGroup>(R.id.container)

        // Inflate the body then add to the container.
        val bodyView = inflater.inflate(layoutResId, container, false)
        bodyView.id = R.id.msg_body
        container.addView(bodyView, if (isHost) container.childCount else 0)

        // Init ViewHolder.
        return holder.invoke(frameView).init(isHost, adapter, itemListener, resLoader, formatter)
    }

    /**
     * Experimental, create Views using Anko.
     */
    private inline fun ankoStyle(parent: ViewGroup,
                                 @LayoutRes layoutResId: Int,
                                 adapter: MessageAdapter,
                                 holder: (View) -> AbstractViewHolder,
                                 viewType: Int,
                                 isHost: Boolean): AbstractViewHolder {

        // AnkoContext.
        val ankoContext = AnkoContext.create(parent.context, parent)

        if (viewType == HOLDER_NOTICE) {
            return holder.invoke(NoticeMessageLayout().createView(ankoContext))
        }

        val frame = if (isHost) {
            MessageFrameOutgoing().createView(ankoContext)
        } else {
            MessageFrameIncoming().createView(ankoContext)
        }

        val bodyView = when (layoutResId) {
            R.layout.vkit_layout_msg_text -> TextMessageLayout()
            R.layout.vkit_layout_msg_url -> UrlMessageLayout()
            R.layout.vkit_layout_msg_location -> LocationMessageLayout()
            R.layout.vkit_layout_msg_file -> FileMessageLayout()
            R.layout.vkit_layout_msg_audio -> AudioMessageLayout()
            R.layout.vkit_layout_msg_media -> MediaMessageLayout()
            else -> TextMessageLayout()
        }.createView(AnkoContext.create(parent.context, parent))

        bodyView.id = R.id.msg_body

        val container = frame.findViewById<ViewGroup>(R.id.container)
        container.addView(bodyView, if (isHost) container.childCount else 0)

        // Init ViewHolder.
        return holder.invoke(frame).init(isHost, adapter, itemListener, resLoader, formatter)
    }

    /**
     * TODO: Experimental, create Views using JetPack.
     */
    private fun jetPackStyle() {
    }
}

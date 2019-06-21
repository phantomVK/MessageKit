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

package com.phantomvk.vkit.adapter

import android.app.Activity
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.adapter.holder.*
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.model.Message
import com.phantomvk.vkit.widget.anko.frame.MessageIncomingFrame
import com.phantomvk.vkit.widget.anko.frame.MessageOutgoingFrame
import com.phantomvk.vkit.widget.anko.layout.*
import org.jetbrains.anko.AnkoContext

class MessageHolders(private val mInflater: LayoutInflater,
                     private val mItemListener: IMessageItemListener,
                     private val mResLoader: IMessageResLoader) {

    private val isXmlStyle = true

    /**
     * Bind view.
     */
    fun onBind(activity: Activity, holder: AbstractViewHolder, message: IMessage) {
        holder.onBind(activity, message)
    }

    /**
     * Get view type by Message's msgType.
     */
    fun getViewType(message: IMessage, isSender: Boolean): Int {
        val viewType = sViewType[message.getMsgType()] ?: HOLDER_DEFAULT
        return if (isSender) viewType else -viewType
    }

    /**
     * Get view holder.
     */
    fun getHolder(parent: ViewGroup,
                  viewType: Int,
                  adapter: MessageAdapter): AbstractViewHolder {

        val isHost = viewType > 0
        val absViewType = Math.abs(viewType)
        val config = sContentTypes.get(absViewType)

        return if (config != null) {
            getHolder(parent, config.layoutId, adapter, config.holder, absViewType, isHost)
        } else {
            getHolder(parent, R.layout.vkit_layout_msg_text, adapter, ::TextViewHolder, absViewType, isHost)
        }
    }

    /**
     * Get view holder.
     */
    private fun getHolder(parent: ViewGroup,
                          @LayoutRes layoutResId: Int,
                          adapter: MessageAdapter,
                          holder: (View) -> AbstractViewHolder,
                          viewType: Int,
                          isHost: Boolean): AbstractViewHolder {

        return if (viewType == HOLDER_NOTICE) {
            holder.invoke(mInflater.inflate(layoutResId, parent, false))
        } else {
            if (isXmlStyle) {
                xmlStyle(isHost, parent, layoutResId, adapter, holder)
            } else {
                dslStyle(isHost, parent, layoutResId, adapter, holder)
            }
        }
    }

    private fun xmlStyle(isHost: Boolean, parent: ViewGroup, layoutResId: Int, adapter: MessageAdapter,
                         holder: (View) -> AbstractViewHolder): AbstractViewHolder {
        val frame = if (isHost) {
            R.layout.vkit_item_msg_frame_outgoing
        } else {
            R.layout.vkit_item_msg_frame_incoming
        }
        val frameView = mInflater.inflate(frame, parent, false)

        val container = frameView.findViewById<LinearLayout>(R.id.container)

        // Inflate the body then add to the container.
        val bodyView = mInflater.inflate(layoutResId, container, false)
        bodyView.id = R.id.msg_body
        container.addView(bodyView, if (isHost) container.childCount else 0)

        // Init ViewHolder.
        return holder.invoke(frameView).init(isHost, adapter, mItemListener, mResLoader)
    }

    private fun dslStyle(isHost: Boolean, parent: ViewGroup, layoutResId: Int, adapter: MessageAdapter,
                         holder: (View) -> AbstractViewHolder): AbstractViewHolder {
        val frame = if (isHost) {
            MessageOutgoingFrame<ViewGroup>().createView(AnkoContext.create(parent.context, parent))
        } else {
            MessageIncomingFrame<ViewGroup>().createView(AnkoContext.create(parent.context, parent))
        }

        val container = frame.findViewById<LinearLayout>(R.id.container)

        val bodyView = when (layoutResId) {
            R.layout.vkit_layout_msg_url -> UrlMessageLayout<ViewGroup>()
            R.layout.vkit_layout_msg_notice -> NoticeMessageLayout<ViewGroup>()
            R.layout.vkit_layout_msg_file -> FileMessageLayout()
            R.layout.vkit_layout_msg_audio -> AudioMessageLayout()
            R.layout.vkit_layout_msg_media -> MediaMessageLayout()
            R.layout.vkit_layout_msg_location -> LocationMessageLayout()
            else -> TextMessageLayout()
        }.createView(AnkoContext.create(parent.context, parent))

        bodyView.id = R.id.msg_body
        container.addView(bodyView, if (isHost) container.childCount else 0)

        // Init ViewHolder.
        return holder.invoke(frame).init(isHost, adapter, mItemListener, mResLoader)
    }

    companion object {
        private const val HOLDER_DEFAULT = 1
        private const val HOLDER_TEXT = 2
        private const val HOLDER_URL = 3
        private const val HOLDER_LOCATION = 4
        private const val HOLDER_NOTICE = 5
        private const val HOLDER_FILE = 6
        private const val HOLDER_AUDIO = 7
        private const val HOLDER_IMAGE = 8
        private const val HOLDER_VIDEO = 9

        /**
         * Get view type id by message's type string.
         */
        private val sViewType = HashMap<String, Int>().apply {
            put(Message.MESSAGE_TYPE_TEXT, HOLDER_TEXT)
            put(Message.MESSAGE_TYPE_URL, HOLDER_URL)
            put(Message.MESSAGE_TYPE_LOCATION, HOLDER_LOCATION)
            put(Message.MESSAGE_TYPE_NOTICE, HOLDER_NOTICE)
            put(Message.MESSAGE_TYPE_FILE, HOLDER_FILE)
            put(Message.MESSAGE_TYPE_AUDIO, HOLDER_AUDIO)
            put(Message.MESSAGE_TYPE_IMAGE, HOLDER_IMAGE)
            put(Message.MESSAGE_TYPE_VIDEO, HOLDER_VIDEO)
        }

        /**
         * Content types array.
         *
         * Use SparseArray<HolderConfig>() instead of HashMap<int, HolderConfig>() to save memory.
         */
        private val sContentTypes by lazy(LazyThreadSafetyMode.NONE) {
            val textConfig = HolderConfig(R.layout.vkit_layout_msg_text, ::TextViewHolder, 15)
            val urlConfig = HolderConfig(R.layout.vkit_layout_msg_url, ::UrlViewHolder, 10)
            val locationConfig = HolderConfig(R.layout.vkit_layout_msg_location, ::LocationViewHolder, 8)
            val noticeConfig = HolderConfig(R.layout.vkit_layout_msg_notice, ::NoticeViewHolder, 8, true)
            val fileConfig = HolderConfig(R.layout.vkit_layout_msg_file, ::FileViewHolder, 11)
            val audioConfig = HolderConfig(R.layout.vkit_layout_msg_audio, ::AudioViewHolder, 14)
            val mediaConfig = HolderConfig(R.layout.vkit_layout_msg_media, ::MediaViewHolder, 8)

            return@lazy SparseArray<HolderConfig>().apply {
                put(HOLDER_DEFAULT, textConfig)
                put(HOLDER_TEXT, textConfig)
                put(HOLDER_URL, urlConfig)
                put(HOLDER_LOCATION, locationConfig)
                put(HOLDER_NOTICE, noticeConfig)
                put(HOLDER_FILE, fileConfig)
                put(HOLDER_AUDIO, audioConfig)
                put(HOLDER_IMAGE, mediaConfig)
                put(HOLDER_VIDEO, mediaConfig)
            }
        }

        /**
         * Set max scrap.
         */
        fun setMaxScrap(view: RecyclerView?) {
            if (view == null) return
            val size = sContentTypes.size()
            for (i in 0 until size) {
                val viewId = sContentTypes.keyAt(i)
                val config = sContentTypes.get(viewId)
                if (config.unique) {
                    view.recycledViewPool.setMaxRecycledViews(viewId, config.maxScrap)
                } else {
                    view.recycledViewPool.setMaxRecycledViews(viewId, config.maxScrap)
                    view.recycledViewPool.setMaxRecycledViews(-viewId, config.maxScrap)
                }
            }
        }
    }
}

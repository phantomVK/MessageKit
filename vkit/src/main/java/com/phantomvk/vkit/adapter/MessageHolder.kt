package com.phantomvk.vkit.adapter

import android.content.Context
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

class MessageHolders(private val mInflater: LayoutInflater,
                     private val mItemListener: IMessageItemListener,
                     private val mResLoader: IMessageResLoader) {
    /**
     * Get view holder.
     */
    fun getHolder(parent: ViewGroup, viewType: Int, resLoader: IMessageResLoader): AbstractViewHolder {
        val isSender = viewType > 0
        val absViewType = Math.abs(viewType)
        val config = sContentTypes.get(absViewType)

        return if (config != null) {
            getHolder(parent, config.layoutId, config.holder, absViewType, isSender)
        } else {
            getHolder(parent, R.layout.vkit_layout_msg_text, ::TextViewHolder, absViewType, isSender)
        }
    }

    /**
     * Get view holder.
     */
    private fun getHolder(parent: ViewGroup, @LayoutRes layout: Int,
                          holder: (View) -> AbstractViewHolder,
                          viewType: Int,
                          isSender: Boolean): AbstractViewHolder {
        return when (viewType) {
            HOLDER_NOTICE -> holder.invoke(mInflater.inflate(layout, parent, false))

            // Text, Image, Video, Audio and etc.
            else -> {
                // Inflate the frame then find the container.
                val frame =
                        if (isSender) R.layout.vkit_item_msg_frame_outgoing else R.layout.vkit_item_msg_frame_incoming
                val frameView = mInflater.inflate(frame, parent, false)
                val container = frameView.findViewById<LinearLayout>(R.id.container)

                // Inflate the body and add to the container.
                val bodyView = mInflater.inflate(layout, container, false)
                bodyView.id = R.id.msg_body
                container.addView(bodyView, if (isSender) container.childCount else 0)
                // Init ViewHolder.
                holder.invoke(frameView).init(isSender, mItemListener, mResLoader)
            }
        }
    }

    /**
     * Bind view.
     */
    fun onBind(context: Context, holder: RecyclerView.ViewHolder, message: IMessage) {
        (holder as AbstractViewHolder).onBind(context, message)
    }

    /**
     * Get view type by Message's msgType.
     */
    fun getViewType(message: IMessage, isSender: Boolean): Int {
        val viewType = sViewType[message.getMsgType()] ?: HOLDER_DEFAULT
        return if (isSender) viewType else -viewType
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
         */
        private val sContentTypes = SparseArray<HolderConfig>().apply {
            put(MessageHolders.HOLDER_DEFAULT, HolderConfig(R.layout.vkit_layout_msg_text, ::TextViewHolder))
            put(MessageHolders.HOLDER_TEXT, HolderConfig(R.layout.vkit_layout_msg_text, ::TextViewHolder))
            put(MessageHolders.HOLDER_URL, HolderConfig(R.layout.vkit_layout_msg_url, ::UrlViewHolder))
//            put(MessageHolders.HOLDER_LOCATION, HolderConfig(R.layout.vkit_layout_message_location, ::LocationViewHolder))
            put(MessageHolders.HOLDER_NOTICE, HolderConfig(R.layout.vkit_layout_msg_notice, ::NoticeViewHolder, true))
//            put(MessageHolders.HOLDER_FILE, HolderConfig(R.layout.vkit_layout_message_file, ::FileViewHolder))
//            put(MessageHolders.HOLDER_AUDIO, HolderConfig(R.layout.vkit_layout_message_audio, ::AudioViewHolder))
            put(MessageHolders.HOLDER_IMAGE, HolderConfig(R.layout.vkit_layout_msg_media, ::MediaViewHolder))
            put(MessageHolders.HOLDER_VIDEO, HolderConfig(R.layout.vkit_layout_msg_media, ::MediaViewHolder))
        }

        /**
         * Set mac recycled views count.
         */
        fun setMaxRecycledViews(view: RecyclerView?) {
            if (view == null) return
            val size = sContentTypes.size()
            for (i in 0 until size) {
                val viewId = sContentTypes.keyAt(i)
                val config = sContentTypes.get(viewId)
                val max = config.maxRecycledViews

                if (config.unique) {
                    view.recycledViewPool.setMaxRecycledViews(viewId, max)
                } else {
                    view.recycledViewPool.setMaxRecycledViews(viewId, max)
                    view.recycledViewPool.setMaxRecycledViews(-viewId, max)
                }
            }
        }
    }
}
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
    fun getHolder(parent: ViewGroup,
                  viewType: Int,
                  adapter: AbstractMessageAdapter<RecyclerView.ViewHolder>): AbstractViewHolder {

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
                          adapter: AbstractMessageAdapter<RecyclerView.ViewHolder>,
                          holder: (View) -> AbstractViewHolder,
                          viewType: Int,
                          isHost: Boolean): AbstractViewHolder {

        return if (viewType == HOLDER_NOTICE) {
            holder.invoke(mInflater.inflate(layoutResId, parent, false))
        } else {
            // Text, Image, Video, Audio and etc.
            // Inflate the frame then find the container.
            val framework = if (isHost) {
                R.layout.vkit_item_msg_frame_outgoing
            } else {
                R.layout.vkit_item_msg_frame_incoming
            }

            val frameView = mInflater.inflate(framework, parent, false)
            val container = frameView.findViewById<LinearLayout>(R.id.container)

            // Inflate the body then add to the container.
            val bodyView = mInflater.inflate(layoutResId, container, false)
            bodyView.id = R.id.msg_body
            container.addView(bodyView, if (isHost) container.childCount else 0)

            // Init ViewHolder.
            holder.invoke(frameView).init(isHost, adapter, mItemListener, mResLoader)
        }
    }

    /**
     * Bind view.
     */
    fun onBind(context: Context, holder: AbstractViewHolder, message: IMessage) {
        holder.onBind(context, message)
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
         *
         * Use SparseArray<HolderConfig> instead of HashMap<int, HolderConfig> for less memory consuming.
         */
        private val sContentTypes by lazy(LazyThreadSafetyMode.NONE) {
            val textConfig = HolderConfig(R.layout.vkit_layout_msg_text, ::TextViewHolder)
            val mediaConfig = HolderConfig(R.layout.vkit_layout_msg_media, ::MediaViewHolder)
            val urlConfig = HolderConfig(R.layout.vkit_layout_msg_url, ::UrlViewHolder)
            val locationConfig = HolderConfig(R.layout.vkit_layout_msg_location, ::LocationViewHolder)
            val noticeConfig = HolderConfig(R.layout.vkit_layout_msg_notice, ::NoticeViewHolder, true)
            val fileConfig = HolderConfig(R.layout.vkit_layout_msg_file, ::FileViewHolder)

            return@lazy SparseArray<HolderConfig>().apply {
                put(HOLDER_DEFAULT, textConfig)
                put(HOLDER_TEXT, textConfig)
                put(HOLDER_URL, urlConfig)
                put(HOLDER_LOCATION, locationConfig)
                put(HOLDER_NOTICE, noticeConfig)
                put(HOLDER_FILE, fileConfig)
//            put(HOLDER_AUDIO, HolderConfig(R.layout.vkit_layout_message_audio, ::AudioViewHolder))
                put(HOLDER_IMAGE, mediaConfig)
                put(HOLDER_VIDEO, mediaConfig)
            }
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

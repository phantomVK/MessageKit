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

import android.util.SparseArray
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.adapter.holder.*
import com.phantomvk.messagekit.model.Message
import com.phantomvk.vkit.adapter.HolderConfig

/**
 * Register ViewHolders.
 */
object HolderRegister {
    private const val HOLDER_DEFAULT = 1
    private const val HOLDER_TEXT = 2
    private const val HOLDER_URL = 3
    private const val HOLDER_LOCATION = 4
    const val HOLDER_NOTICE = 5
    private const val HOLDER_FILE = 6
    private const val HOLDER_AUDIO = 7
    private const val HOLDER_IMAGE = 8
    private const val HOLDER_VIDEO = 9

    /**
     * Get view type id by message's type string.
     */
    @JvmStatic
    private val sViewType = HashMap<String, Int>(8)

    /**
     * Content types array.
     *
     * Use SparseArray<HolderConfig>() instead of HashMap<int, HolderConfig>() to save memory.
     */
    @JvmStatic
    private val sContentTypes = SparseArray<HolderConfig>(9)

    init {
        sViewType.apply {
            put(Message.MESSAGE_TYPE_TEXT, HOLDER_TEXT)
            put(Message.MESSAGE_TYPE_URL, HOLDER_URL)
            put(Message.MESSAGE_TYPE_LOCATION, HOLDER_LOCATION)
            put(Message.MESSAGE_TYPE_NOTICE, HOLDER_NOTICE)
            put(Message.MESSAGE_TYPE_FILE, HOLDER_FILE)
            put(Message.MESSAGE_TYPE_AUDIO, HOLDER_AUDIO)
            put(Message.MESSAGE_TYPE_IMAGE, HOLDER_IMAGE)
            put(Message.MESSAGE_TYPE_VIDEO, HOLDER_VIDEO)
        }

        val textConfig = HolderConfig(R.layout.vkit_layout_msg_text, ::TextViewHolder, 15)
        val urlConfig = HolderConfig(R.layout.vkit_layout_msg_url, ::UrlViewHolder, 10)
        val locationConfig = HolderConfig(R.layout.vkit_layout_msg_location, ::LocationViewHolder, 8)
        val noticeConfig = HolderConfig(R.layout.vkit_layout_msg_notice, ::NoticeViewHolder, 8, true)
        val fileConfig = HolderConfig(R.layout.vkit_layout_msg_file, ::FileViewHolder, 11)
        val audioConfig = HolderConfig(R.layout.vkit_layout_msg_audio, ::AudioViewHolder, 14)
        val mediaConfig = HolderConfig(R.layout.vkit_layout_msg_media, ::MediaViewHolder, 8)

        sContentTypes.apply {
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

    fun register(msgType: String, holderId: Int, @LayoutRes layoutId: Int, config: HolderConfig) {
        sViewType[msgType] = holderId
        sContentTypes.put(layoutId, config)
    }

    fun getViewType(msgType: String): Int {
        return sViewType[msgType] ?: HOLDER_DEFAULT
    }

    fun getContentType(@IntRange(from = 0) absViewType: Int): HolderConfig {
        return sContentTypes.get(absViewType) ?: sContentTypes[HOLDER_DEFAULT]
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

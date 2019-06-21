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

package com.phantomvk.vkit.adapter.holder

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.adapter.MessageAdapter
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.IMessage

/**
 * The abstract ViewHolder for messages to implement.
 */
abstract class AbstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * Tells if I'm the sender of current message.
     */
    protected var mIsHost: Boolean = false

    /**
     * Message item click listener.
     */
    protected lateinit var mItemListener: IMessageItemListener

    /**
     * Message resource loader.
     */
    protected lateinit var mResLoader: IMessageResLoader

    /**
     * For adding, removing, replacing items.
     */
    lateinit var messageAdapter: MessageAdapter

    /**
     * Init resources and setup configurations only once when holder is created.
     */
    abstract fun onHolderCreated()

    /**
     * Bind message to current item.
     */
    abstract fun onBind(activity: Activity, message: IMessage)

    /**
     * Init params.
     */
    open fun init(isHost: Boolean,
                  adapter: MessageAdapter,
                  listener: IMessageItemListener,
                  resLoader: IMessageResLoader): AbstractViewHolder {

        mIsHost = isHost
        mItemListener = listener
        mResLoader = resLoader
        messageAdapter = adapter
        onHolderCreated()
        return this
    }
}

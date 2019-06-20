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

import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.model.IMessage

abstract class AbstractMessageAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    /**
     * Call when activity or fragment pauses to save battery by stopping the playing medias.
     */
    open fun onPause() {}

    /**
     * Add a new message to the adapter and refresh.
     */
    abstract fun add(message: IMessage)

    /**
     * Add a new message to the adapter, no refreshing.
     */
    abstract fun add(message: IMessage, refresh: Boolean)

    /**
     * Add new messages to the adapter and refresh.
     */
    abstract fun addAll(messages: List<IMessage>)

    /**
     * Add a new message at the front of adapter.
     */
    abstract fun addToFront(message: IMessage, refresh: Boolean)

    /**
     * Remove a message from the adapter by adapter position.
     */
    abstract fun remove(adapterPos: Int)

    /**
     * Clear all messages from the adapter.
     */
    abstract fun clear()

    /**
     * Start or finish the selecting mode.
     */
    abstract fun setSelecting(isSelecting: Boolean)

    /**
     * Get the selecting status.
     */
    abstract fun getSelecting(): Boolean
}

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

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.adapter.holder.BaseViewHolder
import com.phantomvk.vkit.model.IMessage

abstract class AbstractMessageAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    /**
     * Call when activity or fragment pauses, in order to save battery by stopping the playing media.
     */
    open fun onPause() {}

    /**
     * Add a new message to the adapter.
     */
    abstract fun add(message: IMessage, refresh: Boolean = false)

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
     * Start or finish the selecting mode, notify items range changed.
     */
    abstract fun setSelecting(isSelecting: Boolean, positionStart: Int, positionLast: Int)

    /**
     * Start or finish the selecting mode.
     */
    abstract fun setSelecting(itemView: View, isSelecting: Boolean)

    /**
     * Start or finish the selecting mode, notify items range changed.
     */
    abstract fun setSelecting(itemView: View, isSelecting: Boolean, positionStart: Int, positionLast: Int)

    /**
     * Get the selecting states.
     */
    abstract fun getSelecting(): Boolean

    /**
     * Change item selected states.
     */
    abstract fun onItemSelectedChange(position: Int, isSelected: Boolean, message: IMessage?)

    /**
     * Get all selected items.
     */
    abstract fun getSelectedItems(): List<IMessage>

    /**
     * Tells if the item has been selected.
     */
    abstract fun isItemSelected(index: Int): Boolean

    /**
     * Clear all selected items.
     */
    abstract fun clearSelectedItems()

    /**
     * Get message from adapter by adapter position.
     */
    abstract fun getMessage(position: Int): IMessage?

    /**
     * Get message from adapter by ViewHolder.
     */
    abstract fun getMessage(holder: BaseViewHolder): IMessage?
}

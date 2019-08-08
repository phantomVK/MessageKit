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
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.core.util.contains
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.adapter.AbstractMessageAdapter
import com.phantomvk.vkit.adapter.AbstractMessageHolder
import com.phantomvk.vkit.adapter.AbstractViewHolder
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.util.displayDensity
import com.phantomvk.vkit.util.displaySize

open class MessageAdapter(private val activity: Activity,
                          private val itemListener: IMessageItemListener,
                          private val holders: AbstractMessageHolder<MessageAdapter>)
    : AbstractMessageAdapter<AbstractViewHolder>() {

    /**
     * All received messages.
     */
    private val mMessages = ArrayList<IMessage>()

    private val displayDensity = activity.displayDensity()

    /**
     * Min size for displaying thumbnail.
     */
    val minSize = 48 * displayDensity

    /**
     * Max size for displaying thumbnail.
     */
    val maxSize = 134 * displayDensity

    /**
     * Max width pixel for displaying ImageMessage.
     */
    val maxImageWidth: Float

    /**
     * Max height pixel for displaying ImageMessage.
     */
    val maxImageHeight: Float

    /**
     * Start messages selecting.
     */
    private var selecting: Boolean = false

    /**
     * Selected items.
     */
    private val selectedItems = SparseArray<IMessage>()

    init {
        val point = activity.displaySize()

        // Init only once.
        if (point.x < point.y) { // landscape
            maxImageWidth = point.x * 0.6F
            maxImageHeight = point.y * 0.4F
        } else { // portrait.
            maxImageWidth = point.x * 0.4F
            maxImageHeight = point.y * 0.6F
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        return holders.getHolder(parent, viewType, this)
    }

    override fun getItemId(position: Int): Long {
        return mMessages[position].hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return mMessages.size
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holders.onBind(activity, holder, mMessages[position])
    }

    override fun getItemViewType(position: Int): Int {
        val message = mMessages[position]
        return holders.getViewType(message, isHost("Austin", message))
    }

    override fun add(message: IMessage, refresh: Boolean) {
        mMessages.add(message)
        if (refresh) notifyItemInserted(mMessages.size - 1)
    }

    override fun addAll(messages: List<IMessage>) {
        mMessages.addAll(messages)
        notifyItemRangeInserted(mMessages.size, messages.size)
    }

    override fun addToFront(message: IMessage, refresh: Boolean) {
        mMessages.add(0, message)
        if (refresh) notifyItemInserted(0)
    }

    override fun remove(adapterPos: Int) {
        mMessages.removeAt(adapterPos)
        notifyItemRemoved(adapterPos)
    }

    private fun isHost(currUserId: String, message: IMessage): Boolean {
        return currUserId == message.getSender()
    }

    override fun clear() {
        val count = mMessages.size
        mMessages.clear()
        mMessages.trimToSize()
        notifyItemRangeRemoved(0, count)
    }

    override fun getMessage(position: Int): IMessage? {
        return if (position == -1) null else mMessages[position]
    }

    override fun getMessage(holder: AbstractViewHolder): IMessage? {
        return mMessages[holder.layoutPosition]
    }

    override fun setSelecting(isSelecting: Boolean) {
        if (selecting == isSelecting) return

        selecting = isSelecting
        notifyDataSetChanged()
    }

    override fun setSelecting(isSelecting: Boolean, positionStart: Int, positionLast: Int) {
        if (selecting == isSelecting) return
        selecting = isSelecting

        if (positionStart != RecyclerView.NO_POSITION) {
            val itemCount = positionLast - positionStart + 1
            notifyItemRangeChanged(positionStart, itemCount)
        }
    }

    override fun setSelecting(itemView: View, isSelecting: Boolean) {
        if (selecting == isSelecting) return

        selecting = isSelecting
        itemListener.onStatesChanged(itemView, selecting)
        notifyDataSetChanged()
    }

    override fun setSelecting(itemView: View, isSelecting: Boolean,
                              positionStart: Int, positionLast: Int) {

        if (selecting == isSelecting) return

        selecting = isSelecting
        itemListener.onStatesChanged(itemView, selecting)

        if (positionStart != RecyclerView.NO_POSITION) {
            val itemCount = positionLast - positionStart + 1
            notifyItemRangeChanged(positionStart, itemCount)
        }
    }

    override fun getSelecting() = selecting

    override fun onItemSelectedChange(position: Int, isSelected: Boolean, message: IMessage?) {
        if (isSelected) {
            selectedItems.remove(position)
        } else {
            selectedItems.put(position, message)
        }
    }

    override fun getSelectedItems(): List<IMessage> {
        val size = selectedItems.size()
        val selected = ArrayList<IMessage>(size)
        for (index in 0 until size) {
            selected.add(selectedItems.valueAt(index))
        }
        return selected
    }

    override fun isItemSelected(index: Int): Boolean {
        return selectedItems.contains(index)
    }

    override fun clearSelectedItems() {
        selectedItems.clear()
    }
}

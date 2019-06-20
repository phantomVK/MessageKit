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
import android.content.Context
import android.graphics.Point
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.adapter.holder.AbstractViewHolder
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.IMessage

open class MessageAdapter(private val mActivity: Activity,
                          private val mItemListener: IMessageItemListener,
                          resLoader: IMessageResLoader) : AbstractMessageAdapter<RecyclerView.ViewHolder>() {

    /**
     * Message holders to inflate view by message's type.
     */
    private val mHolders = MessageHolders(mActivity.layoutInflater, mItemListener, resLoader)

    /**
     * All received messages.
     */
    private val mMessages = ArrayList<IMessage>()

    /**
     * Min size for displaying thumbnail.
     */
    val minSize = 54 * mActivity.resources.displayMetrics.density

    /**
     * Max size for displaying thumbnail.
     */
    val maxSize = 134 * mActivity.resources.displayMetrics.density

    /**
     * Max width pixel for displaying ImageMessage.
     */
    val maxImageWidth: Int

    /**
     * Max height pixel for displaying ImageMessage.
     */
    val maxImageHeight: Int

    /**
     * Start messages selecting.
     */
    private var selecting: Boolean = false

    /**
     * Selected items.
     */
    private val selectedItems = ArrayList<IMessage>()

    init {
        val point = Point()
        (mActivity.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(point)

        // Init only once.
        if (point.x < point.y) { // landscape
            maxImageWidth = Math.round(point.x * 0.6F)
            maxImageHeight = Math.round(point.y * 0.4F)
        } else { // portrait.
            maxImageWidth = Math.round(point.x * 0.4F)
            maxImageHeight = Math.round(point.y * 0.6F)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mHolders.getHolder(parent, viewType, this)
    }

    override fun getItemCount(): Int {
        return mMessages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mHolders.onBind(mActivity, holder as AbstractViewHolder, mMessages[position])
    }

    override fun getItemViewType(position: Int): Int {
        val message = mMessages[position]
        return mHolders.getViewType(message, isHost("Mike", message))
    }

    override fun add(message: IMessage) {
        add(message, false)
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

    private fun isHost(userId: String, message: IMessage): Boolean {
        return userId == message.getSender()
    }

    override fun clear() {
        val count = mMessages.size
        mMessages.clear()
        notifyItemRangeRemoved(0, count)
    }

    override fun setSelecting(isSelecting: Boolean) {
        if (selecting != isSelecting) {
            selecting = isSelecting

            if (!isSelecting) {
                selectedItems.clear()
            }

            notifyDataSetChanged()

            mItemListener.onStatesChanged(selecting)
        }
    }

    override fun getSelecting(): Boolean {
        return selecting
    }
}

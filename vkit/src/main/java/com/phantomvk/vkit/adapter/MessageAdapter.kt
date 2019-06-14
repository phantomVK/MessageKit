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
                          resLoader: IMessageResLoader)
    : AbstractMessageAdapter<RecyclerView.ViewHolder>() {

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
    val minSize = 48 * mActivity.resources.displayMetrics.density

    /**
     * Max size for displaying thumbnail.
     */
    val maxSize = 134 * mActivity.resources.displayMetrics.density

    /**
     * Max width pixel for displaying ImageMessage.
     */
    var maxImageWidth: Int = 0

    /**
     * Max height pixel for displaying ImageMessage.
     */
    var maxImageHeight: Int = 0

    /**
     * Start messages selecting.
     */
    private var selecting: Boolean = false

    init {
        val point = Point(0, 0)
        (mActivity.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(point)

        if (point.x < point.y) {
            // landscape
            maxImageWidth = Math.round(point.x * 0.6F)
            maxImageHeight = Math.round(point.y * 0.4F)
        } else {
            // portrait.
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
        return mHolders.getViewType(message, isSender("Mike", message))
    }

    override fun add(message: IMessage) {
        add(message, false)
    }

    override fun add(message: IMessage, refresh: Boolean) {
        mMessages.add(message)
        if (refresh) notifyItemInserted(mMessages.size - 1)
    }

    override fun addToFront(message: IMessage, refresh: Boolean) {
        mMessages.add(0, message)
        if (refresh) notifyItemInserted(0)
    }

    override fun remove(adapterPos: Int) {
        mMessages.removeAt(adapterPos)
        notifyItemRemoved(adapterPos)
    }

    private fun isSender(userId: String, message: IMessage): Boolean {
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
                // TODO: Clear all selected messages in the pending queue.
            }

            notifyDataSetChanged()

            mItemListener.onSelectionChanged(selecting)
        }
    }

    override fun getSelecting(): Boolean {
        return selecting
    }
}

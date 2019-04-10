package com.phantomvk.vkit.adapter

import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.IMessage

class MessageAdapter(context: Context, inflater: LayoutInflater, private val resLoader: IMessageResLoader? = null) :
    AbstractMessageAdapter<RecyclerView.ViewHolder>() {
    /**
     * Message holders to inflate view by message's type.
     */
    private val mHolders = MessageHolders(inflater)

    /**
     * All received messages.
     */
    private val mMessages = ArrayList<IMessage>()

    /**
     * Min size for displaying thumbnail.
     */
    val minSize = 48 * context.resources.displayMetrics.density

    /**
     * Max size for displaying thumbnail.
     */
    val maxSize = 134 * context.resources.displayMetrics.density

    /**
     * Max width pixel for displaying ImageMessage.
     */
    var maxImageWidth: Int = 0

    /**
     * Max height pixel for displaying ImageMessage.
     */
    var maxImageHeight: Int = 0

    init {
        val point = Point(0, 0)
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(point)

        if (point.x < point.y) { // landscape
            maxImageWidth = Math.round(point.x * 0.6F)
            maxImageHeight = Math.round(point.y * 0.4F)
        } else { // portrait.
            maxImageWidth = Math.round(point.x * 0.4F)
            maxImageHeight = Math.round(point.y * 0.6F)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mHolders.getHolder(parent, viewType, resLoader)
    }

    override fun getItemCount(): Int {
        return mMessages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mHolders.onBind(holder, mMessages[position])
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

    override fun remove(message: IMessage) {
        mMessages.remove(message)
    }

    private fun isSender(userId: String, message: IMessage): Boolean {
        return userId == message.getSender()
    }

    override fun clear() {
        val count = mMessages.size
        mMessages.clear()
        notifyItemRangeRemoved(0, count)
    }
}

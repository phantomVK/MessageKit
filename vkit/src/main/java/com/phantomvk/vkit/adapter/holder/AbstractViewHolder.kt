package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.listener.IMessageItemListener
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.IMessage

/**
 * The abstract class of ViewHolder for all incoming or outgoing messages.
 */
abstract class AbstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * Tels if I am the sender of the current message.
     */
    protected var isSender: Boolean = false

    /**
     * Message item click listener.
     */
    protected var messageItemListener: IMessageItemListener? = null

    /**
     * Message resource loader.
     */
    protected var messageResLoader: IMessageResLoader? = null

    /**
     * Context.
     */
    protected val context: Context = itemView.context

    /**
     * Bind a message to current item.
     */
    abstract fun onBind(message: IMessage)

    /**
     * Init params.
     */
    open fun init(
        sender: Boolean,
        listener: IMessageItemListener? = null,
        resLoader: IMessageResLoader? = null
    ): AbstractViewHolder {
        isSender = sender
        messageItemListener = listener
        messageResLoader = resLoader
        return this
    }
}

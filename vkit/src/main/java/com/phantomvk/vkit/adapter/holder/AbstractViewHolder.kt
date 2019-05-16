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
    protected lateinit var messageItemListener: IMessageItemListener

    /**
     * Message resource loader.
     */
    protected lateinit var messageResLoader: IMessageResLoader

    /**
     * Bind a message to current item.
     */
    abstract fun onBind(context: Context, message: IMessage)

    /**
     * Init params.
     */
    open fun init(sender: Boolean,
                  listener: IMessageItemListener,
                  resLoader: IMessageResLoader): AbstractViewHolder {
        isSender = sender
        messageItemListener = listener
        messageResLoader = resLoader
        return this
    }
}

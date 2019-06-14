package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.adapter.AbstractMessageAdapter
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
    protected var mIsHost: Boolean = false

    /**
     * Message item click listener.
     */
    protected lateinit var mMessageItemListener: IMessageItemListener

    /**
     * Message resource loader.
     */
    protected lateinit var mMessageResLoader: IMessageResLoader

    /**
     * Adapter
     */
    protected lateinit var mAdapter: AbstractMessageAdapter<RecyclerView.ViewHolder>

    /**
     * Init params.
     */
    open fun init(isHost: Boolean,
                  adapter: AbstractMessageAdapter<RecyclerView.ViewHolder>,
                  listener: IMessageItemListener,
                  resLoader: IMessageResLoader): AbstractViewHolder {

        mIsHost = isHost
        mAdapter = adapter
        mMessageItemListener = listener
        mMessageResLoader = resLoader
        onInit()
        return this
    }

    /**
     * Init resources or setup configurations only once.
     */
    abstract fun onInit()

    /**
     * Bind a message to current item.
     */
    abstract fun onBind(context: Context, message: IMessage)
}

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
     * Add a new message to the adapter,no refreshing.
     */
    abstract fun add(message: IMessage, refresh: Boolean)

    /**
     * Add a new message at the front of adapter.
     */
    abstract fun addToFront(message: IMessage, refresh: Boolean)

    /**
     * Remove a message from the adapter.
     */
    abstract fun remove(message: IMessage)

    /**
     * Clear all messages from the adapter.
     */
    abstract fun clear()
}

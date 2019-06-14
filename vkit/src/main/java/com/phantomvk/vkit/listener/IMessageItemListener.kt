package com.phantomvk.vkit.listener

import android.graphics.PointF
import android.view.View

/**
 * Message item listener.
 */
interface IMessageItemListener {
    /**
     * Click on the user avatar.
     */
    fun onAvatarClick(itemView: View)

    /**
     * Long click on the user avatar.
     */
    fun onAvatarLongClick(itemView: View): Boolean

    /**
     * Click on the message content.
     */
    fun onContentClick(itemView: View)

    /**
     * Long click on the message content.
     */
    fun onContentLongClick(itemView: View, point: PointF, adapterPosition: Int): Boolean

    /**
     * Double click on the message content.
     */
    fun onContentDoubleClick(itemView: View)

    /**
     * The action of the long click content.
     */
    fun onContentAction(itemView: View, adapterPosition: Int)

    /**
     * Selection mode changed callback.
     */
    fun onSelectionChanged(isSelecting: Boolean)
}

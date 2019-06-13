package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage

/**
 * The base ViewHolder includes some basic views for all kinds of messages.
 * Use {@link AbstractViewHolder} instead if this class is not suitable.
 */
open class BaseViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    /**
     * Message date.
     */
    protected var date: TextView = itemView.findViewById(R.id.date)

    /**
     * Message user avatar.
     */
    protected var avatar: ImageView = itemView.findViewById(R.id.avatar)

    /**
     * Message selection checkbox.
     */
    protected var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

    /**
     * Message content view.
     */
    protected var contentView: View = itemView.findViewById(R.id.msg_body)

    /**
     * Message audio playing progressbar.
     */
    protected var progressBarView: ProgressBar? = itemView.findViewById(R.id.progress_bar)

    /**
     * Message user username.
     */
    protected var username: TextView? = itemView.findViewById(R.id.username)

    /**
     * Message resend button.
     */
    protected var resendView: ImageView? = itemView.findViewById(R.id.resend)

    override fun onInit() {
    }

    /**
     * Template Pattern to bind ViewHolder.
     */
    override fun onBind(context: Context, message: IMessage) {
        loadAvatar(context)
        setDisplayName(message)
    }

    /**
     * Set user avatar. Override this if needed.
     */
    open fun loadAvatar(context: Context) {
        messageResLoader.loadAvatar(context, 0, avatar)
    }

    /**
     * Set user display name. Override this if needed.
     */
    open fun setDisplayName(message: IMessage) {
        username?.text = message.getSender()
    }
}

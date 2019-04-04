package com.phantomvk.vkit.adapter.holder

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
    protected var date: TextView? = itemView.findViewById(R.id.date)

    /**
     * Message user avatar.
     */
    protected var avatar: ImageView = itemView.findViewById(R.id.avatar)

    /**
     * Message selection checkbox.
     */
    protected var checkBox: CheckBox? = itemView.findViewById(R.id.checkbox)

    /**
     * Message user username.
     */
    protected var username: TextView? = itemView.findViewById(R.id.username)

    /**
     * Message content view.
     */
    protected var contentView: View = itemView.findViewById(R.id.msg_body)

    /**
     * Message audio playing progressbar.
     */
    protected var progressBarView: ProgressBar? = itemView.findViewById(R.id.progress_bar)

    /**
     * Message resend button.
     */
    protected var resendView: ImageView? = itemView.findViewById(R.id.resend)

    /**
     * Template Pattern to bind ViewHolder.
     */
    override fun onBind(message: IMessage) {
        loadAvatar()
        setDisplayName(message)
        setBackground()
    }

    /**
     * Set user avatar. Override this if needed.
     */
    open fun loadAvatar() {
    }

    /**
     * Set user display name. Override this if needed.
     */
    open fun setDisplayName(message: IMessage) {
    }

    /**
     * Set message view background.
     */
    open fun setBackground() {
    }
}

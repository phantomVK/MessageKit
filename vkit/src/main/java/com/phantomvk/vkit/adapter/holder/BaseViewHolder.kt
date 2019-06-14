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
    protected var mDateView: TextView = itemView.findViewById(R.id.date)

    /**
     * Message user avatar.
     */
    protected var mAvatarView: ImageView = itemView.findViewById(R.id.avatar)

    /**
     * Message selection checkbox.
     */
    protected var mCheckBox: CheckBox = itemView.findViewById(R.id.checkbox)

    /**
     * Message content view.
     */
    protected var mContentView: View = itemView.findViewById(R.id.msg_body)

    /**
     * Message audio playing progressbar.
     */
    protected var mProgressBarView: ProgressBar? = itemView.findViewById(R.id.progress_bar)

    /**
     * Message user username.
     */
    protected var mUsername: TextView? = itemView.findViewById(R.id.username)

    /**
     * Message resend button.
     */
    protected var mResendView: ImageView? = itemView.findViewById(R.id.resend)

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
        mMessageResLoader.loadAvatar(context, 0, mAvatarView)
    }

    /**
     * Set user display name. Override this if needed.
     */
    open fun setDisplayName(message: IMessage) {
        mUsername?.text = message.getSender()
    }
}

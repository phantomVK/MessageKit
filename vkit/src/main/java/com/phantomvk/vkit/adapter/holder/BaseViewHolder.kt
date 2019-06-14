package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.graphics.PointF
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.adapter.OnGestureListener
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

    /**
     * For long click popup menu.
     */
    protected val mPoint = PointF()

    /**
     * GestureDetector for SingleTap
     */
    private lateinit var mGestureDetector: GestureDetector

    override fun onInit() {
        mAvatarView.setOnClickListener { mMessageItemListener.onAvatarClick(itemView) }
        mAvatarView.setOnLongClickListener { mMessageItemListener.onAvatarLongClick(itemView) }

        val l = OnGestureListener(this, mMessageItemListener)
        mGestureDetector = GestureDetector(itemView.context, l)

        mContentView.setOnLongClickListener { return@setOnLongClickListener false }
        mContentView.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                mPoint.set(event.x, event.y)
            }

            mGestureDetector.onTouchEvent(event)
            return@setOnTouchListener false
        }
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

    fun getContentView() = mContentView

    fun getPoint() = mPoint

    fun getAdapter() = mAdapter
}

package com.phantomvk.vkit.adapter.holder

import android.app.Activity
import android.content.Context
import android.graphics.PointF
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.phantomvk.vkit.R
import com.phantomvk.vkit.bubble.Direction
import com.phantomvk.vkit.listener.OnGestureListener
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.util.dip
import com.phantomvk.vkit.widget.IBubbleLayout
import com.phantomvk.vkit.widget.InterceptTouchRelativeLayout

/**
 * The base ViewHolder includes some basic views for all kinds of messages.
 * Use {@link AbstractViewHolder} instead if this class is not suitable.
 */
open class BaseViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    /**
     * Message date.
     */
    protected val mDateView: TextView = itemView.findViewById(R.id.date)

    /**
     * Message user avatar.
     */
    protected val mAvatarView: ImageView = itemView.findViewById(R.id.avatar)

    /**
     * Message selection checkbox.
     */
    protected val mCheckBox: CheckBox = itemView.findViewById(R.id.checkbox)

    /**
     * Message content view.
     */
    val contentView: View = itemView.findViewById(R.id.msg_body)

    /**
     * Message audio playing progressbar.
     */
    protected val mProgressBarView: ProgressBar? = itemView.findViewById(R.id.progress_bar)

    /**
     * Message user username.
     */
    protected val mUsername: TextView? = itemView.findViewById(R.id.username)

    /**
     * Message resend button.
     */
    protected val mResendView: ImageView? = itemView.findViewById(R.id.resend)

    /**
     * For long click popup menu.
     */
    val point = PointF()

    /**
     * The selecting mode in this view holder.
     */
    protected var holderSelecting = false

    /**
     * GestureDetector for SingleTap
     */
    private lateinit var mGestureDetector: GestureDetector

    final override fun onHolderCreated() {
        setItemListener()
        setLayoutBubble()
    }

    /**
     * Template Pattern to bind ViewHolder.
     */
    override fun onBind(activity: Activity, message: IMessage) {
        loadAvatar(activity)
        setDisplayName(message)
        selectingMode()
    }

    /**
     * Set user avatar. Override this if needed.
     */
    open fun loadAvatar(context: Context) {
        mResLoader.loadAvatar(context, 0, mAvatarView)
    }

    /**
     * Set user display name. Override this if needed.
     */
    open fun setDisplayName(message: IMessage) {
        mUsername?.text = message.getSender()
    }

    private fun selectingMode() {
        val adapterSelecting = adapter.getSelecting()

        if (holderSelecting != adapterSelecting) {
            holderSelecting = adapterSelecting
            mCheckBox.isVisible = adapterSelecting
            (itemView as InterceptTouchRelativeLayout).intercepted = adapterSelecting

            if (adapterSelecting) {
                itemView.setOnClickListener { mCheckBox.isChecked = !mCheckBox.isChecked }
            } else {
                itemView.setOnClickListener(null)
            }
        }
    }

    /**
     * Set all kinds of click listeners to the item.
     */
    open fun setItemListener() {
        mAvatarView.setOnClickListener { mItemListener.onAvatarClick(itemView) }
        mAvatarView.setOnLongClickListener { mItemListener.onAvatarLongClick(itemView) }

        val l = OnGestureListener(this, mItemListener)
        mGestureDetector = GestureDetector(itemView.context, l)

        contentView.setOnLongClickListener { return@setOnLongClickListener false }
        contentView.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) point.set(event.x, event.y)
            mGestureDetector.onTouchEvent(event)
            return@setOnTouchListener false
        }
    }

    open fun setLayoutBubble() {
        val direction = if (mIsHost) Direction.END else Direction.START
        (contentView as IBubbleLayout).setBubbleDirection(direction)

        val paddingLeft = if (mIsHost) 0 else itemView.context.dip(5)
        val paddingRight = if (mIsHost) itemView.context.dip(5) else 0
        contentView.setPadding(paddingLeft, 0, paddingRight, 0)
    }
}

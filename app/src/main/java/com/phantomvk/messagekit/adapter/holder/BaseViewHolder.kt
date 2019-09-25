/**
 * MIT License
 *
 * Copyright (c) 2019 Wenkang Tan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.phantomvk.messagekit.adapter.holder

import android.app.Activity
import android.content.Context
import android.graphics.PointF
import android.text.format.DateUtils
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IntRange
import androidx.core.view.isVisible
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.listener.OnGestureListener
import com.phantomvk.vkit.adapter.AbstractViewHolder
import com.phantomvk.vkit.bubble.Direction
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.widget.IBubbleLayout
import com.phantomvk.vkit.widget.InterceptedRelativeLayout
import org.jetbrains.anko.dip
import java.util.*

/**
 * The base ViewHolder includes some basic views for all kinds of messages.
 * Use {@link AbstractViewHolder} as super class instead if this class is not suitable for you.
 */
open class BaseViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    /**
     * Message date.
     */
    private val mDateView: TextView = itemView.findViewById(R.id.date)

    /**
     * Message user avatar.
     */
    private val mAvatarView: ImageView = itemView.findViewById(R.id.avatar)

    /**
     * Message selection checkbox.
     */
    private val mCheckBox: CheckBox = itemView.findViewById(R.id.checkbox)

    /**
     * Message content view.
     */
    val contentView: View = itemView.findViewById(R.id.msg_body)

    /**
     * Message sending progressbar.
     */
    private val mSendingView: ProgressBar? = itemView.findViewById(R.id.sending)

    /**
     * Message user username.
     */
    private val mUsername: TextView? = itemView.findViewById(R.id.username)

    /**
     * Message resending button.
     */
    private val mResendView: ImageView? = itemView.findViewById(R.id.resend)

    /**
     * For long click popup menu.
     */
    val point = PointF()

    /**
     * Do NOT override this method.
     */
    final override fun onCreate() {
        setResendListener(mResendView)
        setItemListener(mAvatarView)
        setLayoutBubble()
    }

    /**
     * Set listener to resend view.
     */
    open fun setResendListener(view: ImageView?) {
        view?.setOnClickListener { itemListener.onContentResend(itemView) }
    }

    /**
     * Set all kinds of click listeners to the item, overridden by subclasses.
     */
    open fun setItemListener(view: ImageView) {
        view.setOnClickListener { itemListener.onAvatarClick(itemView) }
        view.setOnLongClickListener { itemListener.onAvatarLongClick(itemView) }

        val l = OnGestureListener(this, itemListener)
        val detector = GestureDetector(itemView.context, l)

        contentView.setOnLongClickListener { return@setOnLongClickListener false }
        contentView.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) point.set(event.x, event.y)
            detector.onTouchEvent(event)
            return@setOnTouchListener false
        }

        itemView.setOnClickListener {
            if (!sHolderSelecting) return@setOnClickListener
            val isSelected = mCheckBox.isChecked
            mCheckBox.isChecked = !isSelected
            adapter.onItemSelectedChange(layoutPosition, isSelected, null)
        }
    }

    open fun setLayoutBubble() {
        val paddingLeft: Int
        val paddingRight: Int

        if (isHost) {
            paddingLeft = 0
            paddingRight = itemView.context.dip(5)
            (contentView as IBubbleLayout).setBubbleDirection(Direction.END)
        } else {
            paddingLeft = itemView.context.dip(5)
            paddingRight = 0
            (contentView as IBubbleLayout).setBubbleDirection(Direction.START)
        }

        contentView.setPadding(paddingLeft, 0, paddingRight, 0)
    }

    /**
     * Template Pattern to bind ViewHolder, overridden by subclasses.
     */
    override fun onBind(activity: Activity, message: IMessage) {
        loadAvatar(activity, message)
        setDisplayName(message)
        setSelecting(adapter.getSelecting())
        setDateView(mDateView, message)
        sendingStates(0)
    }

    /**
     * Load user avatar, overridden by subclasses.
     */
    open fun loadAvatar(context: Context, message: IMessage) {
        resLoader.loadAvatar(context, message.getSender(), mAvatarView)
    }

    /**
     * Set user display name, overridden by subclasses.
     */
    open fun setDisplayName(message: IMessage) {
        mUsername?.text = message.getSender()
    }

    /**
     * ItemView blocked all touch events if adapterSelecting is true.
     */
    private fun setSelecting(adapterSelecting: Boolean) {
        (itemView as InterceptedRelativeLayout).intercepted = adapterSelecting
        sHolderSelecting = adapterSelecting
        mCheckBox.isVisible = adapterSelecting
        if (adapterSelecting) {
            mCheckBox.isChecked = adapter.isItemSelected(layoutPosition)
        }
    }

    /**
     * -1: Resendable.
     *  0: Sent.
     *  1: Sending.
     */
    open fun sendingStates(@IntRange(from = -1, to = 1) states: Int) {
        mSendingView?.isVisible = (states == 1)
        mResendView?.isVisible = (states == -1)
    }

    /**
     * Set text to date view. Timestamps used below are millisecond and must not be negative.
     *
     * View visible:
     *     1. Current message timestamp is #MSG_SPAN larger than the previous;
     *     2. The message is the last one, also has been sent more then #MSG_SPAN;
     *     3. The message has been redacted, just shows the timestamp of redaction.
     *
     * View gone:
     *     All other conditions.
     *
     * Variable:
     *     preTs     previous message timestamp
     *     msgTs     current message timestamp
     *     sysTs     system current timestamp
     *     tsSpan    messages timestamp span, must more than 1s
     *     redacted  current message is redacted
     *
     * @param message current message
     */
    open fun setDateView(view: TextView, message: IMessage) {
        val msgTs = message.getTimestamp()
        val preTs = adapter.getMessage(layoutPosition - 1)?.getTimestamp() ?: 0
        val sysTs = System.currentTimeMillis()

        if (msgTs == 0L) {
            view.isVisible = false
        } else if ((msgTs - preTs > MSG_SPAN)
            || (layoutPosition == adapter.itemCount - 1 && sysTs - msgTs > MSG_SPAN)) {
            view.isVisible = true
            view.text = getDateText(itemView.context, msgTs, sysTs)
        } else {
            view.isVisible = false
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun getDateText(context: Context, ts: Long, sysTs: Long): String {
        sCalendar.timeInMillis = ts
        val interval = sysTs - sCalendar.timeInMillis

        return when {
            DateUtils.DAY_IN_MILLIS > interval -> {
                DateUtils.formatDateTime(context, ts, DateUtils.FORMAT_SHOW_TIME)
            }

            DateUtils.DAY_IN_MILLIS * 2 > interval -> {
                val yesterday = DateUtils.getRelativeTimeSpanString(ts, sysTs,
                    DateUtils.DAY_IN_MILLIS,
                    DateUtils.FORMAT_SHOW_DATE).toString()

                yesterday + DateUtils.formatDateTime(context, ts, DateUtils.FORMAT_SHOW_TIME)
            }

            DateUtils.WEEK_IN_MILLIS > interval -> {
                DateUtils.formatDateTime(context, ts,
                    DateUtils.FORMAT_SHOW_TIME
                            or DateUtils.FORMAT_SHOW_WEEKDAY
                            or DateUtils.FORMAT_ABBREV_ALL)
            }

            else -> {
                DateUtils.formatDateTime(context, ts,
                    DateUtils.FORMAT_SHOW_TIME
                            or DateUtils.FORMAT_SHOW_DATE
                            or DateUtils.FORMAT_SHOW_YEAR)
            }
        }
    }

    private companion object {
        /**
         * Instance used by all ViewHolders in order to reduce memory usage.
         *
         * Warning: Used in UiThread only.
         */
        @JvmStatic
        private val sCalendar = GregorianCalendar().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        /**
         * Default message timestamp span in millisecond.
         */
        private const val MSG_SPAN = 3 * 60 * 1000L // 3min.

        /**
         * Current mode of this view holder.
         */
        private var sHolderSelecting = false
    }
}

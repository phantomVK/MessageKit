package com.phantomvk.vkit.adapter.holder

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.phantomvk.vkit.model.IMessage
import kotlinx.android.synthetic.main.vkit_layout_msg_notice.view.*

class NoticeViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    /**
     * Notice text.
     */
    private val mText: TextView = itemView.notice

    /**
     * Empty implementation.
     */
    override fun onHolderCreated() {
    }

    override fun onBind(activity: Activity, message: IMessage) {
        mText.text = message.getBody()
    }
}

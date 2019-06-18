package com.phantomvk.vkit.adapter.holder

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.bubble.getStateListDrawable
import com.phantomvk.vkit.model.IMessage

class TextViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * TextView.
     */
    private val mText: TextView = itemView.findViewById(R.id.text)

    override fun onHolderCreated() {
        mText.background = getStateListDrawable(itemView.context, mIsHost)
        setItemListener()
    }

    override fun onBind(activity: Activity, message: IMessage) {
        super.onBind(activity, message)
        mText.text = message.getBody()
    }
}

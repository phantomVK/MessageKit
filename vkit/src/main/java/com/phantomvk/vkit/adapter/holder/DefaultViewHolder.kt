package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage

/**
 * For any unsupported message.
 */
class DefaultViewHolder(itemView: View) : TextViewHolder(itemView) {

    override fun onBind(context: Context, message: IMessage) {
        super.onBind(context, message)
        mText.text = "The message is not supported."
    }
}
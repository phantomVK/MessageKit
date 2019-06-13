package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import com.phantomvk.vkit.model.IMessage

/**
 * For any not supported message.
 */
class DefaultViewHolder(itemView: View) : TextViewHolder(itemView) {

    override fun onBind(context: Context, message: IMessage) {
        mText.text = "The message is not supported."
    }
}
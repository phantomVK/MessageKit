package com.phantomvk.vkit.adapter.holder

import android.view.View
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage

class TextViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * TextView.
     */
    private val text: TextView = itemView.findViewById(R.id.text)

    override fun onBind(message: IMessage) {
        super.onBind(message)
        text.text = message.getBody()
    }
}
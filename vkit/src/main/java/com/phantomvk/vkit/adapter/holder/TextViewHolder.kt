package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage

open class TextViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * TextView.
     */
    protected val mText: TextView = itemView.findViewById(R.id.text)

    override fun onBind(context: Context, message: IMessage) {
        super.onBind(context, message)
        mText.text = message.getBody()
    }
}
package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.model.UrlMessage

class UrlViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * Website title, required.
     */
    private val mTitle: TextView = itemView.findViewById(R.id.title)

    /**
     * Website icon, required.
     */
    private val mImage: ImageView = itemView.findViewById(R.id.image)

    /**
     * Website domain, required.
     */
    private val mSource: TextView = itemView.findViewById(R.id.source)

    /**
     * Website description, optional.
     */
    private val mDescription: TextView = itemView.findViewById(R.id.description)

    override fun onBind(context: Context, message: IMessage) {
        super.onBind(context, message)
        val urlMessage = message as UrlMessage
        mTitle.text = urlMessage.title
        mSource.text = urlMessage.source ?: urlMessage.domain ?: ""
        mDescription.text = urlMessage.description
        messageResLoader.loadImage(context, "", mImage)
    }
}

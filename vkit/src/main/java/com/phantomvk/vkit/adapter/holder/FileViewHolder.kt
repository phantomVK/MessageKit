package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.FileMessage
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.util.FileUtil

class FileViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private val mImage: ImageView = itemView.findViewById(R.id.image)

    private val mName: TextView = itemView.findViewById(R.id.name)

    private val mSize: TextView = itemView.findViewById(R.id.size)

    private val mSource: TextView = itemView.findViewById(R.id.source)

    private val mCover: ProgressBar = itemView.findViewById(R.id.cover)

    override fun onBind(context: Context, message: IMessage) {
        super.onBind(context, message)
        val msg = message as FileMessage
        mName.text = msg.getBody()
        mSize.text = FileUtil.formatFileSize(msg.size)
        mSource.text = "Website"
        mResLoader.loadImage(itemView.context, "", mImage)
    }
}
package com.phantomvk.vkit.adapter.holder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.FileMessage
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.util.FileUtil

class FileViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * File type icon image.
     */
    private val mImage: ImageView = itemView.findViewById(R.id.image)

    /**
     * File name.
     */
    private val mName: TextView = itemView.findViewById(R.id.name)

    /**
     * File size.
     */
    private val mSize: TextView = itemView.findViewById(R.id.size)

    /**
     * The source of file.
     */
    private val mSource: TextView = itemView.findViewById(R.id.source)

    /**
     * File download or upload progress cover.
     */
    private val mCover: ProgressBar = itemView.findViewById(R.id.cover)

    override fun onBind(activity: Activity, message: IMessage) {
        super.onBind(activity, message)
        val msg = message as FileMessage
        mName.text = msg.getBody()
        mSize.text = FileUtil.formatFileSize(msg.size)
        mSource.text = "Website"
        mResLoader.loadImage(activity, "", mImage)
    }
}
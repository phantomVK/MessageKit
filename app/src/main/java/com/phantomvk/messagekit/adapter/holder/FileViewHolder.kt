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
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.model.FileMessage
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.util.FileUtil

class FileViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * File type icon, required.
     */
    private val mImage: ImageView = itemView.findViewById(R.id.image)

    /**
     * File name, required.
     */
    private val mName: TextView = itemView.findViewById(R.id.name)

    /**
     * File size, required.
     */
    private val mSize: TextView = itemView.findViewById(R.id.size)

    /**
     * The source of file comes from, optional.
     */
    private val mSource: TextView = itemView.findViewById(R.id.source)

    /**
     * File download or upload progress bar cover, optional.
     */
    private val mCover: ProgressBar = itemView.findViewById(R.id.cover)

    override fun onBind(activity: Activity, message: IMessage) {
        super.onBind(activity, message)
        val msg = message as FileMessage
        mName.text = msg.getBody()
        mSize.text = FileUtil.formatFileSize(msg.size)
        mSource.text = "Website"
        resLoader.loadImage(activity, "", mImage)
    }
}

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

package com.phantomvk.vkit.adapter.holder

import android.app.Activity
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

    override fun onBind(activity: Activity, message: IMessage) {
        super.onBind(activity, message)
        val msg = message as UrlMessage
        mTitle.text = msg.title
        mSource.text = msg.source ?: msg.domain ?: ""
        mDescription.text = msg.description
        mResLoader.loadImage(activity, msg.image, mImage)
    }
}

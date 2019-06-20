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
import android.graphics.Point
import android.view.View
import android.widget.ImageView
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.model.Message
import kotlinx.android.synthetic.main.vkit_layout_msg_media.view.*

class MediaViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * ImageView to display a thumbnail.
     */
    private val mImageView: ImageView = itemView.image

    /**
     * An icon to indicate that this is a VideoMessage.
     */
    private val mIconPlay: ImageView = itemView.play

    override fun onBind(activity: Activity, message: IMessage) {
        val msgType = message.getMsgType()
        if (msgType == Message.MESSAGE_TYPE_IMAGE) {
            mIconPlay.visibility = View.GONE
        } else {
            mIconPlay.visibility = View.VISIBLE
        }
    }

    /**
     * Calculate the size of thumbnail ImageView.
     */
    private fun resize(point: Point,
                       width: Int, height: Int,
                       maxWidth: Int, maxHeight: Int,
                       minSize: Float, maxSize: Float) {
        var scale = if (width / height > maxWidth / maxHeight) {
            maxWidth.toFloat() / width
        } else {
            maxHeight.toFloat() / height
        }

        var maxScale = maxSize / (if (width > height) width else height)
        maxScale = Math.max(1F, maxScale)

        scale = Math.min(maxScale, scale)
        point.x = Math.max(minSize, width * scale).toInt()
        point.y = Math.max(minSize, height * scale).toInt()
    }
}

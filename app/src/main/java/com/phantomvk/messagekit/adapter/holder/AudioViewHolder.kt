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
import com.phantomvk.messagekit.model.AudioMessage
import com.phantomvk.vkit.model.IMessage
import kotlinx.android.synthetic.main.vkit_layout_msg_audio.view.*
import kotlinx.android.synthetic.main.vkit_view_msg_audio_progressbar.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AudioViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * Play icon.
     */
    private val playIcon: ImageView = itemView.play

    /**
     * Progress bar.
     */
    private val progressBar: ProgressBar = itemView.progress

    /**
     * Duration.
     */
    private val duration: TextView = itemView.duration

    /**
     * SimpleDateFormat
     */
    private val dateFormat: DateFormat = SimpleDateFormat("mm:ss", Locale.ROOT)

    init {
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    }

    override fun onBind(activity: Activity, message: IMessage) {
        super.onBind(activity, message)

        val msg = message as AudioMessage
        duration.text = dateFormat.format(msg.duration)
    }
}

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
import android.widget.TextView
import com.phantomvk.vkit.adapter.AbstractViewHolder
import com.phantomvk.vkit.model.IMessage
import kotlinx.android.synthetic.main.vkit_layout_msg_notice.view.*

class NoticeViewHolder(itemView: View) : AbstractViewHolder(itemView) {
    /**
     * Notice text.
     */
    private val mText: TextView = itemView.notice

    /**
     * Override as an empty implementation.
     */
    override fun onCreate() {
    }

    override fun onBind(activity: Activity, message: IMessage) {
        mText.text = message.getBody()
    }
}

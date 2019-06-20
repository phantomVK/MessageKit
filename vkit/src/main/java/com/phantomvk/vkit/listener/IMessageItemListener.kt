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

package com.phantomvk.vkit.listener

import android.graphics.PointF
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.vkit.adapter.AbstractMessageAdapter

/**
 * Message item listener.
 */
interface IMessageItemListener {
    /**
     * Click on the user avatar.
     */
    fun onAvatarClick(itemView: View)

    /**
     * Long click on the user avatar.
     */
    fun onAvatarLongClick(itemView: View): Boolean

    /**
     * Click on the message content.
     */
    fun onContentClick(itemView: View)

    /**
     * Long click on the message content.
     */
    fun onContentLongClick(itemView: View,
                           point: PointF,
                           adapter: AbstractMessageAdapter<RecyclerView.ViewHolder>,
                           adapterPosition: Int): Boolean

    /**
     * Double click on the message content.
     */
    fun onContentDoubleClick(itemView: View)

    /**
     * The action of the long click content.
     */
    fun onContentAction(itemView: View, adapterPosition: Int)

    /**
     * Selection mode changed callback.
     */
    fun onStatesChanged(isSelecting: Boolean)
}

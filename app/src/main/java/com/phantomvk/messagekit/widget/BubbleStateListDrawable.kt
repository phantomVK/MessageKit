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

package com.phantomvk.messagekit.widget

import android.content.Context
import android.graphics.drawable.StateListDrawable
import androidx.core.content.ContextCompat
import com.phantomvk.messagekit.R
import com.phantomvk.vkit.bubble.BubbleShape
import com.phantomvk.vkit.bubble.Direction
import com.phantomvk.vkit.util.dip
import com.phantomvk.vkit.widget.BubbleStateListDrawable

fun getStateListDrawable(context: Context, isHost: Boolean): StateListDrawable {
    val colorRes = if (isHost) R.color.vkit_color_host_solid else R.color.vkit_color_guest_solid
    val color = ContextCompat.getColor(context, colorRes)

    val strokeColorRes = if (isHost) R.color.vkit_color_host_stroke else R.color.vkit_color_guest_stroke
    val strokeColor = ContextCompat.getColor(context, strokeColorRes)

    val direction = if (isHost) Direction.END else Direction.START

    val shape = BubbleShape(direction,
        context.dip(6F),
        context.dip(12F),
        context.dip(4F),
        context.dip(0.5F),
        context.dip(10F),
        color, strokeColor)

    return BubbleStateListDrawable(context, shape)
}
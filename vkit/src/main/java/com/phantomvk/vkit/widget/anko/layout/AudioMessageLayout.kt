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

package com.phantomvk.vkit.widget.anko.layout

import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.phantomvk.vkit.R
import org.jetbrains.anko.*

class AudioMessageLayout<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        bubbleLinearLayout {
            lparams(ViewGroup.LayoutParams.WRAP_CONTENT, dip(40))
            gravity = Gravity.CENTER_VERTICAL
            orientation = LinearLayout.HORIZONTAL
            topPadding = dip(10)
            bottomPadding = dip(10)

            imageView {
                id = R.id.play
                imageResource = R.drawable.vkit_ic_play_audio
            }.lparams(width = dip(18), height = dip(18)) {
                marginStart = dip(10)
            }

            // Warning: Something wrong with setting progress bar style using Anko.
            // To fix this bug, you'd better include view from xml.
            progressBar {
                id = R.id.progress
                progressDrawable = ContextCompat.getDrawable(context, android.R.drawable.progress_horizontal)
                indeterminateDrawable = ContextCompat.getDrawable(context, R.drawable.vkit_list_progress_bar)
            }.lparams(width = dip(100), height = dip(2)) {
                marginStart = dip(6)
            }

            textView {
                id = R.id.duration
                text = "00:12"
                textColor = ContextCompat.getColor(context, R.color.vkit_color_host_stroke)
                textSize = 11f //sp
            }.lparams {
                marginStart = dip(6)
                marginEnd = dip(10)
            }
        }
    }
}
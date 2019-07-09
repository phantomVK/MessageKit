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

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.phantomvk.vkit.R
import org.jetbrains.anko.*

class FileMessageLayout : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        bubbleRelativeLayout {
            lparams(dip(232), ViewGroup.LayoutParams.WRAP_CONTENT)

            imageView {
                id = R.id.image
            }.lparams(width = dip(40), height = dip(40)) {
                alignParentEnd()
                topMargin = dip(10)
                marginEnd = dip(10)
            }

            progressBar {
                id = R.id.cover
                isIndeterminate = false
                visibility = View.GONE
            }.lparams(width = 0, height = 0) {
                alignStart(R.id.image)
                alignEnd(R.id.image)
                sameTop(R.id.image)
                sameBottom(R.id.image)
            }

            textView {
                id = R.id.name
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 2
                textColor = Color.BLACK
                textSize = 16f //sp
            }.lparams {
                startOf(R.id.image)
                alignParentStart()
                marginStart = dip(10)
                topMargin = dip(10)
                marginEnd = dip(20)
            }

            textView {
                id = R.id.size
                textColor = Color.GRAY
                textSize = 11f //sp
            }.lparams {
                alignStart(R.id.name)
                below(R.id.name)
                topMargin = dip(2)
            }

            view {
                id = R.id.divider
                backgroundColor = Color.parseColor("#d8d8d8")
            }.lparams(width = matchParent, height = 1) {
                below(R.id.size)
                topMargin = dip(4)
            }

            textView {
                id = R.id.source
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 1
                textColor = Color.GRAY
                textSize = 11f //sp
            }.lparams(width = matchParent) {
                below(R.id.divider)
                marginStart = dip(10)
                topMargin = dip(2)
                marginEnd = dip(10)
                bottomMargin = dip(2)
            }
        }
    }
}

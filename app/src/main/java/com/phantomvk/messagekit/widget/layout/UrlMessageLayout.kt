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

package com.phantomvk.messagekit.widget.layout

import android.graphics.Color
import android.text.TextUtils
import android.view.Gravity
import android.view.ViewGroup
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.widget.bubbleRelativeLayout
import org.jetbrains.anko.*

class UrlMessageLayout : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        bubbleRelativeLayout {
            lparams(dip(232), ViewGroup.LayoutParams.WRAP_CONTENT)

            textView {
                id = R.id.title
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER_VERTICAL
                includeFontPadding = false
                maxLines = 2
                textColor = Color.parseColor("#222222")
                setTextIsSelectable(false)
                textSize = 15f //sp
            }.lparams(width = matchParent) {
                alignParentTop()
                marginStart = dip(10)
                topMargin = dip(10)
                marginEnd = dip(10)
            }

            imageView {
                id = R.id.image
            }.lparams(width = dip(43), height = dip(43)) {
                below(R.id.title)
                alignParentEnd()
                topMargin = dip(5)
                marginEnd = dip(10)
            }

            textView {
                id = R.id.description
                ellipsize = TextUtils.TruncateAt.END
                includeFontPadding = false
                maxLines = 3
                textColor = Color.parseColor("#666666")
                setTextIsSelectable(false)
                textSize = 11f //sp
            }.lparams(width = matchParent) {
                sameTop(R.id.image)
                alignParentStart()
                startOf(R.id.image)
                marginStart = dip(10)
                marginEnd = dip(12)
            }

            view {
                id = R.id.line
                backgroundColor = Color.parseColor("#FFCFCFCF")
            }.lparams(width = matchParent, height = 1) {
                below(R.id.image)
                topMargin = dip(8)
            }

            textView {
                id = R.id.source
                autoLinkMask = 0
                ellipsize = TextUtils.TruncateAt.END
                includeFontPadding = false
                singleLine = false
                textColor = Color.GRAY
                setTextIsSelectable(false)
                textSize = 10f //sp
            }.lparams(width = matchParent) {
                below(R.id.line)
                marginStart = dip(10)
                topMargin = dip(4)
                marginEnd = dip(10)
                bottomMargin = dip(4)
            }
        }
    }
}

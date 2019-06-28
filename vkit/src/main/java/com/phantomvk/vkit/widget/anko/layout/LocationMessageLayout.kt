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
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import com.phantomvk.vkit.R
import org.jetbrains.anko.*

class LocationMessageLayout<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        bubbleRelativeLayout {
            lparams(dip(232), ViewGroup.LayoutParams.WRAP_CONTENT)

            textView {
                id = R.id.name
                ellipsize = TextUtils.TruncateAt.END
                gravity = Gravity.CENTER_VERTICAL
                includeFontPadding = false
                maxLines = 1
                textColor = Color.parseColor("#222222")
                setTextIsSelectable(false)
                textSize = 15f //sp
            }.lparams(width = matchParent) {
                alignParentTop()
                marginStart = dip(10)
                topMargin = dip(10)
                marginEnd = dip(10)
            }

            textView {
                id = R.id.address
                ellipsize = TextUtils.TruncateAt.END
                maxLines = 1
                textColor = Color.parseColor("#666666")
                textSize = 11f //sp
            }.lparams(width = matchParent) {
                below(R.id.name)
                marginStart = dip(10)
                topMargin = dip(5)
                marginEnd = dip(10)
                bottomMargin = dip(7)
            }

            view {
                id = R.id.line
                backgroundColor = Color.parseColor("#CFCFCF")
            }.lparams(width = matchParent, height = 1) {
                below(R.id.address)
                topMargin = dip(8)
            }

            imageView {
                id = R.id.image
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams(width = matchParent, height = dip(90)) {
                below(R.id.line)
            }
        }
    }
}

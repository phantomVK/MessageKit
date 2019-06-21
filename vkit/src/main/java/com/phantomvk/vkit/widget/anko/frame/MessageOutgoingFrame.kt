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

package com.phantomvk.vkit.widget.anko.frame

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.phantomvk.vkit.R
import com.phantomvk.vkit.widget.anko.layout.interceptTouchRelativeLayout
import org.jetbrains.anko.*

class MessageOutgoingFrame<T> : AnkoComponent<T> {
    override fun createView(ui: AnkoContext<T>) = with(ui) {
        interceptTouchRelativeLayout {
            topPadding = dip(6)
            bottomPadding = dip(6)

            textView {
                id = R.id.date
                backgroundResource = R.drawable.vkit_bg_message_header_date
                textColor = Color.WHITE
                textSize = 12f //sp
                visibility = View.GONE
            }.lparams {
                centerHorizontally()
                topMargin = dip(4)
                bottomMargin = dip(12)
            }

            checkBox {
                id = R.id.checkbox
                applyRecursively { R.style.vkit_checkbox_style }
                visibility = View.GONE
            }.lparams {
                below(R.id.date)
                margin = dip(9)
            }

            imageView {
                id = R.id.avatar
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams(width = dip(40), height = dip(40)) {
                alignParentEnd()
                below(R.id.date)
                marginEnd = dip(9)
            }

            linearLayout {
                id = R.id.container
                gravity = Gravity.CENTER_VERTICAL
                orientation = LinearLayout.HORIZONTAL
                textView {
                    id = R.id.read_receipt
                    includeFontPadding = false
                    textColor = Color.parseColor("#b2b2b2")
                    textSize = 11f //sp
                    visibility = View.GONE
                }.lparams {
                    gravity = Gravity.BOTTOM
                    marginStart = dip(5)
                    bottomMargin = dip(6)
                }

                progressBar {
                    id = R.id.sending
                    isIndeterminate = true
                    visibility = View.GONE
                }.lparams(width = dip(17), height = dip(17)) {
                    marginEnd = dip(10)
                }

                imageView {
                    id = R.id.resend
                    padding = dip(5)
                    imageResource = R.drawable.vkit_selector_warning
                    visibility = View.GONE
                }.lparams(width = dip(30), height = dip(30)) {
                    marginEnd = dip(5)
                }
            }.lparams {
                startOf(R.id.avatar)
                below(R.id.date)
                sameTop(R.id.avatar)
                marginStart = dip(10)
                marginEnd = dip(4)
            }
        }
    }
}

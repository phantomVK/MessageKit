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
import android.view.Gravity
import android.view.ViewGroup
import com.phantomvk.vkit.R
import org.jetbrains.anko.*

class NoticeMessageLayout : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        frameLayout {
            lparams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            isClickable = false

            textView {
                id = R.id.notice
                autoLinkMask = 0
                backgroundResource = R.drawable.vkit_shape_msg_notice_bg
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
                textColor = Color.parseColor("#ffffff")
                setTextIsSelectable(false)
                textSize = 13f //sp
            }.lparams {
                gravity = Gravity.CENTER
                topMargin = dip(6)
                bottomMargin = dip(6)
                marginStart = dip(30)
                marginEnd = dip(30)
            }
        }
    }
}

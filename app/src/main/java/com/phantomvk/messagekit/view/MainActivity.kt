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

package com.phantomvk.messagekit.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.phantomvk.messagekit.view.MessagesActivity.Companion.STYLE_ANKO
import com.phantomvk.messagekit.view.MessagesActivity.Companion.STYLE_JET_PACK
import com.phantomvk.messagekit.view.MessagesActivity.Companion.STYLE_LAYOUT_INFLATER

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val button = Button(this)
        button.text = "LayoutInflater"
        button.isAllCaps = false
        button.setOnClickListener { MessagesActivity.startActivity(this, STYLE_LAYOUT_INFLATER) }
        layout.addView(button)

        val button2 = Button(this)
        button2.text = "Anko"
        button2.isAllCaps = false
        button2.setOnClickListener { MessagesActivity.startActivity(this, STYLE_ANKO) }
        layout.addView(button2)

        val button3 = Button(this)
        button3.text = "JetPack"
        button3.isAllCaps = false
        button3.setOnClickListener { MessagesActivity.startActivity(this, STYLE_JET_PACK) }
        layout.addView(button3)

        val params = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        params.gravity = Gravity.CENTER
        addContentView(layout, params)
    }
}

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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.tools.MessageItemListener
import com.phantomvk.messagekit.tools.MessageResLoader
import com.phantomvk.messagekit.tools.MessageViewPool
import com.phantomvk.vkit.adapter.MessageAdapter
import com.phantomvk.vkit.adapter.MessageHolders
import com.phantomvk.vkit.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MessagesActivity : AppCompatActivity() {
    private lateinit var mAdapter: MessageAdapter

    /**
     * LinearLayoutManager for MessageAdapter.
     */
    private val mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = MessageAdapter(this, MessageItemListener(this), MessageResLoader)
        mLayoutManager.isSmoothScrollbarEnabled = true
        messageView.layoutManager = mLayoutManager
        messageView.adapter = mAdapter
        messageView.setRecycledViewPool(MessageViewPool)
        MessageHolders.setMaxRecycledViews(messageView)

//        addText()
//        addUrl()
//        addLocation()
//        addFile()
        addImages()
        audio()
    }

    private fun addText() {
        val longText = TextMessage("Hello")
        longText.setSender("Mike")
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)

        val shortText2 = TextMessage("Hi")
        shortText2.setSender("John")
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
        mAdapter.add(shortText2, false)
    }

    private fun addUrl() {
        val url = UrlMessage("Google", "https://www.google.com")
        url.setSender("Mike")
        url.description = "This is a website.This is a website." +
                "This is a website.This is a website." +
                "This is a website.This is a website." +
                "This is a website.This is a website." +
                "This is a website.This is a website."
        url.domain = "www.google.com"
        mAdapter.add(url, false)

        val url2 = UrlMessage("Google", "https://www.google.com")
        url2.setSender("John")
        url2.description = "This is a website."
        url2.domain = "www.google.com"
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
    }

    private fun addLocation() {
        val locationMessage = LocationMessage("Baker street.")
        locationMessage.address = "ABCDABCDABCDABCDABCDABCDABCDABCDABCD"
        locationMessage.setSender("Mike")

        val locationMessage2 = LocationMessage("Baker street.")
        locationMessage2.setSender("John")

        mAdapter.add(locationMessage, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
        mAdapter.add(locationMessage2, false)
    }

    private fun addFile() {
        val file = FileMessage("Android_development_handbook.pdf")
        file.setSender("Mike")

        val file2 = FileMessage("Android_development_handbook.pdf")
        file2.setSender("John")

        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file, false)
        mAdapter.add(file2, false)
        mAdapter.add(file2, false)
        mAdapter.add(file2, false)
        mAdapter.add(file2, false)
    }

    private fun addImages() {
        val msg = ImageMessage("img_1")
        msg.setSender("Mike")
        msg.width = 714
        msg.height = 7730
        msg.url = "https://img.zcool.cn/community/02172958db679ca801219c7790da38.jpg"

        val msg2 = ImageMessage("img_2")
        msg2.setSender("Bob")
        msg2.width = 750
        msg2.height = 500
        msg2.url = "https://kaboompics.com/cache/2/2/4/5/3/22453134c1a1f749446bc9d40312dbec26cc6c92.jpeg?version=v49"

        val msg3 = ImageMessage("img_3")
        msg3.setSender("Bob")
        msg3.width = 2145
        msg3.height = 544
        msg3.url = "http://ps.missyuan.com/upimg/allimg/090121/1424321.jpg"

        mAdapter.add(msg, false)
        mAdapter.add(msg2, false)
        mAdapter.add(msg2, false)
        mAdapter.add(msg, false)
        mAdapter.add(msg, false)
        mAdapter.add(msg2, false)
        mAdapter.add(msg, false)
        mAdapter.add(msg2, false)
        mAdapter.add(msg, false)
        mAdapter.add(msg2, false)
        mAdapter.add(msg3, false)
        mAdapter.add(msg3, false)
        mAdapter.add(msg3, false)
        mAdapter.add(msg)
    }

    private fun audio() {
        val msg = AudioMessage("audio")
        msg.setSender("John")
        mAdapter.add(msg)
    }

    override fun onPause() {
        super.onPause()
        mAdapter.onPause()
    }
}

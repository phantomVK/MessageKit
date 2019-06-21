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
import kotlinx.android.synthetic.main.activity_message.*

class MessagesActivity : AppCompatActivity() {
    private lateinit var mAdapter: MessageAdapter

    /**
     * LinearLayoutManager for MessageAdapter.
     */
    private val mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        mAdapter = MessageAdapter(this, MessageItemListener(this), MessageResLoader)
        mAdapter.setHasStableIds(true)

        mLayoutManager.isSmoothScrollbarEnabled = true
        messageView.layoutManager = mLayoutManager
        messageView.adapter = mAdapter
        messageView.setRecycledViewPool(MessageViewPool)
        messageView.setHasFixedSize(true)
        MessageHolders.setMaxScrap(messageView)

        addText()
        addUrl()
        addLocation()
        addFile()
        addImages()
        addAudio()

        mAdapter.notifyDataSetChanged()
    }

    private fun addText() {
        val msg1 = TextMessage("Hello")
        msg1.setSender("Austin")
        msg1.setTimestamp(System.currentTimeMillis() - 1000 * 60 * 10 - 1000 * 60 * 60 * 25)
        mAdapter.add(msg1)

        val msg2 = TextMessage("Hi")
        msg2.setSender("Daniel")
        msg2.setTimestamp(System.currentTimeMillis() - 1000 * 60 * 6)
        mAdapter.add(msg2)
    }

    private fun addUrl() {
        val msg1 = UrlMessage("Google", "https://www.google.com")
        msg1.setSender("Austin")
        msg1.image = "https://image.flaticon.com/teams/slug/google.jpg"
        msg1.domain = "https://www.google.com"
        msg1.description = "Google LLC is an American multinational technology company " +
                "that specializes in Internet-related services and products, which include " +
                "online advertising technologies, search engine, cloud computing, software, " +
                "and hardware."
        mAdapter.add(msg1)

        val msg2 = UrlMessage("Google", "https://www.google.com")
        msg2.setSender("Daniel")
        msg2.image = "https://image.flaticon.com/teams/slug/google.jpg"
        msg2.domain = "https://www.google.com"
        msg2.description = "Google LLC is an American multinational technology company " +
                "that specializes in Internet-related services and products, which include " +
                "online advertising technologies, search engine, cloud computing, software, " +
                "and hardware."
        mAdapter.add(msg2)
    }

    private fun addLocation() {
        val msg1 = LocationMessage("Baker street")
        msg1.setSender("Austin")
        mAdapter.add(msg1)

        val msg2 = LocationMessage("Baker street")
        msg2.setSender("Daniel")
        mAdapter.add(msg2)
    }

    private fun addFile() {
        val mag1 = FileMessage("Android_development_handbook.pdf")
        mag1.setSender("Austin")
        mAdapter.add(mag1)

        val msg2 = FileMessage("Android_development_handbook.pdf")
        msg2.setSender("Daniel")
        mAdapter.add(msg2)
    }

    private fun addImages() {
        val msg1 = ImageMessage("img_2")
        msg1.setSender("Austin")
        msg1.width = 668
        msg1.height = 1002
        msg1.url =
            "https://images.unsplash.com/photo-1558960561-ad9fef3d65f2?ixlib=rb-1.2.1&auto=format&fit=crop&w=668&q=80"

        val msg3 = ImageMessage("img_3")
        msg3.setSender("Daniel")
        msg3.width = 2850
        msg3.height = 1900
        msg3.url =
            "https://images.unsplash.com/photo-1558981130-93d25a4d4212?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2850&q=80"

        mAdapter.add(msg1)
        mAdapter.add(msg3)
    }

    private fun addAudio() {
        val msg = AudioMessage("audio")
        msg.setSender("Austin")
        mAdapter.add(msg)

        val msg1 = AudioMessage("audio")
        msg1.setSender("Daniel")
        mAdapter.add(msg1)
    }
}

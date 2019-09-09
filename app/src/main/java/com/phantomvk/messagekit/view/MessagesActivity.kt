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

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.adapter.HolderRegister
import com.phantomvk.messagekit.adapter.MessageAdapter
import com.phantomvk.messagekit.adapter.MessageHolder
import com.phantomvk.messagekit.listener.MessageItemListener
import com.phantomvk.messagekit.listener.MessageResLoader
import com.phantomvk.messagekit.model.*

class MessagesActivity : AppCompatActivity() {

    private lateinit var mAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.isSmoothScrollbarEnabled = true

        val messageView = RecyclerView(this)
        messageView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        messageView.layoutParams = layoutParams
        messageView.layoutManager = layoutManager
        messageView.setHasFixedSize(true)
        messageView.setBackgroundColor(Color.WHITE)

        val itemListener = MessageItemListener(messageView)
        val holder = MessageHolder(layoutInflater, itemListener, MessageResLoader())
        mAdapter = MessageAdapter(this, itemListener, holder)
        mAdapter.setHasStableIds(true)

        messageView.adapter = mAdapter
        HolderRegister.setMaxScrap(messageView)
        addContentView(messageView, layoutParams)

        initData()
    }

    override fun onBackPressed() {
        if (mAdapter.getSelecting()) {
            mAdapter.setSelecting(false)
            mAdapter.clearSelectedItems()
        } else {
            super.onBackPressed()
        }
    }

    private fun initData() {
        for (i in 0..5) {
            addText()
            addUrl()
            addLocation()
            addFile()
            addImages()
            addAudio()
            notice()
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun addText() {
        val msg1 = TextMessage("Hello")
        msg1.setSender("Austin")
        mAdapter.add(msg1)

        val msg2 = TextMessage("Hi")
        msg2.setSender("Daniel")
        mAdapter.add(msg2)
    }

    private fun addUrl() {
        val msg1 = UrlMessage("Google", "https://www.google.com")
        msg1.setSender("Austin")
        msg1.domain = "https://www.google.com"
        msg1.image = "http://img1.imgtn.bdimg.com/it/u=3690048061,2739563546&fm=214&gp=0.jpg"
        msg1.description = "Google LLC is an American multinational technology company " +
                "that specializes in Internet-related services and products, which include " +
                "online advertising technologies, search engine, cloud computing, software, " +
                "and hardware."
        mAdapter.add(msg1)

        val msg2 = UrlMessage("Google", "https://www.google.com")
        msg2.setSender("Daniel")
        msg2.domain = "https://www.google.com"
        msg2.image = "http://img1.imgtn.bdimg.com/it/u=3690048061,2739563546&fm=214&gp=0.jpg"
        msg2.description = "Google LLC is an American multinational technology company " +
                "that specializes in Internet-related services and products, which include " +
                "online advertising technologies, search engine, cloud computing, software, " +
                "and hardware."
        mAdapter.add(msg2)
    }

    private fun addLocation() {
        val msg1 = LocationMessage("Beijing, China")
        msg1.setSender("Austin")
        msg1.address = "Wangjing, Chaoyang District, Beijing City, China"
        msg1.image =
            "http://restapi.amap.com/v3/staticmap" +
                    "?location=116.481485,39.990464" +
                    "&zoom=12&size=696*270" +
                    "&key=6912dce4d721f10e97753912cdb9e885"
        mAdapter.add(msg1)

        val msg2 = LocationMessage("Beijing, China")
        msg2.setSender("Daniel")
        msg2.address = "Wangjing, Chaoyang District, Beijing City"
        msg2.image =
            "http://restapi.amap.com/v3/staticmap" +
                    "?location=116.481485,39.990464" +
                    "&zoom=12&size=696*270" +
                    "&key=6912dce4d721f10e97753912cdb9e885"
        mAdapter.add(msg2)
    }

    private fun addFile() {
        val msg1 = FileMessage("Android_development_handbook.pdf")
        msg1.setSender("Austin")
        msg1.size = 125536 // bytes
        mAdapter.add(msg1)

        val msg2 = FileMessage("Android_development_handbook.pdf")
        msg2.setSender("Daniel")
        msg2.size = 125536 // bytes
        mAdapter.add(msg2)
    }

    private fun addImages() {
        val msg1 = ImageMessage("img_2")
        msg1.setSender("Austin")
        msg1.width = 1330
        msg1.height = 2000
        msg1.url = "http://up.tukuwa.com/pic_source/ec/eb/7d/eceb7d5bdb5ea716be17bc20d15c0275.jpg"
        mAdapter.add(msg1)

        val msg2 = ImageMessage("img_3")
        msg2.setSender("Daniel")
        msg2.width = 2000
        msg2.height = 1330
        msg2.url = "http://up.tukuwa.com/pic_source/a3/3f/03/a33f0374d254b20975790f69c177205d.jpg"
        mAdapter.add(msg2)
    }

    private fun addAudio() {
        val msg = AudioMessage("audio")
        msg.setSender("Austin")
        msg.duration = 12000
        mAdapter.add(msg)

        val msg1 = AudioMessage("audio")
        msg1.setSender("Daniel")
        msg1.duration = 60000
        mAdapter.add(msg1)
    }

    private fun notice() {
        val msg = NoticeMessage("This is notice")
        val msg2 = NoticeMessage("This is notice")
        mAdapter.add(msg)
        mAdapter.add(msg2)
    }
}

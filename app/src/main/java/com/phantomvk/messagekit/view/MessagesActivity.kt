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
import com.phantomvk.vkit.model.NoticeMessage
import com.phantomvk.vkit.model.TextMessage
import com.phantomvk.vkit.model.UrlMessage
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
        messageView.layoutManager = mLayoutManager
        messageView.adapter = mAdapter
        messageView.setRecycledViewPool(MessageViewPool)
        MessageHolders.setMaxRecycledViews(messageView)

        init()

//        messageView.postDelayed({ mAdapter.setSelecting(true) }, 3000)
//        messageView.postDelayed({ mAdapter.setSelecting(false) }, 10000)
    }

    private fun init() {
        addText()
        addUrl()
        addNotification()
    }

    private fun addText() {
        val longText = TextMessage("1234567890ABCDEFG_测试你好")
        longText.setSender("Mike")
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)
        mAdapter.add(longText, false)

        val shortTextMe = TextMessage("ABCD")
        shortTextMe.setSender("Mike")
        mAdapter.add(shortTextMe, false)
        mAdapter.add(shortTextMe, false)
        mAdapter.add(shortTextMe, false)
        mAdapter.add(shortTextMe, false)
        mAdapter.add(shortTextMe, false)

        val longText2 = TextMessage("1234567890ABCDEFG_测试你好")
        longText2.setSender("John")
        mAdapter.add(longText2, false)
        mAdapter.add(longText2, false)
        mAdapter.add(longText2, false)
        mAdapter.add(longText2, false)
        mAdapter.add(longText2, false)

        val shortText2 = TextMessage("ABCD")
        shortText2.setSender("John")
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
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
        mAdapter.add(url, false)
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
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
        mAdapter.add(url2, false)
    }

    private fun addNotification() {
        val notification = NoticeMessage("dfsdgnfhm")
        mAdapter.add(notification, false)
        mAdapter.add(notification, false)
        mAdapter.add(notification, false)
        mAdapter.add(notification)
    }

    override fun onPause() {
        super.onPause()
        mAdapter.onPause()
    }
}

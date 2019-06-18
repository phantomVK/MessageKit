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
        messageView.layoutManager = mLayoutManager
        messageView.adapter = mAdapter
        messageView.setRecycledViewPool(MessageViewPool)
        MessageHolders.setMaxRecycledViews(messageView)

        addText()
        addUrl()
        addNotification()
        addLocation()
        addFile()
    }

    private fun addText() {
        val longText = TextMessage("Hello")
        longText.setSender("Mike")
        mAdapter.add(longText, false)

        val shortText2 = TextMessage("Hi")
        shortText2.setSender("John")
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
    }

    private fun addNotification() {
        val notification = NoticeMessage("dfsdgnfhm")
        mAdapter.add(notification)
    }

    private fun addLocation() {
        val locationMessage = LocationMessage("Baker street.")
        locationMessage.address = "ABCDABCDABCDABCDABCDABCDABCDABCDABCD"
        locationMessage.setSender("Mike")

        val locationMessage2 = LocationMessage("Baker street.")
        locationMessage2.setSender("John")

        mAdapter.add(locationMessage, false)
        mAdapter.add(locationMessage2, false)
    }

    private fun addFile() {
        val file = FileMessage("Android_development_handbook.pdf")
        file.setSender("Mike")

        val file2 = FileMessage("Android_development_handbook.pdf")
        file2.setSender("John")

        mAdapter.add(file, false)
        mAdapter.add(file2)
    }

    override fun onPause() {
        super.onPause()
        mAdapter.onPause()
    }
}

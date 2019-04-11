package com.phantomvk.messagekit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phantomvk.messagekit.R
import com.phantomvk.messagekit.tools.MessageResLoader
import com.phantomvk.messagekit.tools.RecyclerViewPool
import com.phantomvk.vkit.adapter.MessageAdapter
import com.phantomvk.vkit.adapter.MessageHolders
import com.phantomvk.vkit.model.TextMessage
import kotlinx.android.synthetic.main.activity_list.*

class MessagesActivity : AppCompatActivity() {
    private lateinit var mAdapter: MessageAdapter

    /**
     * LinearLayoutManager for MessageAdapter.
     */
    private val mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        mAdapter = MessageAdapter(this, MessageResLoader)
        messageView.layoutManager = mLayoutManager
        messageView.adapter = mAdapter
        messageView.setRecycledViewPool(RecyclerViewPool)
        MessageHolders.setMaxRecycledViews(messageView)

        init()
    }

    private fun init() {
        addText()
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

    private fun addImage() {
    }
}
package com.phantomvk.messagekit

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phantomvk.vkit.adapter.MessageAdapter
import com.phantomvk.vkit.listener.IMessageResLoader
import com.phantomvk.vkit.model.TextMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMessageResLoader {

    private lateinit var mAdapter: MessageAdapter

    /**
     * LinearLayoutManager for MessageAdapter.
     */
    private val mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = MessageAdapter(this, layoutInflater, this)
        messageView.layoutManager = mLayoutManager
        messageView.adapter = mAdapter

        init()
    }

    private fun init() {
        addText()
    }

    private fun addText() {
        val longText = TextMessage("1234567890ABCDEFG_测试你好")
        longText.setSender("Mike")
        mAdapter.add(longText, false)

        val shortTextMe = TextMessage("ABCD")
        shortTextMe.setSender("Mike")
        mAdapter.add(shortTextMe, false)

        val longText2 = TextMessage("1234567890ABCDEFG_测试你好")
        longText2.setSender("John")
        mAdapter.add(longText2, false)

        val shortText2 = TextMessage("ABCD")
        shortText2.setSender("John")
        mAdapter.add(shortText2, false)
    }

    override fun loadAvatar(context: Context, image: String, view: ImageView) {
        Glide.with(context)
            .asDrawable()
            .load(image)
            .into(view)
    }

    override fun loadAvatar(context: Context, @RawRes @DrawableRes resId: Int, view: ImageView) {
        Glide.with(context)
            .asDrawable()
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }
}

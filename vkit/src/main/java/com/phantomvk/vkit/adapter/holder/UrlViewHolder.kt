package com.phantomvk.vkit.adapter.holder

import android.content.Context
import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.model.UrlMessage

class UrlViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * Website title, required.
     */
    private val mTitle: TextView = itemView.findViewById(R.id.title)

    /**
     * Website icon, required.
     */
    private val mImage: ImageView = itemView.findViewById(R.id.image)

    /**
     * Website domain, required.
     */
    private val mSource: TextView = itemView.findViewById(R.id.source)

    /**
     * Website description, optional.
     */
    private val mDescription: TextView = itemView.findViewById(R.id.description)

    /**
     * For long click popup menu.
     */
    private val mPoint = PointF()

    override fun onInit() {
        mContentView.setOnClickListener {
            mMessageItemListener.onContentClick(itemView)
        }

        mContentView.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                mPoint.set(event.x, event.y)
            }
            return@setOnTouchListener false
        }

        mContentView.setOnLongClickListener {
            val location = IntArray(2)
            mContentView.getLocationInWindow(location)
            mPoint.offset(location[0].toFloat(), location[1].toFloat() - mContentView.measuredHeight)
            mMessageItemListener.onContentLongClick(itemView, mPoint, mAdapter, adapterPosition)
            return@setOnLongClickListener false
        }
    }

    override fun onBind(context: Context, message: IMessage) {
        super.onBind(context, message)
        val urlMessage = message as UrlMessage
        mTitle.text = urlMessage.title
        mSource.text = urlMessage.source ?: urlMessage.domain ?: ""
        mDescription.text = urlMessage.description
        mMessageResLoader.loadImage(context, urlMessage.image ?: "", mImage)
    }
}

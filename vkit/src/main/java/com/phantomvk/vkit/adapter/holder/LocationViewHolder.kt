package com.phantomvk.vkit.adapter.holder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.phantomvk.vkit.R
import com.phantomvk.vkit.model.IMessage
import com.phantomvk.vkit.model.LocationMessage

class LocationViewHolder(itemView: View) : BaseViewHolder(itemView) {
    /**
     * The name of the location, required.
     */
    private val mName: TextView = itemView.findViewById(R.id.name)

    /**
     * The address of the location, required.
     */
    private val mAddress: TextView = itemView.findViewById(R.id.address)

    /**
     * The map image of the location, optional.
     */
    private val mImage: ImageView = itemView.findViewById(R.id.image)

    override fun onBind(activity: Activity, message: IMessage) {
        super.onBind(activity, message)
        val msg = message as LocationMessage
        mName.text = msg.name
        mAddress.text = msg.address
        mResLoader.loadImage(activity, "", mImage)
    }
}

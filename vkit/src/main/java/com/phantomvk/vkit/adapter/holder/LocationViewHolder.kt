package com.phantomvk.vkit.adapter.holder

import android.content.Context
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

    override fun onBind(context: Context, message: IMessage) {
        super.onBind(context, message)
        val locationMessage = message as LocationMessage
        mName.text = locationMessage.name
        mAddress.text = locationMessage.address
        mResLoader.loadImage(itemView.context, "", mImage)
    }
}

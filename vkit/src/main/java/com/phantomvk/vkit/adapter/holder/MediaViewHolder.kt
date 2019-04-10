package com.phantomvk.vkit.adapter.holder

import android.graphics.Point
import android.view.View

class MediaViewHolder(itemView: View) : BaseViewHolder(itemView) {

    /**
     * Calculate the size of thumbnail ImageView.
     */
    private fun calculateSize(
        point: Point,
        width: Int, height: Int,
        maxWidth: Int, maxHeight: Int,
        minSize: Float, maxSize: Float
    ) {
        var scale = if (width / height > maxWidth / maxHeight) {
            maxWidth.toFloat() / width
        } else {
            maxHeight.toFloat() / height
        }

        var maxScale = if (width > height) maxSize / width else maxSize / height
        maxScale = Math.max(1F, maxScale)

        scale = Math.min(maxScale, scale)
        point.x = Math.max(minSize, width * scale).toInt()
        point.y = Math.max(minSize, height * scale).toInt()
    }
}

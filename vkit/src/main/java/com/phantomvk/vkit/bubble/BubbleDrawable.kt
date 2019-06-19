package com.phantomvk.vkit.bubble

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import com.phantomvk.vkit.util.dip

class BubbleDrawable(context: Context, shape: BubbleShape) : ShapeDrawable(shape) {
    init {
        val padding = context.dip(10)
        val paddingStart = if (shape.arrowDirection == Direction.END) padding else context.dip(6) + padding
        val paddingEnd = if (shape.arrowDirection == Direction.END) context.dip(6) + padding else padding
        setPadding(paddingStart, padding, paddingEnd, padding)
    }
}

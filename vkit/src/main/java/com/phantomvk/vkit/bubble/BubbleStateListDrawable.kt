package com.phantomvk.vkit.bubble

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import androidx.core.content.ContextCompat
import com.phantomvk.vkit.R
import com.phantomvk.vkit.util.dip

class BubbleStateListDrawable(context: Context, shape: BubbleShape) : StateListDrawable() {
    init {
        val color = shape.solidColor
        val darkColor = Color.argb(
            (Color.alpha(color)),
            (Color.red(color) * 0.9F).toInt(),
            (Color.green(color) * 0.9F).toInt(),
            (Color.blue(color) * 0.9F).toInt())
        val darkShape = shape.clone()
        darkShape.solidColor = darkColor


        val darkDrawable = BubbleDrawable(context, darkShape)
        addState(intArrayOf(android.R.attr.state_pressed), darkDrawable)
        addState(intArrayOf(android.R.attr.state_selected), darkDrawable)
        addState(intArrayOf(), BubbleDrawable(context, shape))
    }
}

fun getStateListDrawable(context: Context, isSender: Boolean): StateListDrawable {
    val colorRes = if (isSender) R.color.vkit_color_host_solid else R.color.vkit_color_guest_solid
    val color = ContextCompat.getColor(context, colorRes)

    val strokeColorRes = if (isSender) R.color.vkit_color_host_stroke else R.color.vkit_color_guest_stroke
    val strokeColor = ContextCompat.getColor(context, strokeColorRes)

    val direction = if (isSender) Direction.END else Direction.START

    val shape = BubbleShape(
        direction,
        context.dip(6F),
        context.dip(12F),
        context.dip(4F),
        context.dip(0.5F),
        context.dip(10F),
        color, strokeColor)

    return BubbleStateListDrawable(context, shape)
}

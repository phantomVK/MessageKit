package com.phantomvk.vkit.listener

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

interface IMessageResLoader {
    /**
     * Load drawable resource from string into ImageView.
     */
    fun loadAvatar(context: Context, image: String, view: ImageView)

    /**
     * Load drawable resource from ResId into ImageView.
     */
    fun loadAvatar(context: Context, @RawRes @DrawableRes resId: Int, view: ImageView)
}
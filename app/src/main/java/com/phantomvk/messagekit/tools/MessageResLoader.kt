package com.phantomvk.messagekit.tools

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.phantomvk.messagekit.R
import com.phantomvk.vkit.listener.IMessageResLoader

object MessageResLoader : IMessageResLoader {

    override fun loadAvatar(context: Context, image: String, view: ImageView) {
        Glide.with(context)
            .asDrawable()
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }

    override fun loadAvatar(context: Context, @RawRes @DrawableRes resId: Int, view: ImageView) {
        Glide.with(context)
            .asDrawable()
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }

    override fun loadImage(context: Context, image: String, view: ImageView) {
        Glide.with(context)
            .asDrawable()
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }

    override fun loadImage(context: Context, @RawRes @DrawableRes resId: Int, view: ImageView) {
        Glide.with(context)
            .asDrawable()
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }
}

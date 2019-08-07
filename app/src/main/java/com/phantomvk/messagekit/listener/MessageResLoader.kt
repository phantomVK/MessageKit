/**
 * MIT License
 *
 * Copyright (c) 2019 Wenkang Tan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.phantomvk.messagekit.listener

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.phantomvk.messagekit.R
import com.phantomvk.vkit.listener.IMessageResLoader

class MessageResLoader : IMessageResLoader {

    override fun loadAvatar(context: Context, image: String?, view: ImageView) {
        // Free images from: https://www.pexels.com/search/people/
        val url = if (image == "Daniel") {
            "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&w=350&h=350&fit=crop&fm=jpg"
        } else {
            "https://images.pexels.com/photos/736716/pexels-photo-736716.jpeg?auto=compress&w=350&h=350&fit=crop&fm=jpg"
        }

        Glide.with(context)
            .load(url)
            .into(view)
    }

    override fun loadAvatar(context: Context, @RawRes @DrawableRes resId: Int, view: ImageView) {
        Glide.with(context)
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }

    override fun loadImage(context: Context, image: String?, view: ImageView) {
        Glide.with(context)
            .load(image ?: R.drawable.ic_launcher_background)
            .into(view)
    }

    override fun loadImage(context: Context, @RawRes @DrawableRes resId: Int, view: ImageView) {
        Glide.with(context)
            .load(R.drawable.ic_launcher_background)
            .into(view)
    }
}

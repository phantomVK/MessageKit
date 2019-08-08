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

package com.phantomvk.messagekit

import android.app.Application
import android.util.Log
import com.bumptech.glide.Glide
import com.phantomvk.messagekit.adapter.MessageAdapter
import com.phantomvk.messagekit.adapter.MessageHolder
import com.phantomvk.messagekit.listener.MessageResLoader
import com.phantomvk.messagekit.widget.layout.*

class KitApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Force ClassLoader to load classes in advance, optional.
        // Samsung Galaxy S4 in release: 88ms.
        Thread {
            val start = System.currentTimeMillis()
            Glide.with(this)
            MessageAdapter::javaClass.name
            MessageHolder.setMaxScrap(null)
            AudioMessageLayout::javaClass.name
            FileMessageLayout::javaClass.name
            LocationMessageLayout::javaClass.name
            MediaMessageLayout::javaClass.name
            NoticeMessageLayout::javaClass.name
            TextMessageLayout::javaClass.name
            UrlMessageLayout::javaClass.name
            MessageResLoader::javaClass.name
            Log.v("KitApplication", "onCreate: ${System.currentTimeMillis() - start}ms")
        }.start()
    }
}
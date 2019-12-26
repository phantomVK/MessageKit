/*
 * MIT License
 * <p>
 * Copyright (c) 2019 Wenkang Tan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.phantomvk.messagekit.model

import com.phantomvk.vkit.model.IMessage

abstract class Message(private val type: String, private val body: String) : IMessage {
    /**
     * Message id, required.
     */
    private val messageId: String? = null

    /**
     * Message sender, required;
     */
    private var sender: String? = null

    /**
     * Message comes from roomId, required.
     */
    private val roomId: String? = null

    /**
     * Message timestamp, required.
     */
    private var timestamp = System.currentTimeMillis()

    override fun getMsgType(): String {
        return type
    }

    override fun getId(): String {
        return messageId ?: ""
    }

    override fun getBody(): String {
        return body
    }

    override fun getSender(): String {
        return sender ?: ""
    }

    override fun getTimestamp(): Long {
        return timestamp
    }

    override fun setTimestamp(timestamp: Long) {
        this.timestamp = timestamp
    }

    override fun getRoomId(): String {
        return roomId ?: ""
    }

    override fun setSender(userId: String) {
        sender = userId
    }

    companion object {
        const val MESSAGE_TYPE_TEXT = "TEXT"
        const val MESSAGE_TYPE_URL = "URL"
        const val MESSAGE_TYPE_LOCATION = "LOCATION"
        const val MESSAGE_TYPE_NOTICE = "NOTICE"
        const val MESSAGE_TYPE_FILE = "FILE"
        const val MESSAGE_TYPE_IMAGE = "IMAGE"
        const val MESSAGE_TYPE_AUDIO = "AUDIO"
        const val MESSAGE_TYPE_VIDEO = "VIDEO"
    }
}

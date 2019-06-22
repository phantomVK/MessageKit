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

package com.phantomvk.vkit.model

/**
 * Implemented by message model.
 */
interface IMessage {
    /**
     * Return the message type.
     */
    abstract fun getMsgType(): String

    /**
     * Returns the message identifier.
     */
    abstract fun getId(): String

    /**
     * Return the message body.
     */
    abstract fun getBody(): String

    /**
     * Return the message sender. See the {@link IUser} for more details.
     */
    abstract fun getSender(): String

    /**
     * Set message sender. See the {@link IUser} for more details.
     */
    abstract fun setSender(userId: String)

    /**
     * Return the message timestamp in millisecond.
     */
    abstract fun getTimestamp(): Long

    /**
     * Set message timestamp in millisecond.
     */
    abstract fun setTimestamp(timestamp: Long)

    /**
     * Return the message roomId.
     */
    abstract fun getRoomId(): String
}

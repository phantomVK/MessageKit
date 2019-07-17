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

package com.phantomvk.vkit.model;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public abstract class Message implements IMessage {
    /**
     * Message type, required.
     */
    private String type;

    /**
     * Message id, required.
     */
    private String messageId;

    /**
     * Message body, required.
     */
    private String body;

    /**
     * Message sender, required;
     */
    private String sender;

    /**
     * Message comes from roomId, required.
     */
    private String roomId;

    /**
     * Message timestamp, required.
     */
    private long timestamp;

    public Message(@NonNull String type, @NonNull String body) {
        this.type = type;
        this.body = body;
    }

    public Message(String type, String messageId,
                   String body, String sender,
                   String roomId, long timestamp) {
        this.type = type;
        this.messageId = messageId;
        this.body = body;
        this.sender = sender;
        this.roomId = roomId;
        this.timestamp = timestamp;
    }

    @NotNull
    @Override
    public String getMsgType() {
        return type;
    }

    @NotNull
    @Override
    public String getId() {
        return messageId;
    }

    @NotNull
    @Override
    public String getBody() {
        return body;
    }

    @NotNull
    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @NotNull
    @Override
    public String getRoomId() {
        return roomId;
    }

    @Override
    public void setSender(@NotNull String user) {
        sender = user;
    }

    public static final String MESSAGE_TYPE_TEXT = "TEXT";
    public static final String MESSAGE_TYPE_URL = "URL";
    public static final String MESSAGE_TYPE_LOCATION = "LOCATION";
    public static final String MESSAGE_TYPE_NOTICE = "NOTICE";
    public static final String MESSAGE_TYPE_FILE = "FILE";
    public static final String MESSAGE_TYPE_IMAGE = "IMAGE";
    public static final String MESSAGE_TYPE_AUDIO = "AUDIO";
    public static final String MESSAGE_TYPE_VIDEO = "VIDEO";
}

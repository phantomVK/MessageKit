package com.phantomvk.vkit.model;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

public abstract class Message extends IMessage {
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
    public static final String MESSAGE_TYPE_ALERT = "ALERT";
    public static final String MESSAGE_TYPE_URL = "URL";
    public static final String MESSAGE_TYPE_LOCATION = "LOCATION";
    public static final String MESSAGE_TYPE_NOTICE = "NOTICE";
    public static final String MESSAGE_TYPE_FILE = "FILE";
    public static final String MESSAGE_TYPE_IMAGE = "IMAGE";
    public static final String MESSAGE_TYPE_AUDIO = "AUDIO";
    public static final String MESSAGE_TYPE_VIDEO = "VIDEO";
}

package com.phantomvk.vkit.model;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public abstract class Message extends IMessage {
    /**
     * Message type, required.
     */
    String type;

    /**
     * Message id, required.
     */
    String messageId;

    /**
     * Message body, required.
     */
    String body;

    /**
     * Message sender, required;
     */
    String sender;

    /**
     * Message comes from roomId, required.
     */
    String roomId;

    /**
     * Message timestamp, required.
     */
    protected long timestamp;

    /**
     * Constrictor.
     *
     * @param type Message type.
     */
    public Message(@NonNull String type, @NonNull String body) {
        this.type = type;
        this.body = body;
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
        return 0;
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
    public static final String MESSAGE_TYPE_ALERT = "ALERT";
    public static final String MESSAGE_TYPE_FILE = "FILE";
    public static final String MESSAGE_TYPE_IMAGE = "IMAGE";
    public static final String MESSAGE_TYPE_AUDIO = "AUDIO";
    public static final String MESSAGE_TYPE_VIDEO = "VIDEO";
}

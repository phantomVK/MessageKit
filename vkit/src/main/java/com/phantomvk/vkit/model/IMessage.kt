package com.phantomvk.vkit.model

/**
 * For implementing by the real message model.
 */
abstract class IMessage {
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
     * Return the message roomId.
     */
    abstract fun getRoomId(): String
}

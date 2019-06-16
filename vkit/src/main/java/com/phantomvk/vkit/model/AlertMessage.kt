package com.phantomvk.vkit.model

class AlertMessage(body: String) : Message(Message.MESSAGE_TYPE_ALERT, body) {
    /**
     * Alert users to read this message carefully.
     */
    private val mUserIds = ArrayList<String>()
}

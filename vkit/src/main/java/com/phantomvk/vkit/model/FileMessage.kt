package com.phantomvk.vkit.model

class FileMessage(body: String) : Message(MESSAGE_TYPE_FILE, body) {

    var size: Long = 17223542 // bytes.
}
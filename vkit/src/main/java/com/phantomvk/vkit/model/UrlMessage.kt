package com.phantomvk.vkit.model

/**
 * UrlMessage class.
 *
 * Website title and website url are both required.
 */
class UrlMessage(var title: String, url: String) : Message(MESSAGE_TYPE_URL, url) {
    /**
     * Website icon url, optional.
     */
    var image: String? = null

    /**
     * Website source, optional.
     */
    var source: String? = null

    /**
     * Website domain, optional.
     */
    var domain: String? = null

    /**
     * Website description, optional.
     */
    var description: String? = null
}

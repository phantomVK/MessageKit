package com.phantomvk.vkit.util

import java.math.BigDecimal

class FileUtil {
    companion object {
        /**
         * @param size The size value in bytes.
         */
        @JvmStatic
        fun formatFileSize(size: Long): String {
            if (size == 0L) return "0B"

            val kilobytes = size / 1024.0
            if (kilobytes < 1) {
                return size.toString() + "B"
            }

            val megabytes = kilobytes / 1024
            if (megabytes < 1) {
                return BigDecimal(kilobytes)
                    .setScale(0, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB"
            }

            val gigabytes = megabytes / 1024
            if (gigabytes < 1) {
                return BigDecimal(megabytes)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB"
            }

            val terabytes = gigabytes / 1024
            if (terabytes < 1) {
                return BigDecimal(gigabytes)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB"
            }

            return BigDecimal(terabytes)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "TB"
        }
    }
}

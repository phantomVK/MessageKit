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

package com.phantomvk.vkit.util

import java.math.BigDecimal

class FileUtil {
    companion object {
        /**
         * @param size The size value in bytes.
         */
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

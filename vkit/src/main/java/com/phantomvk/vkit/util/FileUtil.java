package com.phantomvk.vkit.util;

import java.math.BigDecimal;

public class FileUtil {

    public static String formatFileSize(long size) {
        if (size < 0) throw new IllegalArgumentException("size must be positive.");
        if (size == 0) return "0B";

        double kiloByte = size / 1024f;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            return new BigDecimal(Double.toString(kiloByte))
                    .setScale(0, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            return new BigDecimal(Double.toString(megaByte))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            return new BigDecimal(Double.toString(gigaByte))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }

        return new BigDecimal(teraBytes)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "TB";
    }
}

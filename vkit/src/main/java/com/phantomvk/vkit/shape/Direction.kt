package com.phantomvk.vkit.shape

import androidx.annotation.IntDef
import com.phantomvk.vkit.shape.Direction.Companion.END
import com.phantomvk.vkit.shape.Direction.Companion.START

@IntDef(START, END)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.VALUE_PARAMETER)
annotation class Direction {
    companion object {
        const val START = 0 // Default value.
        const val END = 1
    }
}

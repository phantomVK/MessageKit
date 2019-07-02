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

import android.view.ViewManager
import android.widget.ProgressBar
import androidx.appcompat.view.ContextThemeWrapper
import org.jetbrains.anko.custom.ankoView

/**
 * Set custom style to ProgressBar programmatically, do it yourself because it is not supported by Anko.
 *
 * See: https://github.com/Kotlin/anko/issues/16
 * See: https://stackoverflow.com/q/11723881/8750399
 */
inline fun ViewManager.styledProgressBar(styleRes: Int = 0, init: ProgressBar.() -> Unit): ProgressBar {
    return ankoView({
        if (styleRes == 0) {
            ProgressBar(it)
        } else {
            ProgressBar(ContextThemeWrapper(it, styleRes), null, 0)
        }
    }, styleRes) { init() }
}

/**
 * Set custom style to ProgressBar programmatically, do it yourself because it is not supported by Anko.
 *
 * See: https://github.com/Kotlin/anko/issues/16
 * See: https://stackoverflow.com/q/11723881/8750399
 */
fun ViewManager.styledProgressBar(styleRes: Int = 0): ProgressBar = styledProgressBar(styleRes) {}

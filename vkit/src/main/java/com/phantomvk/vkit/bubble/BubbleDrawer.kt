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

package com.phantomvk.vkit.bubble

import android.content.Context
import android.graphics.*
import androidx.annotation.ColorInt
import com.phantomvk.vkit.util.dip

class BubbleDrawer(var context: Context,
                   @Direction var arrowDirection: Int = Direction.START,
                   var arrowWidth: Float = context.dip(6F),
                   var arrowHeight: Float = context.dip(12F),
                   var arrowMarginTop: Float = context.dip(4F),
                   var cornerRadius: Float = context.dip(10F),
                   var strokeOffset: Float = context.dip(1F),
                   @ColorInt var strokeColor: Int = 0xFFCFCFCF.toInt()) {

    private val path = Path()

    /**
     * Stroke offset.
     */
    private var mStrokeOffset = (strokeOffset / 2)

    /**
     * The height of upper area with no arrow.
     */
    private val mUpperHeightNA = cornerRadius + arrowMarginTop + mStrokeOffset

    /**
     * The height of upper area with half arrow.
     */
    private val mUpperHeightHA = mUpperHeightNA + (arrowHeight / 2)

    /**
     * The height of upper area with full arrow.
     */
    private val mUpperHeightFA = mUpperHeightNA + arrowHeight

    fun clipPath(canvas: Canvas) {
        canvas.clipPath(path)
    }

    fun draw(canvas: Canvas) {
        sPaint.color = strokeColor
        sPaint.strokeWidth = strokeOffset
        canvas.drawPath(path, sPaint)
    }

    fun resize(w: Float, h: Float) {
        path.reset()

        // Draw arrow.
        path.moveTo(arrowWidth, mUpperHeightFA)
        path.lineTo(0F, mUpperHeightHA)
        path.lineTo(arrowWidth, mUpperHeightNA)
        path.lineTo(arrowWidth, cornerRadius)

        // Upper left corner and the upper line.
        sRectF.set(arrowWidth, 0F, arrowWidth + cornerRadius, cornerRadius)
        path.arcTo(sRectF, 180F, 90F)
        path.lineTo(w - cornerRadius, 0F)

        // Upper right corner and the right line.
        sRectF.set(w - cornerRadius, 0F, w, cornerRadius)
        path.arcTo(sRectF, 270F, 90F)
        path.lineTo(w, h - cornerRadius)

        // Bottom right corner and the bottom line.
        sRectF.set(w - cornerRadius, h - cornerRadius, w, h)
        path.arcTo(sRectF, 0F, 90F)
        path.lineTo((arrowWidth + cornerRadius), h)

        // Bottom left corner.
        sRectF.set(arrowWidth, h - cornerRadius, arrowWidth + cornerRadius, h)
        path.arcTo(sRectF, 90F, 90F)

        path.close()

        if (arrowDirection == Direction.END) {
            val matrix = Matrix()
            matrix.postScale(-1f, 1f)
            matrix.postTranslate(w, 0f)
            path.transform(matrix)
        }
    }

    private companion object {
        @JvmStatic
        private var sRectF = RectF()

        @JvmStatic
        private val sPaint = Paint()

        init {
            sPaint.isAntiAlias = true
            sPaint.style = Paint.Style.STROKE
        }
    }
}

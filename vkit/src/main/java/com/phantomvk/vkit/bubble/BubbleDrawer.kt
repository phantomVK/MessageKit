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

import android.graphics.*
import androidx.annotation.ColorInt

class BubbleDrawer(@Direction var arrowDirection: Int = Direction.START,
                   var arrowWidth: Float = 18F,
                   val arrowHeight: Float = 36F,
                   var arrowMarginTop: Float = 12F,
                   var cornerRadius: Float = 30F,
                   width: Float = 3F,
                   @ColorInt var strokeColor: Int = 0xFFCFCFCF.toInt()) {

    /**
     * Path.
     */
    val path = Path()

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * Stroke offset.
     */
    private var mStrokeOffset = (width / 2)

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

    /**
     * RectF for reusing.
     */
    private var mRectF = RectF()

    init {
        mPaint.color = strokeColor
        mPaint.strokeWidth = width
        mPaint.style = Paint.Style.STROKE
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(path, mPaint)
    }

    fun resize(w: Float, h: Float) {
        path.reset()

        // Draw arrow.
        path.moveTo(arrowWidth, mUpperHeightFA)
        path.lineTo(0F, mUpperHeightHA)
        path.lineTo(arrowWidth, mUpperHeightNA)
        path.lineTo(arrowWidth, cornerRadius)

        // Upper left corner and the upper line.
        mRectF.set(arrowWidth, 0F, arrowWidth + cornerRadius, cornerRadius)
        path.arcTo(mRectF, 180F, 90F)
        path.lineTo(w - cornerRadius, 0F)

        // Upper right corner and the right line.
        mRectF.set(w - cornerRadius, 0F, w, cornerRadius)
        path.arcTo(mRectF, 270F, 90F)
        path.lineTo(w, h - cornerRadius)

        // Bottom right corner and the bottom line.
        mRectF.set(w - cornerRadius, h - cornerRadius, w, h)
        path.arcTo(mRectF, 0F, 90F)
        path.lineTo((arrowWidth + cornerRadius), h)

        // Bottom left corner.
        mRectF.set(arrowWidth, h - cornerRadius, arrowWidth + cornerRadius, h)
        path.arcTo(mRectF, 90F, 90F)

        path.close()

        if (arrowDirection == Direction.END) {
            val matrix = Matrix()
            matrix.postScale(-1f, 1f)
            matrix.postTranslate(w, 0f)
            path.transform(matrix)
        }
    }
}

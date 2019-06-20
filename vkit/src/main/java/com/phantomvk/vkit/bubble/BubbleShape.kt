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

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.shapes.Shape
import androidx.annotation.ColorInt

/*
 * This class used for main thread only, because properties are shared in different cloned objects.
 *
 *   Direction.START           Direction.END
 *    ╭----------╮             ╭----------╮
 *   <           ┆             ┆           >
 *    ┆ Message. ┆             ┆ Message. ┆
 *    ┆          ┆             ┆          ┆
 *    ╰----------╯             ╰----------╯
 */
class BubbleShape(@Direction var arrowDirection: Int = Direction.START,
                  var arrowWidth: Float,
                  var arrowHeight: Float,
                  var arrowMarginTop: Float,
                  var strokeWidth: Float,
                  var cornerRadius: Float,
                  @ColorInt var solidColor: Int,
                  @ColorInt var strokeColor: Int) : Shape() {

    /**
     * The path to fill mCanvas.
     */
    private var mPathFill = Path()

    /**
     * The path to draw stroke.
     */
    private var mPathStroke = Path()

    /**
     * RectF for reusing.
     */
    private var mRectF = RectF()

    /**
     * Stroke offset.
     */
    private var mStrokeOffset = (strokeWidth / 2)

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

    override fun draw(canvas: Canvas, paint: Paint) {
        // Set arrow direction.
        if (arrowDirection == Direction.END) {
            canvas.scale(-1F, 1F, width / 2, height / 2)
        }

        canvas.save()
        paint.isAntiAlias = true

        setPaintFill(paint)
        canvas.drawPath(mPathFill, paint)

        setPaintStroke(paint)
        canvas.drawPath(mPathStroke, paint)
        canvas.restore()
    }

    private fun setPaintFill(paint: Paint) {
        paint.color = solidColor
        paint.style = Paint.Style.FILL
    }

    private fun setPaintStroke(paint: Paint) {
        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = strokeWidth
    }

    /**
     * Resize when width or height is changed.
     */
    override fun onResize(width: Float, height: Float) {
        resizeStrokePath(width, height)
        resizeFillPath(width, height)
    }

    private fun resizeFillPath(width: Float, height: Float) {
        val cornerRadius = cornerRadius
        val arrowWidth = arrowWidth

        mPathFill.reset()

        // Draw arrow.
        mPathFill.moveTo(arrowWidth, mUpperHeightFA)
        mPathFill.lineTo(0F, mUpperHeightHA)
        mPathFill.lineTo(arrowWidth, mUpperHeightNA)
        mPathFill.lineTo(arrowWidth, cornerRadius)

        // Upper left corner and the upper line.
        mRectF.set(arrowWidth, 0F, arrowWidth + cornerRadius, cornerRadius)
        mPathFill.arcTo(mRectF, 180F, 90F)
        mPathFill.lineTo(width - cornerRadius, 0F)

        // Upper right corner and the right line.
        mRectF.set(width - cornerRadius, 0F, width, cornerRadius)
        mPathFill.arcTo(mRectF, 270F, 90F)
        mPathFill.lineTo(width, height - cornerRadius)

        // Bottom right corner and the bottom line.
        mRectF.set(width - cornerRadius, height - cornerRadius, width, height)
        mPathFill.arcTo(mRectF, 0F, 90F)
        mPathFill.lineTo((arrowWidth + cornerRadius), height)

        // Bottom left corner.
        mRectF.set(arrowWidth, height - cornerRadius, arrowWidth + cornerRadius, height)
        mPathFill.arcTo(mRectF, 90F, 90F)

        mPathFill.close()
    }

    private fun resizeStrokePath(width: Float, height: Float) {
        val strokeOffset = mStrokeOffset
        val cornerRadius = cornerRadius
        val arrowWidth = arrowWidth

        mPathStroke.reset()

        // Arrow and the upper left line.
        mPathStroke.moveTo(arrowWidth + strokeOffset, mUpperHeightFA)
        mPathStroke.lineTo(strokeOffset, mUpperHeightHA)
        mPathStroke.lineTo(arrowWidth + strokeOffset, mUpperHeightNA)
        mPathStroke.lineTo(arrowWidth + strokeOffset, cornerRadius)

        // Upper left corner and the upper line.
        mRectF.set(
            arrowWidth + strokeOffset,
            strokeOffset,
            arrowWidth + cornerRadius - strokeOffset,
            cornerRadius - strokeOffset)
        mPathStroke.arcTo(mRectF, 180F, 90F)
        mPathStroke.lineTo(width - cornerRadius, strokeOffset)

        // Upper right corner and the right line.
        mRectF.set(width - cornerRadius + strokeOffset, strokeOffset, width - strokeOffset, cornerRadius - strokeOffset)
        mPathStroke.arcTo(mRectF, 270F, 90F)
        mPathStroke.lineTo(width - strokeOffset, height - cornerRadius)

        // Bottom right corner and the bottom line.
        mRectF.set(
            width - cornerRadius + strokeOffset,
            height - cornerRadius + strokeOffset,
            width - strokeOffset,
            height - strokeOffset)
        mPathStroke.arcTo(mRectF, 0F, 90F)
        mPathStroke.lineTo((arrowWidth + cornerRadius), height - strokeOffset)

        // Bottom left corner.
        mRectF.set(
            arrowWidth + strokeOffset,
            height - cornerRadius + strokeOffset,
            arrowWidth + cornerRadius - strokeOffset,
            height - strokeOffset)
        mPathStroke.arcTo(mRectF, 90F, 90F)

        mPathStroke.close()
    }

    /**
     * Shallow clone as a new object.
     */
    override fun clone(): BubbleShape = super.clone() as BubbleShape
}

package com.phantomvk.vkit.bubble

import android.graphics.*
import androidx.annotation.ColorInt

class BubbleDrawer(@Direction var arrowDirection: Int = Direction.START,
                   var arrowWidth: Float = 18F,
                   val arrowHeight: Float = 36F,
                   var arrowMarginTop: Float = 12F,
                   var cornerRadius: Float = 30F,
                   width: Float = 3F,
                   @ColorInt var color: Int = 0xFFCFCFCF.toInt()) {

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
        mPaint.color = color
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
            matrix.postTranslate(w.toFloat(), 0f)
            path.transform(matrix)
        }
    }
}

package sbis.helpers.view.circle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class CustomCircle @JvmOverloads constructor(
    context: Context? = null,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = -1
) : View(context, attrs, defStyleAttr) {

    val circleBackgroundPaint = Paint().apply {
        isAntiAlias = true
        color = Color.GRAY
        style = Paint.Style.FILL
//        setShadowLayer(5.5F, 6.0F, 6.0F, Color.BLACK)
    }

    val circlePaint = Paint().apply {
        isAntiAlias = true
        color = Color.GREEN
        style = Paint.Style.FILL
        setShadowLayer(5.5F, 6.0F, 6.0F, Color.BLACK)
    }

    val textPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        textSize = 48F
    }

    val textTen = "10"
    val textWidth = textPaint.measureText(textTen)
    val bounds = Rect().apply {
        textPaint.getTextBounds(textTen, 0, textTen.length, this)
    }
    val textHeight = bounds.height()


    override fun onDraw(canvas: Canvas) {
        val x = (width / 2).toFloat()
        val y = (height / 2).toFloat()
        val fullViewRadius = (width / 2).toFloat()

        //Draw Background Circle
        canvas.drawCircle(x, y, fullViewRadius, circleBackgroundPaint)

        //Draw Foreground Circle
        canvas.drawCircle(x, y, fullViewRadius / 2, circlePaint)

        //Draw Center Text
        val des = (textPaint.descent() + textPaint.ascent()) / 2
        canvas.drawText(textTen, x - (textWidth / 2), y - des, textPaint)

    }
}
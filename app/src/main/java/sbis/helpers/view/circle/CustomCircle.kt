package sbis.helpers.view.circle

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import sbis.faceinfo.R

class CustomCircle @JvmOverloads constructor(
    context: Context? = null,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = -1
) : View(context, attrs, defStyleAttr) {

    private var paddingView = 0F
    private var isInverseColors = false
    private var isCenterParam = false
    private var isEnabledView = true
    private var index = 0

    private val colors = listOf(
        Color.parseColor("#F82E47"), // <- BAD
        Color.parseColor("#FF3366"),
        Color.parseColor("#FF6666"),
        Color.parseColor("#FF9966"),
        Color.parseColor("#FF9933"),
        Color.parseColor("#FFCC33"),
        Color.parseColor("#99CC00"),
        Color.parseColor("#00CC33"),
        Color.parseColor("#00CC99"),
        Color.parseColor("#00CC66")  // <- GOOD
    )

    private val colorsCenter = listOf(
        Color.parseColor("#F82E47"), //F82E47<- BAD
        Color.parseColor("#FF9966"), //FF9966
        Color.parseColor("#FFCC33"), //FFCC33
        Color.parseColor("#00CC33"), //00CC33
        Color.parseColor("#00CC66"), //00CC66
        Color.parseColor("#00CC33"), //00CC33
        Color.parseColor("#FFCC33"), //FFCC33
        Color.parseColor("#FF9966"), //FF9966
        Color.parseColor("#FF3366"), //FF3366
        Color.parseColor("#F82E47")  // F82E47 // <- GOOD
    )

    private val scaleStart = 0.4F
    private val scaleEnd = 1.0F
    private val scaleStepCount = 9

    private val defaultBackgroundColor = ContextCompat.getColor(context!!, R.color.colorCircleBackground)
    private val defaultBackgroundColorInactive =
        ContextCompat.getColor(context!!, R.color.colorCircleBackgroundInactive)

    private val scaleList = mutableListOf<Float>().apply {
        val scaleStep = (scaleEnd - scaleStart) / scaleStepCount
        add(scaleStart)

        (0..8).forEach { add(this[it] + scaleStep) }
    }

    private val scaleListCenter = mutableListOf<Float>().apply {
        add(0.4F)
        add(0.53333336F)
        add(0.73333335F)
        add(0.8666667F)
        add(1.0F)
        add(0.8666667F)
        add(0.73333335F)
        add(0.53333336F)
        add(0.46666667F)
        add(0.4F)
    }

    private val circleBackgroundPaint = Paint().apply {
        isAntiAlias = true
        color = defaultBackgroundColor
        style = Paint.Style.FILL
    }

    private val circlePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL

        //shadow
        setShadowLayer(10f, 0f, 0f, 0x80000000.toInt())
        setLayerType(View.LAYER_TYPE_SOFTWARE, this)
    }

    private val textPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        textSize = 48F
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

        setLayerType(View.LAYER_TYPE_SOFTWARE, this)
    }

    private var textValue = "10"
    private var textWidth = textPaint.measureText(textValue)
    private var bounds = Rect().apply {
        textPaint.getTextBounds(textValue, 0, textValue.length, this)
    }
    private var textHeight = bounds.height()

    fun setInverseColors(isInverse: Boolean) {
        isInverseColors = isInverse
    }

    fun setTextSize(testSizeDp: Float) {
        textPaint.textSize = testSizeDp.dp2px()
    }

    fun setTextColor(colorId: Int) {
        textPaint.color = ContextCompat.getColor(context, colorId)
    }

    fun setPadding(paddingDp: Float) {
        paddingView = paddingDp.dp2px()
    }

    fun setParamValue(value: Int) {
        index = value
        textValue = if (value >= 0) value.toString() else "?"
        calculateTextParam()
    }

    fun setCenterParam(isCenter: Boolean) {
        isCenterParam = isCenter
    }

    fun setEnabledView(isEnabled: Boolean) {
        isEnabledView = isEnabled
    }

    private fun calculateTextParam() {
        textWidth = textPaint.measureText(textValue)

        textPaint.getTextBounds(textValue, 0, textValue.length, bounds)
        textHeight = bounds.height()
    }

    override fun onDraw(canvas: Canvas) {
        val x = (width / 2).toFloat()
        val y = (height / 2).toFloat()
        val fullViewRadius = (width / 2).toFloat() - paddingView * 2

        //Draw Background Circle
        canvas.drawCircle(x, y, fullViewRadius, circleBackgroundPaint)

        //Draw Foreground Circle
        val paramMaxIndex = 9 //0..9

        val tempIndex = index - 1
        val currentIndex = if (tempIndex < 0) 0 else tempIndex
        val colorIndex = if (!isInverseColors) currentIndex else paramMaxIndex - currentIndex

        val scaleIndex = if (currentIndex >= 0) currentIndex else 0
        val foregroundCircleRadius = fullViewRadius * if(!isCenterParam) scaleList[scaleIndex] else scaleListCenter[scaleIndex]
        canvas.drawCircle(x, y, foregroundCircleRadius, circlePaint.apply {
            color =
                    if (!isEnabledView) defaultBackgroundColorInactive
                    else {
                        if (!isCenterParam) colors[colorIndex] else colorsCenter[colorIndex]
                    }
        })

        //Draw Center Text
        val des = (textPaint.descent() + textPaint.ascent()) / 2
        canvas.drawText(textValue, x - (textWidth / 2), y - des, textPaint)
    }

    private fun Float.dp2px() =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics
        )
}
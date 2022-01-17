package android.examample.imageloader_hw_31.HomeWorck_4.UI

import android.content.Context
import android.examample.imageloader_hw_31.R
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class CustomView_Clock @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defaultAttrs: Int = 0
) : View(context, attrs, defaultAttrs) {

    private var hour = 0
    private var minute = 0
    private var secunde = 0

    private var centerX = 0f
    private var centerY = 0f

    private var ARROWS_DATA = mapOf(
        HOURS_ARROW to ArrowData(Color.BLACK, 40f, 250f, HOURS_DEGRES),
        MINUTES_ARROW to ArrowData(Color.BLACK, 30f, 330f, MANDS_DEGRES),
        SECONDS_ARROW to ArrowData(Color.BLACK, 15f, 410f, MANDS_DEGRES))

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomView_Clock, 0, 0).apply {
            try {
                ARROWS_DATA[HOURS_ARROW]!!.color = getColor(R.styleable.CustomView_Clock_hoursArrowCollor, ARROWS_DATA[HOURS_ARROW]!!.color)
                ARROWS_DATA[HOURS_ARROW]!!.strokeWith = getFloat(R.styleable.CustomView_Clock_hoursArrowThickness, ARROWS_DATA[HOURS_ARROW]!!.strokeWith)
                ARROWS_DATA[HOURS_ARROW]!!.length = getFloat(R.styleable.CustomView_Clock_hoursArrowLength, ARROWS_DATA[HOURS_ARROW]!!.length)

                ARROWS_DATA[MINUTES_ARROW]!!.color = getColor(R.styleable.CustomView_Clock_minutesArrowCollor, ARROWS_DATA[MINUTES_ARROW]!!.color)
                ARROWS_DATA[MINUTES_ARROW]!!.strokeWith = getFloat(R.styleable.CustomView_Clock_minutesArrowThickness, ARROWS_DATA[MINUTES_ARROW]!!.strokeWith)
                ARROWS_DATA[MINUTES_ARROW]!!.length = getFloat(R.styleable.CustomView_Clock_minutesArrowLength, ARROWS_DATA[MINUTES_ARROW]!!.length)

                ARROWS_DATA[SECONDS_ARROW]!!.color = getColor(R.styleable.CustomView_Clock_secondsArrowCollor, ARROWS_DATA[SECONDS_ARROW]!!.color)
                ARROWS_DATA[SECONDS_ARROW]!!.strokeWith = getFloat(R.styleable.CustomView_Clock_secondsArrowThickness, ARROWS_DATA[SECONDS_ARROW]!!.strokeWith)
                ARROWS_DATA[SECONDS_ARROW]!!.length = getFloat(R.styleable.CustomView_Clock_secondsArrowLength, ARROWS_DATA[SECONDS_ARROW]!!.length)
            } finally {
                recycle()
            }
        }
    }

    private val painter = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = CIRCLE_LINE_THIKNESS
    }

    private val painterArrow = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
    }

    private fun getDate() {
        val calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        secunde = calendar.get(Calendar.SECOND)
    }

    private fun drawClockCanvas(canvas: Canvas, x: Float, y: Float) {
        painter.style = Paint.Style.STROKE
        canvas.drawCircle(centerX, centerY, RADIUS, painter)
        for (i: Int in (1..12)) {
            painter.style = Paint.Style.FILL
            canvas.drawLine(x, y - RADIUS + 60, x, y - RADIUS, painter)
            canvas.rotate(HOURS_DEGRES, x, y)
        }
    }

    private fun drawClockArrows(canvas: Canvas, x: Float, y: Float, data: ArrowData, time: Int) {
        var angle = data.angleMultiplaer * time
        if (data.angleMultiplaer.equals(HOURS_DEGRES))
            angle += (minute / 60f) * HOURS_DEGRES

        painterArrow.color = data.color
        painterArrow.strokeWidth = data.strokeWith

        canvas.rotate(angle, x, y)
        canvas.drawLine(x, y + data.length*ARROW_LENGTH_MINUS_MULTIPLY, x, y - data.length*ARROW_LENGTH_PLUSE_MULTIPLY, painterArrow)
        canvas.rotate(-angle, x, y)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        getDate()
        canvas.drawColor(Color.WHITE)
        drawClockCanvas(canvas, centerX, centerY)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[HOURS_ARROW]!!, hour)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[MINUTES_ARROW]!!, minute)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[SECONDS_ARROW]!!, secunde)
        invalidate()
    }

    private companion object {
        private const val CIRCLE_LINE_THIKNESS = 30f
        private const val RADIUS = 400f
        private const val HOURS_DEGRES = 30f
        private const val MANDS_DEGRES = 6f
        private const val HOURS_ARROW = "hoursArrow"
        private const val MINUTES_ARROW = "minutesArrow"
        private const val SECONDS_ARROW = "secondsArrow"
        private const val ARROW_LENGTH_PLUSE_MULTIPLY = 0.85f
        private const val ARROW_LENGTH_MINUS_MULTIPLY = 0.15F
    }
}

class ArrowData(var color: Int, var strokeWith: Float, var length: Float, val angleMultiplaer: Float)
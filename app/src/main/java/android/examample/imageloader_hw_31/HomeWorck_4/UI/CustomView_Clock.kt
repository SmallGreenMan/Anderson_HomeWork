package android.examample.imageloader_hw_31.HomeWorck_4.UI

import android.content.Context
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
        canvas.drawCircle(centerX, centerY, SMILE_RADIUS, painter)
        for (i: Int in (1..12)) {
            painter.style = Paint.Style.FILL
            canvas.drawLine(x, y - SMILE_RADIUS + 60, x, y - SMILE_RADIUS, painter)
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
        canvas.drawLine(x, y + data.lengthMinus, x, y - data.lengthPlus, painterArrow)
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
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[HOURS_ARROW]!!, hour)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[MINUTES_ARROW]!!, minute)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[SECONDS_ARROW]!!, secunde)
        drawClockCanvas(canvas, centerX, centerY)
        invalidate()
    }

    private companion object {
        private const val CIRCLE_LINE_THIKNESS = 30f
        private const val SMILE_RADIUS = 400f
        private const val HOURS_DEGRES = 30f
        private const val MANDS_DEGRES = 6f
        private const val HOURS_ARROW = "hoursArrow"
        private const val MINUTES_ARROW = "minutesArrow"
        private const val SECONDS_ARROW = "secondsArrow"
        private val ARROWS_DATA = mapOf(
            HOURS_ARROW to ArrowData(Color.BLUE, 40f, 70, 180, HOURS_DEGRES),
            MINUTES_ARROW to ArrowData(Color.GREEN, 30f, 90, 240, MANDS_DEGRES),
            SECONDS_ARROW to ArrowData(Color.RED, 15f, 120, 280, MANDS_DEGRES))
    }
}

class ArrowData(val color: Int, val strokeWith: Float, val lengthMinus: Int, val lengthPlus: Int, val angleMultiplaer: Float)
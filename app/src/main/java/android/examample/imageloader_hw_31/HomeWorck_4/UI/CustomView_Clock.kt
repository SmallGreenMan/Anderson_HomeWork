package android.examample.imageloader_hw_31.HomeWorck_4.UI

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

@SuppressLint("Recycle")
class CustomView_Clock @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defaultAttrs: Int = 0
) : View(context, attrs, defaultAttrs) {

    private var hour = 0
    private var minute = 0
    private var secunde = 0

    private var angle = 0f

    private var centerX = 0f
    private var centerY = 0f

    private val painter = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 30F
    }

    private val painterClockLine = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = 30F
    }

    private val painterArrow = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = 10f
    }

    private fun getDate() {
        val calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        secunde = calendar.get(Calendar.SECOND)
    }

    private fun drawClockLine(canvas: Canvas, x: Float, y: Float) {
        for (i: Int in (1..12)) {
            canvas.drawLine(x, y - SMILE_RADIUS + 60, x, y - SMILE_RADIUS, painterClockLine)
            canvas.rotate(HOURS_DEGRES, x, y)
        }
    }

    private fun drawClockArrows(canvas: Canvas, x: Float, y: Float, data: ArrowData, time: Int) {
        angle = data.angleMultiplaer * time
        if (data.angleMultiplaer.equals(HOURS_DEGRES))
            angle += (minute / 60f) * HOURS_DEGRES

        painterArrow.color = data.color
        painterArrow.strokeWidth = data.strokeWith

        canvas.rotate(angle, x, y)
        canvas.drawLine(x, y + data.lengthMinus, x, y - data.lengthPlus, painterArrow)
        canvas.rotate(-angle, x, y)
    }

    override fun onDraw(canvas: Canvas) {
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
        getDate()
        canvas.drawColor(Color.WHITE)
        canvas.save()
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[HOURS_ARROW]!!, hour)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[MINUTES_ARROW]!!, minute)
        drawClockArrows(canvas, centerX, centerY, ARROWS_DATA[SECONDS_ARROW]!!, secunde)
        canvas.drawCircle(centerX, centerY, SMILE_RADIUS, painter)
        drawClockLine(canvas, centerX, centerY)
        canvas.restore()
        invalidate()
    }

    private companion object {
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
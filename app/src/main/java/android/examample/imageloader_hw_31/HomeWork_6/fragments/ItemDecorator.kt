package android.examample.imageloader_hw_31.HomeWork_6.fragments

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator(
    private val marginTop: Int = 0,
    private val marginBottom: Int = 0,
    private val marginLeft: Int = 0,
    private val marginRight: Int = 0,
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.top = view.context.dpToPx(marginTop).toInt()
        outRect.bottom = view.context.dpToPx(marginBottom).toInt()
        outRect.left = view.context.dpToPx(marginLeft).toInt()
        outRect.right = view.context.dpToPx(marginRight).toInt()
    }

    private fun Context.dpToPx(dp: Int): Float {
        return dp.toFloat() * this.resources.displayMetrics.density
    }
}
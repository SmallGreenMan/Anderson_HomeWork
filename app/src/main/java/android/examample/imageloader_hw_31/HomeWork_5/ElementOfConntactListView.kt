package android.examample.imageloader_hw_31.HomeWork_5

import android.content.Context
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHw5ListElementBinding
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout


//@JvmOverloads constructor

class ElementOfConntactListView (
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, null, 0,0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, null, 0)
    constructor(context: Context) : this(context, null)


    private val binding: FragmentHw5ListElementBinding

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.fragment_hw_5_list_element, this, true)
        binding = FragmentHw5ListElementBinding.bind(this)

    }
}
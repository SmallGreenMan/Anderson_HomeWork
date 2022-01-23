package android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement

import android.content.Context
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHw5ListElementBinding
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout


enum class ElementOfContactActions {
    OPEN, DELETE
}

typealias ElementOfContactActionsListener = (ElementOfContactActions) -> Unit

class ElementOfContactListView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var listener: ElementOfContactActionsListener? = null

    private var id: Int? = -1
        set(value) {
            field = value
        }


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        null,
        0,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, null, 0)
    constructor(context: Context) : this(context, null)

    private val binding: FragmentHw5ListElementBinding

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.fragment_hw_5_list_element, this, true)
        binding = FragmentHw5ListElementBinding.bind(this)
        initListeners()
    }

    private fun initListeners() {
        binding.elementButton.setOnClickListener {
            this.listener?.invoke(ElementOfContactActions.OPEN)
        }
        binding.elementButton.setOnLongClickListener {
            this.listener?.invoke(ElementOfContactActions.DELETE)
            return@setOnLongClickListener true
        }
    }

    fun onLongClick(v: View?): Boolean {
        this.listener?.invoke(ElementOfContactActions.OPEN)
        return false
    }

    fun setFirstNameText(text: String?) {
        binding.firstNameTextView.text = text
    }

    fun setlastNameText(text: String?) {
        binding.lastNameTextView.text = text
    }

    fun setTelephoneNumberText(text: String?) {
        binding.telephonNumberTextView.text = text
    }

    fun setListener(listener: ElementOfContactActionsListener?) {
        this.listener = listener
    }
}
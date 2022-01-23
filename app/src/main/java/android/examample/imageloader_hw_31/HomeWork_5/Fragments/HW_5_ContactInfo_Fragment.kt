package android.examample.imageloader_hw_31.HomeWork_5.Fragments

import android.examample.imageloader_hw_31.HomeWork_5.testIndex
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHW5ContactInfoBinding
import android.widget.Toast
import androidx.core.os.bundleOf

class HW_5_ContactInfo_Fragment : Fragment() {

    private var _binding: FragmentHW5ContactInfoBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var id: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHW5ContactInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = arguments?.getString(NAME_KEY) ?: ""
        val lastName = arguments?.getString(LASTNAME_KEY) ?: ""
        val telephoneNumber = arguments?.getString(TELEPHONE_NUMBER_KEY) ?: ""
        id = arguments?.getInt(ID_KEY) ?: -1

        binding.firstNameEditText.setText(testIndex.toString())//firstName)
        binding.lastNameEditText.setText(lastName)
        binding.telephoneNumberEditText.setText(telephoneNumber)

        binding.cancelButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
        binding.saveButton.setOnClickListener {
            val firstNamesResours =
                resources.getStringArray(android.examample.imageloader_hw_31.R.array.firstNames)
            val lastNamesResours =
                resources.getStringArray(android.examample.imageloader_hw_31.R.array.lastNames)
            val telephoneNumbersResours =
                resources.getStringArray(android.examample.imageloader_hw_31.R.array.telephoneNumbers)


            Toast.makeText(context, "${id} ${binding.firstNameEditText.text} EXIT !!!!", Toast.LENGTH_SHORT).show()
            if (id!! >= 0){
                firstNamesResours[id!!] = binding.firstNameEditText.text.toString()
                lastNamesResours[id!!] = binding.lastNameEditText.text.toString()
                telephoneNumbersResours[id!!] = binding.telephoneNumberEditText.text.toString()
            }

            requireActivity().supportFragmentManager.popBackStackImmediate()

//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.fragment_container_HW_5,
//                    HW_5_ContactList_Fragment.newInstance(
//                        binding.firstNameEditText.text.toString(),
//                        binding.lastNameEditText.text.toString(),
//                        binding.telephoneNumberEditText.text.toString(),
//                        id!!
//                    )
//                )
//                .addToBackStack(null) // если необходимо, чтоб по нажатию "назад" вы могли вернуться на предыдущий фрагмент. Вместо null можно задать свой ключ.
//                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val NAME_KEY = "NAME_KEY"
        private const val LASTNAME_KEY = "LASTNAME_KEY"
        private const val TELEPHONE_NUMBER_KEY = "TELEPHONE_NUMBER_KEY"
        private const val ID_KEY = "ID_KEY"

        @JvmStatic
        fun newInstance(
            firstName: String,
            lastName: String,
            telephoneNumber: String,
            id: Int
        ) = HW_5_ContactInfo_Fragment().apply {
                arguments = bundleOf(
                    NAME_KEY to firstName,
                    LASTNAME_KEY to lastName,
                    TELEPHONE_NUMBER_KEY to telephoneNumber,
                    ID_KEY to id
                )
            }
    }
}
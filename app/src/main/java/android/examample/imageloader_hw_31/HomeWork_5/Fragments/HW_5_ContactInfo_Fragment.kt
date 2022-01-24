package android.examample.imageloader_hw_31.HomeWork_5.Fragments

import android.examample.imageloader_hw_31.HomeWork_5.contactsData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.examample.imageloader_hw_31.databinding.FragmentHW5ContactInfoBinding
import androidx.core.os.bundleOf

class HW_5_ContactInfo_Fragment : Fragment() {

    private var _binding: FragmentHW5ContactInfoBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var id: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHW5ContactInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = arguments?.getString(NAME_KEY) ?: ""
        val lastName = arguments?.getString(LASTNAME_KEY) ?: ""
        val telephoneNumber = arguments?.getString(TELEPHONE_NUMBER_KEY) ?: ""
        id = arguments?.getInt(ID_KEY) ?: -1

        binding.firstNameEditText.setText(firstName)
        binding.lastNameEditText.setText(lastName)
        binding.telephoneNumberEditText.setText(telephoneNumber)

        initOnClickListeners()
    }

    private fun initOnClickListeners(){
        binding.cancelButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
        binding.saveButton.setOnClickListener {
            if (id!! >= 0) {
                contactsData[id!!]?.name = binding.firstNameEditText.text.toString()
                contactsData[id!!]?.lastName = binding.lastNameEditText.text.toString()
                contactsData[id!!]?.telephoneNumber =
                    binding.telephoneNumberEditText.text.toString()
            }
            requireActivity().supportFragmentManager.popBackStackImmediate()
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
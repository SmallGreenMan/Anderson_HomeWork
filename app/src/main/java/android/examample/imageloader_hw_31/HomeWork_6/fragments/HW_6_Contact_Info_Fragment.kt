package android.examample.imageloader_hw_31.HomeWork_6.fragments

import android.examample.imageloader_hw_31.HomeWork_5.contactsData
import android.examample.imageloader_hw_31.HomeWork_6.data.hw6_contactsData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHW6ContactInfoBinding
import androidx.core.os.bundleOf
import com.squareup.picasso.Picasso


class HW_6_Contact_Info_Fragment : Fragment() {

    private var _binding: FragmentHW6ContactInfoBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var id: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHW6ContactInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = arguments?.getString(NAME_KEY) ?: ""
        val lastName = arguments?.getString(LASTNAME_KEY) ?: ""
        val telephoneNumber = arguments?.getString(TELEPHONE_NUMBER_KEY) ?: ""
        val img = arguments?.getString(IMG_KEY) ?: ""
        id = arguments?.getInt(ID_KEY) ?: -1

        binding.firstNameEditText.setText(firstName)
        binding.lastNameEditText.setText(lastName)
        binding.telephoneNumberEditText.setText(telephoneNumber)

        Picasso.get()
            .load(img)
            .placeholder(R.drawable.ic_placeholder_foreground)
            .error(R.drawable.ic_placeholder_error_foreground)
            .into(binding.imgImageView)

        initOnClickListeners()
    }

    private fun initOnClickListeners(){
        binding.cancelButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
        binding.saveButton.setOnClickListener {
            if (id!! >= 0) {
                hw6_contactsData[id!!]?.name = binding.firstNameEditText.text.toString()
                hw6_contactsData[id!!]?.lastName = binding.lastNameEditText.text.toString()
                hw6_contactsData[id!!]?.telephoneNumber =
                    binding.telephoneNumberEditText.text.toString()
            }
            requireActivity().supportFragmentManager.popBackStackImmediate()
        }
    }

    companion object {
        private const val NAME_KEY = "NAME_KEY"
        private const val LASTNAME_KEY = "LASTNAME_KEY"
        private const val TELEPHONE_NUMBER_KEY = "TELEPHONE_NUMBER_KEY"
        private const val IMG_KEY = "IMG_KEY"
        private const val ID_KEY = "ID_KEY"

        @JvmStatic
        fun newInstans(
            firstName: String,
            lastName: String,
            telephoneNumber: String,
            img: String,
            id: Int
        ) = HW_6_Contact_Info_Fragment().apply {
            arguments = bundleOf(
                NAME_KEY to firstName,
                LASTNAME_KEY to lastName,
                TELEPHONE_NUMBER_KEY to telephoneNumber,
                IMG_KEY to img,
                ID_KEY to id
            )
        }
    }

}
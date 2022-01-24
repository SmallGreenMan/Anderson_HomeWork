package android.examample.imageloader_hw_31.HomeWork_5.Fragments

import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactActions
import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactListView
import android.examample.imageloader_hw_31.HomeWork_5.MainActivity_HW_5
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHW5ContactListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf

import android.examample.imageloader_hw_31.HomeWork_5.contactsData
import android.text.Layout

class HW_5_ContactList_Fragment : Fragment() {

    private var _binding: FragmentHW5ContactListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHW5ContactListBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawContactList(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun drawContactList(view: View) {
        val mainLayout = binding.linerLayoutHw5

        for ((key, value) in contactsData) {

            val contactView = context?.let { ElementOfContactListView(it) }
            contactView?.setFirstNameText(value.name)
            contactView?.setlastNameText(value.lastName)
            contactView?.setTelephoneNumberText(value.telephoneNumber)
            mainLayout.addView(contactView)

            contactView?.setListener {
                when (it) {
                    ElementOfContactActions.OPEN -> {       // --- Open Contact_Info_Fragment
                        openContactInfo(value)
                    }
                    else -> {                               // --- Delete chosen contact
                        Toast.makeText(context, "${value.id} $it", Toast.LENGTH_SHORT).show()
                        deleteChosenElementFromList(view, mainLayout, value.id)
                    }
                }
            }
        }
    }

    private fun openContactInfo(e: MainActivity_HW_5.ListOfContactsClass){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_HW_5,
                HW_5_ContactInfo_Fragment.newInstance(
                    e.name,
                    e.lastName,
                    e.telephoneNumber,
                    e.id
                )
            )
            .addToBackStack(null)
            .commit()
    }

    private fun deleteChosenElementFromList(view: View, mainLayout: LinearLayout, id: Int){
        if (contactsData.containsKey(id)) {
            contactsData.remove(id)
            mainLayout.removeAllViews()
            drawContactList(view)
        }
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
        ) = HW_5_ContactList_Fragment().apply {
            arguments = bundleOf(
                NAME_KEY to firstName,
                LASTNAME_KEY to lastName,
                TELEPHONE_NUMBER_KEY to telephoneNumber,
                ID_KEY to id
            )
        }
    }
}
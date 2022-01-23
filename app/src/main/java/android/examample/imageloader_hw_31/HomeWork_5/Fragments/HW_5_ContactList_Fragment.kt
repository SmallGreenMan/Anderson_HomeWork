package android.examample.imageloader_hw_31.HomeWork_5.Fragments

import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactActions
import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactListView
import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.FragmentHW5ContactInfoBinding
import android.examample.imageloader_hw_31.databinding.FragmentHW5ContactListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf

class HW_5_ContactList_Fragment : Fragment() {

    private var _binding: FragmentHW5ContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactsData: MutableMap<Int, ListOfContactsClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(
//            android.examample.imageloader_hw_31.R.layout.fragment_h_w_5_contact_list_,
//            container,
//            false
//        )

        _binding = FragmentHW5ContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //_binding = FragmentHW5ContactListBinding.bind(view)

        val id = arguments?.getInt(HW_5_ContactList_Fragment.ID_KEY) ?: -1

//        Toast.makeText(context, "${id} ${savedInstanceState}", Toast.LENGTH_SHORT).show()

        if (savedInstanceState == null)
            initData()
//        else{
//            val firstName = arguments?.getString(HW_5_ContactList_Fragment.NAME_KEY)
//            val lastName = arguments?.getString(HW_5_ContactList_Fragment.LASTNAME_KEY)
//            val telephoneNumber = arguments?.getString(HW_5_ContactList_Fragment.TELEPHONE_NUMBER_KEY)
//            //val id = arguments?.getInt(HW_5_ContactList_Fragment.ID_KEY) ?: -1
//
//            if (id > -1){
//                contactsData[id]?.name = firstName!!
//                contactsData[id]?.lastName = lastName!!
//                contactsData[id]?.telephoneNumber = telephoneNumber!!
//            }
//        }

        droweList(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val firstNames =
            resources.getStringArray(android.examample.imageloader_hw_31.R.array.firstNames)
        val lastNames =
            resources.getStringArray(android.examample.imageloader_hw_31.R.array.lastNames)
        val telephoneNumbers =
            resources.getStringArray(android.examample.imageloader_hw_31.R.array.telephoneNumbers)

        contactsData = mutableMapOf()

        for (i in (0..firstNames.size - 1)) {
            contactsData.put(
                i, ListOfContactsClass(
                    firstNames[i],
                    lastNames[i],
                    telephoneNumbers[i],
                    i
                )
            )
        }
    }

    private fun droweList(view: View) {
        val mainLayout = binding?.linerLayoutHw5 as LinearLayout

        for ((key, value) in contactsData) {

            val contactView = getContext()?.let { ElementOfContactListView(it) }
            contactView?.setFirstNameText(value.name)
            contactView?.setlastNameText(value.lastName)
            contactView?.setTelephoneNumberText(value.telephoneNumber)
            mainLayout.addView(contactView)

            contactView?.setListener {
                when (it) {
                    ElementOfContactActions.OPEN -> {
                        //Toast.makeText(context, "${value.id} ${it}", Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            //.add(R.id.fragment_container_HW_5, HW_5_ContactInfo_Fragment()) // or replace с теми же параметрами
                            .replace(
                                R.id.fragment_container_HW_5,
                                HW_5_ContactInfo_Fragment.newInstance(
                                    value.name,
                                    value.lastName,
                                    value.telephoneNumber,
                                    value.id
                                )
                            )
                            .addToBackStack(null) // если необходимо, чтоб по нажатию "назад" вы могли вернуться на предыдущий фрагмент. Вместо null можно задать свой ключ.
                            .commit()
                    }
                    else -> {
                        Toast.makeText(context, "${value.id} ${it}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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


    inner class ListOfContactsClass(
        var name: String,
        var lastName: String,
        var telephoneNumber: String,
        var id: Int
    )
}
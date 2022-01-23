package android.examample.imageloader_hw_31.HomeWork_5.Fragments

import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactActions
import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactActionsListener
import android.examample.imageloader_hw_31.HomeWork_5.CustomViewElement.ElementOfContactListView
import android.examample.imageloader_hw_31.databinding.FragmentHW5ContactListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HW_5_ContactList_Fragment : Fragment() {

    private var _binding: FragmentHW5ContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var contactsData: MutableMap<Int, ListOfContactsClass>

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            android.examample.imageloader_hw_31.R.layout.fragment_h_w_5_contact_list_,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHW5ContactListBinding.bind(view)

        if (savedInstanceState == null)
            initData()

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

        for (i in (0..firstNames.size-1)) {
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

    private fun droweList(view: View){
        //val mainLayout = findViewById<view>(R.id.linerLayout_hw_5) as LinearLayout
        val mainLayout = _binding?.linerLayoutHw5 as LinearLayout

        for ((key, value) in contactsData){

            val contactView = getContext()?.let { ElementOfContactListView(it) }
                contactView?.setFirstNameText(value.name)
                contactView?.setlastNameText(value.lastName)
                contactView?.setTelephoneNumberText(value.telephoneNumber)
                mainLayout.addView(contactView)

                contactView?.setListener {
                    if (it == ElementOfContactActions.OPEN) {
                        Toast.makeText(context, "${value.id} ${it}", Toast.LENGTH_SHORT).show()
                    }
                    if (it == ElementOfContactActions.DELETE) {
                        Toast.makeText(context, "${value.id} ${it}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HW_5_ContactList_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    inner class ListOfContactsClass(
        var name: String,
        var lastName: String,
        var telephoneNumber: String,
        var id: Int
    )
}
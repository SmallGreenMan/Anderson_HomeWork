package android.examample.imageloader_hw_31.HomeWork_5

import android.examample.imageloader_hw_31.HomeWork_5.Fragments.HW_5_ContactList_Fragment
import android.examample.imageloader_hw_31.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

lateinit var contactsData: MutableMap<Int, MainActivity_HW_5.ListOfContactsClass>

class MainActivity_HW_5 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw5)

        if (savedInstanceState == null)
            initData()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_HW_5, HW_5_ContactList_Fragment())
            .commit()
    }

    private fun initData() {
        val firstNames = resources.getStringArray(R.array.firstNames)
        val lastNames = resources.getStringArray(R.array.lastNames)
        val telephoneNumbers = resources.getStringArray(R.array.telephoneNumbers)

        contactsData = mutableMapOf()

        for (i in firstNames.indices) {
            contactsData[i] = ListOfContactsClass(
                firstNames[i],
                lastNames[i],
                telephoneNumbers[i],
                i
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
package android.examample.imageloader_hw_31.HomeWork_6


import android.examample.imageloader_hw_31.HomeWork_6.data.HW_6_ContactsData
import android.examample.imageloader_hw_31.HomeWork_6.data.intData
import android.examample.imageloader_hw_31.HomeWork_6.fragments.HW_6_Contact_List_Fragment
import android.examample.imageloader_hw_31.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity_HW_6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw6)

        if (savedInstanceState == null) {
            intData(160)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_HW_6, HW_6_Contact_List_Fragment())
            .commit()
    }
}
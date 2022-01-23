package android.examample.imageloader_hw_31.HomeWork_5

import android.examample.imageloader_hw_31.HomeWork_5.Fragments.HW_5_ContactList_Fragment
import android.examample.imageloader_hw_31.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class MainActivity_HW_5 : AppCompatActivity() {

//    lateinit var contactsData: List<ListOfContactsClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw5)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_HW_5, HW_5_ContactList_Fragment()) // or replace с теми же параметрами
            //.addToBackStack(null) // если необходимо, чтоб по нажатию "назад" вы могли вернуться на предыдущий фрагмент. Вместо null можно задать свой ключ.
            .commit();
    }
}
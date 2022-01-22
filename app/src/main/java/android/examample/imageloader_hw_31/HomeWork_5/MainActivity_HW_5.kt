package android.examample.imageloader_hw_31.HomeWork_5

import android.examample.imageloader_hw_31.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import android.view.View

import android.widget.LinearLayout
import android.widget.Toast


class MainActivity_HW_5 : AppCompatActivity() {

    lateinit var contactsData: List<ListOfContactsClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw5)

        if (savedInstanceState == null)
            initData()

        val mainLayout = findViewById<View>(R.id.linerLayout_hw_5) as LinearLayout

        for (e in contactsData){
            val textView = TextView(this)

            textView.setText(e.name)
            textView.setId(e.id)

            mainLayout.addView(textView)

            textView.setOnClickListener{
                Toast.makeText(this, e.id.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initData(){
        contactsData = listOf(
            ListOfContactsClass("Петр", "Петрович", "+111111111", 101),
            ListOfContactsClass("Александр", "Александрович", "+222222222", 102)
        )
    }

    inner class ListOfContactsClass (var name: String, var lastName: String, var telephoneNumber: String, var id: Int)
}
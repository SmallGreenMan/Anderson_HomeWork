package android.examample.imageloader_hw_31.HomeWorck_4

import android.examample.imageloader_hw_31.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class MainActivity_HW_4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw4)

        findViewById<Toolbar>(R.id.toolBar_HW_4).setNavigationOnClickListener{
            finish()
        }
    }
}
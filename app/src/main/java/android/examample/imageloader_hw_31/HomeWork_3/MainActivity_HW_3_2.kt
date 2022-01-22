package android.examample.imageloader_hw_31.HomeWork_3

import android.examample.imageloader_hw_31.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar


class MainActivity_HW_3_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw32)

        findViewById<Toolbar>(R.id.toolBar_HW_32).setNavigationOnClickListener{
            finish()
        }

    }
}
package android.examample.imageloader_hw_31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.examample.imageloader_hw_31.databinding.ActivityMainBinding
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToHomework_3_1(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_3_1::class.java))
    }

    fun goToHomework_3_2(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_3_2::class.java))
    }

    fun goToHomework_2_1(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_2_1::class.java))
    }

    fun goToHomework_2_2(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_2_2::class.java))
    }

    fun goToHomework_2_3(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_2_3::class.java))
    }


}
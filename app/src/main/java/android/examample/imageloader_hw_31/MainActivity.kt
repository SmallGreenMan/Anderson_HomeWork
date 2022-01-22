package android.examample.imageloader_hw_31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.examample.imageloader_hw_31.HomeWork_2.MainActivity_HW_2_1
import android.examample.imageloader_hw_31.HomeWork_2.MainActivity_HW_2_2
import android.examample.imageloader_hw_31.HomeWork_2.MainActivity_HW_2_3
import android.examample.imageloader_hw_31.HomeWork_3.MainActivity_HW_3_1
import android.examample.imageloader_hw_31.HomeWork_3.MainActivity_HW_3_2
import android.examample.imageloader_hw_31.HomeWork_4.MainActivity_HW_4
import android.examample.imageloader_hw_31.HomeWork_5.MainActivity_HW_5
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {

    private lateinit var nightTeamSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nightTeamSwitch = findViewById(R.id.nightTheamSwitch)
        nightTeamSwitch.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (savedInstanceState == null) {
            nightTeamSwitch.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
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

    fun goToHomework_4(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_4::class.java))
    }

    fun goToHomework_5(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_5::class.java))
    }


}
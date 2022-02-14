package android.examample.imageloader_hw_31

import android.content.Intent
import android.examample.imageloader_hw_31.HomeWork_2.MainActivity_HW_2_1
import android.examample.imageloader_hw_31.HomeWork_2.MainActivity_HW_2_2
import android.examample.imageloader_hw_31.HomeWork_2.MainActivity_HW_2_3
import android.examample.imageloader_hw_31.HomeWork_3.MainActivity_HW_3_1
import android.examample.imageloader_hw_31.HomeWork_3.MainActivity_HW_3_2
import android.examample.imageloader_hw_31.HomeWork_4.MainActivity_HW_4
import android.examample.imageloader_hw_31.HomeWork_5.MainActivity_HW_5
import android.examample.imageloader_hw_31.HomeWork_6.MainActivity_HW_6
import android.examample.imageloader_hw_31.OkHttp.OkHttp
import android.examample.imageloader_hw_31.recycler_mvvm.MvvmActivity
import android.examample.imageloader_hw_31.recycler_observer.RecyclerObserverActivity
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
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

    fun goToHomework_6(view: android.view.View) {
        startActivity(Intent(this, MainActivity_HW_6::class.java))
    }

    fun goOkHttp(view: android.view.View) {
        startActivity(Intent(this, OkHttp::class.java))
    }

    fun goRecyclerObserver(view: android.view.View) {
        startActivity(Intent(this, RecyclerObserverActivity::class.java))
    }

    fun goRecyclerMvvm(view: android.view.View) {
        startActivity(Intent(this, MvvmActivity::class.java))
    }


}
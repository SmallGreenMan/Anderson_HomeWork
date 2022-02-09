package android.examample.imageloader_hw_31.recycler_observer

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.ActivityRecyclerObserverBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RecyclerObserverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerObserverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_observer)
    }
}
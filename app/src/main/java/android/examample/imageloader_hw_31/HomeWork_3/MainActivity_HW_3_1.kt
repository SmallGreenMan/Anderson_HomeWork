package android.examample.imageloader_hw_31.HomeWork_3

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.ActivityMainHw31Binding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import android.view.inputmethod.InputMethodManager

class MainActivity_HW_3_1 : AppCompatActivity() {

    private lateinit var binding: ActivityMainHw31Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hw31)

        binding = ActivityMainHw31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        init(savedInstanceState)

        binding.toolBarHW31.setNavigationOnClickListener {
            finish()
        }
    }

    private fun init(savedInstanceState: Bundle?) {
        binding.buttonLoad.setOnClickListener {
            var link = binding.editTextLink.text.toString()
            if (link.equals("")) {
                link = getString(R.string.default_link)
                binding.editTextLink.setText(R.string.default_link)
            }
            try {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }
            catch(e:Exception){
                Log.d("HW_3_1",e.toString())}
            loadImage(link)
        }
    }

    private fun loadImage(link: String) {
        binding.textViewImageLink.setText(link)
        Picasso.get()
            .load(link)
            .placeholder(R.drawable.ic_serch_image)
            .error(R.drawable.ic_image_error)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .into(binding.imageViewImage);
    }
}
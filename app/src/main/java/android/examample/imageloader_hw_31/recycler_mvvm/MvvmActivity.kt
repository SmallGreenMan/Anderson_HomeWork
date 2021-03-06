package android.examample.imageloader_hw_31.recycler_mvvm

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.databinding.ActivityMvvmBinding
import android.examample.imageloader_hw_31.recycler_mvvm.screens.UserDetailsFragment
import android.examample.imageloader_hw_31.recycler_mvvm.screens.UserListFragment
import android.examample.imageloader_hw_31.recycler_observer.model.User
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MvvmActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerMvvm, UserListFragment())
                .commit()
        }
    }

    override fun showDetails(user: User) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerMvvm, UserDetailsFragment.newInstance(user.id))
            .commit()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun toast(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }
}
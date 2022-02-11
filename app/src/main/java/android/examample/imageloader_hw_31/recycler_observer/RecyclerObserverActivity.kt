package android.examample.imageloader_hw_31.recycler_observer

import android.examample.imageloader_hw_31.databinding.ActivityRecyclerObserverBinding
import android.examample.imageloader_hw_31.recycler_observer.model.UserService
import android.examample.imageloader_hw_31.recycler_observer.model.UsersListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class RecyclerObserverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerObserverBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UserService
        get() = (applicationContext as App).userService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerObserverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewObserver.layoutManager = layoutManager
        binding.recyclerViewObserver.adapter = adapter

        usersService.addListener(usersListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.remuveListener (usersListener)
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}
package android.examample.imageloader_hw_31.recycler_observer

import android.examample.imageloader_hw_31.databinding.ActivityRecyclerObserverBinding
import android.examample.imageloader_hw_31.recycler_observer.model.User
import android.examample.imageloader_hw_31.recycler_observer.model.UserService
import android.examample.imageloader_hw_31.recycler_observer.model.UsersListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
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

        adapter = UsersAdapter(object: UserActionListener{
            override fun onUserMove(user: User, moveBy: Int){
                usersService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User){
                usersService.deleteUser(user)
            }

            override fun onUserDetails(user: User){
                Toast.makeText(this@RecyclerObserverActivity, "User: ${user.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onUserFire(user: User) {
                usersService.fireUser(user)
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewObserver.layoutManager = layoutManager
        binding.recyclerViewObserver.adapter = adapter

        val itemAnimator = binding.recyclerViewObserver.itemAnimator
        if (itemAnimator is  DefaultItemAnimator){
            itemAnimator.supportsChangeAnimations = false
        }

        usersService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener (usersListener)
    }

    private val usersListener: UsersListener = {
        //adapter.users = it
    }
}
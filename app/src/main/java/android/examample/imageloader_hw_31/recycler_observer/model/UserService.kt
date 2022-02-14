package android.examample.imageloader_hw_31.recycler_observer.model

import android.examample.imageloader_hw_31.recycler_mvvm.UserNotFoundException
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.SimpleTask
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.Task
import com.github.javafaker.Faker
import java.util.*
import java.util.concurrent.Callable
import kotlin.collections.ArrayList

typealias UsersListener = (users: List<User>) -> Unit

class UserService {

    private var users: MutableList<User> = mutableListOf<User>()
    private var loaded = false

    private val listeners: MutableSet<UsersListener> = mutableSetOf<UsersListener>()

    fun loadUsers(): Task<Unit> = SimpleTask<Unit>( Callable {
        Thread.sleep(2000)
        val faker: Faker = Faker.instance()
        users = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                telephone = faker.phoneNumber().phoneNumber(),
                company = faker.company().name(),
                photo = "https://picsum.photos/300/300/?temp=${it}"
            )
        }.toMutableList()
        loaded = true
        notifyChanges()
    })

    fun geById(id: Long): Task<UserDetails> = SimpleTask<UserDetails>( Callable {
        Thread.sleep(2000)
        val user: User = users.firstOrNull { it.id == id } ?: throw UserNotFoundException()
        return@Callable UserDetails(
            user = user,
            details = Faker.instance().lorem().paragraphs(3).joinToString("\n\n")
        )
    })

    fun deleteUser(user: User): Task<Unit> = SimpleTask<Unit>( Callable {
        val indexToDelete = users.indexOfFirst { user.id == it.id }
        if (indexToDelete != -1){
            users = ArrayList(users)
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    })

    fun moveUser(user: User, moveBy: Int): Task<Unit> = SimpleTask<Unit>( Callable {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1)  return@Callable
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex > users.size) return@Callable
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    })

    fun fireUser(user: User){
        val index = users.indexOfFirst { it.id == user.id }
        if (index == -1) return
        val updatedUser = users[index].copy(company = "")
        users = ArrayList(users)
        users[index] = updatedUser
        notifyChanges()
    }

    fun addListener(listener: UsersListener){
        listeners.add(listener)
        if (loaded)
            listener.invoke(users)
    }

    fun removeListener(listener: UsersListener){
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        if (!loaded) return
        listeners.forEach { it.invoke(users) }
    }
}
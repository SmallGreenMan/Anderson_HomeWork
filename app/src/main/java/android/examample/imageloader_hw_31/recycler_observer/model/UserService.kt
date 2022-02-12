package android.examample.imageloader_hw_31.recycler_observer.model

import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

typealias UsersListener = (users: List<User>) -> Unit

class UserService {

    private var users: MutableList<User> = mutableListOf<User>()

    private val listeners: MutableSet<UsersListener> = mutableSetOf<UsersListener>()

    init {
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
    }

    fun getUsers(): List<User>{
        return users
    }

    fun deleteUser(user: User){
        val indexToDelete = users.indexOfFirst { user.id == it.id }
        if (indexToDelete != -1){
            users = ArrayList(users)
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy: Int){
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1)  return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex > users.size) return
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

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
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener){
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(users) }
    }
}
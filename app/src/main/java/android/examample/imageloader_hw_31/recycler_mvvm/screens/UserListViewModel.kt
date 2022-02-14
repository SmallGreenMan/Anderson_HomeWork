package android.examample.imageloader_hw_31.recycler_mvvm.screens

import android.examample.imageloader_hw_31.recycler_observer.model.User
import android.examample.imageloader_hw_31.recycler_observer.model.UserService
import android.examample.imageloader_hw_31.recycler_observer.model.UsersListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel(
    private val userService: UserService
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users:LiveData<List<User>> = _users

    private val listener: UsersListener = {
        _users.value = it
    }

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        userService.removeListener(listener)
    }

    fun loadUsers(){
        userService.addListener(listener)
    }

    fun moveUser(user: User, moveBy: Int){
        userService.moveUser(user, moveBy)
    }

    fun deleteUser(user: User){
        userService.deleteUser(user)
    }
}
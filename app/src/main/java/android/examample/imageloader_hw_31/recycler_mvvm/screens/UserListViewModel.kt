package android.examample.imageloader_hw_31.recycler_mvvm.screens

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.*
import android.examample.imageloader_hw_31.recycler_observer.UserActionListener
import android.examample.imageloader_hw_31.recycler_observer.model.User
import android.examample.imageloader_hw_31.recycler_observer.model.UserService
import android.examample.imageloader_hw_31.recycler_observer.model.UsersListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class UserListItem(
    val user: User,
    val isInProgress: Boolean
)

class UserListViewModel(
    private val userService: UserService
) : BaseViewModel(), UserActionListener {

    private val _users = MutableLiveData<Result<List<UserListItem>>>()
    val users:LiveData<Result<List<UserListItem>>> = _users

    private val _actionShowDetails = MutableLiveData<Event<User>>()
    val actionShowDetails: LiveData<Event<User>> = _actionShowDetails

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val userIdIsInProgress: MutableSet<Long> = mutableSetOf<Long>()
    private var userResult: Result<List<User>> = EmptyResult()
        set(value){
            field = value
            notifyUpdates()
        }

    private val listener: UsersListener = {
        userResult = if (it.isEmpty()) {
            EmptyResult()
        } else {
            SuccessResult(it)
        }
    }

    init {
        loadUsers()
        userService.addListener(listener)
    }

    override fun onCleared() {
        super.onCleared()
        userService.removeListener(listener)

    }

    fun loadUsers(){
        userResult = PendingResult()
        userService.loadUsers()
            .onError {
                userResult = ErrorResult(it)
            }
            .autoCancel()
    }

    override fun onUserMove(user: User, moveBy: Int) {
        if (isInProgress(user)) return
        addProgressTo(user)
        userService.moveUser(user, moveBy)
            .onSuccess {
                removeProgressFrom(user)
            }
            .onError {
                removeProgressFrom(user)
                _actionShowToast.value = Event(R.string.cant_move_user)
            }
            .autoCancel()
    }


    override fun onUserDelete(user: User) {
        if (isInProgress(user)) return
        addProgressTo(user)
        userService.deleteUser(user)
            .onSuccess {
                removeProgressFrom(user)
            }
            .onError {
                removeProgressFrom(user)
                _actionShowToast.value = Event(R.string.cant_delete_user)
            }
            .autoCancel()
    }

    override fun onUserDetails(user: User) {
        _actionShowDetails.value = Event(user)
    }

    override fun onUserFire(user: User) {
        TODO("Not yet implemented")
    }

    private fun addProgressTo(user: User){
        userIdIsInProgress.add(user.id)
        notifyUpdates()
    }

    private fun removeProgressFrom(user: User){
        userIdIsInProgress.remove(user.id)
        notifyUpdates()
    }

    private fun isInProgress(user: User): Boolean {
        return userIdIsInProgress.contains(user.id)
    }


    private fun notifyUpdates(){
        _users.postValue( userResult.map { users ->
            users.map { user -> UserListItem(user, isInProgress(user)) }
        })
    }

}
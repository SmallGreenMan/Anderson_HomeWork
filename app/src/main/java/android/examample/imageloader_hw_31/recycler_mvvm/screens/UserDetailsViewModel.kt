package android.examample.imageloader_hw_31.recycler_mvvm.screens

import android.examample.imageloader_hw_31.R
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.EmptyResult
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.PendingResult
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.Result
import android.examample.imageloader_hw_31.recycler_mvvm.tasks.SuccessResult
import android.examample.imageloader_hw_31.recycler_observer.model.UserDetails
import android.examample.imageloader_hw_31.recycler_observer.model.UserService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserDetailsViewModel(
    private val userService: UserService
) : BaseViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val _actionGoBack = MutableLiveData<Event<Unit>>()
    val actionGoBack: LiveData<Event<Unit>> = _actionGoBack


    private val currentState: State get() = state.value!!

    init {
        _state.value = State(
            userDetailsResult = EmptyResult(),
            deletingInProgress = false
        )
    }

    fun loadUser(userId: Long){
        if (currentState.userDetailsResult is SuccessResult) return

        _state.value = currentState.copy(userDetailsResult = PendingResult())

        userService.geById(userId)
            .onSuccess {
                _state.value = currentState.copy(userDetailsResult = SuccessResult(it))
            }
            .onError {
                _actionShowToast.value = Event(R.string.cant_load_user_details)
                _actionGoBack.value = Event(Unit)
            }
            .autoCancel()
    }

    fun deleteUser(){
        val userDetailsResult: Result<UserDetails> = currentState.userDetailsResult
        if (userDetailsResult !is SuccessResult) return
        _state.value = currentState.copy(deletingInProgress = true)
        userService.deleteUser(userDetailsResult.data.user)
            .onSuccess {
                _actionShowToast.value = Event(R.string.User_has_been_deleted)
                _actionGoBack.value = Event(Unit)
            }
            .onError {
                _state.value = currentState.copy(deletingInProgress = false)
                _actionShowToast.value = Event(R.string.cant_delete_user)
            }
            .autoCancel()
    }

    data class State(
        val userDetailsResult: Result<UserDetails>,
        private val deletingInProgress: Boolean
    ){
        val showContent: Boolean get() = userDetailsResult is SuccessResult
        val showProgress: Boolean get() = userDetailsResult is PendingResult || deletingInProgress
        val enableDeleteButton: Boolean get() = !deletingInProgress
    }
}
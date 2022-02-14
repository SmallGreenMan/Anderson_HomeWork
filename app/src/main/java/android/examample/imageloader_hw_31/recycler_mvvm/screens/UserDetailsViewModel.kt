package android.examample.imageloader_hw_31.recycler_mvvm.screens

import android.examample.imageloader_hw_31.recycler_mvvm.UserNotFoundException
import android.examample.imageloader_hw_31.recycler_observer.model.UserDetails
import android.examample.imageloader_hw_31.recycler_observer.model.UserService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDetailsViewModel(
    private val userService: UserService
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    fun loadUser(userId: Long){
        if (_userDetails.value != null) return
        try {
            _userDetails.value = userService.geById(userId)
        } catch (e: UserNotFoundException) {
            e.printStackTrace()
        }
    }

    fun deleteUser(){
        val userDetails : UserDetails = this.userDetails.value ?: return
        userService.deleteUser(userDetails.user)
    }
}
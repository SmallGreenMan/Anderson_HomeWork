package android.examample.imageloader_hw_31.recycler_mvvm.screens

import android.examample.imageloader_hw_31.recycler_mvvm.Navigator
import android.examample.imageloader_hw_31.recycler_observer.App
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            UserListViewModel::class.java -> {
                UserListViewModel(app.userService)
            }
            UserDetailsViewModel::class.java ->{
                UserDetailsViewModel(app.userService)
            }
            else -> {
                throw  IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator
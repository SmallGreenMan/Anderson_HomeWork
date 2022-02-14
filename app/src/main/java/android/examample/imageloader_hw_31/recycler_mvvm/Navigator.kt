package android.examample.imageloader_hw_31.recycler_mvvm

import android.examample.imageloader_hw_31.recycler_observer.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)

}
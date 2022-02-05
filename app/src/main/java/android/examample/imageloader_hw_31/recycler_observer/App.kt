package android.examample.imageloader_hw_31.recycler_observer

import android.app.Application
import android.examample.imageloader_hw_31.recycler_observer.model.UserService

class App : Application() {
    val userService = UserService()
}
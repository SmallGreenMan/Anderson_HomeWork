package android.examample.imageloader_hw_31.recycler_mvvm.screens

import android.examample.imageloader_hw_31.recycler_mvvm.tasks.Task
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val tasks: MutableList<Task<*>> = mutableListOf<Task<*>>()

    override fun onCleared() {
        super.onCleared()
        tasks.forEach { it.cancel() }
    }

    fun <T> Task<T>.autoCancel(){
        tasks.add(this)
    }
}

class Event<T> (
    private val value: T
    ){

    private var handled: Boolean = false

    fun gerValue() : T? {
        if (handled) return null
        handled = true
        return value
    }
}
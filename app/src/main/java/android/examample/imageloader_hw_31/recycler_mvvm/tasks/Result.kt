package android.examample.imageloader_hw_31.recycler_mvvm.tasks

sealed class Result<T>

class SuccessResult<T> (
    val data: T
    ) : Result<T>()

class ErrorResult<T> (
    val error: Throwable
) : Result<T>()

class PendingResult<T> : Result<T>()

class EmptyResult<T> : Result<T>()
package com.varqulabs.dolarblue.core.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase<T : Any, R : Any>(
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(input: T): Flow<Result<R>> =
        executeData(input).map { Result.Success(it) as Result<R> }
            .flowOn(dispatcher)
            .catch {
                emit(Result.Error(UseCaseException. extractException(it)))
            }
    internal abstract fun executeData(input: T): Flow<R>
}
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val exception: UseCaseException) : Result<Nothing>()
}


sealed class UseCaseException(
    override val cause: Throwable?
) : Throwable(cause) {
    class UserException(cause: Throwable) : UseCaseException(cause)
    class LocationException(cause: Throwable) : UseCaseException(cause)
    class UnknownException(cause: Throwable) : UseCaseException(cause)

    companion object {
        fun extractException(throwable: Throwable): UseCaseException {
            return if (throwable is UseCaseException)
                throwable
            else UnknownException(throwable)
        }
    }
}
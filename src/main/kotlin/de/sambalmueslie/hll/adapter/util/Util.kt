package de.sambalmueslie.hll.adapter.util


import io.micronaut.data.repository.CrudRepository
import io.micronaut.data.repository.PageableRepository

inline fun <T> Iterable<T>.forEachWithTryCatch(action: (T) -> Unit) {
    try {
        for (element in this) action(element)
    } catch (e: Exception) {
        // intentionally left empty
    }
}

inline fun <T> measureTimeMillisWithReturn(function: () -> T): Pair<Long, T> {
    val startTime = System.currentTimeMillis()
    val result: T = function.invoke()
    val duration = System.currentTimeMillis() - startTime
    return Pair(duration, result)
}

inline fun <T> executeWithReturn(result: T? = null, function: () -> Any): T? {
    function.invoke()
    return result
}


fun <E, ID : Any> CrudRepository<E, ID>.findByIdOrNull(id: ID): E? = this.findById(id).orElseGet { null }
fun <E, ID : Any> PageableRepository<E, ID>.findByIdOrNull(id: ID): E? = this.findById(id).orElseGet { null }

package fardavide.molecule.presentation

import arrow.core.Either

sealed interface Lce<out T : Any> {

    data object Loading : Lce<Nothing>

    @JvmInline value class Content<out T : Any>(val value: T) : Lce<T>

    data object Error : Lce<Nothing>
}

fun <T : Any> Either<*, T>.toLce(): Lce<T> = fold(
    { Lce.Error },
    { Lce.Content(it) }
)

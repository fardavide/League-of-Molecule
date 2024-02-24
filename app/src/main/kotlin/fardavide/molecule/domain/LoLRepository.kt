package fardavide.molecule.domain

import arrow.core.Either
import kotlinx.coroutines.flow.Flow

interface LoLRepository {

    fun getAllChampions(): Flow<Either<DataError, List<Champion>>>
}

package fardavide.molecule.domain

import arrow.core.Either
import kotlinx.coroutines.flow.Flow

interface LoLRepository {

    fun getChampions(
        version: Version,
        query: String,
        sortBy: SortBy
    ): Flow<Either<DataError, List<Champion>>>

    fun getVersions(): Flow<Either<DataError, List<Version>>>
}

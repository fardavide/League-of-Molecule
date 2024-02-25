package fardavide.molecule.presentation

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.DataError
import fardavide.molecule.domain.LoLRepository
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.SortDirection
import fardavide.molecule.domain.Version
import fardavide.molecule.samples
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ListPresenterTest {

    @Test
    fun `on start, initial state`() = runTest {
        // given
        val scenario = Scenario()

        // when
        scenario.flow.test {

            // then
            assertEquals(
                ListState(
                    championsState = Lce.Loading,
                    selectedVersion = null,
                    searchQuery = "",
                    sort = SortBy.Name(SortDirection.Ascending),
                    versions = Lce.Loading
                ),
                awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when loaded, last version is selected`() = runTest {
        // given
        val scenario = Scenario()

        // when
        scenario.flow.test {
            skipItems(1)

            // then
            assertEquals(
                Version.samples.latest().value,
                awaitItem().selectedVersion
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when loaded, champions are shown`() = runTest {
        // given
        val scenario = Scenario()

        // when
        scenario.flow.test {
            skipItems(2)

            // then
            assertEquals(
                Champion.samples.all().toModels(Version.samples.latest()),
                awaitItem().championsState.assertContent()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when search, state is updated`() = runTest {
        // given
        val scenario = Scenario()

        scenario.flow.test {
            skipItems(3)

            // when
            scenario.submit(ListAction.Search("Teemo"))

            // then
            assertEquals(
                "Teemo",
                awaitItem().searchQuery
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when search, items are filtered`() = runTest {
        // given
        val scenario = Scenario()

        scenario.flow.test {
            skipItems(3)

            // when
            scenario.submit(ListAction.Search("Teemo"))
            skipItems(1)

            // then
            assertEquals(
                "Teemo",
                scenario.repository.getChampionsInvocations.last().query
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when clear search, state is updated`() = runTest {
        // given
        val scenario = Scenario()

        scenario.flow.test {
            skipItems(3)
            scenario.submit(ListAction.Search("Teemo"))
            skipItems(1)

            // when
            scenario.submit(ListAction.ClearSearch)

            // then
            assertEquals(
                "",
                awaitItem().searchQuery
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when clear search, items are not filtered`() = runTest {
        // given
        val scenario = Scenario()

        scenario.flow.test {
            skipItems(3)
            scenario.submit(ListAction.Search("Teemo"))
            skipItems(1)

            // when
            scenario.submit(ListAction.ClearSearch)
            skipItems(1)

            // then
            assertEquals(
                "",
                scenario.repository.getChampionsInvocations.last().query
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when sort, state is updated`() = runTest {
        // given
        val scenario = Scenario()

        scenario.flow.test {
            skipItems(3)

            // when
            scenario.submit(ListAction.Sort(SortBy.Attack(SortDirection.Ascending)))

            // then
            assertEquals(
                SortBy.Attack(SortDirection.Ascending),
                awaitItem().sort
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when sort, items are sorted`() = runTest {
        // given
        val scenario = Scenario()

        scenario.flow.test {
            skipItems(3)

            // when
            scenario.submit(ListAction.Sort(SortBy.Attack(SortDirection.Ascending)))
            skipItems(1)

            // then
            assertEquals(
                SortBy.Attack(SortDirection.Ascending),
                scenario.repository.getChampionsInvocations.last().sortBy
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}

private class Scenario(
    champions: List<Champion> = Champion.samples.all(),
    championsEither: Either<DataError, List<Champion>> = champions.right(),
    versions: List<Version> = Version.samples.all(),
    versionsEither: Either<DataError, List<Version>> = versions.right()
) {

    val repository = FakeLoLRepository(championsEither, versionsEither)
    private val actions = MutableSharedFlow<ListAction>()
    private val presenter = ListPresenter(repository = repository)

    val flow = moleculeFlow(mode = RecompositionMode.Immediate) {
        presenter.models(actions = actions)
    }.distinctUntilChanged()

    suspend fun submit(action: ListAction) {
        actions.emit(action)
    }
}

private class FakeLoLRepository(
    private val championsEither: Either<DataError, List<Champion>>,
    private val versionsEither: Either<DataError, List<Version>>
) : LoLRepository {

    var getChampionsInvocations = emptyList<GetChampionsParams>()
        private set

    override fun getChampions(
        version: Version,
        query: String,
        sortBy: SortBy
    ): Flow<Either<DataError, List<Champion>>> {
        getChampionsInvocations += GetChampionsParams(version, query, sortBy)
        return flowOf(championsEither)
    }

    override fun getVersions(): Flow<Either<DataError, List<Version>>> = flowOf(versionsEither)
}

private data class GetChampionsParams(
    val version: Version,
    val query: String,
    val sortBy: SortBy
)

private fun <T : Any> Lce<T>.assertContent(): T {
    assertTrue(this is Lce.Content)
    return (this as Lce.Content).value
}

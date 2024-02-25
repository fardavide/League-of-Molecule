package fardavide.molecule.data

import app.cash.turbine.test
import arrow.core.Either
import fardavide.molecule.domain.Champion
import fardavide.molecule.domain.SortBy
import fardavide.molecule.domain.SortDirection
import fardavide.molecule.domain.Version
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RealLolRepositoryTest {

    @Test
    fun `when get champions from server, is success`() = runTest {
        // given
        val repository = Scenario(useRealServer = true).sut

        // when
        val flow = repository.getChampions(
            version = Version("14.4.1"),
            query = "",
            sortBy = SortBy.Name(SortDirection.Ascending)
        )
        flow.test {
            // then
            assertTrue(awaitItem().isRight())
            awaitComplete()
        }
    }

    @Test
    fun `when get all champions, contains all champions`() = runTest {
        // given
        val repository = Scenario().sut

        // when
        val flow = repository.getChampions(
            version = Version("1.2.3"),
            query = "",
            sortBy = SortBy.Name(SortDirection.Ascending)
        )
        flow.test {
            // then
            assertEquals(167, awaitItem().size)
            awaitComplete()
        }
    }

    @Test
    fun `when get champions by query, results includes matched by named`() = runTest {
        // given
        val repository = Scenario().sut

        // when
        val flow = repository.getChampions(
            version = Version("1.2.3"),
            query = "aat",
            sortBy = SortBy.Name(SortDirection.Ascending)
        )
        flow.test {
            // then
            assertEquals(listOf("Aatrox"), awaitItem().names())
            awaitComplete()
        }
    }

    @Test
    fun `when get champions by query, results includes matched by title`() = runTest {
        // given
        val repository = Scenario().sut

        // when
        val flow = repository.getChampions(
            version = Version("1.2.3"),
            query = "the Darkin Blade",
            sortBy = SortBy.Name(SortDirection.Ascending)
        )
        flow.test {
            // then
            assertEquals(listOf("Aatrox"), awaitItem().names())
            awaitComplete()
        }
    }

    @Test
    fun `when get champions by query, results includes matched by tags`() = runTest {
        // given
        val repository = Scenario().sut

        // when
        val flow = repository.getChampions(
            version = Version("1.2.3"),
            query = "fighter",
            sortBy = SortBy.Name(SortDirection.Ascending)
        )
        flow.test {
            // then
            assertEquals(71, awaitItem().size)
            awaitComplete()
        }
    }

    @Test
    fun `when get champions by query, results includes matched by blurb`() = runTest {
        // given
        val repository = Scenario().sut

        // when
        val flow = repository.getChampions(
            version = Version("1.2.3"),
            query = "Innately magic spirit realm",
            sortBy = SortBy.Name(SortDirection.Ascending)
        )
        flow.test {
            // then
            assertEquals(listOf("Ahri"), awaitItem().names())
            awaitComplete()
        }
    }
}

private class Scenario(
    useRealServer: Boolean = false
) {

    val sut = RealLolRepository(client = if (useRealServer) Client() else FakeClient())

    private fun FakeClient() = HttpClient(
        MockEngine {
            respond(
                javaClass.classLoader!!.getResource("champions.json")!!.readText(),
                status = HttpStatusCode.OK,
                headers = Headers.build { append(HttpHeaders.ContentType, "application/json") }
            )
        }
    ) {
        install(ContentNegotiation) {
            json()
        }
    }
}

private fun Either<*, List<Champion>>.assertSuccess() = getOrNull()!!
private fun Either<*, List<Champion>>.names() = assertSuccess().names()
private val Either<*, List<Champion>>.size get() = assertSuccess().size
private fun List<Champion>.names() = map { it.name }

package fardavide.molecule.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class RealLolRepositoryTest {

    @Test
    fun `get all champions from server`() = runTest {
        // given
        val repository = RealLolRepository(Client())

        // when
        val result = repository.getAllChampions().first()

        // then
        assertTrue(result.isRight())
    }
}

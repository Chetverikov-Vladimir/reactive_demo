package coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class Coroutines {
    private val logger = KotlinLogging.logger { }
    private val service = Service()

    @Test
    fun coroutines(): Unit = runBlocking {
        val asyncResult = async {
            val id = service.getId()
            service.getBalanceById(id)
        }
        logger.info("Main thread is working...")
        logger.info("Balance ${asyncResult.await()}")
    }
}
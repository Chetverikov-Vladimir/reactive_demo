package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class FlowDemo {
    private val logger = KotlinLogging.logger { }

    @Test
    fun flowDemo() = runBlocking {
        val detector = flow {
            for (i in 0L until 15L) {
                delay(1000)
                emit(i)
            }
        }
        val temperature = flow {
            for (i in 10L until 25L) {
                delay(1000)
                emit(i)
            }
        }
        detector.zip(temperature) { smoke, temp -> Indication(smoke, temp) }.collect { checkAlert(it) }
    }


    private fun checkAlert(indication: Indication) {
        logger.info("Detector = ${indication.smoke} temperature = ${indication.temperature}")
        if (indication.smoke > 15 || indication.temperature > 20) {
            logger.warn("DANGER!!!")
        } else {
            logger.info("OK")
        }
    }
}

data class Indication(
    val smoke: Long,
    val temperature: Long
)
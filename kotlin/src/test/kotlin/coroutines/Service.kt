package coroutines

import mu.KotlinLogging

class Service {
    private val logger = KotlinLogging.logger { }
    fun getId(): Int {
        Thread.sleep(2000)
        logger.info("Return ID")
        return 1
    }

    fun getBalanceById(id: Int): Int {
        Thread.sleep(2000)
        logger.info("Return balance")
        return 1000 + id
    }
}

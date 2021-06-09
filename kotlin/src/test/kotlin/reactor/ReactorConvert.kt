package reactor


import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux


class ReactorConvert {
    @Test
    fun reactorConvert(): Unit = runBlocking {

        val flux = Flux.just("")
        val flow = flux.asFlow()
        val flowToFlux = flow.asFlux()

        flowToFlux.blockFirst()
    }
}
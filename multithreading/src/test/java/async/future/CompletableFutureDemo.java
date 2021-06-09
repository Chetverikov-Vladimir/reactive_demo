package async.future;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    private static Logger logger = LogManager.getLogger();

    static {
        Configurator.setRootLevel(Level.INFO);
    }


    private Service service = new Service();

    /**
     * Необходимо получить id, после чего получить баланс и напечатать его.
     */
    @Test
    public void withFuture() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> idFuture = executorService.submit(service::getId);
        logger.info("Main thread is working...");

        Future<Integer> balance = executorService.submit(
                () -> service.getBalanceById(idFuture.get()));
        logger.info("Balance " + balance.get());
    }

    @Test
    public void withCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> asyncResult = CompletableFuture
                .supplyAsync(service::getId)
                .thenApply(service::getBalanceById);

        logger.info("Main thread is working...");
        logger.info("Balance " + asyncResult.get());
    }
}

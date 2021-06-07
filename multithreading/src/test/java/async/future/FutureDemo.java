package async.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class FutureDemo {
    private Service service = new Service();

    /**
     * Необходимо получить id, после чего получить баланс и напечатать его.
     */
    @Test
    public void withFuture() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> idFuture = executorService.submit(service::getId);
        Future<Integer> balance = executorService.submit(
                () -> service.getBalanceById(idFuture.get()));
        System.out.println("Balance " + balance.get());
    }

    @Test
    public void withCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture
                .supplyAsync(service::getId)
                .thenApply(service::getBalanceById)
                .thenAccept((balance) -> System.out.println("Balance " + balance))
                .get();
    }
}

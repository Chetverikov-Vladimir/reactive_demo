package executor_framework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {

    @Test
    public void withRunnable() {
        Runnable task = () -> System.out.println("Hi from runnable in thread " + Thread.currentThread());
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.execute(task);
        }
    }

    @Test
    public void withCallable() {
        Callable<String> task = () -> "Hi from callable in thread " + Thread.currentThread();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            result.add(executor.submit(task));
        }

        result.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

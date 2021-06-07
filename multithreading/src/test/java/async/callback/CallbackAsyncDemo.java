package async.callback;

import org.junit.jupiter.api.Test;

public class CallbackAsyncDemo {

    @Test
    public void demo() throws InterruptedException {
        ClassWithCallback callback = new ClassWithCallback();
        Runnable task = () -> new Computer(callback).compute(0);

        System.out.println("Thread " + Thread.currentThread().getName() +
                " Task was running");
        System.out.println("_________________________________________________________");
        new Thread(task).start();

        while (true) {
            if (callback.getResult() == 0) {
                System.out.println("Thread " + Thread.currentThread().getName() + " wait result...");
                Thread.sleep(1000);
            } else {
                System.out.println("Thread " + Thread.currentThread().getName() + " result in callback is " + callback.getResult());
                break;
            }
        }

        System.out.println("_________________________________________________________");
        System.out.println("Thread " + Thread.currentThread().getName() + " End program");
    }

}

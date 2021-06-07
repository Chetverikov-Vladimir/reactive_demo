package multithreading.threads;

import org.junit.jupiter.api.Test;

public class SimpleThreadRun {

    /**
     * Запуск нового потока путем наследования от Thread
     */
    @Test
    public void threadExtends() {
        Thread sampleThread = new SampleThread();
        sampleThread.start();
    }

    /**
     * Запуск нового потока путем имплементации интерфейса Runnable
     */
    @Test
    public void runnableImplements() {
        Runnable task = () -> System.out.println("Print from  " + this.getClass().getSimpleName() + " thread " + Thread.currentThread());
        Thread runnableThread = new Thread(task);
        runnableThread.start();
    }

    class SampleThread extends Thread {
        public void run() {
            System.out.println("Print from  " + this.getClass().getSimpleName() + " thread " + Thread.currentThread());
        }
    }
}

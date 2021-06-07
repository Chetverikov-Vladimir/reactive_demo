package async.callback;

public class Computer {
    Callback callback;

    public Computer(Callback callback) {
        this.callback = callback;
    }

    public void compute(int param) {
        System.out.println("Thread " + Thread.currentThread().getName() + " Compute information...");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.callMe(param + 2);
    }
}

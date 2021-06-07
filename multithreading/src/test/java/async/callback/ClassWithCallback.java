package async.callback;

public class ClassWithCallback implements Callback {
    private int result;

    @Override
    public void callMe(int result) {
        System.out.println("Thread " + Thread.currentThread().getName() + " Receive result " + result);
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}

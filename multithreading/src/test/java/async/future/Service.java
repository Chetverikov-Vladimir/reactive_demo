package async.future;

public class Service {

    public int getId() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println("Return ID");
        return 1;
    }

    public int getBalanceById(int id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Return balance");
        return 1000 + id;
    }
}

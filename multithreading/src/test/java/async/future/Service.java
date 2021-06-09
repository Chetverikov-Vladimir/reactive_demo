package async.future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Service {
    private static Logger logger = LogManager.getLogger();

    public int getId() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Return ID");
        return 1;
    }

    public int getBalanceById(int id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Return balance");
        return 1000 + id;
    }
}

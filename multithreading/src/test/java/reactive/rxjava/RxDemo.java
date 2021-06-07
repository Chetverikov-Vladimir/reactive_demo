package reactive.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class RxDemo {

    @Test
    public void alertDemo() throws InterruptedException {
        Observable<Long> detector = Observable.intervalRange(0, 15, 0, 1000, TimeUnit.MILLISECONDS);
        Observable<Long> temperature = Observable.intervalRange(10, 15, 0, 1000, TimeUnit.MILLISECONDS);

        Observable.combineLatest(detector, temperature, Indication::new)
                .subscribe(this::checkAlert);

        Thread.sleep(15000);
    }

    private void checkAlert(Indication indication) {
        System.out.print("Detector = " + indication.smoke + " temperature = " + indication.temperature);
        if (indication.smoke > 15 || indication.temperature > 20) {
            System.out.println(" - DANGER!!!");
        } else {
            System.out.println(" - OK");
        }
    }

    class Indication {
        Long smoke;
        Long temperature;

        public Indication(Long smoke, Long temperature) {
            this.smoke = smoke;
            this.temperature = temperature;
        }
    }
}

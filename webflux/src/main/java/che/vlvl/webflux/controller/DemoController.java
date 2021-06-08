package che.vlvl.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {
    private static List<String> numbers = new ArrayList<>();

    static {
        numbers.add("One");
        numbers.add("Two");
        numbers.add("Three");
        numbers.add("Four");
        numbers.add("Five");
    }


    @GetMapping("/plain")
    public List<String> plain() {
        return numbers;
    }

    @GetMapping(value = "/stream", produces = "text/event-stream")
    public Flux<String> stream() {
        return Flux
                .fromIterable(numbers)
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "/json", produces = "application/stream+json")
    public Flux<DemoObject> json() {
        return Flux
                .fromIterable(numbers)
                .delayElements(Duration.ofSeconds(1))
                .map((element) -> new DemoObject(element, LocalDateTime.now()));
    }

    class DemoObject {
        private String number;
        private LocalDateTime time;

        public DemoObject(String number, LocalDateTime time) {
            this.number = number;
            this.time = time;
        }

        public String getNumber() {
            return number;
        }

        public LocalDateTime getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "DemoObject{" +
                    "number='" + number + '\'' +
                    ", time=" + time +
                    '}';
        }
    }
}

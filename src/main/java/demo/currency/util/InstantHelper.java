package demo.currency.util;

import java.time.Duration;
import java.time.Instant;

public class InstantHelper {

    public static Instant calculateHistoryPeriod(int period) {
        return Instant.now().minus(Duration.ofHours(period));
    }
}

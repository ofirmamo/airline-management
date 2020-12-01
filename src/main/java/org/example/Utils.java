package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author Ofir Mamo
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {}

    public static double airlinePrice(double originalPrice, LocalDate purchasedDate, LocalDate soldDate) {
        if(soldDate.compareTo(purchasedDate) < 0) {
            logger.warn("While trying to sell aircraft, sold date greater than purchased date");
            return 0;
        }

        long monthsInUse = ChronoUnit.MONTHS.between(purchasedDate, soldDate);
        return originalPrice * (1 - (monthsInUse * 0.02));
    }
}

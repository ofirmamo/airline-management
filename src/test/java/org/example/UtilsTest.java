package org.example;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void airlinePrice() {
        double originalPrice = 100;

        TemporalAccessor accessor = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2020-09-21");
        LocalDate purchasedDate = LocalDate.from(accessor);

        accessor = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2020-12-21");
        LocalDate soldDate = LocalDate.from(accessor);

        double price = Utils.airlinePrice(originalPrice, purchasedDate, soldDate);
        assertEquals(94, price);
    }
}
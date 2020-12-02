package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.StreamSupport;

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

    @Test
    void getMax() {
        Iterable<Double> numbers = Arrays.asList((double) 30, (double) 20, (double) 20, (double) 10);
        double max = StreamSupport.stream(numbers.spliterator(), false).sorted(Comparator.reverseOrder()).findFirst().orElse((double) 0);
        assertEquals(30, max);
    }
}
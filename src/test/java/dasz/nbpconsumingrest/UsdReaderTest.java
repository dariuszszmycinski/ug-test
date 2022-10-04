package dasz.nbpconsumingrest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsdReaderTest {

    @Test
    void getUsdForDate() {
        assertEquals(4.618, UsdReader.getUsdForDate("2022-08-01"));
        assertEquals(4.676, UsdReader.getUsdForDate("2022-09-01"));
    }
}

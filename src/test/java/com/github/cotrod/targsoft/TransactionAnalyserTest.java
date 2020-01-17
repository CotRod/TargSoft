package com.github.cotrod.targsoft;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionAnalyserTest {
    String merchant = "Kwik-E-Mart";
    String fromDate = "20/08/2018 12:00:00";
    String toDate = "20/08/2018 13:00:00";

    @Test
    void analyse() {
        TransactionAnalyser.analyse(merchant, fromDate, toDate);
    }
}
package com.github.cotrod.targsoft;

import com.github.cotrod.targsoft.models.Transaction;
import com.github.cotrod.targsoft.models.TypeOfTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class Utils {
    public static Transaction createTransaction(List<String> elementsFromLine) {
        Transaction transaction = new Transaction();
        transaction.setId(elementsFromLine.get(0));
        transaction.setDate(getParse(elementsFromLine.get(1)));
        transaction.setAmount(new BigDecimal(elementsFromLine.get(2)));
        transaction.setMerchant(elementsFromLine.get(3));
        transaction.setType(TypeOfTransaction.valueOf(elementsFromLine.get(4)));

        return transaction;
    }

    public static LocalDateTime getParse(String forParsingDate) {
        ResourceBundle resource = ResourceBundle.getBundle("conf");
        return LocalDateTime.parse(forParsingDate, DateTimeFormatter.ofPattern(resource.getString("dateTimePattern")));
    }

    public static BigDecimal getAvgTransactionValue(List<Transaction> transactions, int amountOfTransactions) {
        BigDecimal sumOfAmount = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(new BigDecimal("0.0"), (BigDecimal::add));
        return sumOfAmount.divide(new BigDecimal(amountOfTransactions), 2, BigDecimal.ROUND_HALF_UP);
    }
}

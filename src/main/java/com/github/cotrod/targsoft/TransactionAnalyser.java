package com.github.cotrod.targsoft;

import com.github.cotrod.targsoft.models.Transaction;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.cotrod.targsoft.Utils.*;

public class TransactionAnalyser {
    private static String pathFolder = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "csvFile";

    public static void analyse(String inputMerchant, String fromDate, String toDate) {
        File[] files = new File(pathFolder).listFiles();
        File file;
        if (files.length > 0) {
            file = files[0];
        } else {
            System.out.println("There are no files");
            throw new RuntimeException();
        }
        List<Transaction> transactions = new ArrayList<>();
        try (FileReader fr = new FileReader(file); Scanner sc = new Scanner(fr)) {
            while (sc.hasNextLine()) {
                String lineFromFile = sc.nextLine();
                if (lineFromFile.contains(inputMerchant)) {
                    List<String> elementsFromLine = Arrays.stream(lineFromFile.split(",")).map(String::trim).collect(Collectors.toList());
                    if (elementsFromLine.size() == 6) {
                        transactions = transactions.stream().filter(t -> !t.getId().equals(elementsFromLine.get(5))).collect(Collectors.toList());
                    } else {
                        Transaction transaction = createTransaction(elementsFromLine);
                        transactions.add(transaction);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime transactionsFromDate = getParse(fromDate);
        LocalDateTime transactionsaToDate = getParse(toDate);
        transactions = transactions.stream()
                .filter(transaction -> transaction.getDate().isAfter(transactionsFromDate) || transaction.getDate().isEqual(transactionsFromDate))
                .filter(transaction -> transaction.getDate().isBefore(transactionsaToDate) || transaction.getDate().isEqual(transactionsaToDate))
                .collect(Collectors.toList());

        int amountOfTransactions = transactions.size();
        if (amountOfTransactions > 0) {
            BigDecimal avgTransactionValue = getAvgTransactionValue(transactions, amountOfTransactions);
            System.out.println("Number of transactions = " + amountOfTransactions);
            System.out.println("Average Transaction Value = " + avgTransactionValue);
        } else {
            System.out.println("There are no transactions");
        }
    }
}

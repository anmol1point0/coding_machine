package service;

import comparator.AmountBasedComparator;
import comparator.TimeBasedComparator;
import dao.TransactionDao;
import enums.SORTING_TYPE;
import enums.TRANSACTION_TYPE;
import model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionService {

    private final TransactionDao transactionDao;

    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Boolean recordTransaction(String senderName, String recipientName, BigDecimal amount, Long
            timeOfTransaction) {
        if (Objects.isNull(senderName)) {

        }
        if (Objects.isNull(senderName)) {

        }
        if (Objects.isNull(senderName)) {

        }
        if (Objects.isNull(senderName)) {

        }

        return transactionDao.recordTransactions(senderName, recipientName, amount, timeOfTransaction);

    }

    public List<Transaction> getTransactions(String userName, String transactionType, String sortingType) {
        if (Objects.isNull(userName)) {
            System.out.println("User: " + userName + " does not exist while getting transactions");
            return null;
        }
        if (Objects.isNull(transactionType)) {
            System.out.println("transactionType: " + transactionType + " does not exist while getting transactions");
            return null;
        }
        if (Objects.isNull(sortingType)) {
            System.out.println("sortingType: " + sortingType + " does not exist while getting transactions");
            return null;
        }

        List<Transaction> allTransactions = transactionDao.getAllTransactionsForUser(userName);
        
        List<Transaction> resultTransactions = null;


        if (TRANSACTION_TYPE.RECEIVE.toString().equals(transactionType)) {

            List<Transaction> allReceiveTransactions = allTransactions.stream().filter(
                    t -> t.getTransactionType().equals(TRANSACTION_TYPE.RECEIVE)
            ).collect(Collectors.toList());


            if (SORTING_TYPE.AMOUNT.toString().equals(sortingType)) {
                allReceiveTransactions.sort(new AmountBasedComparator());
            } else if (SORTING_TYPE.TIME.toString().equals(sortingType)) {
                allReceiveTransactions.sort(new TimeBasedComparator());
            } else {
                //todo
            }

            for(Transaction t : allReceiveTransactions){
                System.out.println(t.getSenderName() + " -> " + t.getRecipientName() + " : " + t.getAmount() + "Rs");
            }

        } else if (TRANSACTION_TYPE.SEND.toString().equals(transactionType)) {

            List<Transaction> allSendTransactions = allTransactions.stream().filter(
                    t -> t.getTransactionType().equals(TRANSACTION_TYPE.SEND)
            ).collect(Collectors.toList());


            if (SORTING_TYPE.AMOUNT.toString().equals(sortingType)) {
                allSendTransactions.sort(new AmountBasedComparator());
            } else if (SORTING_TYPE.TIME.toString().equals(sortingType)) {
                allSendTransactions.sort(new TimeBasedComparator());
            } else {
                //todo
            }
            for(Transaction t : allSendTransactions){
                System.out.println(t.getSenderName() + " -> " + t.getRecipientName() + " : " + t.getAmount() + "Rs");
            }
        } else {
            //todo
        }

        return new ArrayList<>();

    }
}

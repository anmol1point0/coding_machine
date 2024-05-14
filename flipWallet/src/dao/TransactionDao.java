package dao;

import enums.TRANSACTION_TYPE;
import model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TransactionDao {

    private final HashMap<String, List<Transaction>> userTransactionsMap;

    public TransactionDao() {
        this.userTransactionsMap = new HashMap<>();
    }

    public Boolean recordTransactions(String senderName, String recipientName, BigDecimal amount, Long
                                         timeOfTransaction){

        Transaction sendTransaction = new Transaction();
        sendTransaction.setSenderName(senderName);
        sendTransaction.setRecipientName(recipientName);
        sendTransaction.setAmount(amount);
        sendTransaction.setTransactionTimeStamp(timeOfTransaction);
        sendTransaction.setTransactionType(TRANSACTION_TYPE.SEND);

        List<Transaction> userSendTransactions = userTransactionsMap.get(senderName);

        if(Objects.isNull(userSendTransactions)){
            userSendTransactions =new ArrayList<>();
        }

        userSendTransactions.add(sendTransaction);

        userTransactionsMap.put(senderName, userSendTransactions);

        Transaction receiveTransaction = new Transaction();
        receiveTransaction.setSenderName(senderName);
        receiveTransaction.setRecipientName(recipientName);
        receiveTransaction.setAmount(amount);
        receiveTransaction.setTransactionTimeStamp(timeOfTransaction);
        receiveTransaction.setTransactionType(TRANSACTION_TYPE.RECEIVE);

        List<Transaction> userReceiveTransactions = userTransactionsMap.get(recipientName);

        if(Objects.isNull(userReceiveTransactions)){
            userReceiveTransactions =new ArrayList<>();
        }

        userReceiveTransactions.add(receiveTransaction);

        userTransactionsMap.put(recipientName, userReceiveTransactions);


        return true;
    }

    public List<Transaction> getAllTransactionsForUser(String userName){
        if(!userTransactionsMap.containsKey(userName)){
            System.out.println("User: " + userName + " not registered while getting transactions");
        }
        return userTransactionsMap.get(userName);
    }
}

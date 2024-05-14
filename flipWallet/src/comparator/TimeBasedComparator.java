package comparator;

import model.Transaction;

import java.util.Comparator;

public class TimeBasedComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getTransactionTimeStamp().compareTo(t2.getTransactionTimeStamp());
    }
}

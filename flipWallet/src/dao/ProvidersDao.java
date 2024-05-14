package dao;

import java.math.BigDecimal;

public interface ProvidersDao {

    public Boolean getMoney(String userName, BigDecimal amount);
}


package pl.training.bank.service;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class HibernateIncrementalAccountNumberGenerator extends IncrementalAccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER_HQL = "select max(a.number) from Account a";

    public HibernateIncrementalAccountNumberGenerator(SessionFactory sessionFactory) {
        String lastAccountNumber = (String) sessionFactory.openSession()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER_HQL)
                .uniqueResult();
        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.parseLong(lastAccountNumber));
        }
    }

}
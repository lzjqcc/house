import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.domain.Account;
import com.qcc.service.AccountService;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountDaoTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDao accountDao;
    @Test
    public void testInsert() {
        Account account = new Account();
        accountDao.save(account);

    }
    @Test
    public void testSelect() {

    }
    @Test
    public void update() {

        Account account = accountDao.findOne(2);
        account.setAge(30);
        account.setPassword("123");
       // account.setAge(30);
       // accountDao.save(account);
    }
}

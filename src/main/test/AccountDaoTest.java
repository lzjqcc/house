import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountDaoTest {
    @Autowired
    AccountDao accountDao;
    @Test
    public void testInsert() {
        Account account = new Account();
        accountDao.save(account);
    }
}

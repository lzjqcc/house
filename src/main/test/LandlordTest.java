import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.domain.Account;
import com.qcc.domain.Landlord;
import com.qcc.service.LandlordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LandlordTest {
    @Autowired
    LandlordDao landlordDao;
    @Autowired
    AccountDao accountDao;
    @Test
    public void testInsert() {
        Landlord landlord = new Landlord();
        Account account = accountDao.findOne(1);
        Account account1 = new Account();
        BeanUtils.copyProperties(account,account1);
        landlord.setAccount(account1);
        landlordDao.save(landlord);
    }
    @Test
    public void select() {

    }
}

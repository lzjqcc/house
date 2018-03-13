import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.domain.Account;
import com.qcc.domain.Landlord;
import com.qcc.service.LandlordService;
import com.qcc.utils.CommUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LandlordTest {
    @Autowired
    LandlordDao landlordDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    ResourceLoader resourceLoader;
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
    public void select() throws IOException {
       Resource resource = resourceLoader.getResource("file:src/main/resources/images/2");
       File file = Files.createDirectories(Paths.get(resource.getFile().getPath(), "b.txt")).toFile();
        System.out.println(CommUtils.getImageURL(file.getPath()));
    }
}

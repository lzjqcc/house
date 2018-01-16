import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.dao.TenantDao;
import com.qcc.domain.Account;
import com.qcc.domain.Tenant;
import com.qcc.service.TenantService;
import com.qcc.utils.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TenantDaoTest {
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TenantService tenantService;
    @Test
    public void testInsert() {
        Tenant tenant = new Tenant();
        Account account = new Account();
        BeanUtils.copyProperties(accountDao.findOne(3), account);
        tenant.setAccount(account);
        tenantDao.save(tenant);
    }
    @Test
    public void testSelect(){
        ResponseVO<Set<Tenant>> responseVO = tenantService.findTenants(1);
        System.out.print(responseVO.getResult().size());
    }
}

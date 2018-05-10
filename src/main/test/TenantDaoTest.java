import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.dao.RepairInfoOrderDao;
import com.qcc.dao.RepairmanDao;
import com.qcc.dao.TenantDao;
import com.qcc.domain.Landlord;
import com.qcc.domain.RepairInfoOrder;
import com.qcc.domain.Repairman;
import com.qcc.domain.Tenant;
import com.qcc.service.TenantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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
    @Autowired
    RepairInfoOrderDao repairInfoOrderDao;
    @Test
    public void testRe() {
        Repairman repairman = new Repairman();
        repairman.setId(1);
        PageImpl<RepairInfoOrder> repairInfoOrders = repairInfoOrderDao.findRepairInfoOrderByRepairman(repairman, new PageRequest(0, 1));
        System.out.println(repairInfoOrders);
    }
    @Test
    public void testInsert() {
     /*   Tenant tenant = new Tenant();
        Account account = new Account();
        BeanUtils.copyProperties(accountDao.findOne(3), account);
        tenant.setAccount(account);
        tenantDao.save(tenant);*/
    }
    @Test
    public void testSelect(){
        Landlord landlord = new Landlord();
        landlord.setId(1);
        PageRequest request = new PageRequest(1,1);
      /*  List<Tenant> list = tenantDao.findTenantsByLandlord(landlord, request);
        for (Tenant tenant : list) {
            System.out.println(tenant.getAccount());
        }*/
    }
    @Test
    public void deleteRelation() {
        //tenantService.deleteHouseTenantRelation(2);
    }
}

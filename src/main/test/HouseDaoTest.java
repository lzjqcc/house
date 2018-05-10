import com.qcc.Application;
import com.qcc.annotation.Cache;
import com.qcc.controller.ImageController;
import com.qcc.dao.dto.HouseDto;
import com.qcc.domain.Account;
import com.qcc.domain.House;
import com.qcc.domain.HouseLog;
import com.qcc.domain.Tenant;
import com.qcc.service.HouseLogService;
import com.qcc.service.HouseService;
import com.qcc.utils.CacheMap;
import com.qcc.utils.PageVO;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HouseDaoTest {
    @Autowired
    private HouseService houseService;
    @Autowired
    HouseLogService houseLogService;
    @Cache(space = "image")
    CacheMap<List<ImageController.ImageFile>> cacheMap;
    @Test
    public void save() {
        Account account = new Account();
        account.setId(1);
        House house = new House();
        house.setAddress("上饶市余干县");
        ImageController.ImageFile imageFile = new ImageController.ImageFile();
        imageFile.setUrl("/a/a/a/a/");
        ImageController.ImageFile imageFile1 = new ImageController.ImageFile();
        imageFile1.setUrl("/b/b/b/b/b");
        cacheMap.put(account.getId()+"", Lists.newArrayList(imageFile, imageFile1));
        int i = 0;

    }
    @Test
    public void select() {
        HouseDto houseDto = new HouseDto();
        PageVO pageVO = new PageVO();
        pageVO.setCurrentPage(1);
        pageVO.setSize(10);
        houseDto.setAddress("西湖区");
        PageVO<List<HouseDto>> pageVO1 = houseService.findHouses(houseDto, pageVO);
        System.out.println(pageVO1.getEntity().size());
    }
    @Test
    public void test() {
        HouseLog log = new HouseLog();
        Tenant tenant = new Tenant();
        tenant.setId(1);
        log.setTenant(tenant);
    }
}

import com.qcc.Application;
import com.qcc.dao.dto.HouseDto;
import com.qcc.service.HouseService;
import com.qcc.utils.PageVO;
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
    @Test
    public void select() {
        HouseDto houseDto = new HouseDto();
        PageVO pageVO = new PageVO();
        pageVO.setCurrentPage(1);
        pageVO.setSize(10);
        PageVO<List<HouseDto>> pageVO1 = houseService.findHouses(houseDto, pageVO);
        System.out.println(pageVO1.getEntity().size());
    }
}

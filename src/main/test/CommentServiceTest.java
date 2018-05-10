import com.qcc.Application;
import com.qcc.dao.AccountDao;
import com.qcc.dao.dto.CommentDto;
import com.qcc.domain.Account;
import com.qcc.service.CommentService;
import com.qcc.utils.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CommentServiceTest {
    @Autowired
    AccountDao accountDao;
    @Autowired
    CommentService commentService;
    @Test
    public void test() {

        Account fromAccount = accountDao.findOne(4);
        CommentDto commentDto = new CommentDto();
        commentDto.setToAccountId(1);
        commentDto.setConversation("真是菜");
        commentService.pushComment(fromAccount,commentDto);
    }
}

import com.qcc.Application;
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
    CommentService commentService;
    @Test
    public void test() {
        ResponseVO responseVO = commentService.pullComment(1);
        int i = 0;
    }
}

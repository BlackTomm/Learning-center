package chapter2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorcodeApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
    }
    @Test
    public void testThrowingCustoException(){
        jdbcTemplate.execute("INSERT INTO FOO (ID,BAR) VALUES(1,'A')");
        jdbcTemplate.execute("INSERT INTO FOO (ID,BAR) VALUES(2,'B')");
    }

}

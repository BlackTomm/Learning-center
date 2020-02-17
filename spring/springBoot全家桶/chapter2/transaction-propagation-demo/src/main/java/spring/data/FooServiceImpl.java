package spring.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;

    @Override
    @Transactional(rollbackFor = RollbackException.class,propagation = Propagation.REQUIRED)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    /**
     * 回滚解析: 外面invokeInsertThenRollback()； 里面 调用的insertThenRollback()
     * REQUIRED： 里面或外面只要有一个回滚就都回滚
     * REQUIRES_NEW: 外面与里面回滚独立
     * NESTED： 外面回滚里面也回滚，里面回滚不影响外面回滚
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void invokeInsertThenRollback() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        try{
            fooService.insertThenRollback();
        }catch (RollbackException e){
            log.error("RollbackException",e);
        }
      //throw  new RuntimeException();
    }
}

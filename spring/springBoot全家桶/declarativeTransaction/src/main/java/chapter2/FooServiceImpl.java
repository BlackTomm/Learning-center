package chapter2;

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

    //配置用于回滚测试
    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES('AAA')");
    }

    /**
     * 关于事务回滚，示例1 输出 AAA 1; BBB 0; BBB 1;
     * @throws RollbackException
     */
//   @Override
//    @Transactional(rollbackFor = RollbackException.class)
//    public void insertThenRollback() throws RollbackException {
//        jdbcTemplate.execute("INSERT INTO FOO(BAR) VALUES('BBB')");
//        throw new RollbackException();
//    }
//
//    @Override
//    public void invokeInsertThenRollback() throws RollbackException{
//        insertThenRollback();
//    }

    @Override
    @Transactional(rollbackFor = RollbackException.class,propagation = Propagation.REQUIRES_NEW)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO(BAR) VALUES('BBB')");
 //       throw new RollbackException();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void invokeInsertThenRollback(){
        jdbcTemplate.execute("INSERT INTO FOO(BAR) VALUES('AAA')");
        try{
            fooService.insertThenRollback();
        }catch (RollbackException e){
            log.error("RollbackException",e);
        }
        throw new RuntimeException();
    }
    Plat


}

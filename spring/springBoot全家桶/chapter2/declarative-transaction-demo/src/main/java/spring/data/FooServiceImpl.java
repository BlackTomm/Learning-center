package spring.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooServiceImpl self;

    @Override
    @Transactional
    public void insertRecord(){
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO(BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    //未继承insertThenRollback()y由于抛出异常就回滚属性
    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        /**原方法:内部仅调用insertThenRollback()，为注入实例化对象self，事物不回滚
         * 把自己的实例注入进来，内部方法调用改为直接调用注入的实例。因为Spring其实是为你创建了一个代理
         *利用代理类回滚
         */
                self.insertThenRollback();

    }
}

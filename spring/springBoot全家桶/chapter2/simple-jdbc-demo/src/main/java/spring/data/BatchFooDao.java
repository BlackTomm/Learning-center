package spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BatchFooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert() {
        /**
         * 两种数据批量插入方式 jdbcTemplate 与 namedParameterJdbcTemplate
         */
        jdbcTemplate.batchUpdate("INSERT INTO FOO(BAR) VALUES (?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setString(1, "b-" + i);
                    }

                    //限定插入数据多少，i从0取值，0索引对应1
                    @Override
                    public int getBatchSize() {
                        return 5;
                    }
                });


        List<Foo> list = new ArrayList<>();
        list.add(Foo.builder().id(100L).bar("b-100").build());
        list.add(Foo.builder().id(101L).bar("b-101").build());

        namedParameterJdbcTemplate
                .batchUpdate("INSERT INTO FOO(ID,BAR) VALUES(:id,:bar)",
                        SqlParameterSourceUtils.createBatch(list));

    }
}

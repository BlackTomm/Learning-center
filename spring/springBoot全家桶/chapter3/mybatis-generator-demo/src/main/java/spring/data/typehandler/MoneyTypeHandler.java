package spring.data.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个类是MyBatis为我们提供来应对Java和jdbc字段类型不匹配的情况。也就是说你model声明的字段和数据库不匹配时
 * 需要在这个环节处理，在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤。写入通过
 * setNonNullParameter方法过滤，读通过getNullableResult方法过滤，parseMoney方法读作用是读取金额从分转成元
 */

@Slf4j
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Money money, JdbcType jdbcType) throws SQLException {
        log.info("setNonNullParameter(PreparedStatement " + preparedStatement, "" +
                ", Money ", money, ",  JdbcType  " + jdbcType);
        preparedStatement.setLong(i, money.getAmountMinorLong());
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        log.info("getNullableResult(ResultSet " + resultSet +
                ", String columnName: ", columnName);
        return parseMoney(resultSet.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        log.info("getNullableResult(ResultSet " + resultSet +
                ", int columnIndex ", columnIndex);
        return parseMoney(resultSet.getLong((columnIndex)));
    }

    @Override
    public Money getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        log.info("getNullableResult(CallableStatement " + callableStatement +
                ", int columnIndex ", columnIndex);
        return parseMoney(callableStatement.getLong(columnIndex));
    }

    private Money parseMoney(Long value) {
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
    }
}

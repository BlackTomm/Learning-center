package spring.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import spring.data.typehandler.Coffee;

@Component
@Mapper
public interface CoffeeMapper {
    @Insert("insert into t_coffee (name,price,create_time,update_time)"
            + "values (#{name},#{price},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Coffee coffee);

    @Select("select * from t_coffee where id= #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
    })
    Coffee findById(@Param("id") Long id);
}

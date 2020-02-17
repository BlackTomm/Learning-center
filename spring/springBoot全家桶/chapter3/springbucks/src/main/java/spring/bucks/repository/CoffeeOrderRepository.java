package spring.bucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.bucks.model.CoffeeOrder;

import java.util.List;

/**
 * 1. CrudRepository<CoffeeOrder,Long>包含基本增删改查方法
 * 2. 更改为BaseRepository<CoffeeOrder,Long>,添加方法：
 *      findByCustomerOrderById(String Customer);
 *      findByItems_Name(String name);
 * 3.更改为JpaRepository<CoffeeOrder,Long>,删除所有方法，添加service文件夹及文件
 *
 * CoffeeRepository 仅改变继承的Repository
 */
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder,Long> {
    /*List<CoffeeOrder> findByCustomerOrderById(String customer);
    List<CoffeeOrder> findByItems_Name(String name);*/
}

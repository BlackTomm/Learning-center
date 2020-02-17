package spring.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import spring.data.converter.MoneyConveter;
import spring.data.model.Coffee;
import spring.data.repository.CoffeeRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

//@EnableMongoRepositories 如果使用Repository则需添加该注解
@SpringBootApplication
@Slf4j
@EnableMongoRepositories
public class MongoDemoApplication implements ApplicationRunner {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyConveter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee saved = mongoTemplate.save(espresso);
        log.info("Coffee: {}", saved);

        //第一种匹配方法
//        List<Coffee> list = mongoTemplate.find(
//                Query.query(Criteria.where("name").is("espresso")), Coffee.class);
        //第二种匹配方法
        Coffee probe = Coffee.builder().name("espresso").build();
        List<Coffee> list = mongoTemplate.find(
                query(Criteria.byExample(Example.of(probe, ExampleMatcher.matchingAny()))), Coffee.class);

        log.info("Find {} Coffee, list is {}", list.size(), Arrays.asList(list));

        //修改价格，注意更新时间
        Thread.sleep(1000);
        UpdateResult updateResult = mongoTemplate.updateFirst(query(where("name").is("espresso")),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 23)).currentDate("updateTime"),
                Coffee.class);
        log.info("Update Result: {}", updateResult.getModifiedCount());
        Coffee updateOne = mongoTemplate.findById(saved.getId(), Coffee.class);
        log.info("Update Result: {}", updateOne);
        mongoTemplate.remove(updateOne);


        //使用jpaRepository管理数据
        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 23.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        coffeeRepository.insert(Arrays.asList(espresso, latte));
        coffeeRepository.findAll(Sort.by("name"))
                .forEach((c -> log.info("JpaRepository Saved Coffee {}", c)));
        //修改价格
        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        latte.setUpdateTime(new Date());
        coffeeRepository.findByName("latte")
                .forEach(c -> log.info("Coffee {}", c));
        coffeeRepository.deleteAll();
    }

}

package spring.bucks;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import spring.bucks.model.Coffee;
import spring.bucks.model.CoffeeOrder;
import spring.bucks.model.OrderState;
import spring.bucks.repository.CoffeeOrderRepository;
import spring.bucks.repository.CoffeeRepository;
import spring.bucks.service.CoffeeOrderService;
import spring.bucks.service.CoffeeService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableTransactionManagement
public class BucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;
    public static void main(String[] args) {
        SpringApplication.run(BucksApplication.class, args);
    }

    //添加service后
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        //initOrders();
        //findOrders();

        //调用service
        myService();

    }

    private void initOrders(){
        Coffee espresso= Coffee.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee: {}",espresso);

        Coffee latte= Coffee.builder().name("latte").price(Money.of(CurrencyUnit.of("CNY"),22.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}",latte);

        CoffeeOrder order=CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("Order: {}",order);

       order=CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("Order: {}",order);
    }


     /*private void findOrders(){
        coffeeRepository
                .findAll(Sort.by(Sort.Direction.DESC,"id"))
                .forEach(c->log.info("Loading {}", c));

        List<CoffeeOrder> list=coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}",getJoinedOrderId(list));

        list=coffeeOrderRepository.findByCustomerOrderById("Li Lei");
        log.info("findByCustomerOrderById: {}",list);

        //不开启事务会报LazyInitializationException
         // spring 中的懒加载与事务 -- 排坑记录 https://www.cnkirito.moe/spring-transation-1/
        list.forEach(o->{
            log.info("Order: {}",o.getId());
            o.getItems().forEach(i->log.info("  Items {}",i));
        });

        list=coffeeOrderRepository.findByItems_Name("latte");
        log.info("findByItems_Name {}", getJoinedOrderId(list));
     }*/


     private String getJoinedOrderId(List<CoffeeOrder> list){
        return list.stream().map(o->o.getId().toString())
                .collect(Collectors.joining(","));
     }

     public void myService(){
         log.info("All Coffee: {}", coffeeRepository.findAll());
         Optional<Coffee> latte=coffeeService.findOneCoffee("Latte");
         if(latte.isPresent()){
             CoffeeOrder order=coffeeOrderService.createOrder("Xu Gu", latte.get());
             log.info(("Update INIT to PAID: {}"), coffeeOrderService.updateState(order,OrderState.PAID));
             log.info(("Update PAID to INIT: {}"), coffeeOrderService.updateState(order,OrderState.INIT));

         }
     }
}

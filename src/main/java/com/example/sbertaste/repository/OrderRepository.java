package com.example.sbertaste.repository;

import com.example.sbertaste.dto.statistic.CustomersToReturn;
import com.example.sbertaste.dto.statistic.DailyStatistic;
import com.example.sbertaste.dto.statistic.PizzaBestSeller;
import com.example.sbertaste.model.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "select * from (select p.name, sum(op.quantity) as quantity " +
            "from order_position op " +
            "inner join \"order\" o on op.order_id = o.id and o.created_timestamp between :begin and :end " +
            "left join pizza p on op.pizza_id = p.id " +
            "group by p.name " +
            "order by quantity desc limit :count) a",
            countQuery = "select count(op.pizza_id) from order_position op " +
                    "inner join \"order\" o on op.order_id = o.id and o.created_timestamp between :begin and :end " +
                    "group by op.pizza_id limit :count", nativeQuery = true)
    Page<PizzaBestSeller> getBestSellerByPeriod(@Param("count") int count,
                                                @Param("begin") LocalDate begin,
                                                @Param("end") LocalDate end,
                                                Pageable paging);

    @Query(value = "select date(o.created_timestamp) as report_date, " +
            "count(o.id) as daily_orders, " +
            "sum(o.amount) as daily_income " +
            "from order_with_amount o " +
            "where o.created_timestamp between :begin and :end " +
            "group by report_date ",
            countQuery = "select date(o.created_timestamp) as report_date, count(o.id) " +
                    "from order_with_amount o " +
                    "where o.created_timestamp between :begin and :end group by report_date",
            nativeQuery = true)
    Page<DailyStatistic> getDailyStatistic(@Param("begin") LocalDate begin,
                                           @Param("end") LocalDate end,
                                           Pageable paging);

    @Query(value = "select o.customer_id, c.name, o.phone, max (o.created_timestamp) as last_order_date " +
            "from \"order\" o " +
            "inner join customer c on o.customer_id = c.id " +
            "group by o.customer_id, c.name, o.phone " +
            "having max (o.created_timestamp) < now() - interval '1 day' " +
            "and max (o.created_timestamp) > now() - interval '3 months'",
            countQuery = "select o.customer_id, max (o.created_timestamp) as last_order_date " +
                    "from \"order\" o " +
                    "inner join customer c on o.customer_id = c.id " +
                    "group by o.customer_id " +
                    "having max (o.created_timestamp) < now() - interval '1 day' and max (o.created_timestamp) > now() - interval '3 months'",
            nativeQuery = true)
    Page<CustomersToReturn> getCustomersToReturn(Pageable paging);

    @Query(value = "select sum(op.quantity * op.price) " +
            "from order_position op " +
            "inner join \"order\" o on op.order_id = o.id and o.created_timestamp between :begin and :end",
            nativeQuery = true)
    Integer getIncomeByPeriod(@Param("begin") LocalDate begin, @Param("end") LocalDate end);


    @Query(value = "select round(avg(o.amount), 2) " +
            "from order_with_amount o " +
            "where o.created_timestamp between :begin and :end",
            nativeQuery = true)
    Double getAvgBill(@Param("begin") LocalDate begin, @Param("end") LocalDate plusDays);

}

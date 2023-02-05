package com.example.sbertaste.repository;

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
            countQuery = "select p.name, sum(op.quantity) as quantity from order_position op " +
                    "inner join \"order\" o on op.order_id = o.id and o.created_timestamp between :begin and :end " +
                    "left join pizza p on op.pizza_id = p.id " +
                    "group by p.name " +
                    "order by quantity desc limit :count", nativeQuery = true)
    Page<PizzaBestSeller> getBestSellerByPeriod(@Param("count") int count,
                                                @Param("begin") LocalDate begin,
                                                @Param("end") LocalDate end,
                                                Pageable paging);

    @Query(value = "select sum(op.quantity * op.price) " +
            "from order_position op " +
            "inner join \"order\" o on op.order_id = o.id and o.created_timestamp between :begin and :end",
            nativeQuery = true)
    Integer getIncomeByPeriod(@Param("begin") LocalDate begin, @Param("end") LocalDate end);


    @Query(value = "select sum(op.quantity * op.price) * 1.0 / count(distinct o.id) " +
            "from order_position op " +
            "inner join \"order\" o on op.order_id = o.id and o.created_timestamp between :begin and :end",
            nativeQuery = true)
    Double getAvgBill(@Param("begin") LocalDate begin, @Param("end") LocalDate plusDays);

}

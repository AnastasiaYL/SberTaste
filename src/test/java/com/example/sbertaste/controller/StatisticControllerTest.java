package com.example.sbertaste.controller;

import com.example.sbertaste.dto.statistic.TopRequest;
import com.example.sbertaste.repository.OrderRepository;
import com.example.sbertaste.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.springframework.data.domain.Sort.Direction.DESC;

@SpringBootTest
@AutoConfigureTestDatabase
class StatisticControllerTest {

    @Autowired
    @InjectMocks
    private StatisticController controller;
    @SpyBean
    private StatisticService service;
    @SpyBean
    private OrderRepository repository;

    LocalDate begin = LocalDate.now().minusDays(10);
    LocalDate end = LocalDate.now().plusDays(1);

    @Test
    void getBestSeller() {
        TopRequest request = new TopRequest();
        request.setCount(3);
        request.setBegin(begin);
        request.setEnd(end.minusDays(1));

        controller.getBestSeller(request, 1, 10, "quantity", DESC);

        Pageable paging = PageRequest.of(0, 10, DESC, "quantity");

        Mockito.verify(service, Mockito.times(1)).getBestSellerByPeriod(request, paging);
        Mockito.verify(repository,
                Mockito.times(1)).getBestSellerByPeriod(3, begin, end, paging);
    }

    @Test
    void getIncomeByPeriod() {
        controller.getIncomeByPeriod(begin, end.minusDays(1));

        Mockito.verify(service, Mockito.times(1)).getIncomeByPeriod(begin, end.minusDays(1));
        Mockito.verify(repository, Mockito.times(1)).getIncomeByPeriod(begin, end);
    }

    @Test
    void getAvgBill() {
        controller.getAvgBill(LocalDate.now().minusDays(10), LocalDate.now());

        Mockito.verify(service, Mockito.times(1)).getAvgBill(begin, end.minusDays(1));
        Mockito.verify(repository, Mockito.times(1)).getAvgBill(begin, end);
    }
}
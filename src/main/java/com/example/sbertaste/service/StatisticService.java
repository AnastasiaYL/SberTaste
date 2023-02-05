package com.example.sbertaste.service;

import com.example.sbertaste.dto.statistic.PizzaBestSeller;
import com.example.sbertaste.dto.statistic.TopRequest;
import com.example.sbertaste.repository.OrderPositionRepository;
import com.example.sbertaste.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatisticService {

    private final OrderRepository orderRepository;

    public StatisticService(OrderRepository orderRepository, OrderPositionRepository orderPositionRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<PizzaBestSeller> getBestSellerByPeriod(TopRequest request, Pageable paging) {
        return orderRepository.getBestSellerByPeriod(request.getCount(),
                request.getBegin(),
                request.getEnd().plusDays(1),
                paging);
    }

    public Integer getIncomeByPeriod(LocalDate begin, LocalDate end) {
        return orderRepository.getIncomeByPeriod(begin, end.plusDays(1));
    }

    public Double getAvgBill(LocalDate begin, LocalDate end) {
        return orderRepository.getAvgBill(begin, end.plusDays(1));
    }
}

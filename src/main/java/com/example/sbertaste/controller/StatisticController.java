package com.example.sbertaste.controller;

import com.example.sbertaste.dto.statistic.CustomersToReturn;
import com.example.sbertaste.dto.statistic.DailyStatistic;
import com.example.sbertaste.dto.statistic.PizzaBestSeller;
import com.example.sbertaste.dto.statistic.TopRequest;
import com.example.sbertaste.service.StatisticService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistic")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Statistics API", description = "Director's statistics")
public class StatisticController {

    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @PostMapping("/best-seller")
    public Map<String, Object> getBestSeller(@RequestBody TopRequest request,
                                             @RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "50") int size,
                                             @RequestParam(required = false, defaultValue = "quantity") String sortBy,
                                             @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction) {
        Pageable paging = PageRequest.of(page - 1, size, direction, sortBy);
        Page<PizzaBestSeller> bestSellerPage = service.getBestSellerByPeriod(request, paging);

        return getResponse(bestSellerPage);
    }

    @PostMapping("/daily-statistic")
    public Map<String, Object> getDailyStatistics(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "50") int size,
                                                  @RequestParam(required = false, defaultValue = "report_date") String sortBy,
                                                  @RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
        Pageable paging = PageRequest.of(page - 1, size, direction, sortBy);
        Page<DailyStatistic> dailyStatistic = service.getDailyStatistic(begin, end, paging);

        return getResponse(dailyStatistic);
    }

    @PostMapping("/customers-to-return")
    public Map<String, Object> getCustomersToReturn(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "50") int size,
                                                    @RequestParam(required = false, defaultValue = "customer_id") String sortBy,
                                                    @RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
        Pageable paging = PageRequest.of(page - 1, size, direction, sortBy);
        Page<CustomersToReturn> customersToReturn = service.getCustomersToReturn(paging);

        return getResponse(customersToReturn);
    }

    @GetMapping("/income")
    public Integer getBestSeller(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getIncomeByPeriod(begin, end);
    }

    @GetMapping("/average-bill")
    public Double getAvgBill(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.getAvgBill(begin, end);
    }

    private Map<String, Object> getResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent());
        response.put("currentPage", page.getNumber() + 1);
        response.put("totalPages", page.getTotalPages());
        response.put("totalItems", page.getTotalElements());

        return response;
    }
}

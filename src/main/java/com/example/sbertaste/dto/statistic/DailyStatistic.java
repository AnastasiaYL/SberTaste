package com.example.sbertaste.dto.statistic;

import java.time.LocalDate;

@SuppressWarnings("unused")
public interface DailyStatistic {
    LocalDate getReport_date();

    int getDaily_orders();

    int getDaily_income();
}

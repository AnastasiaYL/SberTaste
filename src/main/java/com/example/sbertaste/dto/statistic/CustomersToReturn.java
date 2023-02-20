package com.example.sbertaste.dto.statistic;

import java.time.LocalDate;

@SuppressWarnings("unused")
public interface CustomersToReturn {
    int getCustomer_id();

    String getName();

    String getPhone();

    LocalDate getLast_order_date();
}

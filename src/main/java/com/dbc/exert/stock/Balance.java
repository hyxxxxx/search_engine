package com.dbc.exert.stock;

import lombok.Data;

import java.util.List;

@Data
public class Balance {

    private String annual_settle_date;
    private String currency;
    private String currency_name;
    private String last_report_name;
    private int org_type;
    private String quote_name;

    private List<BalanceItem> list;
}

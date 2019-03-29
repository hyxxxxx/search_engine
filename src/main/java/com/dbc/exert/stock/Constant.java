package com.dbc.exert.stock;

public interface Constant {

    String basic_url = "https://stock.xueqiu.com/v5/stock/finance/us/";

    String balance_url = "balance.json?symbol=%s&type=all&is_detail=true&count=%s&timestamp=%s";

    String finance_url = "income.json?symbol={0}&type=all&is_detail=true&count={1}&timestamp={2}";

}

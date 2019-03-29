package com.dbc.exert.stock;

import lombok.Data;

@Data
public class BalanceItem {

    private long report_date; //报告期
    private String report_name; //报告名称
    private int report_type_code;
    private int report_annual;  //报告年份
    private String sd;      //开始日期
    private String ed;    //结束日期
    private double[] total_assets;  //资产合计
    private double[] total_liab;    //负债合计
    private double[] asset_liab_ratio;  //资产负债比率？
    private double[] net_property_plant_and_equip;  //固定资产净额
    private double[] total_assets_special_subject;  //资产特殊科目
    private double[] lt_debt;   //长期借款
    private double[] total_liab_si; //负债合计特殊科目
    private double[] preferred_stock;   //优先股
    private double[] common_stock;  //普通股
    private double[] add_paid_in_capital;   //额外实收资本
    private double[] retained_earning;  //未分配利润
    private double[] treasury_stock;    //库存股
    private double[] accum_othr_compre_income;  //累计损益
    private double[] total_holders_equity_si;   //归属于母公司股东权益特殊项目
    private double[] total_holders_equity;  //归属于母公司股东权益合计
    private double[] minority_interest; //归属于少数股东权益
    private double[] total_equity_special_subject;  //权益特殊项目
    private double[] total_equity;  //股东权益合计
    private double[] cce;   //现金与现金等价物
    private double[] st_invest; //短期投资
    private double[] total_cash;    //总现金
    private double[] net_receivables;   //应收账款
    private double[] inventory; //存货
    private double[] dt_assets_current_assets;  //流动资产递延所得税
    private double[] prepaid_expense;   //预付款项
    private double[] current_assets_special_subject;    //流动资产特殊项目
    private double[] total_current_assets;  //流动资产合计
    private double[] gross_property_plant_and_equip;    //固定资产总额
    private double[] accum_depreciation;    //固定资产折旧
    private double[] equity_and_othr_invest;    //股权投资和长期投资
    private double[] goodwill;  //商誉
    private double[] net_intangible_assets; //无形资产净额
    private double[] accum_amortization;    //累计摊销
    private double[] dt_assets_noncurrent_assets;   //非流动资产递延所得税
    private double[] nca_si;    //非流动资产特殊项目
    private double[] total_noncurrent_assets;   //非流动资产合计
    private double[] st_debt;   //短期借款
    private double[] accounts_payable;  //应付账款
    private double[] income_tax_payable;    //应缴所得税
    private double[] accrued_liab;  //应计负债
    private double[] deferred_revenue_current_liab; //流动负债递延收入
    private double[] current_liab_si;   //流动负债特殊科目
    private double[] total_current_liab;    //流动负债合计
    private double[] deferred_tax_liab; //递延所得税负债
    private double[] dr_noncurrent_liab;    //非流动负债递延收入
    private double[] noncurrent_liab_si;    //非流动负债特殊科目
    private double[] total_noncurrent_liab; //非流动负债合计

}

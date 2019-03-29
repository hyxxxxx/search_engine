package com.dbc.exert.stock;

import com.alibaba.fastjson.JSONObject;
import com.dbc.exert.net.HttpHelper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

@Component
public class BalanceExecutor implements Runnable {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void run() {

        String balanceUrl = String.format(Constant.balance_url, "SZ000651", 5, System.currentTimeMillis());

        String body = null;
        try {
            body = HttpHelper.xueqiuGet("https://stock.xueqiu.com/v5/stock/finance/us/balance.json?symbol=BEDU&type=all&is_detail=true&count=5&timestamp=1553824811932");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Objects.nonNull(body)) {
            Balance balance = JSONObject.parseObject(body, Balance.class);
            System.out.println(balance);

        }

    }
}

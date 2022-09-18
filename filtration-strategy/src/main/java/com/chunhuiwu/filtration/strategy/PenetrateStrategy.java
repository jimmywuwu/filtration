package com.chunhuiwu.filtration.strategy;
import com.chunhuiwu.filtration.data_source.DataSource;

import java.util.logging.Logger;

public class PenetrateStrategy extends Strategy{
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public int signal() {
        DataSource price = datasource.get("log_normal");
        double curr = price.getCurr_price();
        double high_1 = price.getHigh(1);
        double open_1 = price.getOpen(1);
        double close_1 = price.getClose(1);
        double low_1 = price.getLow(1);
        int sig = curr > high_1? 1:0;
        logger.info(String.format("Signal: %d, Current Price: %f , former bar open %f, former bar high %f,former bar close %f,former bar low %f", sig,curr, open_1, high_1,close_1,low_1));
        return curr > high_1? 1:0;
    }
}

package com.chunhuiwu.filtration.strategy;
import com.chunhuiwu.filtration.data_source.DataSource;

import java.util.logging.Logger;

public class PenetrateStrategy extends Strategy{
    public PenetrateStrategy(){};
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private int burn_out = 1;
    @Override
    public int signal() {
        DataSource price = datasource.get("log_normal");
        if (price.getLow(1)!=0 & price.getOpen(0)!=0){
            double curr = price.getCurr_price();
            double high_1 = price.getHigh(1);
            double open_1 = price.getOpen(1);
            double close_1 = price.getClose(1);
            double low_1 = price.getLow(1);
            int sig = 0;
            if (curr> high_1){
                sig = 1;
            } else if (curr < low_1) {
                sig = -1;
            }
            logger.info(String.format("Signal: %d, Current Price: %f , former bar open %f, former bar high %f,former bar close %f,former bar low %f", sig,curr, open_1, high_1,close_1,low_1));
            return sig;
        }
        System.out.println("Bar not ready");
        return 0;
    }
}

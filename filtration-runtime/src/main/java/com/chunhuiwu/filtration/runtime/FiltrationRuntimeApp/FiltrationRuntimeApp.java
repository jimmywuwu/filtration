package com.chunhuiwu.filtration.runtime.FiltrationRuntimeApp;
import com.chunhuiwu.filtration.data_source.*;

import java.util.HashMap;
import java.util.Map;

public class FiltrationRuntimeApp {

    private static Map<String,DataSource> data_sources = new HashMap<String, DataSource>();

    public static void init(){
        LogNormalRandomnPrice log_p = new LogNormalRandomnPrice(12000,1.03/8640,1.03/8640);
        LogNormalRandomnPrice log_p2 = new LogNormalRandomnPrice(12000,1.03/8640,1.03/8640);
        data_sources.put("log_normal",log_p);
        for (Map.Entry<String, DataSource> entry : data_sources.entrySet()) {
            Thread t = new Thread(entry.getValue());
            t.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        DataSource log_normal = data_sources.get("log_normal");

        for(int i=0; i<1000;i++){
            double close = log_normal.getClose(0);
            double open = log_normal.getOpen(0);
            double high = log_normal.getHigh(0);
            double low = log_normal.getLow(0);
            double curr = log_normal.getCurr_price();
            System.out.println(String.format("open:%f, high:%f, close:%f, low:%f, curr: %f",open,high,close,low,curr));
            Thread.sleep(10000);
        }
    }
}

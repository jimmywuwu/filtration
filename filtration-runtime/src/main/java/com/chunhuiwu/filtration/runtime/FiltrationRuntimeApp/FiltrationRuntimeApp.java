package com.chunhuiwu.filtration.runtime.FiltrationRuntimeApp;
import com.chunhuiwu.filtration.data_source.*;
import com.chunhuiwu.filtration.strategy.PenetrateStrategy;
import com.chunhuiwu.filtration.strategy.Strategy;

import java.util.HashMap;
import java.util.Map;

public class FiltrationRuntimeApp {
    private static Strategy strategy;
    private static Map<String,DataSource> data_sources = new HashMap<String, DataSource>();

    public static void init(){
        LogNormalRandomnPrice log_p = new LogNormalRandomnPrice(12000,1.03/8640,1.03/8640);
        log_p.init();
        strategy = new PenetrateStrategy();
        data_sources.put("log_normal",log_p);
        for (Map.Entry<String, DataSource> entry : data_sources.entrySet()) {
            Thread t = new Thread(entry.getValue());
            t.start();
        }
        strategy.setDatasource(data_sources);
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        DataSource log_normal = data_sources.get("log_normal");


        for(int i=0; i<1000;i++){
            strategy.signal();
            Thread.sleep(10000);
        }
    }
}

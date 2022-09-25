package com.chunhuiwu.filtration.runtime;
import com.chunhuiwu.filtration.data_source.*;
import com.chunhuiwu.filtration.strategy.PenetrateStrategy;
import com.chunhuiwu.filtration.strategy.Strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FiltrationRuntimeApp {
    private static Strategy strategy;
    private static Map<String,DataSource> data_sources = new HashMap<String, DataSource>();

    public static void init() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String use_strategy = System.getProperty("strategy");
        Class<?> clazz = Class.forName(use_strategy);
        Constructor<?> ctr = clazz.getConstructor();
        strategy = (Strategy) ctr.newInstance();

        LogNormalRandomnPrice log_p = new LogNormalRandomnPrice(12000,1.03/8640,1.03/8640);
        log_p.init();
        data_sources.put("log_normal",log_p);
        for (Map.Entry<String, DataSource> entry : data_sources.entrySet()) {
            Thread t = new Thread(entry.getValue());
            t.start();
        }
        strategy.setDatasource(data_sources);
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        init();
        while(true){
            strategy.signal();
            Thread.sleep(1000);
        }
    }
}

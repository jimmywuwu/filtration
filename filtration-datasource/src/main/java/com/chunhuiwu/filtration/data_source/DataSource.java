package com.chunhuiwu.filtration.data_source;
import java.time.Instant;
import java.util.logging.*;

public abstract class DataSource implements Runnable{
    private Logger logger;
    private double[] minute_open = new double[1440];
    private double[] minute_high = new double[1440];
    private double[] minute_close = new double[1440];
    private double[] minute_low = new double[1440];
    private long today_start;
    private double curr_price;
    private int curr_slot;

    public abstract double fetch_price() throws InterruptedException;

    public void init(){
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    public void update_bar() throws InterruptedException {
        Instant instant = Instant.now();
        today_start = instant.getEpochSecond();
        today_start = today_start - today_start%86400;
        long timeStampSeconds;
        while(true){
            Instant now = Instant.now();
            timeStampSeconds = now.getEpochSecond();
            curr_price = fetch_price();
            curr_slot =  (int)(timeStampSeconds - today_start)/60;
            if (minute_open[curr_slot] == 0.0){
                minute_close[curr_slot] = curr_price;
                minute_open[curr_slot] = curr_price;
                minute_high[curr_slot] = curr_price;
                minute_low[curr_slot] = curr_price;
                continue;
            }
            if (curr_price > minute_high[curr_slot]){
                minute_high[curr_slot] = curr_price;
            }
            if (curr_price < minute_low[curr_slot]){
                minute_low[curr_slot] = curr_price;
            }
            minute_close[curr_slot] = curr_price;
        }
    }

    public double getCurr_price(){
        return curr_price;
    }

    public double getHigh(int offset){
        assert curr_slot - offset >0;
        return minute_high[curr_slot - offset];
    }

    public double getLow(int offset){
        assert curr_slot - offset >0;
        return minute_low[curr_slot - offset];
    }

    public double getClose(int offset){
        assert curr_slot - offset >0;
        return minute_close[curr_slot - offset];
    }

    public double getOpen(int offset){
        assert curr_slot - offset >0;
        return this.minute_open[curr_slot - offset];
    }

    @Override
    public void run(){
        while(true){
            try {
                this.update_bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

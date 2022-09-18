package com.chunhuiwu.filtration.data_source;
import java.time.Instant;
import java.util.Random;

public class UniformRandomnPrice extends DataSource{
    private Random r = new Random();
    @Override
    public double fetch_price() throws InterruptedException {
        Thread.sleep(1000);
        return r.nextDouble();
    }
}

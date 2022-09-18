package com.chunhuiwu.filtration.data_source;
import java.time.Instant;
import java.util.Random;

public class LogNormalRandomnPrice extends DataSource{
    private double p0;
    private double mu;
    private double sigma;
    private long init_timestamp;
    Random ran = new Random();

    public LogNormalRandomnPrice(double p0,double mu,double sigma){
        this.p0 = p0;
        this.mu = mu;
        this.sigma = sigma;
        this.init_timestamp = Instant.now().getEpochSecond();
    }

    @Override
    public double fetch_price() throws InterruptedException {
        long timestamp_now = Instant.now().getEpochSecond();
        double gauss = ran.nextGaussian();
        long incr = timestamp_now - init_timestamp;
        Thread.sleep(1000);
        return p0 * Math.exp((sigma*incr*gauss+mu*(incr)));
    }
}

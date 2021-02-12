package com.vortimo.sbu.commitCounter.model;

public class Timespan {
    private TimeUnit timeUnit;
    private Long amount;

    public Timespan(TimeUnit timeUnit, Long amount){
        this.timeUnit = timeUnit;
        this.amount = amount;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Long getAmount() {
        return amount;
    }
}

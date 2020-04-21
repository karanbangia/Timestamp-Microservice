package com.webapp.timeStampMicroservice.models;

public class TimeFormats {
    private long unix;

    public TimeFormats(long unix, String utc) {
        this.unix = unix;
        this.utc = utc;
    }

    private String utc;

    public long getUnix() {
        return unix;
    }

    public void setUnix(long unix) {
        this.unix = unix;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }


}

package org.openjfx;

import java.util.Date;

public class Logging {

    public Logging(){
    }

    public void logged(String Action) {
        System.out.println("Log: "+Action + "; Time:" + getCurrentTime());
    }

    public Date getCurrentTime(){
        Date date = new Date();
        return date;
    }


}


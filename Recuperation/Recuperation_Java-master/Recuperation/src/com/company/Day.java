package com.company;

import java.util.Random;

public class Day {
    private Hour[] day;

    public Day(){
        this.day = new Hour[24];
    }

    // TODO: 27. 3. 2020 random generating of day with some intelligence
    private boolean gen_rand_day() {
        Random r = new Random();
        for (Hour h : day) {
            h.setHum(20 + r.nextInt(40));
            h.setTemp(10 + r.nextInt(20));
        }
        return true;
    }
}

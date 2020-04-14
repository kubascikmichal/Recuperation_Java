package com.company;

public class Hour {
    private double temp;
    private double hum;
    final private  double CO2 = 0.04;

    public Hour(double p_temp, double p_hum) {
        this.hum = p_hum;
        this.temp = p_temp;
    }

    public double getTemp(){
        return this.temp;
    }

    public double getHum() {
        return hum;
    }

    public double getCO2() {
        return CO2;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHum(double hum) {
        this.hum = hum;
    }

}

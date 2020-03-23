package com.company;

public enum Activity {

    // from https://www.engineeringtoolbox.com/co2-persons-d_691.html
    // Normal from 0.08 to 0.13
    SLEEP(0.013), LOW_ACTIVITY(0.02), NORMAL_ACTIVITY(0.1), HARD_ACTIVITY(0.35);

    private double CO2_out_hour;

    private Activity(double p_CO2_out) {
        this.CO2_out_hour = p_CO2_out;
    }

    public double getCO2_out() {
        return CO2_out_hour;
    }
}

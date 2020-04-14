package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Machine {
    /**
     * ideal_temperature - ideal temperature of room, where machine is [Celsius degrees]
     * ideal_humidity - ideal humidity of room, where machine is [%]
     * ideal_CO2 - ideal percentage of CO2 in air of room, where machine is [%]
     */
    private double ideal_temperature;
    private double ideal_humidity;
    private double ideal_CO2;
    //private double efficiency;

    /**
     *
     * @param id_temp - ideal temperature
     * @param id_hum - ideal humidity
     * ideal_CO2 is by default 0.04
     */
    public Machine(double id_temp, double id_hum) {
        this.ideal_temperature = id_temp;
        this.ideal_humidity = id_hum;
        this.ideal_CO2 = 0.04;
    }

    /**
     *
     * @param id_temp - ideal temperature
     * @param id_hum - ideal humidity
     * @param id_CO2 - ideal CO2
     */
    public Machine(double id_temp, double id_hum, double id_CO2) {
        this.ideal_temperature = id_temp;
        this.ideal_humidity = id_hum;
        this.ideal_CO2 = id_CO2;
    }

    /**
     *
     * @param room - room where machine is
     * @param hum_out - humidity outside [%]
     * @param temp_out - temperature outside [%]
     * @param CO2_out - percentage of CO2 out [%], by default should be 0.04
     */
    public void change(@NotNull Room room, double hum_out, double temp_out, double CO2_out) {
        double V_hum_i = ideal_V_hum(room.getVolume_of_room(), room.getHumidity(), hum_out);
        double V_CO2_i = ideal_V_CO2(room.getVolume_of_room(), room.getCO2(), CO2_out);
        double V_temp_i = ideal_V_temp(room.getVolume_of_room(), room.getTemperature(), room.getHumidity(), temp_out, hum_out);

        double V_ideal = (V_hum_i*dist_hum(this.ideal_humidity, room.getHumidity()) + V_temp_i * dist_temp(this.ideal_temperature, room.getTemperature()) + V_CO2_i * dist_CO2(this.ideal_CO2, room.getCO2()))/
                (dist_hum(this.ideal_humidity, room.getHumidity()) + dist_temp(this.ideal_temperature, room.getTemperature()) + dist_CO2(this.ideal_CO2, room.getCO2()));

        if (V_ideal > room.getVolume_of_room()) {
            V_ideal = room.getVolume_of_room();
        }
        room.setHumidity((room.getHumidity() * (room.getVolume_of_room() - V_ideal) + hum_out*V_ideal) / room.getVolume_of_room());
        room.setCO2((room.getCO2() * (room.getVolume_of_room() - V_ideal) + CO2_out * V_ideal)/(room.getVolume_of_room()));


        double temp_ex = (density_mix(room.getHumidity()) * c_mix(room.getHumidity()) * room.getTemperature() + density_mix(hum_out) * c_mix(hum_out) * temp_out)/
                (2 * c_mix(hum_out) * density_mix(hum_out));
        room.setTemperature((density_mix(room.getHumidity()) * c_mix(room.getHumidity()) * room.getTemperature()*(room.getVolume_of_room() - V_ideal)
                + density_mix(hum_out) * c_mix(hum_out) * temp_ex * V_ideal) / (density_mix(room.getHumidity()) * c_mix(room.getHumidity()) * room.getVolume_of_room()));
    }

    /**
     * Count relative distance between ideal CO2 and actual CO2, depending on sensibility of system (for CO2 it is 0.04%)
     * @param ideal_co2 - ideal CO2 set in constructor (by default 0.04 or another value)
     * @param co2 - CO2 actually in room
     * @return relative distance
     */
    private double dist_CO2(double ideal_co2, double co2) {
        return distance(ideal_co2, co2)  / 0.04;
    }

    /**
     * Count relative distance between ideal temperature and actual temperature, depending on sensibility of system (for temperature it is 1)
     * @param ideal_temperature - ideal temperature set in constructor
     * @param temperature - temperature actually in room
     * @return distance between values
     */
    private double dist_temp(double ideal_temperature, double temperature) {
        return distance(ideal_temperature, temperature) / 1;
    }

    /**
     * Count relative distance between ideal humidity and actual humidity, depending on sensibility of system (for humidity it is 5%)
     * @param ideal_humidity - ideal humidity set in constructor
     * @param humidity - humidity actually in room
     * @return distance between values
     */
    private double dist_hum(double ideal_humidity, double humidity) {
        return distance(ideal_humidity, humidity) / 5;
    }

    /**
     *
     * @param V_room Volume of room
     * @param hum_in humidity in room
     * @param hum_out humidity outside
     * @return ideal volume to change
     */
    private double ideal_V_hum(int V_room, double hum_in, double hum_out) {
        double V_ex = V_room;
        if ((hum_in < this.ideal_humidity && hum_out >= this.ideal_humidity) || (hum_in > this.ideal_humidity && hum_out <= this.ideal_humidity)) {
            V_ex *= (((this.ideal_humidity - hum_in) / (hum_out - hum_in)) > 1) ? 1 : ((this.ideal_humidity - hum_in) / (hum_out - hum_in));
            return V_ex;
        } else if ((hum_in > hum_out && hum_out > this.ideal_humidity) || (hum_in < hum_out && hum_out < this.ideal_humidity)) {
            return  V_room;
        }
        return 0;
    }

    /**
     *
     * @param V_room Volume of room
     * @param CO2_in percentage of CO2 inside room
     * @param CO2_out percentage of C02 outside
     * @return ideal volume of change
     */
    private double ideal_V_CO2(int V_room, double CO2_in, double CO2_out ){
        double V_ex = V_room;
        if ((CO2_in < this.ideal_CO2 && CO2_out >= this.ideal_CO2) || (CO2_in > this.ideal_CO2 && CO2_out <= this.ideal_CO2)) {
            V_ex *= (((this.ideal_CO2 - CO2_in) / (CO2_out - CO2_in)) > 1) ? 1 : ((this.ideal_CO2 - CO2_in) / (CO2_out - CO2_in));
            return V_ex;
        } else if((CO2_in > CO2_out && CO2_out > this.ideal_CO2) || (CO2_in < CO2_out && CO2_out < this.ideal_CO2)) {
            return V_room;
        }
        return 0;
    }

    private double ideal_V_temp(int V_room, double temp_in, double hum_in, double temp_out, double hum_out){
        double temp_exch = (c_mix(hum_in) * density_mix(hum_in) * temp_in + density_mix(hum_out) * c_mix(hum_out) * temp_out)/(2*c_mix(hum_out) * density_mix(hum_out));
        double V_ex = ((density_mix(hum_out) * c_mix(hum_out) * ideal_temperature - density_mix(hum_in) * c_mix(hum_in)*temp_in) /
                (density_mix(hum_out) * c_mix(hum_out) * temp_exch - density_mix(hum_in) * c_mix(hum_in) * temp_in)) *V_room;
        return (V_ex > 0 && V_ex <= V_room) ? V_ex : (V_ex > 0) ? V_room : 0;
    }

    /**
     *
     * @param hum humidity of air=
     * @return temperature capacity of mix
     */
    private double c_mix(double hum) {
        return (1131.6*hum + 1290 * (100 - hum))/density_mix(hum);
    }
    /**
     *
     * @param hum humidity of air
     * @return density of mix
     */
    private double density_mix(double hum) {
        return -0.00614 * hum + 1.29;
    }

    /**
     *
     * @param id - ideal value
     * @param val - actual value
     * @return distance between values
     */
    private double distance(double id, double val) {
        return Math.abs(id - val);
    }
}


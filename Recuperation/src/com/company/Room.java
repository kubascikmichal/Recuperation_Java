package com.company;

public class Room {

    private int volume_of_room;
    private double humidity;
    private double temperature;
    private double CO2;

    public Room(int vol, double hum, double temperature, double CO2) {
        this.volume_of_room = vol;
        this.humidity = hum;
        this.temperature = temperature;
        this.CO2 = CO2;
    }


    public double getHumidity() {
        return humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getVolume_of_room() {
        return volume_of_room;
    }

    public double getCO2() {
        return CO2;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setCO2(double CO2) {
        this.CO2 = CO2;
    }

    public void getLog() {
        System.out.println(this.toString());
    }

    public void plus_CO2() {
        // TODO: 27. 3. 2020 plus by hour must depend on count of people & activity & volume of room 
        this.CO2 += 0.01;
    }
    @Override
    public String toString() {
        return String.format("Room %10.4f / %10.4f / %10.4f ", this.temperature, this.humidity, this.CO2);
    }
}

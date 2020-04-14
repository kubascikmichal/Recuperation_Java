package com.company;

public class Room {

    private int volume_of_room;
    private double humidity;
    private double temperature;
    private double CO2;

    /**
     *
     * @param vol - volume of room
     * @param hum - humidity in room
     * @param temperature - temperature in room
     * @param CO2 - percentage of CO2 in room
     */
    public Room(int vol, double hum, double temperature, double CO2) {
        this.volume_of_room = vol;
        this.humidity = hum;
        this.temperature = temperature;
        this.CO2 = CO2;
    }

    /**
     * Getter for humidity
     * @return humidity
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Getter for temperature
     * @return
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Getter for volume
     * @return volume of room
     */
    public int getVolume_of_room() {
        return volume_of_room;
    }

    /**
     * Getter for CO2
     * @return CO2
     */
    public double getCO2() {
        return CO2;
    }

    /**
     * Setter for humidity
     * @param humidity - parameter to set
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * Setter for temperature
     * @param temperature - parameter to set
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Setter for CO2
     * @param CO2 - parameter to set
     */
    public void setCO2(double CO2) {
        this.CO2 = CO2;
    }

    /**
     * Print room statue
     */
    public void getLog() {
        System.out.println(this.toString());
    }

    /**
     * Method to add CO2. Not really important
     */
    public void plus_CO2() {
        this.CO2 += 0.01;
    }
    @Override
    public String toString() {
        return String.format("Room %10.4f / %10.4f / %10.4f ", this.temperature, this.humidity, this.CO2);
    }
}

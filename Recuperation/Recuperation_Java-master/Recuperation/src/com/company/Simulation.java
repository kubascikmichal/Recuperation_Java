package com.company;

public class Simulation {
    /**
     * main process of simulation
     */
    public static void run(){
        Room room = new Room(100, 30, 22,0.04);
        Machine my_intelligent_machine = new Machine(22, 30);
        room.getLog();
        for (int i = 0; i < 100; i++) {
            room.plus_CO2();
            my_intelligent_machine.change(room, 35, 15, 0.05);
            room.getLog();
            room.plus_CO2();
            my_intelligent_machine.change(room, 30, 19, 0.03);
            room.getLog();
            room.plus_CO2();
            my_intelligent_machine.change(room, 25, 15, 0.05);
            room.getLog();
            room.plus_CO2();
            my_intelligent_machine.change(room, 25, 19, 0.03);
            room.getLog();
        }

    }
    public static void main(String[] args) {
        Simulation.run();
    }
}

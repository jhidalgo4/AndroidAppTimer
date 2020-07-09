package edu.utep.cs.cs4381.timer;

public class TimerModel {

    long startTime;
    boolean run;

    TimerModel(){
        this.run = false;
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
        this.run = true;
    }

    public void stop(){
        this.run = false;
    }

    public long elapsedTime(){
        return System.currentTimeMillis() - this.startTime;
    }

    public boolean isRunning(){
        if (this.run) return true;
        return false;
    }
}

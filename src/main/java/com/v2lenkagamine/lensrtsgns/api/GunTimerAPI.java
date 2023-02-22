package com.v2lenkagamine.lensrtsgns.api;
public class GunTimerAPI {

    int reloadTimer = 0;
    public int getTimer() {
        int timer = (getTimerTicks()/20);
        return timer;
    }
    public int getTimerTicks() {
        return reloadTimer;
    }
    public void setTimer(int time) {
        reloadTimer = (time *20);
    }
    public void setTimerTicks(int time) {
        reloadTimer = time;
    }
    public void timerDownTick() {
        int timenew = getTimerTicks();
        timenew--;
        setTimerTicks(timenew);
    }
}
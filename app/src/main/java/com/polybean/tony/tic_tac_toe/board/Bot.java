package com.polybean.tony.tic_tac_toe.board;

/**
 * Created by Antony Lulciuc on 11/5/2016.
 */
public class Bot{
    public boolean botTurn = false;
    public Bot(Runnable _run){
        new Thread(_run).start();
    }
}

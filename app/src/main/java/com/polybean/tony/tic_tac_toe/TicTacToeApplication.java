package com.polybean.tony.tic_tac_toe;

import android.app.Application;

/**
 * Created by Antony Lulciuc on 11/5/2016.
 */
public class TicTacToeApplication extends Application {
    public static final int TURN_X = 0x01;
    public static final int TURN_O = 0x02;
    public static int PlayerXScore = 0;
    public static int PlayerOScore = 0;
    public static int PlayerTurn = TURN_X;
}

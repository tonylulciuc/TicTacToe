package com.polybean.tony.tic_tac_toe;

import android.app.Application;

import com.polybean.tony.tic_tac_toe.board.BoardLogic;
import com.polybean.tony.tic_tac_toe.board.Bot;

/**
 * Created by Antony Lulciuc on 11/5/2016.
 */
public class TicTacToeApplication extends Application {
    public static final int TURN_X = 0x01;
    public static final int TURN_O = 0x02;
    public static int PlayerXScore = 0;
    public static int PlayerOScore = 0;
    public static int PlayerTurn = TURN_X;
    public static char[][] Board = null;
    public static Bot BotOpponent;
    public static BoardLogic TicTacToeBoardLogic;

    /**
     * Initializes the board game only once
     */
    public void initBoard(){
        if (Board == null) {
            Board = new char[][]{{'n', 'n', 'n'},
                                 {'n', 'n', 'n'},
                                 {'n', 'n', 'n'}};

            BotOpponent = new Bot();
            TicTacToeBoardLogic = new BoardLogic();
        }
    }
}

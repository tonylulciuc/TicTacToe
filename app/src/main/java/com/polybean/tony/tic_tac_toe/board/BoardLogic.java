package com.polybean.tony.tic_tac_toe.board;

import android.app.Activity;
import android.content.Context;

import com.polybean.tony.tic_tac_toe.TicTacToeApplication;

import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.Board;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.PlayerTurn;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.TURN_X;

/**
 * Created by Antony Lulciuc on 11/5/2016.
 */
public class BoardLogic {
    public static final int VICTORY_HORIZONTAL_BAR_ROW_1  = 0x01;
    public static final int VICTORY_HORIZONTAL_BAR_ROW_2  = 0x02;
    public static final int VICTORY_HORIZONTAL_BAR_ROW_3  = 0x03;
    public static final int VICTORY_VERTICAL_BAR_COLUMN_1 = 0x04;
    public static final int VICTORY_VERTICAL_BAR_COLUMN_2 = 0x05;
    public static final int VICTORY_VERTICAL_BAR_COLUMN_3 = 0x06;
    public static final int VICTORY_DIAGONAL_BAR_SLANT_1  = 0x07;
    public static final int VICTORY_DIAGONAL_BAR_SLANT_2  = 0x08;
    public static final int DRAW_NO_ONE_WON = 0xFF;
    private int mMovesMade = 0;

    /**
     * Apply logic to players move
     * @param _row [in] row selected
     * @param _column [in] column selected
     * @return returns
     */
    public int selectBlock(int _row, int _column){
        // If move valid then proceed
        if (moveValid(_row, _column)){
             return (winningMove(_row, _column));
        }

        return (-1);
    }

    /**
     * Resets the game board
     */
    public void resetBoard(){
        for (int i = 0; i < Board.length; i++){
            for (int j = 0; j < Board[i].length; j++){
                Board[i][j] = 'n';
            }
        }

        mMovesMade = 0;
    }

    /**
     * Get value contained in board
     * @param _row [in] desired row
     * @param _column [in] desired column
     * @return value contained in position
     */
    public char get(int _row, int _column){
        return (Board[_row][_column]);
    }

    /**
     * Determines if move made was a winning move
     * @param _row [in] row selected
     * @param _column [in] column selected
     * @return
     */
    private int winningMove(int _row, int _column){
        char playerValue = PlayerTurn == TURN_X ? 'x' : 'o';
        int result = -1;

        try {
            Board[_row][_column] = playerValue;
        }catch (Exception err){
            return (-1);
        }

        // Check all possible win states for move
        if ((result = checkHorizontal(_row, playerValue)) == 0) {
            if ((result = checkVertical(_column, playerValue)) == 0) {
                result = checkDiagonal(playerValue);
            }
        }

        // If we reached the end. Check if draw
        if ((++mMovesMade) == 9 && result == 0){
            return (DRAW_NO_ONE_WON);
        }

        return (result);
    }

    /**
     * Determines if move is valid (i.e. was it already taken)
     * @param _row [in] row selected
     * @param _column [in] column selected
     * @return true if it is valid else false
     */
    private boolean moveValid(int _row, int _column){
        return (Board[_row][_column] == 'n');
    }

    /**
     * Check if player won via row match
     * @param _row [in] row user selected
     * @param _playerValue [in] value representing user contained in matrix
     * @return 0 if player did not when else > 0
     */
    private int checkHorizontal(int _row, char _playerValue) {
        if (Board[_row][0] == _playerValue && Board[_row][1] == _playerValue && Board[_row][2] == _playerValue) {
            switch (_row){
                case 0:
                    return (VICTORY_HORIZONTAL_BAR_ROW_1);
                case 1:
                    return (VICTORY_HORIZONTAL_BAR_ROW_2);
                case 2:
                    return (VICTORY_HORIZONTAL_BAR_ROW_3);
            }
        }

        return (0);
    }

    /**
     * Check if player won via column match
     * @param _column [in] column user selected
     * @param _playerValue [in] value representing user contained in matrix
     * @return 0 if player did not when else > 0
     */
    private int checkVertical(int _column, char _playerValue){
        if (Board[0][_column] == _playerValue && Board[1][_column] == _playerValue && Board[2][_column] == _playerValue) {
            switch (_column){
                case 0:
                    return (VICTORY_VERTICAL_BAR_COLUMN_1);
                case 1:
                    return (VICTORY_VERTICAL_BAR_COLUMN_2);
                case 2:
                    return (VICTORY_VERTICAL_BAR_COLUMN_3);
            }
        }

        return (0);
    }

    /**
     * Check if player won via row match
     * @param _playerValue [in] value representing user contained in matrix
     * @return 0 if player did not when else > 0
     */
    private int checkDiagonal(char _playerValue){
        if (Board[1][1] == _playerValue) {
            if (Board[0][0] == _playerValue && Board[2][2] == _playerValue){
                return (VICTORY_DIAGONAL_BAR_SLANT_1);
            }

            if (Board[2][0] == _playerValue && Board[0][2] == _playerValue){
                return (VICTORY_DIAGONAL_BAR_SLANT_2);
            }
        }
        return (0);
    }
}

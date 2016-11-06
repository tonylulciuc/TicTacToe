package com.polybean.tony.tic_tac_toe.board;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.polybean.tony.tic_tac_toe.R;

import java.util.ArrayList;

import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.TicTacToeBoardLogic;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.PlayerOScore;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.PlayerTurn;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.PlayerXScore;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.TURN_X;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.TURN_O;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {
    public static final int GAME_PLAYER_VERSUS_PLAYER   = 0x01;
    public static final int GAME_PLAYER_VERSUS_COMPUTER = 0x02;
    private View mSelf;
    private TextView mTextViewXScore;
    private TextView mTextViewOScore;
    private TextView mTextViewGameType;
    private ArrayList<View> mGrid = null;

    public BoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mSelf             = inflater.inflate(R.layout.fragment_board, container, false);
        mTextViewXScore   = (TextView)mSelf.findViewById(R.id.x_score);
        mTextViewOScore   = (TextView)mSelf.findViewById(R.id.o_score);
        mTextViewGameType = (TextView)mSelf.findViewById(R.id.game_type);

        initGrid();
        restoreGameState();
        setClickListener();
        return (mSelf);
    }

    /**
     * Starts a new game and resets the boars
     * @param _gameType
     */
    public void startNewGame(int _gameType){
        resetBoard();
        resetScore();

        /**
         * Display game type
         */
        switch (_gameType){
            case GAME_PLAYER_VERSUS_PLAYER:
                mTextViewGameType.setText(getResources().getString(R.string.game_pvp));
                break;
            case GAME_PLAYER_VERSUS_COMPUTER:
                mTextViewGameType.setText(getResources().getString(R.string.game_pvc));
                break;
        }
    }

    /**
     * Handle orientation change
     */
    private void restoreGameState(){
        char value;
        int row    = 0;
        int column = 0;

        // reset board display
        for (View v : mGrid){

            // Reset display for block
            switch (TicTacToeBoardLogic.get(row, column)){
                case 'x':
                    v.setBackgroundResource(R.drawable.x_mark);
                    break;
                case 'o':
                    v.setBackgroundResource(R.drawable.o_mark);
                    break;
                default:
                    v.setBackgroundResource(0);
            }

            ++column;

            // If reached last column go to next row
            if (column != 0 && (column % 3) == 0){
                ++row;
                column = 0;
            }
        }
    }

    /**
     * Initialize game board grid
     */
    private void initGrid(){
        mGrid = new ArrayList<>();

        // ROW 1
        mGrid.add(mSelf.findViewById(R.id.block_00));
        mGrid.add(mSelf.findViewById(R.id.block_01));
        mGrid.add(mSelf.findViewById(R.id.block_02));

        // ROW 2
        mGrid.add(mSelf.findViewById(R.id.block_10));
        mGrid.add(mSelf.findViewById(R.id.block_11));
        mGrid.add(mSelf.findViewById(R.id.block_12));

        // ROW 3
        mGrid.add(mSelf.findViewById(R.id.block_20));
        mGrid.add(mSelf.findViewById(R.id.block_21));
        mGrid.add(mSelf.findViewById(R.id.block_22));
    }

    /**
     *  Sets handler to manage user input
     */
    private void setClickListener(){
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                int result = 0;

                if (PlayerTurn == TURN_X){
                    if ((result = processMove(_view.getId())) != -1) {
                        _view.setBackgroundResource(R.drawable.x_mark);
                        PlayerTurn = TURN_O;
                    }
                }else{
                    if ((result = processMove(_view.getId())) != -1) {
                        _view.setBackgroundResource(R.drawable.o_mark);
                        PlayerTurn = TURN_X;
                    }
                }

                // check and inform players of result of move
                if (result == -1)
                    Toast.makeText(mSelf.getContext(), "Invalid move!", Toast.LENGTH_LONG).show();
                else
                    checkResult(result);
            }
        };

        // Set event handler
        for (View v : mGrid){
            v.setOnClickListener(ocl);
        }
    }

    /**
     * Process move made by the player
     * @param _move [in] move made
     * @return -1 if move was not valid else >= 0
     */
    private int processMove(int _move){
        switch (_move){
            case R.id.block_00:
                return (TicTacToeBoardLogic.selectBlock(0, 0));
            case R.id.block_01:
                return (TicTacToeBoardLogic.selectBlock(0, 1));
            case R.id.block_02:
                return (TicTacToeBoardLogic.selectBlock(0, 2));
            case R.id.block_10:
                return (TicTacToeBoardLogic.selectBlock(1, 0));
            case R.id.block_11:
                return (TicTacToeBoardLogic.selectBlock(1, 1));
            case R.id.block_12:
                return (TicTacToeBoardLogic.selectBlock(1, 2));
            case R.id.block_20:
                return (TicTacToeBoardLogic.selectBlock(2, 0));
            case R.id.block_21:
                return (TicTacToeBoardLogic.selectBlock(2, 1));
            case R.id.block_22:
                return (TicTacToeBoardLogic.selectBlock(2, 2));
            default:
                return (-1);
        }
    }

    /**
     * Check result for game state
     * @param _result [in] result of move made
     */
    private void checkResult(int _result) {
        String msg;

        switch (_result) {
            case 0:
                return;
            case BoardLogic.DRAW_NO_ONE_WON:
                Toast.makeText(mSelf.getContext(), "DRAW!", Toast.LENGTH_LONG).show();
                resetBoard();
                break;
            default:
                PlayerXScore = PlayerTurn == TURN_X ? PlayerXScore : PlayerXScore + 1;
                PlayerOScore = PlayerTurn == TURN_O ? PlayerOScore : PlayerOScore + 1;
                mTextViewXScore.setText(String.valueOf(PlayerXScore));
                mTextViewOScore.setText(String.valueOf(PlayerOScore));
                resetBoard();
                Toast.makeText(mSelf.getContext(), "WIN!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * Reset EVERYTHING!!
     */
    private void resetBoard(){
        TicTacToeBoardLogic.resetBoard();

        // Reset background
        for (View v : mGrid){
            v.setBackgroundResource(0);
        }
    }

    private void resetScore(){
        // Reset score
        PlayerXScore = 0;
        PlayerOScore = 0;

        mTextViewXScore.setText("0");
        mTextViewOScore.setText("0");
    }
}
























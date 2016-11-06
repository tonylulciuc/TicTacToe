package com.polybean.tony.tic_tac_toe.board;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.TextView;

import com.polybean.tony.tic_tac_toe.R;

import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.PlayerTurn;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.TURN_X;
import static com.polybean.tony.tic_tac_toe.TicTacToeApplication.TURN_O;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {
    public static final int GAME_PLAYER_VERSUS_PLAYER = 0x01;
    public static final int MAGE_PLAYER_VERSUS_COMPUTER = 0x02;
    private View mSelf;
    private TextView mTextViewXScore;
    private TextView mTextViewOScore;

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GridLayout gridLayout;

        // Inflate the layout for this fragment
        mSelf = inflater.inflate(R.layout.fragment_board, container, false);
        mTextViewXScore = (TextView)mSelf.findViewById(R.id.x_score);
        mTextViewOScore = (TextView)mSelf.findViewById(R.id.o_score);
        setClickListener();
        return (mSelf);
    }


    public void startNewGame(int _gameType){

    }

    /**
     *
     */
    private void setClickListener(){
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (PlayerTurn == TURN_X){
                    _view.setBackgroundResource(R.drawable.x_mark);
                    PlayerTurn = TURN_O;
                }else{
                    _view.setBackgroundResource(R.drawable.o_mark);
                    PlayerTurn = TURN_X;
                }
            }
        };

        // ROW 1
        mSelf.findViewById(R.id.block_00).setOnClickListener(ocl);
        mSelf.findViewById(R.id.block_01).setOnClickListener(ocl);
        mSelf.findViewById(R.id.block_02).setOnClickListener(ocl);

        // ROW 2
        mSelf.findViewById(R.id.block_10).setOnClickListener(ocl);
        mSelf.findViewById(R.id.block_11).setOnClickListener(ocl);
        mSelf.findViewById(R.id.block_12).setOnClickListener(ocl);

        // ROW 3
        mSelf.findViewById(R.id.block_20).setOnClickListener(ocl);
        mSelf.findViewById(R.id.block_21).setOnClickListener(ocl);
        mSelf.findViewById(R.id.block_22).setOnClickListener(ocl);
    }



}
























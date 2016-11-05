package com.polybean.tony.tic_tac_toe;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.polybean.tony.tic_tac_toe.board.BoardFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mLeftDrawer;
    private BoardFragment mBoardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Acquire drawer view
        mLeftDrawer = (DrawerLayout)findViewById(R.id.left_drawer);

        // create board
        createBoard();
    }

    /**
     * Create tic-tac-toe board
     */
    private void createBoard(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;

        // If fragment already exists
        if ((fragment = fragmentManager.findFragmentByTag("board")) != null){
            mBoardFragment = (BoardFragment)fragment;
            fragmentTransaction.replace(R.id.details_fragment, mBoardFragment, "board");
        }else{
            mBoardFragment = new BoardFragment();
            fragmentTransaction.add(R.id.details_fragment, mBoardFragment, "board");
        }

        // Commit fragment
        fragmentTransaction.commit();
    }


    /**
     * Handle hamburger click events
     * @param _view [in] view associated with click event
     */
    public void onHamburgerClick(View _view){
        if (mLeftDrawer.isDrawerOpen(Gravity.LEFT))
            mLeftDrawer.closeDrawer(Gravity.LEFT);
        else
            mLeftDrawer.openDrawer(Gravity.LEFT);
    }

    /**
     * Handle play friend request event on menu
     * @param _view [in] view associated with click event
     */
    public void onPlayFriendClick(View _view){
        // TODO : create game for pvp
        Toast.makeText(this, "Player versus player option not ready!", Toast.LENGTH_LONG).show();
    }

    /**
     * Handle on play computer click event on menu
     * @param _view [in] view associated with click event
     */
    public void onPlayComputerClick(View _view){
        // TODO : create game for pvc
        Toast.makeText(this, "Player versus computer option not ready!", Toast.LENGTH_LONG).show();
    }

    /**
     * Handle on settings click event on menu
     * @param _view [in] view associated with click event
     */
    public void onSettingsClick(View _view){
        // TODO : create settings activity for pvc options
        Toast.makeText(this, "Settings not ready!", Toast.LENGTH_LONG).show();
    }
}

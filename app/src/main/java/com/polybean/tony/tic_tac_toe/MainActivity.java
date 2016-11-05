package com.polybean.tony.tic_tac_toe;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mLeftDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Acquire drawer view
        mLeftDrawer = (DrawerLayout)findViewById(R.id.left_drawer);
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

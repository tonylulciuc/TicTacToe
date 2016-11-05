package com.polybean.tony.tic_tac_toe;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

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
}

package activities;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageButton;

import constants.MenuConstants;
import fragments.BusTaxAdderFragment;
import fragments.CardAdderFragment;
import fragments.CardListerFragment;
import checker.ticker.bus.basic.R;
import fragments.NavigationDrawerFragment;

// https://guides.codepath.com/android/Fragment-Navigation-Drawer

public class MainDrawerActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ImageButton addBusTravelButton;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setContext(this);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        addBusTravelButton = (ImageButton) findViewById(R.id.addBusTravelButton);
        addBusTravelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainDrawerActivity.this, CheckerActivity.class));
            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new CardListerFragment();

        switch (position){
            case MenuConstants.SHOW_CARDS:
                fragment = new CardListerFragment();
                break;
            case MenuConstants.ADD_BUS_CARD:
                fragment = new CardAdderFragment();
                break;
            case MenuConstants.ADD_BUS_TAX:
                fragment = new BusTaxAdderFragment();
                break;
            case MenuConstants.REMOVE_BUS_CARD:
                fragment = new CardAdderFragment();
                break;
            case MenuConstants.REMOVE_BUS_TAX:
                fragment = new CardAdderFragment();
                break;
        }
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case MenuConstants.ADD_BUS_CARD:
                mTitle = getString(R.string.title_add_card);
                break;
            case MenuConstants.ADD_BUS_TAX:
                mTitle = getString(R.string.title_add_tax);
                break;
            case MenuConstants.REMOVE_BUS_CARD:
                mTitle = getString(R.string.title_remove_card);
                break;
            case MenuConstants.REMOVE_BUS_TAX:
                mTitle = getString(R.string.title_remove_tax);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

}

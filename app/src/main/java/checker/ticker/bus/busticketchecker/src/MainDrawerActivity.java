package checker.ticker.bus.busticketchecker.src;

import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import checker.ticker.bus.busticketchecker.fragments.CardInserterFragment;
import checker.ticker.bus.busticketchecker.fragments.PlaceholderFragment;
import checker.ticker.bus.busticketchecker.R;
import checker.ticker.bus.busticketchecker.fragments.NavigationDrawerFragment;

public class MainDrawerActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

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

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceholderFragment fragment = new PlaceholderFragment();
        CardInserterFragment fragment2 = new CardInserterFragment();

        if(position == 0){
            fragmentTransaction.replace(R.id.container, fragment2);
        }else if(position == 2){
            fragmentTransaction.replace(R.id.container, fragment2);
        }else{
            startActivity(new Intent(this, CheckerActivity.class));
        }
        fragmentTransaction.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_card);
                break;
            case 2:
                mTitle = getString(R.string.title_tax);
                break;
            case 3:
                mTitle = getString(R.string.title_travel);
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

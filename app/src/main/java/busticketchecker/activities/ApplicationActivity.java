package busticketchecker.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageButton;

import java.util.Locale;

import checker.ticker.bus.basic.R;
import busticketchecker.constants.MenuConstants;
import busticketchecker.fragments.BusTaxAdderFragment;
import busticketchecker.fragments.BusTaxRemoverFragment;
import busticketchecker.fragments.CardAdderFragment;
import busticketchecker.fragments.CardListerFragment;
import busticketchecker.fragments.CardRemoverFragment;
import busticketchecker.fragments.NavigationDrawerFragment;

// https://guides.codepath.com/android/Fragment-Navigation-Drawer
// https://github.com/codepath/android_guides/wiki/Fragment-Navigation-Drawer
// http://www.androidhive.info/2013/11/android-sliding-menu-using-navigation-drawer/

public class ApplicationActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        setLocale("pt");

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

//        addBusTravelButton = (ImageButton) findViewById(R.id.addBusTravelButton);
//        addBusTravelButton.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                startActivity(new Intent(ApplicationActivity.this, CheckerActivity.class));
//            }
//        });

    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        // update the main content by replacing busticketchecker.fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new CardListerFragment();

        switch (position)
        {
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
                fragment = new CardRemoverFragment();
                break;
            case MenuConstants.REMOVE_BUS_TAX:
                fragment = new BusTaxRemoverFragment();
                break;
        }
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void onSectionAttached(int number)
    {
        switch (number)
        {
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

    public void restoreActionBar()
    {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    private void setLocale(String country)
    {
        Locale locale = new Locale(country);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}

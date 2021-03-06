package busticketchecker.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import java.util.Locale;

import busticketchecker.constants.MenuConstants;
import busticketchecker.fragments.BusTaxAdderFragment;
import busticketchecker.fragments.BusTaxRemoverFragment;
import busticketchecker.fragments.CardAdderFragment;
import busticketchecker.fragments.CardListerFragment;
import busticketchecker.fragments.CardRemoverFragment;
import busticketchecker.fragments.NavigationDrawerFragment;
import busticketchecker.fragments.StatisticsFragment;
import checker.ticker.bus.basic.R;

public class ApplicationActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setLocale("pt");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
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
            case MenuConstants.STATISTICS:
                fragment = new StatisticsFragment();
                break;
        }
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void setLocale(String country)
    {
        Locale locale = new Locale(country);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplication().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}

package checker.ticker.bus.busticketchecker.src;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import checker.ticker.bus.busticketchecker.R;

public class CheckerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerBusTaxes;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);
        spinnerBusTaxes = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> busTaxes = new ArrayList<String>();
        busTaxes.add("Tarifa A");
        busTaxes.add("Tarifa B");
        busTaxes.add("Tarifa C");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, busTaxes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBusTaxes.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

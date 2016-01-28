package activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import checker.ticker.bus.basic.R;
import fragments.TimePickerFragment;

public class CheckerActivity extends Activity implements AdapterView.OnItemSelectedListener
{

    private Spinner spinnerBusTaxes;
    private Button newTimeButton;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        Intent intent = getIntent();
        String cardName = intent.getStringExtra("cardName");
        this.setTitle(cardName);

        spinnerBusTaxes = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> busTaxes = new ArrayList<>();
        busTaxes.add("Tarifa A");
        busTaxes.add("Tarifa B");
        busTaxes.add("Tarifa C");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, busTaxes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBusTaxes.setAdapter(adapter);

        newTimeButton = (Button) findViewById(R.id.newTimeButton);
        newTimeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
            }
        });

        checkoutButton = (Button) findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0)
    {
        // TODO Auto-generated method stub
    }

}

package fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import java.util.Calendar;

import checker.ticker.bus.basic.R;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    @Override
    public void onTimeSet(TimePicker view, int hours, int minutes) {
        Button time = (Button) getActivity().findViewById(R.id.newTimeButton);
        String formattedHour = hours + ":";
        if(minutes < 10){
            formattedHour += "0";
        }
        formattedHour += minutes;
        time.setText(formattedHour);
    }

}
